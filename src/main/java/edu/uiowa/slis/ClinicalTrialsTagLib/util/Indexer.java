package edu.uiowa.slis.ClinicalTrialsTagLib.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import edu.uiowa.emr.CachingConceptRecognizer;
import edu.uiowa.emr.Concept;
import edu.uiowa.emr.ConceptRecognizer;

public class Indexer implements Runnable {
    static Logger logger = Logger.getLogger(Indexer.class);
    static Vector<Integer> idVector = new Vector<Integer>();

    static Connection getConnection() throws ClassNotFoundException, SQLException {
	Connection conn = null;
	Properties prop_file = PropertyLoader.loadProperties("loader");

	String use_ssl = prop_file.getProperty("nihdb.use.ssl", "false");
	logger.debug("Database SSL: " + use_ssl);

	String databaseHost = prop_file.getProperty("db.host", "localhost");
	logger.debug("Database Host: " + databaseHost);

	String databaseName = prop_file.getProperty("db.name", "loki");
	logger.debug("Database Name: " + databaseName);

	String db_url = prop_file.getProperty("nihdb.url", "jdbc:postgresql://" + databaseHost + "/" + databaseName);
	logger.debug("Database URL: " + db_url);

	Class.forName("org.postgresql.Driver");
	Properties props = new Properties();
	props.setProperty("user", prop_file.getProperty("db.user.name"));
	props.setProperty("password", prop_file.getProperty("db.user.password"));
	if (use_ssl.equals("true")) {
	    props.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
	    props.setProperty("ssl", "true");
	}
	conn = DriverManager.getConnection(db_url, props);
	conn.setAutoCommit(false);

	return conn;
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException, InterruptedException {
	PropertyConfigurator.configure(args[0]);
	Connection initialConn = getConnection();
	int maxCrawlerThreads = Runtime.getRuntime().availableProcessors();
	Thread[] matcherThreads = new Thread[maxCrawlerThreads];

	logger.info("populating queue...");
	PreparedStatement fetchStmt = initialConn.prepareStatement(
		"select id from clinical_trials.clinical_study where not exists (select id from clinical_trials_local.cui_cache where cui_cache.id=clinical_study.id) order by id");
	ResultSet fetchRS = fetchStmt.executeQuery();
	while (fetchRS.next()) {
	    idVector.add(fetchRS.getInt(1));
	}
	fetchStmt.close();
	logger.info("done.");
	for (int i = 0; i < maxCrawlerThreads; i++) {
	    Thread theThread = new Thread(new Indexer());
	    theThread.setPriority(Math.max(theThread.getPriority() - 2, Thread.MIN_PRIORITY));
	    theThread.start();
	    matcherThreads[i] = theThread;
	}
	for (int i = 0; i < maxCrawlerThreads; i++) {
	    matcherThreads[i].join();
	}

	initialConn.close();
    }

    ConceptRecognizer conceptRecognizer = null;
    Connection conn = null;

    public Indexer() throws ClassNotFoundException, SQLException {
	conn = getConnection();
	conceptRecognizer = new CachingConceptRecognizer(conn);
    }

    @Override
    public void run() {
	while (idVector.size() > 0) {
	    try {
		int id = idVector.remove(0);
		index(id);
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}
    }

    void index(int id) throws SQLException {
	logger.info("indexing " + id);
	PreparedStatement fetchStmt = conn.prepareStatement("select brief_title,official_title from clinical_trials.clinical_study where id = ?");
	fetchStmt.setInt(1, id);
	ResultSet fetchRS = fetchStmt.executeQuery();
	while (fetchRS.next()) {
	    String briefTitle = fetchRS.getString(1);
	    String officialTitle = fetchRS.getString(2);

	    logger.info("clinical study: " + id);
	    logger.info("\tbrief title: " + briefTitle);
	    logger.debug("\tofficial title: " + officialTitle);

	    conceptRecognizer.parseSentence(briefTitle);
	    conceptRecognizer.parseSentence(officialTitle);

	    brief(id);
	    detailed(id);
	    biospec(id);
	    intervention(id);
	    study_pop(id);
	    criteria(id);
	    reference(id);
	    result_reference(id);
	    keyword(id);
	    condition_browse(id);
	    intervention_browse(id);

	    cacheCUIs(id);
	    conceptRecognizer.reset();
	    conn.commit();
	}
    }

    void cacheCUIs(int id) throws SQLException {
	Map<String, Concept> conceptMap = new HashMap<String, Concept>();

	for (Concept concept : conceptRecognizer.getLeftCUIs()) {
	    Concept targetConcept = conceptMap.get(concept.getCui() + " " + concept.getPhrase());
	    if (targetConcept == null) {
		conceptMap.put(concept.getCui() + " " + concept.getPhrase(), concept);
	    } else {
		targetConcept.incrementCount();
	    }
	}

	for (Concept concept : conceptRecognizer.getRightCUIs()) {
	    Concept targetConcept = conceptMap.get(concept.getCui() + " " + concept.getPhrase());
	    if (targetConcept == null) {
		conceptMap.put(concept.getCui() + " " + concept.getPhrase(), concept);
	    } else {
		targetConcept.incrementCount();
	    }
	}

	PreparedStatement cacheStmt = conn.prepareStatement("insert into clinical_trials_local.cui_cache values (?,?,?,?,?)");
	int seqnum = 1;
	for (Concept concept : conceptMap.values()) {
	    logger.trace("\t\tstoring cui: " + concept.getCui() + " : " + concept.getCount() + " : " + concept.getPhrase());
	    cacheStmt.setInt(1, id);
	    cacheStmt.setInt(2, seqnum++);
	    cacheStmt.setString(3, concept.getCui());
	    cacheStmt.setString(4, concept.getPhrase());
	    cacheStmt.setInt(5, concept.getCount());
	    cacheStmt.execute();
	}

	if (conceptMap.size() == 0) {
	    // insert dummy to prevent subsequent scans
	    cacheStmt.setInt(1, id);
	    cacheStmt.setInt(2, seqnum++);
	    cacheStmt.setString(3, "");
	    cacheStmt.setString(4, "");
	    cacheStmt.setInt(5, 0);
	    cacheStmt.execute();
	}
	cacheStmt.close();
    }

    void brief(int id) throws SQLException {
	PreparedStatement stmt = conn.prepareStatement("select textblock from clinical_trials.brief_summary where id = ?");
	stmt.setInt(1, id);
	ResultSet rs = stmt.executeQuery();
	while (rs.next()) {
	    String textblock = rs.getString(1);
	    logger.debug("\tbrief: " + textblock);
	    conceptRecognizer.parseSentences(textblock);
	}
	stmt.close();
    }

    void detailed(int id) throws SQLException {
	PreparedStatement stmt = conn.prepareStatement("select textblock from clinical_trials.detailed_description where id = ?");
	stmt.setInt(1, id);
	ResultSet rs = stmt.executeQuery();
	while (rs.next()) {
	    String textblock = rs.getString(1);
	    logger.debug("\tdetailed: " + textblock);
	    conceptRecognizer.parseSentences(textblock);
	}
	stmt.close();
    }

    void biospec(int id) throws SQLException {
	PreparedStatement stmt = conn.prepareStatement("select textblock from clinical_trials.biospec_descr where id = ?");
	stmt.setInt(1, id);
	ResultSet rs = stmt.executeQuery();
	while (rs.next()) {
	    String textblock = rs.getString(1);
	    logger.debug("\tbiospec: " + textblock);
	    conceptRecognizer.parseSentences(textblock);
	}
	stmt.close();
    }

    void intervention(int id) throws SQLException {
	PreparedStatement stmt = conn.prepareStatement("select intervention_name,description from clinical_trials.intervention where id = ?");
	stmt.setInt(1, id);
	ResultSet rs = stmt.executeQuery();
	while (rs.next()) {
	    String name = rs.getString(1);
	    String description = rs.getString(2);
	    logger.debug("\tintervention name: " + name);
	    logger.debug("\tintervention description: " + description);
	    conceptRecognizer.parseSentences(name);
	    conceptRecognizer.parseSentences(description);
	}
	stmt.close();
    }

    void study_pop(int id) throws SQLException {
	PreparedStatement stmt = conn.prepareStatement("select textblock from clinical_trials.study_pop where id = ?");
	stmt.setInt(1, id);
	ResultSet rs = stmt.executeQuery();
	while (rs.next()) {
	    String textblock = rs.getString(1);
	    logger.debug("\tstudy_pop: " + textblock);
	    conceptRecognizer.parseSentences(textblock);
	}
	stmt.close();
    }

    void criteria(int id) throws SQLException {
	PreparedStatement stmt = conn.prepareStatement("select textblock from clinical_trials.criteria where id = ?");
	stmt.setInt(1, id);
	ResultSet rs = stmt.executeQuery();
	while (rs.next()) {
	    String textblock = rs.getString(1);
	    logger.debug("\tcriteria: " + textblock);
	    conceptRecognizer.parseSentences(textblock);
	}
	stmt.close();
    }

    void reference(int id) throws SQLException {
	PreparedStatement stmt = conn.prepareStatement("select pmid from clinical_trials.reference where id = ?");
	stmt.setInt(1, id);
	ResultSet rs = stmt.executeQuery();
	while (rs.next()) {
	    String pmid = rs.getString(1);
	    logger.debug("\treference: " + pmid);
	}
	stmt.close();
    }

    void result_reference(int id) throws SQLException {
	PreparedStatement stmt = conn.prepareStatement("select pmid from clinical_trials.results_reference where id = ?");
	stmt.setInt(1, id);
	ResultSet rs = stmt.executeQuery();
	while (rs.next()) {
	    String pmid = rs.getString(1);
	    logger.debug("\treference: " + pmid);
	}
	stmt.close();
    }

    void keyword(int id) throws SQLException {
	PreparedStatement stmt = conn.prepareStatement("select keyword from clinical_trials.keyword where id = ?");
	stmt.setInt(1, id);
	ResultSet rs = stmt.executeQuery();
	while (rs.next()) {
	    String keyword = rs.getString(1);
	    logger.debug("\tkeyword: " + keyword);
	    conceptRecognizer.parseSentence(keyword);
	}
	stmt.close();
    }

    void condition_browse(int id) throws SQLException {
	PreparedStatement stmt = conn.prepareStatement("select mesh from clinical_trials.condition_browse where id = ?");
	stmt.setInt(1, id);
	ResultSet rs = stmt.executeQuery();
	while (rs.next()) {
	    String mesh = rs.getString(1);
	    logger.debug("\tcondition_browse: " + mesh);
	    conceptRecognizer.parseSentence(mesh);
	}
	stmt.close();
    }

    void intervention_browse(int id) throws SQLException {
	PreparedStatement stmt = conn.prepareStatement("select mesh from clinical_trials.intervention_browse where id = ?");
	stmt.setInt(1, id);
	ResultSet rs = stmt.executeQuery();
	while (rs.next()) {
	    String mesh = rs.getString(1);
	    logger.debug("\tintervention_browse: " + mesh);
	    conceptRecognizer.parseSentence(mesh);
	}
	stmt.close();
    }
}
