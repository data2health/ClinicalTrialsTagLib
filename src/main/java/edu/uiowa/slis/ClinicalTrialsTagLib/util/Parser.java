package edu.uiowa.slis.ClinicalTrialsTagLib.util;

import java.io.IOException;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ListIterator;
import java.util.Properties;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class Parser {
    static Logger logger = Logger.getLogger(Parser.class);
    static Properties prop_file = PropertyLoader.loadProperties("loader");
    static Connection conn = null;

    static int ID = 0;
    static int seqnum = 0;
    static int seqnum2 = 0;
    static int seqnum3 = 0;
    static int seqnum4 = 0;
    static int seqnum5 = 0;

    static Pattern hrefPattern = Pattern.compile(".*href=\"(.*)\"/>.*");
    static SimpleDateFormat formatter = new SimpleDateFormat("MMMMM dd, yyyy");
    static SimpleDateFormat formatter2 = new SimpleDateFormat("MMMMM yyyy");

    // Date d1 = format1.parse( dateStr1 ); handles January 15, 2012

    /**
     * @param args
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     * @throws DocumentException
     * @throws ParseException
     */
    @SuppressWarnings("unused")
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException, DocumentException, ParseException {
	PropertyConfigurator.configure(args[0]);
	String db_user = prop_file.getProperty("db.user.name", "eichmann");
	logger.debug("Database User Name: " + db_user);
	String db_pass = prop_file.getProperty("db.user.password", "translational");

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
	props.setProperty("user", "eichmann");
	props.setProperty("password", "translational");
	if (use_ssl.equals("true")) {
	    props.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
	    props.setProperty("ssl", "true");
	}
	conn = DriverManager.getConnection(db_url, props);
	conn.setAutoCommit(false);

	PreparedStatement scanStmt = conn.prepareStatement("select id from clinical_trials.raw where not exists (select nct_id from clinical_trials.clinical_study where raw.id = nct_id)");
	ResultSet srs = scanStmt.executeQuery();
	while (srs.next()) {
	    String id = srs.getString(1);

	    PreparedStatement checkStmt = conn.prepareStatement("select contents from clinical_trials.raw where id=?");
	    checkStmt.setString(1, id);
	    ResultSet rs = checkStmt.executeQuery();
	    while (rs.next()) {
		String contents = rs.getString(1).trim();
		ID = Integer.parseInt(id.substring(3));
		logger.info("id: " + id + "\t" + ID);
		SAXReader reader = new SAXReader(false);
		Document document = reader.read(new StringReader(contents));
		logger.trace(document.asXML());
		Element root = document.getRootElement();
		record(root);
		conn.commit();
	    }
	    checkStmt.close();
	}
	scanStmt.close();
    }

    @SuppressWarnings("unchecked")
    static void record(Node record) throws SQLException, ParseException {
	String org_study_id = getNode("id_info/org_study_id", record);
	logger.debug("\torg_study_id: " + org_study_id);
	String nct_id = getNode("id_info/nct_id", record);
	logger.debug("\tnct_id: " + nct_id);
	String brief_title = getNode("brief_title", record);
	logger.info("\tbrief_title: " + brief_title);
	String acronym = getNode("acronym", record);
	logger.debug("\tacronym: " + acronym);
	String official_title = getNode("official_title", record);
	logger.debug("\tofficial_title: " + official_title);
	String source = getNode("source", record);
	logger.debug("\tsource: " + source);
	String overall_status = getNode("overall_status", record);
	logger.debug("\toverall_status: " + overall_status);
	String why_stopped = getNode("why_stopped", record);
	logger.debug("\twhy_stopped: " + why_stopped);
	String phase = getNode("phase", record);
	logger.debug("\tphase: " + phase);
	String start_date = getNode("start_date", record);
	logger.debug("\tstart_date: " + start_date);
	String end_date = getNode("end_date", record);
	logger.debug("\tend_date: " + end_date);
	String completion_date = getNode("completion_date", record);
	logger.debug("\tcompletion_date: " + completion_date);
	String primary_completion_date = getNode("primary_completion_date", record);
	logger.debug("\tprimary_completion_date: " + primary_completion_date);
	String study_type = getNode("study_type", record);
	logger.debug("\tstudy_type: " + study_type);
	String study_design = getNode("study_design", record);
	logger.debug("\tstudy_design: " + study_design);
	String target_duration = getNode("target_duration", record);
	logger.debug("\ttarget_duration: " + target_duration);
	String number_of_arms = getNode("number_of_arms", record);
	logger.debug("\tnumber_of_arms: " + number_of_arms);
	String number_of_groups = getNode("number_of_groups", record);
	logger.debug("\tnumber_of_groups: " + number_of_groups);
	String enrollment = getNode("enrollment", record);
	logger.debug("\tenrollment: " + enrollment);
	String condition = getNode("condition", record);
	logger.debug("\tcondition: " + condition);
	String biospec_retention = getNode("biospec_retention", record);
	logger.debug("\tbiospec_retention: " + biospec_retention);
	String verification_date = getNode("verification_date", record);
	logger.debug("\tverification_date: " + verification_date);
	String lastchanged_date = getNode("lastchanged_date", record);
	logger.debug("\tlastchanged_date: " + lastchanged_date);
	String firstreceived_date = getNode("firstreceived_date", record);
	logger.debug("\tfirstreceived_date: " + firstreceived_date);
	String firstreceived_results_date = getNode("firstreceived_results_date", record);
	logger.debug("\tfirstreceived_results_date: " + firstreceived_results_date);
	String is_fda_regulated = getNode("is_fda_regulated", record);
	logger.debug("\tis_fda_regulated: " + is_fda_regulated);
	String is_section_801 = getNode("is_section_801", record);
	logger.debug("\tis_section_801: " + is_section_801);
	String has_expanded_access = getNode("has_expanded_access", record);
	logger.debug("\thas_expanded_access: " + has_expanded_access);

	PreparedStatement citeStmt = conn
		.prepareStatement("insert into clinical_trials.clinical_study values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
	citeStmt.setInt(1, ID);
	citeStmt.setString(2, org_study_id);
	citeStmt.setString(3, nct_id);
	citeStmt.setString(4, brief_title);
	citeStmt.setString(5, acronym);
	citeStmt.setString(6, official_title);
	citeStmt.setString(7, source);
	citeStmt.setString(8, overall_status);
	citeStmt.setString(9, why_stopped);
	citeStmt.setString(10, phase);
	citeStmt.setDate(11, convertDate(start_date));
	citeStmt.setDate(12, convertDate(end_date));
	citeStmt.setDate(13, convertDate(completion_date));
	citeStmt.setDate(14, convertDate(primary_completion_date));
	citeStmt.setString(15, study_type);
	citeStmt.setString(16, study_design);
	citeStmt.setString(17, target_duration);
	citeStmt.setString(18, number_of_arms);
	citeStmt.setString(19, number_of_groups);
	citeStmt.setString(20, enrollment);
	citeStmt.setString(21, condition);
	citeStmt.setString(22, biospec_retention);
	citeStmt.setDate(23, convertDate(verification_date));
	citeStmt.setDate(24, convertDate(lastchanged_date));
	citeStmt.setDate(25, convertDate(firstreceived_date));
	citeStmt.setDate(26, convertDate(firstreceived_results_date));
	citeStmt.setString(27, is_fda_regulated);
	citeStmt.setString(28, is_section_801);
	citeStmt.setString(29, has_expanded_access);
	citeStmt.executeUpdate();
	citeStmt.close();

	seqnum = 1;
	logger.debug("\tsecondary ids:");
	ListIterator<Node> secids = record.selectNodes("id_info/secondary_id").listIterator();
	while (secids.hasNext()) {
	    String secondary_id = secids.next().getText().trim();
	    logger.debug("\t\t" + secondary_id);

	    PreparedStatement cite2Stmt = conn.prepareStatement("insert into clinical_trials.secondary_id values (?,?,?)");
	    cite2Stmt.setInt(1, ID);
	    cite2Stmt.setInt(2, seqnum++);
	    cite2Stmt.setString(3, secondary_id);
	    cite2Stmt.executeUpdate();
	    cite2Stmt.close();
	}

	seqnum = 1;
	logger.debug("\tnct aliases:");
	ListIterator<Node> aliases = record.selectNodes("id_info/nct_alias").listIterator();
	while (aliases.hasNext()) {
	    String nct_alias = aliases.next().getText().trim();
	    logger.debug("\t\t" + nct_alias);

	    PreparedStatement cite2Stmt = conn.prepareStatement("insert into clinical_trials.nct_alias values (?,?,?)");
	    cite2Stmt.setInt(1, ID);
	    cite2Stmt.setInt(2, seqnum++);
	    cite2Stmt.setString(3, nct_alias);
	    cite2Stmt.executeUpdate();
	    cite2Stmt.close();
	}

	sponsors(record);

	seqnum = 1;
	logger.debug("\tbrief summary:");
	ListIterator<Node> briefs = record.selectNodes("brief_summary/textblock").listIterator();
	while (briefs.hasNext()) {
	    String brief = briefs.next().getText().trim();
	    logger.debug("\t\t" + brief);

	    PreparedStatement cite2Stmt = conn.prepareStatement("insert into clinical_trials.brief_summary values (?,?,?)");
	    cite2Stmt.setInt(1, ID);
	    cite2Stmt.setInt(2, seqnum++);
	    cite2Stmt.setString(3, brief);
	    cite2Stmt.executeUpdate();
	    cite2Stmt.close();
	}

	seqnum = 1;
	logger.debug("\tdetailed description:");
	ListIterator<Node> details = record.selectNodes("detailed_description/textblock").listIterator();
	while (details.hasNext()) {
	    String detail = details.next().getText().trim();
	    logger.debug("\t\t" + detail);

	    PreparedStatement cite2Stmt = conn.prepareStatement("insert into clinical_trials.detailed_description values (?,?,?)");
	    cite2Stmt.setInt(1, ID);
	    cite2Stmt.setInt(2, seqnum++);
	    cite2Stmt.setString(3, detail);
	    cite2Stmt.executeUpdate();
	    cite2Stmt.close();
	}

	outcomes(record, "primary");
	outcomes(record, "secondary");
	outcomes(record, "other");
	arm_groups(record);
	interventions(record);

	seqnum = 1;
	logger.debug("\tbiospec description:");
	ListIterator<Node> biospecs = record.selectNodes("biospec_descr/textblock").listIterator();
	while (biospecs.hasNext()) {
	    String description = biospecs.next().getText().trim();
	    logger.debug("\t\t" + description);

	    PreparedStatement cite2Stmt = conn.prepareStatement("insert into clinical_trials.biospec_descr values (?,?,?)");
	    cite2Stmt.setInt(1, ID);
	    cite2Stmt.setInt(2, seqnum++);
	    cite2Stmt.setString(3, description);
	    cite2Stmt.executeUpdate();
	    cite2Stmt.close();
	}

	eligibility(record);
	officials(record);
	contact(record, "overall_contact", 1);
	contact(record, "overall_contact_backup", 2);
	locations(record);

	seqnum = 1;
	logger.debug("\tlocation countries:");
	ListIterator<Node> locCountries = record.selectNodes("location_countries/country").listIterator();
	while (locCountries.hasNext()) {
	    String country = locCountries.next().getText().trim();
	    logger.debug("\t\t" + country);

	    PreparedStatement cite2Stmt = conn.prepareStatement("insert into clinical_trials.location_countries values (?,?,?)");
	    cite2Stmt.setInt(1, ID);
	    cite2Stmt.setInt(2, seqnum++);
	    cite2Stmt.setString(3, country);
	    cite2Stmt.executeUpdate();
	    cite2Stmt.close();
	}

	seqnum = 1;
	logger.debug("\tremoved countries:");
	ListIterator<Node> remCountries = record.selectNodes("removed_countries/country").listIterator();
	while (remCountries.hasNext()) {
	    String country = remCountries.next().getText().trim();
	    logger.debug("\t\t" + country);

	    PreparedStatement cite2Stmt = conn.prepareStatement("insert into clinical_trials.removed_countries values (?,?,?)");
	    cite2Stmt.setInt(1, ID);
	    cite2Stmt.setInt(2, seqnum++);
	    cite2Stmt.setString(3, country);
	    cite2Stmt.executeUpdate();
	    cite2Stmt.close();
	}

	links(record);
	references(record, "reference");
	references(record, "results_reference");
	responsible_parties(record);

	seqnum = 1;
	logger.debug("\tkeywords:");
	ListIterator<Node> keywords = record.selectNodes("keyword").listIterator();
	while (keywords.hasNext()) {
	    String keyword = keywords.next().getText().trim();
	    logger.debug("\t\t" + keyword);

	    PreparedStatement cite2Stmt = conn.prepareStatement("insert into clinical_trials.keyword values (?,?,?)");
	    cite2Stmt.setInt(1, ID);
	    cite2Stmt.setInt(2, seqnum++);
	    cite2Stmt.setString(3, keyword);
	    cite2Stmt.executeUpdate();
	    cite2Stmt.close();
	}

	seqnum = 1;
	logger.debug("\tcondition browse:");
	ListIterator<Node> condMeSHs = record.selectNodes("condition_browse/mesh_term").listIterator();
	while (condMeSHs.hasNext()) {
	    String mesh_term = condMeSHs.next().getText().trim();
	    logger.debug("\t\t" + mesh_term);

	    PreparedStatement cite2Stmt = conn.prepareStatement("insert into clinical_trials.condition_browse values (?,?,?)");
	    cite2Stmt.setInt(1, ID);
	    cite2Stmt.setInt(2, seqnum++);
	    cite2Stmt.setString(3, mesh_term);
	    cite2Stmt.executeUpdate();
	    cite2Stmt.close();
	}

	seqnum = 1;
	logger.debug("\tintervention browse:");
	ListIterator<Node> intMeSHs = record.selectNodes("intervention_browse/mesh_term").listIterator();
	while (intMeSHs.hasNext()) {
	    String mesh_term = intMeSHs.next().getText().trim();
	    logger.debug("\t\t" + mesh_term);

	    PreparedStatement cite2Stmt = conn.prepareStatement("insert into clinical_trials.intervention_browse values (?,?,?)");
	    cite2Stmt.setInt(1, ID);
	    cite2Stmt.setInt(2, seqnum++);
	    cite2Stmt.setString(3, mesh_term);
	    cite2Stmt.executeUpdate();
	    cite2Stmt.close();
	}

	Node clinical_results = record.selectSingleNode("clinical_results");
	if (clinical_results != null)
	    clinical_results(clinical_results);
    }

    static java.sql.Date convertDate(String dateString) {
	java.sql.Date theDate = null;

	if (dateString == null)
	    return null;

	try {
	    theDate = new java.sql.Date(formatter.parse(dateString).getTime());
	} catch (Exception e) {
	    try {
		theDate = new java.sql.Date(formatter2.parse(dateString).getTime());
	    } catch (Exception e2) {
		logger.error("unable to parse date: " + dateString);
	    }
	}

	return theDate;
    }

    static String getNode(String nodeLabel, Node theRoot) {
	Node theNode = theRoot.selectSingleNode(nodeLabel);
	if (theNode == null)
	    return null;
	else
	    return theNode.getText().trim();
    }

    @SuppressWarnings("unchecked")
    static void sponsors(Node record) throws SQLException, ParseException {
	seqnum = 1;
	logger.debug("\tsponsors:");
	ListIterator<Node> sponsors = record.selectNodes("sponsors").listIterator();
	while (sponsors.hasNext()) {
	    Node sponsorNode = sponsors.next();
	    String lead_agency = getNode("lead_sponsor/agency",sponsorNode);
	    String lead_agency_class = getNode("lead_sponsor/agency_class",sponsorNode);
	    logger.debug("\t\tlead agency: " + lead_agency + "\t" + lead_agency_class);

	    PreparedStatement cite2Stmt = conn.prepareStatement("insert into clinical_trials.sponsor values (?,?,?,?)");
	    cite2Stmt.setInt(1, ID);
	    cite2Stmt.setInt(2, seqnum);
	    cite2Stmt.setString(3, lead_agency);
	    cite2Stmt.setString(4, lead_agency_class);
	    cite2Stmt.executeUpdate();
	    cite2Stmt.close();

	    seqnum2 = 1;
	    ListIterator<Node> collaborators = sponsorNode.selectNodes("collaborator").listIterator();
	    while (collaborators.hasNext()) {
		Node collaboratorNode = collaborators.next();
		String agency = collaboratorNode.selectSingleNode("agency").getText().trim();
		String agency_class = collaboratorNode.selectSingleNode("agency_class").getText().trim();
		logger.debug("\t\t" + agency + "\t" + agency_class);

		PreparedStatement cite3Stmt = conn.prepareStatement("insert into clinical_trials.collaborator values (?,?,?,?,?)");
		cite3Stmt.setInt(1, ID);
		cite3Stmt.setInt(2, seqnum);
		cite3Stmt.setInt(3, seqnum2++);
		cite3Stmt.setString(4, agency);
		cite3Stmt.setString(5, agency_class);
		cite3Stmt.executeUpdate();
		cite3Stmt.close();
	    }

	    seqnum++;
	}
    }

    @SuppressWarnings("unchecked")
    static void outcomes(Node record, String type) throws SQLException {
	seqnum = 1;
	logger.debug("\t" + type + " outcomes:");
	ListIterator<Node> outcomes = record.selectNodes(type + "_outcome").listIterator();
	while (outcomes.hasNext()) {
	    Node outcomeNode = outcomes.next();
	    String measure = getNode("measure", outcomeNode);
	    logger.debug("\t\tmeasure: " + measure);
	    String time_frame = getNode("time_frame", outcomeNode);
	    logger.debug("\t\ttime_frame: " + time_frame);
	    String safety_issue = getNode("safety_issue", outcomeNode);
	    logger.debug("\t\tsafety_issue: " + safety_issue);
	    String description = getNode("description", outcomeNode);
	    logger.debug("\t\tdescription: " + description);

	    PreparedStatement cite2Stmt = conn.prepareStatement("insert into clinical_trials." + type + "_outcome values (?,?,?,?,?,?)");
	    cite2Stmt.setInt(1, ID);
	    cite2Stmt.setInt(2, seqnum++);
	    cite2Stmt.setString(3, measure);
	    cite2Stmt.setString(4, time_frame);
	    cite2Stmt.setString(5, safety_issue);
	    cite2Stmt.setString(6, description);
	    cite2Stmt.executeUpdate();
	    cite2Stmt.close();
	}
    }

    @SuppressWarnings("unchecked")
    static void arm_groups(Node record) throws SQLException {
	seqnum = 1;
	logger.debug("\tarm groups:");
	ListIterator<Node> armGroups = record.selectNodes("arm_group").listIterator();
	while (armGroups.hasNext()) {
	    Node armGroupNode = armGroups.next();
	    String arm_group_label = getNode("arm_group_label", armGroupNode);
	    logger.debug("\t\tarm_group_label: " + arm_group_label);
	    String arm_group_type = getNode("arm_group_type", armGroupNode);
	    logger.debug("\t\tarm_group_type: " + arm_group_type);
	    String description = getNode("description", armGroupNode);
	    logger.debug("\t\tdescription: " + description);

	    PreparedStatement cite2Stmt = conn.prepareStatement("insert into clinical_trials.arm_group values (?,?,?,?,?)");
	    cite2Stmt.setInt(1, ID);
	    cite2Stmt.setInt(2, seqnum++);
	    cite2Stmt.setString(3, arm_group_label);
	    cite2Stmt.setString(4, arm_group_type);
	    cite2Stmt.setString(5, description);
	    cite2Stmt.executeUpdate();
	    cite2Stmt.close();
	}
    }

    @SuppressWarnings("unchecked")
    static void interventions(Node record) throws SQLException {
	seqnum = 1;
	logger.debug("\tinterventions:");
	ListIterator<Node> interventionGroups = record.selectNodes("intervention").listIterator();
	while (interventionGroups.hasNext()) {
	    Node interventionNode = interventionGroups.next();
	    String intervention_type = getNode("intervention_type", interventionNode);
	    logger.debug("\t\tintervention_type: " + intervention_type);
	    String intervention_name = getNode("intervention_name", interventionNode);
	    logger.debug("\t\tintervention_name: " + intervention_name);
	    String description = getNode("description", interventionNode);
	    logger.debug("\t\tdescription: " + description);

	    PreparedStatement cite2Stmt = conn.prepareStatement("insert into clinical_trials.intervention values (?,?,?,?,?)");
	    cite2Stmt.setInt(1, ID);
	    cite2Stmt.setInt(2, seqnum);
	    cite2Stmt.setString(3, intervention_type);
	    cite2Stmt.setString(4, intervention_name);
	    cite2Stmt.setString(5, description);
	    cite2Stmt.executeUpdate();
	    cite2Stmt.close();

	    seqnum2 = 1;
	    ListIterator<Node> aglabels = interventionNode.selectNodes("arm_group_label").listIterator();
	    while (aglabels.hasNext()) {
		String arm_group_label = aglabels.next().getText().trim();
		logger.debug("\t\tarm_group_label: " + arm_group_label);

		PreparedStatement cite3Stmt = conn.prepareStatement("insert into clinical_trials.intervention_arm_group values (?,?,?,?)");
		cite3Stmt.setInt(1, ID);
		cite3Stmt.setInt(2, seqnum);
		cite3Stmt.setInt(3, seqnum2++);
		cite3Stmt.setString(4, arm_group_label);
		cite3Stmt.executeUpdate();
		cite3Stmt.close();
	    }

	    seqnum2 = 1;
	    ListIterator<Node> othernames = interventionNode.selectNodes("other_name").listIterator();
	    while (othernames.hasNext()) {
		String other_name = othernames.next().getText().trim();
		logger.debug("\t\tother_name: " + other_name);

		PreparedStatement cite3Stmt = conn.prepareStatement("insert into clinical_trials.intervention_other_name values (?,?,?,?)");
		cite3Stmt.setInt(1, ID);
		cite3Stmt.setInt(2, seqnum);
		cite3Stmt.setInt(3, seqnum2++);
		cite3Stmt.setString(4, other_name);
		cite3Stmt.executeUpdate();
		cite3Stmt.close();
	    }

	    seqnum++;
	}
    }

    @SuppressWarnings("unchecked")
    static void eligibility(Node record) throws SQLException {
	seqnum = 1;
	logger.debug("\teligibility:");
	ListIterator<Node> eligibilities = record.selectNodes("eligibility").listIterator();
	while (eligibilities.hasNext()) {
	    Node eligibilityNode = eligibilities.next();
	    String sampling_method = getNode("sampling_method", eligibilityNode);
	    logger.debug("\t\tsampling_method: " + sampling_method);
	    String gender = getNode("gender", eligibilityNode);
	    logger.debug("\t\tgender: " + gender);
	    String minimum_age = getNode("minimum_age", eligibilityNode);
	    logger.debug("\t\tminimum_age: " + minimum_age);
	    String maximum_age = getNode("maximum_age", eligibilityNode);
	    logger.debug("\t\tmaximum_age: " + maximum_age);
	    String healthy_volunteers = getNode("healthy_volunteers", eligibilityNode);
	    logger.debug("\t\thealthy_volunteers: " + healthy_volunteers);

	    PreparedStatement cite2Stmt = conn.prepareStatement("insert into clinical_trials.eligibility values (?,?,?,?,?,?,?)");
	    cite2Stmt.setInt(1, ID);
	    cite2Stmt.setInt(2, seqnum);
	    cite2Stmt.setString(3, sampling_method);
	    cite2Stmt.setString(4, gender);
	    cite2Stmt.setString(5, minimum_age);
	    cite2Stmt.setString(6, maximum_age);
	    cite2Stmt.setString(7, healthy_volunteers);
	    cite2Stmt.executeUpdate();
	    cite2Stmt.close();

	    seqnum2 = 1;
	    ListIterator<Node> studypops = eligibilityNode.selectNodes("study_pop/textblock").listIterator();
	    while (studypops.hasNext()) {
		String study_pop = studypops.next().getText().trim();
		logger.debug("\t\tstudy_pop: " + study_pop);

		PreparedStatement cite3Stmt = conn.prepareStatement("insert into clinical_trials.study_pop values (?,?,?,?)");
		cite3Stmt.setInt(1, ID);
		cite3Stmt.setInt(2, seqnum);
		cite3Stmt.setInt(3, seqnum2++);
		cite3Stmt.setString(4, study_pop);
		cite3Stmt.executeUpdate();
		cite3Stmt.close();
	    }

	    seqnum2 = 1;
	    ListIterator<Node> criteria = eligibilityNode.selectNodes("criteria/textblock").listIterator();
	    while (criteria.hasNext()) {
		String criterium = criteria.next().getText().trim();
		logger.debug("\t\tcriteria: " + criterium);

		PreparedStatement cite3Stmt = conn.prepareStatement("insert into clinical_trials.criteria values (?,?,?,?)");
		cite3Stmt.setInt(1, ID);
		cite3Stmt.setInt(2, seqnum);
		cite3Stmt.setInt(3, seqnum2++);
		cite3Stmt.setString(4, criterium);
		cite3Stmt.executeUpdate();
		cite3Stmt.close();
	    }
	    seqnum++;
	}
    }

    @SuppressWarnings("unchecked")
    static void officials(Node record) throws SQLException {
	seqnum = 1;
	ListIterator<Node> officials = record.selectNodes("overall_official").listIterator();
	while (officials.hasNext()) {
	    logger.debug("\toverall_official:");
	    Node officialNode = officials.next();
	    String first_name = getNode("first_name", officialNode);
	    logger.debug("\t\tfirst_name: " + first_name);
	    String middle_name = getNode("middle_name", officialNode);
	    logger.debug("\t\tmiddle_name: " + middle_name);
	    String last_name = getNode("last_name", officialNode);
	    logger.debug("\t\tlast_name: " + last_name);
	    String degrees = getNode("degrees", officialNode);
	    logger.debug("\t\tdegrees: " + degrees);
	    String role = getNode("role", officialNode);
	    logger.debug("\t\trole: " + role);
	    String affiliation = getNode("affiliation", officialNode);
	    logger.debug("\t\taffiliation: " + affiliation);

	    PreparedStatement cite2Stmt = conn.prepareStatement("insert into clinical_trials.overall_official values (?,?,?,?,?,?,?,?)");
	    cite2Stmt.setInt(1, ID);
	    cite2Stmt.setInt(2, seqnum++);
	    cite2Stmt.setString(3, first_name);
	    cite2Stmt.setString(4, middle_name);
	    cite2Stmt.setString(5, last_name);
	    cite2Stmt.setString(6, degrees);
	    cite2Stmt.setString(7, role);
	    cite2Stmt.setString(8, affiliation);
	    cite2Stmt.executeUpdate();
	    cite2Stmt.close();
	}
    }

    @SuppressWarnings("unchecked")
    static void location_officials(Node record) throws SQLException {
	seqnum2 = 1;
	ListIterator<Node> officials = record.selectNodes("investigator").listIterator();
	while (officials.hasNext()) {
	    logger.debug("\tinvestigator:");
	    Node officialNode = officials.next();
	    String first_name = getNode("first_name", officialNode);
	    logger.debug("\t\tfirst_name: " + first_name);
	    String middle_name = getNode("middle_name", officialNode);
	    logger.debug("\t\tmiddle_name: " + middle_name);
	    String last_name = getNode("last_name", officialNode);
	    logger.debug("\t\tlast_name: " + last_name);
	    String degrees = getNode("degrees", officialNode);
	    logger.debug("\t\tdegrees: " + degrees);
	    String role = getNode("role", officialNode);
	    logger.debug("\t\trole: " + role);
	    String affiliation = getNode("affiliation", officialNode);
	    logger.debug("\t\taffiliation: " + affiliation);

	    PreparedStatement cite2Stmt = conn.prepareStatement("insert into clinical_trials.official values (?,?,?,?,?,?,?,?,?)");
	    cite2Stmt.setInt(1, ID);
	    cite2Stmt.setInt(2, seqnum);
	    cite2Stmt.setInt(3, seqnum2++);
	    cite2Stmt.setString(4, first_name);
	    cite2Stmt.setString(5, middle_name);
	    cite2Stmt.setString(6, last_name);
	    cite2Stmt.setString(7, degrees);
	    cite2Stmt.setString(8, role);
	    cite2Stmt.setString(9, affiliation);
	    cite2Stmt.executeUpdate();
	    cite2Stmt.close();
	}
    }

    @SuppressWarnings("unchecked")
    static void contact(Node record, String type, int position) throws SQLException {
	ListIterator<Node> contacts = record.selectNodes(type).listIterator();
	while (contacts.hasNext()) {
	    logger.debug("\t" + type + ":");
	    Node contactNode = contacts.next();
	    String first_name = getNode("first_name", contactNode);
	    logger.debug("\t\tfirst_name: " + first_name);
	    String middle_name = getNode("middle_name", contactNode);
	    logger.debug("\t\tmiddle_name: " + middle_name);
	    String last_name = getNode("last_name", contactNode);
	    logger.debug("\t\tlast_name: " + last_name);
	    String degrees = getNode("degrees", contactNode);
	    logger.debug("\t\tdegrees: " + degrees);
	    String phone = getNode("phone", contactNode);
	    logger.debug("\t\tphone: " + phone);
	    String phone_ext = getNode("phone_ext", contactNode);
	    logger.debug("\t\tphone_ext: " + phone_ext);
	    String email = getNode("email", contactNode);
	    logger.debug("\t\temail: " + email);

	    PreparedStatement cite2Stmt = conn.prepareStatement("insert into clinical_trials.overall_contact values (?,?,?,?,?,?,?,?,?)");
	    cite2Stmt.setInt(1, ID);
	    cite2Stmt.setInt(2, position);
	    cite2Stmt.setString(3, first_name);
	    cite2Stmt.setString(4, middle_name);
	    cite2Stmt.setString(5, last_name);
	    cite2Stmt.setString(6, degrees);
	    cite2Stmt.setString(7, phone);
	    cite2Stmt.setString(8, phone_ext);
	    cite2Stmt.setString(9, email);
	    cite2Stmt.executeUpdate();
	    cite2Stmt.close();
	}
    }

    @SuppressWarnings("unchecked")
    static void location_contact(Node record, String type, int position) throws SQLException {
	ListIterator<Node> contacts = record.selectNodes(type).listIterator();
	while (contacts.hasNext()) {
	    logger.debug("\t" + type + ":");
	    Node contactNode = contacts.next();
	    String first_name = getNode("first_name", contactNode);
	    logger.debug("\t\tfirst_name: " + first_name);
	    String middle_name = getNode("middle_name", contactNode);
	    logger.debug("\t\tmiddle_name: " + middle_name);
	    String last_name = getNode("last_name", contactNode);
	    logger.debug("\t\tlast_name: " + last_name);
	    String degrees = getNode("degrees", contactNode);
	    logger.debug("\t\tdegrees: " + degrees);
	    String phone = getNode("phone", contactNode);
	    logger.debug("\t\tphone: " + phone);
	    String phone_ext = getNode("phone_ext", contactNode);
	    logger.debug("\t\tphone_ext: " + phone_ext);
	    String email = getNode("email", contactNode);
	    logger.debug("\t\temail: " + email);

	    PreparedStatement cite2Stmt = conn.prepareStatement("insert into clinical_trials.contact values (?,?,?,?,?,?,?,?,?,?)");
	    cite2Stmt.setInt(1, ID);
	    cite2Stmt.setInt(2, seqnum);
	    cite2Stmt.setInt(3, position);
	    cite2Stmt.setString(4, first_name);
	    cite2Stmt.setString(5, middle_name);
	    cite2Stmt.setString(6, last_name);
	    cite2Stmt.setString(7, degrees);
	    cite2Stmt.setString(8, phone);
	    cite2Stmt.setString(9, phone_ext);
	    cite2Stmt.setString(10, email);
	    cite2Stmt.executeUpdate();
	    cite2Stmt.close();
	}
    }

    @SuppressWarnings("unchecked")
    static void locations(Node record) throws SQLException {
	seqnum = 1;
	ListIterator<Node> locations = record.selectNodes("location").listIterator();
	while (locations.hasNext()) {
	    logger.debug("\tlocation:");
	    Node locationNode = locations.next();
	    String facility_name = getNode("facility/name", locationNode);
	    logger.debug("\t\tfacility_name: " + facility_name);
	    String city = getNode("facility/address/city", locationNode);
	    logger.debug("\t\tcity: " + city);
	    String state = getNode("facility/address/state", locationNode);
	    logger.debug("\t\tstate: " + state);
	    String zip = getNode("facility/address/zip", locationNode);
	    logger.debug("\t\tzip: " + zip);
	    String country = getNode("facility/address/country", locationNode);
	    logger.debug("\t\tcountry: " + country);
	    String status = getNode("status", locationNode);
	    logger.debug("\t\tstatus: " + status);

	    PreparedStatement cite2Stmt = conn.prepareStatement("insert into clinical_trials.location values (?,?,?,?,?,?,?,?)");
	    cite2Stmt.setInt(1, ID);
	    cite2Stmt.setInt(2, seqnum);
	    cite2Stmt.setString(3, facility_name);
	    cite2Stmt.setString(4, city);
	    cite2Stmt.setString(5, state);
	    cite2Stmt.setString(6, zip);
	    cite2Stmt.setString(7, country);
	    cite2Stmt.setString(8, status);
	    cite2Stmt.executeUpdate();
	    cite2Stmt.close();

	    location_officials(locationNode);
	    location_contact(locationNode, "contact", 1);
	    location_contact(locationNode, "contact_backup", 2);

	    seqnum++;
	}
    }

    @SuppressWarnings("unchecked")
    static void links(Node record) throws SQLException {
	seqnum = 1;
	ListIterator<Node> links = record.selectNodes("link").listIterator();
	while (links.hasNext()) {
	    logger.debug("\tlink:");
	    Node linkNode = links.next();
	    String url = getNode("url", linkNode);
	    logger.debug("\t\turl: " + url);
	    String description = getNode("description", linkNode);
	    logger.debug("\t\tdescription: " + description);

	    PreparedStatement cite2Stmt = conn.prepareStatement("insert into clinical_trials.link values (?,?,?,?)");
	    cite2Stmt.setInt(1, ID);
	    cite2Stmt.setInt(2, seqnum++);
	    cite2Stmt.setString(3, url);
	    cite2Stmt.setString(4, description);
	    cite2Stmt.executeUpdate();
	    cite2Stmt.close();
	}
    }

    @SuppressWarnings("unchecked")
    static void references(Node record, String type) throws SQLException {
	seqnum = 1;
	ListIterator<Node> references = record.selectNodes(type).listIterator();
	while (references.hasNext()) {
	    logger.debug("\t" + type + ":");
	    Node referenceNode = references.next();
	    String citation = getNode("citation", referenceNode);
	    logger.debug("\t\tcitation: " + citation);
	    String pmid = getNode("PMID", referenceNode);
	    logger.debug("\t\tpmid: " + pmid);

	    PreparedStatement cite2Stmt = conn.prepareStatement("insert into clinical_trials." + type + " values (?,?,?,?)");
	    cite2Stmt.setInt(1, ID);
	    cite2Stmt.setInt(2, seqnum++);
	    cite2Stmt.setString(3, citation);
	    cite2Stmt.setString(4, pmid);
	    cite2Stmt.executeUpdate();
	    cite2Stmt.close();
	}
    }

    @SuppressWarnings("unchecked")
    static void responsible_parties(Node record) throws SQLException {
	seqnum = 1;
	ListIterator<Node> parties = record.selectNodes("responsible_party").listIterator();
	while (parties.hasNext()) {
	    logger.debug("\tresponsible party:");
	    Node linkNode = parties.next();
	    String name_title = getNode("name_title", linkNode);
	    logger.debug("\t\tname_title: " + name_title);
	    String organization = getNode("organization", linkNode);
	    logger.debug("\t\torganization: " + organization);
	    String responsible_party_type = getNode("responsible_party_type", linkNode);
	    logger.debug("\t\tresponsible_party_type: " + responsible_party_type);
	    String investigator_affiliation = getNode("investigator_affiliation", linkNode);
	    logger.debug("\t\tinvestigator_affiliation: " + investigator_affiliation);
	    String investigator_full_name = getNode("investigator_full_name", linkNode);
	    logger.debug("\t\tinvestigator_full_name: " + investigator_full_name);
	    String investigator_title = getNode("investigator_title", linkNode);
	    logger.debug("\t\tinvestigator_title: " + investigator_title);

	    PreparedStatement cite2Stmt = conn.prepareStatement("insert into clinical_trials.responsible_party values (?,?,?,?,?,?,?,?)");
	    cite2Stmt.setInt(1, ID);
	    cite2Stmt.setInt(2, seqnum++);
	    cite2Stmt.setString(3, name_title);
	    cite2Stmt.setString(4, organization);
	    cite2Stmt.setString(5, responsible_party_type);
	    cite2Stmt.setString(6, investigator_affiliation);
	    cite2Stmt.setString(7, investigator_full_name);
	    cite2Stmt.setString(8, investigator_title);
	    cite2Stmt.executeUpdate();
	    cite2Stmt.close();
	}
    }

    static void clinical_results(Node clinical_results) throws SQLException {
	logger.debug("\tclinical results:");
	String pi_employee = getNode("certain_agreements/pi_employee", clinical_results);
	logger.debug("\t\tpi_employee: " + pi_employee);
	String restrictive_agreement = getNode("certain_agreements/restrictive_agreement", clinical_results);
	logger.debug("\t\trestrictive_agreement: " + restrictive_agreement);
	String contact_name_or_title = getNode("point_of_contact/name_or_title", clinical_results);
	logger.debug("\t\tcontact_name_or_title: " + contact_name_or_title);
	String contact_organization = getNode("point_of_contact/organization", clinical_results);
	logger.debug("\t\tcontact_organization: " + contact_organization);
	String contact_phone = getNode("point_of_contact/phone", clinical_results);
	logger.debug("\t\tcontact_phone: " + contact_phone);
	String contact_email = getNode("point_of_contact/email", clinical_results);
	logger.debug("\t\tcontact_email: " + contact_email);

	PreparedStatement cite2Stmt = conn.prepareStatement("insert into clinical_trials.clinical_results values (?,?,?,?,?,?,?)");
	cite2Stmt.setInt(1, ID);
	cite2Stmt.setString(2, pi_employee);
	cite2Stmt.setString(3, restrictive_agreement);
	cite2Stmt.setString(4, contact_name_or_title);
	cite2Stmt.setString(5, contact_organization);
	cite2Stmt.setString(6, contact_phone);
	cite2Stmt.setString(7, contact_email);
	cite2Stmt.executeUpdate();
	cite2Stmt.close();

	participant_flow(clinical_results);
	baseline(clinical_results);
	outcomes(clinical_results);
	reported_events(clinical_results);
    }

    @SuppressWarnings("unchecked")
    static void participant_flow(Node clinical_results) throws SQLException {
	String recruitment_details = getNode("participant_flow/recruitment_details", clinical_results);
	logger.debug("\t\trecruitment details: " + recruitment_details);
	String pre_assignment_details = getNode("participant_flow/pre_assignment_details", clinical_results);
	logger.debug("\t\tpre_assignment_details: " + pre_assignment_details);

	PreparedStatement cite2Stmt = conn.prepareStatement("insert into clinical_trials.participant_flow values (?,?,?)");
	cite2Stmt.setInt(1, ID);
	cite2Stmt.setString(2, recruitment_details);
	cite2Stmt.setString(3, pre_assignment_details);
	cite2Stmt.executeUpdate();
	cite2Stmt.close();

	ListIterator<Node> groups = clinical_results.selectNodes("participant_flow/group_list/group").listIterator();
	while (groups.hasNext()) {
	    logger.debug("\t\tgroup:");
	    Node groupNode = groups.next();
	    String group_id = ((Element) groupNode).attributeValue("group_id").trim();
	    logger.debug("\t\t\tgroup_id: " + group_id);
	    String title = getNode("title", groupNode);
	    logger.debug("\t\t\ttitle: " + title);
	    String description = getNode("description", groupNode);
	    logger.debug("\t\t\tdescription: " + description);

	    PreparedStatement cite3Stmt = conn.prepareStatement("insert into clinical_trials.participant_flow_group values (?,?,?,?)");
	    cite3Stmt.setInt(1, ID);
	    cite3Stmt.setString(2, group_id);
	    cite3Stmt.setString(3, title);
	    cite3Stmt.setString(4, description);
	    cite3Stmt.executeUpdate();
	    cite3Stmt.close();
	}

	seqnum2 = 1;
	ListIterator<Node> periods = clinical_results.selectNodes("participant_flow/period_list/period").listIterator();
	while (periods.hasNext()) {
	    logger.debug("\t\tperiod:");
	    Node periodNode = periods.next();
	    String title = getNode("title", periodNode);
	    logger.debug("\t\t\ttitle: " + title);

	    PreparedStatement cite3Stmt = conn.prepareStatement("insert into clinical_trials.participant_flow_period values (?,?,?)");
	    cite3Stmt.setInt(1, ID);
	    cite3Stmt.setInt(2, seqnum2);
	    cite3Stmt.setString(3, title);
	    cite3Stmt.executeUpdate();
	    cite3Stmt.close();

	    seqnum3 = 1;
	    ListIterator<Node> milestones = periodNode.selectNodes("milestone_list/milestone").listIterator();
	    while (milestones.hasNext()) {
		logger.debug("\t\t\tmilestone:");
		Node milestoneNode = milestones.next();
		String milestone_title = getNode("title", milestoneNode);
		logger.debug("\t\t\t\ttitle: " + milestone_title);

		PreparedStatement cite4Stmt = conn.prepareStatement("insert into clinical_trials.participant_flow_milestone values (?,?,?,?)");
		cite4Stmt.setInt(1, ID);
		cite4Stmt.setInt(2, seqnum2);
		cite4Stmt.setInt(3, seqnum3);
		cite4Stmt.setString(4, milestone_title);
		cite4Stmt.executeUpdate();
		cite4Stmt.close();

		ListIterator<Node> participants = milestoneNode.selectNodes("participants_list/participants").listIterator();
		while (participants.hasNext()) {
		    logger.debug("\t\t\t\tparticipants:");
		    Node participantNode = participants.next();
		    String group_id = ((Element) participantNode).attributeValue("group_id").trim();
		    logger.debug("\t\t\t\t\tgroup_id: " + group_id);
		    String count = ((Element) participantNode).attributeValue("count").trim();
		    logger.debug("\t\t\t\t\tcount: " + count);

		    PreparedStatement cite5Stmt = conn
			    .prepareStatement("insert into clinical_trials.participant_flow_milestone_participant values (?,?,?,?,?)");
		    cite5Stmt.setInt(1, ID);
		    cite5Stmt.setInt(2, seqnum2);
		    cite5Stmt.setInt(3, seqnum3);
		    cite5Stmt.setString(4, group_id);
		    cite5Stmt.setString(5, count);
		    cite5Stmt.executeUpdate();
		    cite5Stmt.close();
		}
		seqnum3++;
	    }

	    ListIterator<Node> drops = periodNode.selectNodes("drop_withdraw_reason_list/drop_withdraw_reason").listIterator();
	    while (drops.hasNext()) {
		logger.debug("\t\t\tdrop_withdraw_reason:");
		Node milestoneNode = drops.next();
		String milestone_title = getNode("title", milestoneNode);
		logger.debug("\t\t\t\ttitle: " + milestone_title);

		PreparedStatement cite4Stmt = conn.prepareStatement("insert into clinical_trials.participant_flow_drop_withdraw values (?,?,?,?)");
		cite4Stmt.setInt(1, ID);
		cite4Stmt.setInt(2, seqnum2);
		cite4Stmt.setInt(3, seqnum3);
		cite4Stmt.setString(4, milestone_title);
		cite4Stmt.executeUpdate();
		cite4Stmt.close();

		ListIterator<Node> participants = milestoneNode.selectNodes("participants_list/participants").listIterator();
		while (participants.hasNext()) {
		    logger.debug("\t\t\t\tparticipants:");
		    Node participantNode = participants.next();
		    String group_id = ((Element) participantNode).attributeValue("group_id").trim();
		    logger.debug("\t\t\t\t\tgroup_id: " + group_id);
		    String count = ((Element) participantNode).attributeValue("count").trim();
		    logger.debug("\t\t\t\t\tcount: " + count);

		    PreparedStatement cite5Stmt = conn
			    .prepareStatement("insert into clinical_trials.participant_flow_drop_withdraw_participant values (?,?,?,?,?)");
		    cite5Stmt.setInt(1, ID);
		    cite5Stmt.setInt(2, seqnum2);
		    cite5Stmt.setInt(3, seqnum3);
		    cite5Stmt.setString(4, group_id);
		    cite5Stmt.setString(5, count);
		    cite5Stmt.executeUpdate();
		    cite5Stmt.close();
		}
		seqnum3++;
	    }
	    seqnum2++;
	}
    }

    @SuppressWarnings("unchecked")
    static void baseline(Node clinical_results) throws SQLException {
	ListIterator<Node> groups = clinical_results.selectNodes("baseline/group_list/group").listIterator();
	while (groups.hasNext()) {
	    logger.debug("\t\tbaseline group:");
	    Node groupNode = groups.next();
	    String group_id = ((Element) groupNode).attributeValue("group_id").trim();
	    logger.debug("\t\t\tgroup_id: " + group_id);
	    String title = getNode("title", groupNode);
	    logger.debug("\t\t\ttitle: " + title);
	    String description = getNode("description", groupNode);
	    logger.debug("\t\t\tdescription: " + description);

	    PreparedStatement cite2Stmt = conn.prepareStatement("insert into clinical_trials.baseline_group values (?,?,?,?)");
	    cite2Stmt.setInt(1, ID);
	    cite2Stmt.setString(2, group_id);
	    cite2Stmt.setString(3, title);
	    cite2Stmt.setString(4, description);
	    cite2Stmt.executeUpdate();
	    cite2Stmt.close();
	}

	seqnum2 = 1;
	ListIterator<Node> measures = clinical_results.selectNodes("baseline/measure_list/measure").listIterator();
	while (measures.hasNext()) {
	    logger.debug("\t\tbaseline measure:");
	    Node measureNode = measures.next();
	    String title = getNode("title", measureNode);
	    logger.debug("\t\t\ttitle: " + title);
	    String description = getNode("description", measureNode);
	    logger.debug("\t\t\tdescription: " + description);
	    String units = getNode("units", measureNode);
	    logger.debug("\t\t\tunits: " + units);
	    String param = getNode("param", measureNode);
	    logger.debug("\t\t\tparam: " + param);
	    String dispersion = getNode("dispersion", measureNode);
	    logger.debug("\t\t\tdispersion: " + dispersion);

	    PreparedStatement cite3Stmt = conn.prepareStatement("insert into clinical_trials.baseline_measure values (?,?,?,?,?,?,?)");
	    cite3Stmt.setInt(1, ID);
	    cite3Stmt.setInt(2, seqnum2);
	    cite3Stmt.setString(3, title);
	    cite3Stmt.setString(4, description);
	    cite3Stmt.setString(5, units);
	    cite3Stmt.setString(6, param);
	    cite3Stmt.setString(7, dispersion);
	    cite3Stmt.executeUpdate();
	    cite3Stmt.close();

	    seqnum3 = 1;
	    ListIterator<Node> categories = measureNode.selectNodes("category_list/category").listIterator();
	    while (categories.hasNext()) {
		logger.debug("\t\t\tcategory:");
		Node categoryNode = categories.next();
		String sub_title = getNode("sub_title", categoryNode);
		logger.debug("\t\t\t\tsub_title: " + sub_title);

		PreparedStatement cite4Stmt = conn.prepareStatement("insert into clinical_trials.baseline_category values (?,?,?,?)");
		cite4Stmt.setInt(1, ID);
		cite4Stmt.setInt(2, seqnum2);
		cite4Stmt.setInt(3, seqnum3);
		cite4Stmt.setString(4, sub_title);
		cite4Stmt.executeUpdate();
		cite4Stmt.close();

		ListIterator<Node> measurements = categoryNode.selectNodes("measurement_list/measurement").listIterator();
		while (measurements.hasNext()) {
		    logger.debug("\t\t\t\tmeasurement:");
		    Node measurementNode = measurements.next();
		    String group_id = ((Element) measurementNode).attributeValue("group_id");
		    logger.debug("\t\t\t\t\tgroup_id: " + group_id);
		    String value = ((Element) measurementNode).attributeValue("value");
		    logger.debug("\t\t\t\t\tvalue: " + value);
		    String spread = ((Element) measurementNode).attributeValue("spread");
		    logger.debug("\t\t\t\t\tspread: " + spread);
		    String lower_limit = ((Element) measurementNode).attributeValue("lower_limit");
		    logger.debug("\t\t\t\t\tlower_limit: " + lower_limit);
		    String upper_limit = ((Element) measurementNode).attributeValue("upper_limit");
		    logger.debug("\t\t\t\t\tupper_limit: " + upper_limit);

		    PreparedStatement cite5Stmt = conn.prepareStatement("insert into clinical_trials.baseline_measurement values (?,?,?,?,?,?,?,?)");
		    cite5Stmt.setInt(1, ID);
		    cite5Stmt.setInt(2, seqnum2);
		    cite5Stmt.setInt(3, seqnum3);
		    cite5Stmt.setString(4, group_id);
		    cite5Stmt.setString(5, value);
		    cite5Stmt.setString(6, spread);
		    cite5Stmt.setString(7, lower_limit);
		    cite5Stmt.setString(8, upper_limit);
		    cite5Stmt.executeUpdate();
		    cite5Stmt.close();
		}
		seqnum3++;
	    }
	    seqnum2++;
	}
    }

    @SuppressWarnings("unchecked")
    static void outcomes(Node clinical_results) throws SQLException {
	seqnum2 = 1;
	ListIterator<Node> outcomes = clinical_results.selectNodes("outcome_list/outcome").listIterator();
	while (outcomes.hasNext()) {
	    logger.debug("\t\toutcome:");
	    Node outcomeNode = outcomes.next();
	    String posting_date = getNode("posting_date", outcomeNode);
	    logger.debug("\t\t\tposting_date: " + posting_date);
	    String type = getNode("type", outcomeNode);
	    logger.debug("\t\t\ttype: " + type);
	    String title = getNode("title", outcomeNode);
	    logger.debug("\t\t\ttitle: " + title);
	    String description = getNode("description", outcomeNode);
	    logger.debug("\t\t\tdescription: " + description);
	    String time_frame = getNode("time_frame", outcomeNode);
	    logger.debug("\t\t\ttime_frame: " + time_frame);
	    String safety_issue = getNode("safety_issue", outcomeNode);
	    logger.debug("\t\t\tsafety_issue: " + safety_issue);
	    String population = getNode("population", outcomeNode);
	    logger.debug("\t\t\tpopulation: " + population);

	    PreparedStatement cite2Stmt = conn.prepareStatement("insert into clinical_trials.results_outcome values (?,?,?,?,?,?,?,?,?)");
	    cite2Stmt.setInt(1, ID);
	    cite2Stmt.setInt(2, seqnum2);
	    cite2Stmt.setString(3, posting_date);
	    cite2Stmt.setString(4, type);
	    cite2Stmt.setString(5, title);
	    cite2Stmt.setString(6, description);
	    cite2Stmt.setString(7, time_frame);
	    cite2Stmt.setString(8, safety_issue);
	    cite2Stmt.setString(9, population);
	    cite2Stmt.executeUpdate();
	    cite2Stmt.close();

	    ListIterator<Node> groups = outcomeNode.selectNodes("group_list/group").listIterator();
	    while (groups.hasNext()) {
		logger.debug("\t\t\tgroup:");
		Node groupNode = groups.next();
		String group_id = ((Element) groupNode).attributeValue("group_id").trim();
		logger.debug("\t\t\t\tgroup_id: " + group_id);
		String group_title = getNode("title", groupNode);
		logger.debug("\t\t\t\ttitle: " + group_title);
		String group_description = getNode("description", groupNode);
		logger.debug("\t\t\t\tdescription: " + group_description);

		PreparedStatement cite3Stmt = conn.prepareStatement("insert into clinical_trials.results_group values (?,?,?,?,?)");
		cite3Stmt.setInt(1, ID);
		cite3Stmt.setInt(2, seqnum2);
		cite3Stmt.setString(3, group_id);
		cite3Stmt.setString(4, group_title);
		cite3Stmt.setString(5, group_description);
		cite3Stmt.executeUpdate();
		cite3Stmt.close();
	    }

	    seqnum3 = 1;
	    ListIterator<Node> measures = outcomeNode.selectNodes("measure_list/measure").listIterator();
	    while (measures.hasNext()) {
		logger.debug("\t\t\tmeasure:");
		Node measureNode = measures.next();
		String measure_title = getNode("title", measureNode);
		logger.debug("\t\t\t\ttitle: " + measure_title);
		String measure_description = getNode("description", measureNode);
		logger.debug("\t\t\t\tdescription: " + measure_description);
		String units = getNode("units", measureNode);
		logger.debug("\t\t\t\tunits: " + units);
		String param = getNode("param", measureNode);
		logger.debug("\t\t\t\tparam: " + param);
		String dispersion = getNode("dispersion", measureNode);
		logger.debug("\t\t\t\tdispersion: " + dispersion);

		PreparedStatement cite3Stmt = conn.prepareStatement("insert into clinical_trials.results_measure values (?,?,?,?,?,?,?,?)");
		cite3Stmt.setInt(1, ID);
		cite3Stmt.setInt(2, seqnum2);
		cite3Stmt.setInt(3, seqnum3);
		cite3Stmt.setString(4, measure_title);
		cite3Stmt.setString(5, measure_description);
		cite3Stmt.setString(6, units);
		cite3Stmt.setString(7, param);
		cite3Stmt.setString(8, dispersion);
		cite3Stmt.executeUpdate();
		cite3Stmt.close();

		seqnum4 = 1;
		ListIterator<Node> categories = measureNode.selectNodes("category_list/category").listIterator();
		while (categories.hasNext()) {
		    logger.debug("\t\t\t\tcategory:");
		    Node categoryNode = categories.next();
		    String sub_title = getNode("sub_title", categoryNode);
		    logger.debug("\t\t\t\t\tsub_title: " + sub_title);

		    PreparedStatement cite4Stmt = conn.prepareStatement("insert into clinical_trials.results_category values (?,?,?,?,?)");
		    cite4Stmt.setInt(1, ID);
		    cite4Stmt.setInt(2, seqnum2);
		    cite4Stmt.setInt(3, seqnum3);
		    cite4Stmt.setInt(4, seqnum4);
		    cite4Stmt.setString(5, sub_title);
		    cite4Stmt.executeUpdate();
		    cite4Stmt.close();

		    ListIterator<Node> measurements = categoryNode.selectNodes("measurement_list/measurement").listIterator();
		    while (measurements.hasNext()) {
			logger.debug("\t\t\t\t\tmeasurement:");
			Node measurementNode = measurements.next();
			String group_id = ((Element) measurementNode).attributeValue("group_id");
			logger.debug("\t\t\t\t\t\tgroup_id: " + group_id);
			String value = ((Element) measurementNode).attributeValue("value");
			logger.debug("\t\t\t\t\t\tvalue: " + value);
			String spread = ((Element) measurementNode).attributeValue("spread");
			logger.debug("\t\t\t\t\t\tspread: " + spread);
			String lower_limit = ((Element) measurementNode).attributeValue("lower_limit");
			logger.debug("\t\t\t\t\t\tlower_limit: " + lower_limit);
			String upper_limit = ((Element) measurementNode).attributeValue("upper_limit");
			logger.debug("\t\t\t\t\t\tupper_limit: " + upper_limit);

			PreparedStatement cite5Stmt = conn.prepareStatement("insert into clinical_trials.results_measurement values (?,?,?,?,?,?,?,?,?)");
			cite5Stmt.setInt(1, ID);
			cite5Stmt.setInt(2, seqnum2);
			cite5Stmt.setInt(3, seqnum3);
			cite5Stmt.setInt(4, seqnum4);
			cite5Stmt.setString(5, group_id);
			cite5Stmt.setString(6, value);
			cite5Stmt.setString(7, spread);
			cite5Stmt.setString(8, lower_limit);
			cite5Stmt.setString(9, upper_limit);
			cite5Stmt.executeUpdate();
			cite5Stmt.close();
		    }
		    seqnum4++;
		}
		seqnum3++;
	    }

	    seqnum3 = 1;
	    ListIterator<Node> analyses = outcomeNode.selectNodes("analysis_list/analysis").listIterator();
	    while (analyses.hasNext()) {
		logger.debug("\t\t\tanalysis:");
		Node analysisNode = analyses.next();
		String groups_desc = getNode("groups_desc", analysisNode);
		logger.debug("\t\t\t\tgroups_desc: " + groups_desc);
		String non_inferiority = getNode("non_inferiority", analysisNode);
		logger.debug("\t\t\t\tnon_inferiority: " + non_inferiority);
		String non_inferiority_desc = getNode("non_inferiority_desc", analysisNode);
		logger.debug("\t\t\t\tnon_inferiority_desc: " + non_inferiority_desc);
		String p_value = getNode("p_value", analysisNode);
		logger.debug("\t\t\t\tp_value: " + p_value);
		String p_value_desc = getNode("p_value_desc", analysisNode);
		logger.debug("\t\t\t\tp_value_desc: " + p_value_desc);
		String method = getNode("method", analysisNode);
		logger.debug("\t\t\t\tmethod: " + method);
		String method_desc = getNode("method_desc", analysisNode);
		logger.debug("\t\t\t\tmethod_desc: " + method_desc);
		String param_type = getNode("param_type", analysisNode);
		logger.debug("\t\t\t\tparam_type: " + param_type);
		String param_value = getNode("param_value", analysisNode);
		logger.debug("\t\t\t\tparam_value: " + param_value);
		String ci_percent = getNode("ci_percent", analysisNode);
		logger.debug("\t\t\t\tci_percent: " + ci_percent);
		String ci_n_sides = getNode("ci_n_sides", analysisNode);
		logger.debug("\t\t\t\tci_n_sides: " + ci_n_sides);
		String ci_lower_limit = getNode("ci_lower_limit", analysisNode);
		logger.debug("\t\t\t\tci_lower_limit: " + ci_lower_limit);
		String ci_upper_limit = getNode("ci_upper_limit", analysisNode);
		logger.debug("\t\t\t\tci_upper_limit: " + ci_upper_limit);
		String estimate_desc = getNode("estimate_desc", analysisNode);
		logger.debug("\t\t\t\testimate_desc: " + estimate_desc);
		String dispersion_type = getNode("dispersion_type", analysisNode);
		logger.debug("\t\t\t\tdispersion_type: " + dispersion_type);
		String dispersion_value = getNode("dispersion_value", analysisNode);
		logger.debug("\t\t\t\tdispersion_value: " + dispersion_value);

		PreparedStatement cite3Stmt = conn
			.prepareStatement("insert into clinical_trials.results_analysis values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		cite3Stmt.setInt(1, ID);
		cite3Stmt.setInt(2, seqnum2);
		cite3Stmt.setInt(3, seqnum3);
		cite3Stmt.setString(4, groups_desc);
		cite3Stmt.setString(5, non_inferiority);
		cite3Stmt.setString(6, non_inferiority_desc);
		cite3Stmt.setString(7, p_value);
		cite3Stmt.setString(8, p_value_desc);
		cite3Stmt.setString(9, method);
		cite3Stmt.setString(10, method_desc);
		cite3Stmt.setString(11, param_type);
		cite3Stmt.setString(12, param_value);
		cite3Stmt.setString(13, ci_percent);
		cite3Stmt.setString(14, ci_n_sides);
		cite3Stmt.setString(15, ci_lower_limit);
		cite3Stmt.setString(16, ci_upper_limit);
		cite3Stmt.setString(17, estimate_desc);
		cite3Stmt.setString(18, dispersion_type);
		cite3Stmt.setString(19, dispersion_value);
		cite3Stmt.executeUpdate();
		cite3Stmt.close();

		ListIterator<Node> group_ids = analysisNode.selectNodes("group_id_list/group_id").listIterator();
		while (group_ids.hasNext()) {
		    logger.debug("\t\t\t\tgroup:");
		    String group_id = group_ids.next().getText().trim();
		    logger.debug("\t\t\t\t\tgroup_id: " + group_id);

		    PreparedStatement cite4Stmt = conn.prepareStatement("insert into clinical_trials.results_analysis_group values (?,?,?,?)");
		    cite4Stmt.setInt(1, ID);
		    cite4Stmt.setInt(2, seqnum2);
		    cite4Stmt.setInt(3, seqnum3);
		    cite4Stmt.setString(4, group_id);
		    cite4Stmt.executeUpdate();
		    cite4Stmt.close();
		}
		seqnum3++;
	    }
	    seqnum2++;
	}
    }

    @SuppressWarnings("unchecked")
    static void reported_events(Node clinical_results) throws SQLException {
	logger.debug("\t\treported_events:");
	String time_frame = getNode("reported_events/time_frame", clinical_results);
	logger.debug("\t\t\ttime_frame: " + time_frame);
	String desc = getNode("reported_events/desc", clinical_results);
	logger.debug("\t\t\tdesc: " + desc);

	String serious_frequency_threshold = getNode("reported_events/serious_events/frequency_threshold", clinical_results);
	logger.debug("\t\t\tserious_frequency_threshold: " + serious_frequency_threshold);
	String serious_default_vocab = getNode("reported_events/serious_events/default_vocab", clinical_results);
	logger.debug("\t\t\tserious_default_vocab: " + serious_default_vocab);
	String serious_default_assessment = getNode("reported_events/serious_events/default_assessment", clinical_results);
	logger.debug("\t\t\tserious_default_assessment: " + serious_default_assessment);

	String other_frequency_threshold = getNode("reported_events/other_events/frequency_threshold", clinical_results);
	logger.debug("\t\t\tother_frequency_threshold: " + other_frequency_threshold);
	String other_default_vocab = getNode("reported_events/other_events/default_vocab", clinical_results);
	logger.debug("\t\t\tother_default_vocab: " + other_default_vocab);
	String other_default_assessment = getNode("reported_events/other_events/default_assessment", clinical_results);
	logger.debug("\t\t\tother_default_assessment: " + other_default_assessment);

	PreparedStatement cite2Stmt = conn.prepareStatement("insert into clinical_trials.reported_events values (?,?,?,?,?,?,?,?,?)");
	cite2Stmt.setInt(1, ID);
	cite2Stmt.setString(2, time_frame);
	cite2Stmt.setString(3, desc);
	cite2Stmt.setString(4, serious_frequency_threshold);
	cite2Stmt.setString(5, serious_default_vocab);
	cite2Stmt.setString(6, serious_default_assessment);
	cite2Stmt.setString(7, other_frequency_threshold);
	cite2Stmt.setString(8, other_default_vocab);
	cite2Stmt.setString(9, other_default_assessment);
	cite2Stmt.executeUpdate();
	cite2Stmt.close();

	ListIterator<Node> groups = clinical_results.selectNodes("reported_events/group_list/group").listIterator();
	while (groups.hasNext()) {
	    logger.debug("\t\t\tgroup:");
	    Node groupNode = groups.next();
	    String group_id = ((Element) groupNode).attributeValue("group_id").trim();
	    logger.debug("\t\t\t\tgroup_id: " + group_id);
	    String group_title = getNode("title", groupNode);
	    logger.debug("\t\t\t\ttitle: " + group_title);
	    String group_description = getNode("description", groupNode);
	    logger.debug("\t\t\t\tdescription: " + group_description);

	    PreparedStatement cite3Stmt = conn.prepareStatement("insert into clinical_trials.events_group values (?,?,?,?)");
	    cite3Stmt.setInt(1, ID);
	    cite3Stmt.setString(2, group_id);
	    cite3Stmt.setString(3, group_title);
	    cite3Stmt.setString(4, group_description);
	    cite3Stmt.executeUpdate();
	    cite3Stmt.close();
	}

	seqnum2 = 1;
	ListIterator<Node> serious_categories = clinical_results.selectNodes("reported_events/serious_events/category_list/category").listIterator();
	while (serious_categories.hasNext()) {
	    logger.debug("\t\t\tserious event category:");
	    Node categoryNode = serious_categories.next();
	    String category_title = getNode("title", categoryNode);
	    logger.debug("\t\t\t\ttitle: " + category_title);

	    PreparedStatement cite3Stmt = conn.prepareStatement("insert into clinical_trials.serious_category values (?,?,?)");
	    cite3Stmt.setInt(1, ID);
	    cite3Stmt.setInt(2, seqnum2);
	    cite3Stmt.setString(3, category_title);
	    cite3Stmt.executeUpdate();
	    cite3Stmt.close();

	    seqnum3 = 1;
	    ListIterator<Node> events = categoryNode.selectNodes("event_list/event").listIterator();
	    while (events.hasNext()) {
		logger.debug("\t\t\t\tevent:");
		Node eventNode = events.next();
		String sub_title = getNode("sub_title", eventNode);
		logger.debug("\t\t\t\t\tsub_title: " + sub_title);
		String description = getNode("description", eventNode);
		logger.debug("\t\t\t\t\tdescription: " + description);
		String assessment = getNode("assessment", eventNode);
		logger.debug("\t\t\t\t\tassessment: " + assessment);

		PreparedStatement cite4Stmt = conn.prepareStatement("insert into clinical_trials.serious_event values (?,?,?,?,?,?)");
		cite4Stmt.setInt(1, ID);
		cite4Stmt.setInt(2, seqnum2);
		cite4Stmt.setInt(3, seqnum3);
		cite4Stmt.setString(4, sub_title);
		cite4Stmt.setString(5, description);
		cite4Stmt.setString(6, assessment);
		cite4Stmt.executeUpdate();
		cite4Stmt.close();

		ListIterator<Node> counts = eventNode.selectNodes("counts").listIterator();
		while (counts.hasNext()) {
		    logger.debug("\t\t\t\t\tcount:");
		    Node countNode = counts.next();
		    String group_id = ((Element) countNode).attributeValue("group_id").trim();
		    logger.debug("\t\t\t\t\t\tgroup_id: " + group_id);
		    String subjects_affected = ((Element) countNode).attributeValue("subjects_affected");
		    logger.debug("\t\t\t\t\t\tsubjects_affected: " + subjects_affected);
		    String subjects_at_risk = ((Element) countNode).attributeValue("subjects_at_risk");
		    logger.debug("\t\t\t\t\t\tsubjects_at_risk: " + subjects_at_risk);
		    String count_events = ((Element) countNode).attributeValue("events");
		    logger.debug("\t\t\t\t\t\tcount_events: " + count_events);

		    PreparedStatement cite5Stmt = conn.prepareStatement("insert into clinical_trials.serious_event_count values (?,?,?,?,?,?,?)");
		    cite5Stmt.setInt(1, ID);
		    cite5Stmt.setInt(2, seqnum2);
		    cite5Stmt.setInt(3, seqnum3);
		    cite5Stmt.setString(4, group_id);
		    cite5Stmt.setString(5, subjects_affected);
		    cite5Stmt.setString(6, subjects_at_risk);
		    cite5Stmt.setString(7, count_events);
		    cite5Stmt.executeUpdate();
		    cite5Stmt.close();
		}
		seqnum3++;
	    }
	    seqnum2++;
	}

	seqnum2 = 1;
	ListIterator<Node> other_categories = clinical_results.selectNodes("reported_events/other_events/category_list/category").listIterator();
	while (other_categories.hasNext()) {
	    logger.debug("\t\t\tother event category:");
	    Node categoryNode = other_categories.next();
	    String category_title = getNode("title", categoryNode);
	    logger.debug("\t\t\t\ttitle: " + category_title);

	    PreparedStatement cite3Stmt = conn.prepareStatement("insert into clinical_trials.other_category values (?,?,?)");
	    cite3Stmt.setInt(1, ID);
	    cite3Stmt.setInt(2, seqnum2);
	    cite3Stmt.setString(3, category_title);
	    cite3Stmt.executeUpdate();
	    cite3Stmt.close();

	    seqnum3 = 1;
	    ListIterator<Node> events = categoryNode.selectNodes("event_list/event").listIterator();
	    while (events.hasNext()) {
		logger.debug("\t\t\t\tevent:");
		Node eventNode = events.next();
		String sub_title = getNode("sub_title", eventNode);
		logger.debug("\t\t\t\t\tsub_title: " + sub_title);
		String description = getNode("description", eventNode);
		logger.debug("\t\t\t\t\tdescription: " + description);
		String assessment = getNode("assessment", eventNode);
		logger.debug("\t\t\t\t\tassessment: " + assessment);

		PreparedStatement cite4Stmt = conn.prepareStatement("insert into clinical_trials.other_event values (?,?,?,?,?,?)");
		cite4Stmt.setInt(1, ID);
		cite4Stmt.setInt(2, seqnum2);
		cite4Stmt.setInt(3, seqnum3);
		cite4Stmt.setString(4, sub_title);
		cite4Stmt.setString(5, description);
		cite4Stmt.setString(6, assessment);
		cite4Stmt.executeUpdate();
		cite4Stmt.close();

		ListIterator<Node> counts = eventNode.selectNodes("counts").listIterator();
		while (counts.hasNext()) {
		    logger.debug("\t\t\t\t\tcount:");
		    Node countNode = counts.next();
		    String group_id = ((Element) countNode).attributeValue("group_id").trim();
		    logger.debug("\t\t\t\t\t\tgroup_id: " + group_id);
		    String subjects_affected = ((Element) countNode).attributeValue("subjects_affected");
		    logger.debug("\t\t\t\t\t\tsubjects_affected: " + subjects_affected);
		    String subjects_at_risk = ((Element) countNode).attributeValue("subjects_at_risk");
		    logger.debug("\t\t\t\t\t\tsubjects_at_risk: " + subjects_at_risk);
		    String count_events = ((Element) countNode).attributeValue("events");
		    logger.debug("\t\t\t\t\t\tcount_events: " + count_events);

		    PreparedStatement cite5Stmt = conn.prepareStatement("insert into clinical_trials.other_event_count values (?,?,?,?,?,?,?)");
		    cite5Stmt.setInt(1, ID);
		    cite5Stmt.setInt(2, seqnum2);
		    cite5Stmt.setInt(3, seqnum3);
		    cite5Stmt.setString(4, group_id);
		    cite5Stmt.setString(5, subjects_affected);
		    cite5Stmt.setString(6, subjects_at_risk);
		    cite5Stmt.setString(7, count_events);
		    cite5Stmt.executeUpdate();
		    cite5Stmt.close();
		}
		seqnum3++;
	    }
	    seqnum2++;
	}
    }
}
