package edu.uiowa.slis.ClinicalTrialsTagLib.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.net.ssl.HttpsURLConnection;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Harvester {
    static Logger logger = Logger.getLogger(Harvester.class);
    static Properties prop_file = PropertyLoader.loadProperties("loader");
    static Connection conn = null;

    static Pattern hrefPattern = Pattern.compile(".*href=\"(.*)\"/>.*");
    static boolean groupLogged = false;
    static String logPath = null;

    /**
     * @param args
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     * @throws InterruptedException 
     * @throws DocumentException 
     */
    @SuppressWarnings("unused")
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException, InterruptedException, DocumentException {
	PropertyConfigurator.configure(args[0]);
	int count = 0;
	
	String db_user = prop_file.getProperty("db.user.name");
	logger.debug("Database User Name: " + db_user);
	String db_pass = prop_file.getProperty("db.user.password");

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

	logger.info("truncating raw...");
	PreparedStatement closeStmt = conn.prepareStatement("truncate clinical_trials_staging.raw cascade");
	closeStmt.executeUpdate();
	closeStmt.close();

	logger.info("opening /Users/eichmann/downloads/ct/AllAPIJSON.zip");
	ZipFile zipFile = new ZipFile("/Users/eichmann/downloads/ct/AllAPIJSON.zip");
	Enumeration<ZipEntry> entryEnum = (Enumeration<ZipEntry>) zipFile.entries();
	while (entryEnum.hasMoreElements()) {
	    ZipEntry entry = entryEnum.nextElement();
	    if (entry.isDirectory() || !entry.getName().contains(".json")) {
		logger.info("entry: " + entry.getName());
		continue;
	    }
	    logger.debug("entry: " + entry.getName());
	    count++;

	    InputStream content = zipFile.getInputStream(entry);
	    JSONObject object = new JSONObject(new JSONTokener(content));
//	    SAXReader reader = new SAXReader(false);
//	    Document document = reader.read(content);
//	    Element root = document.getRootElement();
//	    logger.debug("document root: " + root.getName());
		PreparedStatement citeStmt = conn.prepareStatement("insert into clinical_trials_staging.raw values (?::jsonb)");
		citeStmt.setString(1, object.toString());
		citeStmt.executeUpdate();
		citeStmt.close();
	}
	zipFile.close();
	logger.info("file count: " + count);
	
	if (1<2) return;

	// doing this simplifies the HTTPS connection request
	System.setProperty("jsse.enableSNIExtension", "false");

	URL theURL = new URL("https://www.clinicaltrials.gov/ct2/crawl");
	BufferedReader IODesc = new BufferedReader(new InputStreamReader(((HttpsURLConnection)theURL.openConnection()).getInputStream()));
	String buffer = null;
	while ((buffer = IODesc.readLine()) != null) {
	    logger.trace("line: " + buffer);
	    Matcher hrefMatcher = hrefPattern.matcher(buffer);
	    if (hrefMatcher.find()) {
		// definition entry
		String path = hrefMatcher.group(1);
		boolean retry = true;
		do {
		    try {
			logPath = path;
			groupLogged = false;
			processGroup(new URL(theURL, path));
			retry = false;
		    } catch (Exception e) {
			logger.error("\t\tretrying due to : " + e);
			Thread.sleep(2000);
		    }
		} while (retry);
	    }
	}
    }

    static void processGroup(URL theURL) throws IOException, SQLException, InterruptedException {
	BufferedReader IODesc = new BufferedReader(new InputStreamReader(((HttpsURLConnection)theURL.openConnection()).getInputStream()));
	String buffer = null;
	while ((buffer = IODesc.readLine()) != null) {
	    logger.trace("line: " + buffer);
	    Matcher hrefMatcher = hrefPattern.matcher(buffer);
	    if (hrefMatcher.find()) {
		// definition entry
		String path = hrefMatcher.group(1);
		boolean retry = true;
		do {
		    try {
			logger.trace("\ttrial path: " + path);
			processTrial(new URL(theURL, path + "?resultsxml=true"), path.substring(path.lastIndexOf('/') + 1));
			retry = false;
		    } catch (Exception e) {
			logger.error("\t\tretrying due to : " + e);
			Thread.sleep(2000);
		    }
		} while (retry);
	    }
	}
    }

    static void processTrial(URL theURL, String ID) throws IOException, SQLException, InterruptedException {
	int count = 0;
	PreparedStatement checkStmt = conn.prepareStatement("select count(*) from clinical_trials.raw where id = ?");
	checkStmt.setString(1, ID);
	ResultSet rs = checkStmt.executeQuery();
	while (rs.next()) {
	    count = rs.getInt(1);
	}
	checkStmt.close();

	if (count > 0)
	    return;

	Thread.sleep(250);
	BufferedReader IODesc = new BufferedReader(new InputStreamReader(((HttpsURLConnection)theURL.openConnection()).getInputStream()));
	String buffer = null;
	StringBuffer theContents = new StringBuffer();
	while ((buffer = IODesc.readLine()) != null) {
	    theContents.append("\n" + buffer);
	}
	IODesc.close();
	if (groupLogged == false) {
	    logger.info("group path: " + logPath);
	    groupLogged = true;
	}
	logger.info("\t\ttrial: " + ID + ": " + theContents.length() + " characters");

	PreparedStatement citeStmt = conn.prepareStatement("insert into clinical_trials.raw values (?,?)");
	citeStmt.setString(1, ID);
	citeStmt.setString(2, theContents.toString());
	citeStmt.executeUpdate();
	citeStmt.close();
    }

}
