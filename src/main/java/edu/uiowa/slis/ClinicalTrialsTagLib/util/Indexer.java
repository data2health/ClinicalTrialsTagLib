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
		"select id from clinical_trials.study where not exists (select id from clinical_trials_local.cui_cache where cui_cache.id=study.id) order by id");
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
	PreparedStatement fetchStmt = conn.prepareStatement("select brief_title,official_title from clinical_trials.study where id = ?");
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

	    condition(id);
	    condition_ancestor(id);
	    condition_browse_leaf(id);
	    condition_mesh(id);
	    intervention(id);
	    intervention_ancestor(id);
	    intervention_browse_branch(id);
	    intervention_browse_leaf(id);
	    intervention_mesh(id);
	    intervention_other_name(id);
	    keyword(id);
	    other_outcome(id);
	    primary_outcome(id);
	    reference(id);
	    secondary_outcome(id);

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

    void condition(int id) throws SQLException {
	PreparedStatement stmt = conn.prepareStatement("select condition from clinical_trials.condition where id = ?");
	stmt.setInt(1, id);
	ResultSet rs = stmt.executeQuery();
	while (rs.next()) {
	    String condition = rs.getString(1);
	    logger.debug("\tcondition: " + condition);
	    conceptRecognizer.parseSentences(condition);
	}
	stmt.close();
    }

    void condition_ancestor(int id) throws SQLException {
	PreparedStatement stmt = conn.prepareStatement("select condition_ancestor_id,condition_ancestor_term from clinical_trials.condition_ancestor where id = ?");
	stmt.setInt(1, id);
	ResultSet rs = stmt.executeQuery();
	while (rs.next()) {
	    String condition_ancestor_id = rs.getString(1);
	    logger.debug("\tcondition_ancestor_id: " + condition_ancestor_id);
	    String condition_ancestor_term = rs.getString(2);
	    logger.debug("\tcondition_ancestor_term: " + condition_ancestor_term);
	    conceptRecognizer.parseSentences(condition_ancestor_id);
	    conceptRecognizer.parseSentences(condition_ancestor_term);
	}
	stmt.close();
    }

    void condition_browse_leaf(int id) throws SQLException {
	PreparedStatement stmt = conn.prepareStatement("select condition_browse_leaf_name,condition_browse_leaf_as_found from clinical_trials.condition_browse_leaf where id = ?");
	stmt.setInt(1, id);
	ResultSet rs = stmt.executeQuery();
	while (rs.next()) {
	    String condition_browse_leaf_name = rs.getString(1);
	    logger.debug("\rcondition_browse_leaf_name: " + condition_browse_leaf_name);
	    conceptRecognizer.parseSentences(condition_browse_leaf_name);
	    String condition_browse_leaf_as_found = rs.getString(2);
	    logger.debug("\tcondition_browse_leaf_as_found: " + condition_browse_leaf_as_found);
	    conceptRecognizer.parseSentences(condition_browse_leaf_as_found);
	}
	stmt.close();
    }

    void condition_mesh(int id) throws SQLException {
	PreparedStatement stmt = conn.prepareStatement("select condition_mesh_id,condition_mesh_term from clinical_trials.condition_mesh where id = ?");
	stmt.setInt(1, id);
	ResultSet rs = stmt.executeQuery();
	while (rs.next()) {
	    String condition_mesh_id = rs.getString(1);
	    logger.debug("\tcondition_mesh_id: " + condition_mesh_id);
	    conceptRecognizer.parseSentences(condition_mesh_id);
	    String condition_mesh_term = rs.getString(2);
	    logger.debug("\tcondition_mesh_term: " + condition_mesh_term);
	    conceptRecognizer.parseSentences(condition_mesh_term);
	}
	stmt.close();
    }

    void intervention(int id) throws SQLException {
	PreparedStatement stmt = conn.prepareStatement("select intervention_name,intervention_description from clinical_trials.intervention where id = ?");
	stmt.setInt(1, id);
	ResultSet rs = stmt.executeQuery();
	while (rs.next()) {
	    String intervention_name = rs.getString(1);
	    logger.debug("\tintervention_name: " + intervention_name);
	    conceptRecognizer.parseSentences(intervention_name);
	    String intervention_description = rs.getString(2);
	    logger.debug("\tintervention_description: " + intervention_description);
	    conceptRecognizer.parseSentences(intervention_description);
	}
	stmt.close();
    }

    void intervention_ancestor(int id) throws SQLException {
	PreparedStatement stmt = conn.prepareStatement("select intervention_ancestor_id,intervention_ancestor_term from clinical_trials.intervention_ancestor where id = ?");
	stmt.setInt(1, id);
	ResultSet rs = stmt.executeQuery();
	while (rs.next()) {
	    String intervention_ancestor_id = rs.getString(1);
	    logger.debug("\tintervention_ancestor_id: " + intervention_ancestor_id);
	    conceptRecognizer.parseSentences(intervention_ancestor_id);
	    String intervention_ancestor_term = rs.getString(2);
	    logger.debug("\tintervention_ancestor_term: " + intervention_ancestor_term);
	    conceptRecognizer.parseSentences(intervention_ancestor_term);
	}
	stmt.close();
    }

    void intervention_browse_branch(int id) throws SQLException {
	PreparedStatement stmt = conn.prepareStatement("select intervention_browse_branch_abbrev,intervention_browse_branch_name from clinical_trials.intervention_browse_branch where id = ?");
	stmt.setInt(1, id);
	ResultSet rs = stmt.executeQuery();
	while (rs.next()) {
	    String intervention_browse_branch_abbrev = rs.getString(1);
	    logger.debug("\tintervention_browse_branch_abbrev: " + intervention_browse_branch_abbrev);
	    conceptRecognizer.parseSentences(intervention_browse_branch_abbrev);
	    String intervention_browse_branch_name = rs.getString(2);
	    logger.debug("\tintervention_browse_branch_name: " + intervention_browse_branch_name);
	    conceptRecognizer.parseSentences(intervention_browse_branch_name);
	}
	stmt.close();
    }

    void intervention_browse_leaf(int id) throws SQLException {
	PreparedStatement stmt = conn.prepareStatement("select intervention_browse_leaf_id,intervention_browse_leaf_name,intervention_browse_leaf_as_found from clinical_trials.intervention_browse_leaf where id = ?");
	stmt.setInt(1, id);
	ResultSet rs = stmt.executeQuery();
	while (rs.next()) {
	    String intervention_browse_leaf_id = rs.getString(1);
	    String intervention_browse_leaf_name = rs.getString(2);
	    String intervention_browse_leaf_as_found = rs.getString(3);
	    logger.debug("\tintervention_browse_leaf_id name: " + intervention_browse_leaf_id);
	    logger.debug("\tintervention_browse_leaf_name: " + intervention_browse_leaf_name);
	    logger.debug("\tintervention_browse_leaf_as_found: " + intervention_browse_leaf_as_found);
	    conceptRecognizer.parseSentences(intervention_browse_leaf_id);
	    conceptRecognizer.parseSentences(intervention_browse_leaf_name);
	    conceptRecognizer.parseSentences(intervention_browse_leaf_as_found);
	}
	stmt.close();
    }

    void intervention_mesh(int id) throws SQLException {
	PreparedStatement stmt = conn.prepareStatement("select intervention_mesh_id,intervention_mesh_term from clinical_trials.intervention_mesh where id = ?");
	stmt.setInt(1, id);
	ResultSet rs = stmt.executeQuery();
	while (rs.next()) {
	    String intervention_mesh_id = rs.getString(1);
	    logger.debug("\tintervention_mesh_id: " + intervention_mesh_id);
	    conceptRecognizer.parseSentences(intervention_mesh_id);
	    String intervention_mesh_term = rs.getString(2);
	    logger.debug("\tintervention_mesh_term: " + intervention_mesh_term);
	    conceptRecognizer.parseSentences(intervention_mesh_term);
	}
	stmt.close();
    }

    void intervention_other_name(int id) throws SQLException {
	PreparedStatement stmt = conn.prepareStatement("select intervention_other_name from clinical_trials.intervention_other_name where id = ?");
	stmt.setInt(1, id);
	ResultSet rs = stmt.executeQuery();
	while (rs.next()) {
	    String intervention_other_name = rs.getString(1);
	    logger.debug("\tintervention_other_name: " + intervention_other_name);
	    conceptRecognizer.parseSentences(intervention_other_name);
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
	    conceptRecognizer.parseSentences(keyword);
	}
	stmt.close();
    }

    void other_outcome(int id) throws SQLException {
	PreparedStatement stmt = conn.prepareStatement("select other_outcome_measure,other_outcome_description,other_outcome_time_frame from clinical_trials.other_outcome where id = ?");
	stmt.setInt(1, id);
	ResultSet rs = stmt.executeQuery();
	while (rs.next()) {
	    String other_outcome_measure = rs.getString(1);
	    logger.debug("\tother_outcome_measure: " + other_outcome_measure);
	    conceptRecognizer.parseSentences(other_outcome_measure);
	    String other_outcome_description = rs.getString(2);
	    logger.debug("\tother_outcome_description: " + other_outcome_description);
	    conceptRecognizer.parseSentences(other_outcome_description);
	    String other_outcome_time_frame = rs.getString(3);
	    logger.debug("\tother_outcome_time_frame: " + other_outcome_time_frame);
	    conceptRecognizer.parseSentences(other_outcome_time_frame);
	}
	stmt.close();
    }

    void primary_outcome(int id) throws SQLException {
	PreparedStatement stmt = conn.prepareStatement("select primary_outcome_measure,primary_outcome_description,primary_outcome_time_frame from clinical_trials.primary_outcome where id = ?");
	stmt.setInt(1, id);
	ResultSet rs = stmt.executeQuery();
	while (rs.next()) {
	    String primary_outcome_measure = rs.getString(1);
	    logger.debug("\tprimary_outcome_measure: " + primary_outcome_measure);
	    conceptRecognizer.parseSentence(primary_outcome_measure);
	    String primary_outcome_description = rs.getString(2);
	    logger.debug("\tprimary_outcome_description: " + primary_outcome_description);
	    conceptRecognizer.parseSentence(primary_outcome_description);
	    String primary_outcome_time_frame = rs.getString(3);
	    logger.debug("\tprimary_outcome_time_frame: " + primary_outcome_time_frame);
	    conceptRecognizer.parseSentence(primary_outcome_time_frame);
	}
	stmt.close();
    }

    void reference(int id) throws SQLException {
	PreparedStatement stmt = conn.prepareStatement("select reference_citation from clinical_trials.reference where id = ?");
	stmt.setInt(1, id);
	ResultSet rs = stmt.executeQuery();
	while (rs.next()) {
	    String reference_citation = rs.getString(1);
	    logger.debug("\treference_citation: " + reference_citation);
	    conceptRecognizer.parseSentence(reference_citation);
	}
	stmt.close();
    }

    void secondary_outcome(int id) throws SQLException {
	PreparedStatement stmt = conn.prepareStatement("select secondary_outcome_measure,secondary_outcome_description,secondary_outcome_time_frame from clinical_trials.secondary_outcome where id = ?");
	stmt.setInt(1, id);
	ResultSet rs = stmt.executeQuery();
	while (rs.next()) {
	    String secondary_outcome_measure = rs.getString(1);
	    logger.debug("\tsecondary_outcome_measure: " + secondary_outcome_measure);
	    conceptRecognizer.parseSentence(secondary_outcome_measure);
	    String secondary_outcome_description = rs.getString(2);
	    logger.debug("\tsecondary_outcome_description: " + secondary_outcome_description);
	    conceptRecognizer.parseSentence(secondary_outcome_description);
	    String secondary_outcome_time_frame = rs.getString(3);
	    logger.debug("\tsecondary_outcome_time_frame: " + secondary_outcome_time_frame);
	    conceptRecognizer.parseSentence(secondary_outcome_time_frame);
	}
	stmt.close();
    }
}
