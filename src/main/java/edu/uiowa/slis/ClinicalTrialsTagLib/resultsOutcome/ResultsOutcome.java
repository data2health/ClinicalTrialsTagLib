package edu.uiowa.slis.ClinicalTrialsTagLib.resultsOutcome;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import edu.uiowa.slis.ClinicalTrialsTagLib.clinicalResults.ClinicalResults;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;
import edu.uiowa.slis.ClinicalTrialsTagLib.Sequence;

@SuppressWarnings("serial")
public class ResultsOutcome extends ClinicalTrialsTagLibTagSupport {

	static ResultsOutcome currentInstance = null;
	boolean commitNeeded = false;
	boolean newRecord = false;

	private static final Log log = LogFactory.getLog(ResultsOutcome.class);

	Vector<ClinicalTrialsTagLibTagSupport> parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	int ID = 0;
	int seqnum = 0;
	String postingDate = null;
	String type = null;
	String title = null;
	String description = null;
	String timeFrame = null;
	String safetyIssue = null;
	String population = null;

	public int doStartTag() throws JspException {
		currentInstance = this;
		try {
			ClinicalResults theClinicalResults = (ClinicalResults)findAncestorWithClass(this, ClinicalResults.class);
			if (theClinicalResults!= null)
				parentEntities.addElement(theClinicalResults);

			if (theClinicalResults == null) {
			} else {
				ID = theClinicalResults.getID();
			}

			ResultsOutcomeIterator theResultsOutcomeIterator = (ResultsOutcomeIterator)findAncestorWithClass(this, ResultsOutcomeIterator.class);

			if (theResultsOutcomeIterator != null) {
				ID = theResultsOutcomeIterator.getID();
				seqnum = theResultsOutcomeIterator.getSeqnum();
			}

			if (theResultsOutcomeIterator == null && theClinicalResults == null && seqnum == 0) {
				// no seqnum was provided - the default is to assume that it is a new ResultsOutcome and to generate a new seqnum
				seqnum = Sequence.generateID();
				insertEntity();
			} else {
				// an iterator or seqnum was provided as an attribute - we need to load a ResultsOutcome from the database
				boolean found = false;
				PreparedStatement stmt = getConnection().prepareStatement("select posting_date,type,title,description,time_frame,safety_issue,population from clinical_trials.results_outcome where id = ? and seqnum = ?");
				stmt.setInt(1,ID);
				stmt.setInt(2,seqnum);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					if (postingDate == null)
						postingDate = rs.getString(1);
					if (type == null)
						type = rs.getString(2);
					if (title == null)
						title = rs.getString(3);
					if (description == null)
						description = rs.getString(4);
					if (timeFrame == null)
						timeFrame = rs.getString(5);
					if (safetyIssue == null)
						safetyIssue = rs.getString(6);
					if (population == null)
						population = rs.getString(7);
					found = true;
				}
				stmt.close();

				if (!found) {
					insertEntity();
				}
			}
		} catch (SQLException e) {
			log.error("JDBC error retrieving seqnum " + seqnum, e);
			throw new JspTagException("Error: JDBC error retrieving seqnum " + seqnum);
		} finally {
			freeConnection();
		}
		return EVAL_PAGE;
	}

	public int doEndTag() throws JspException {
		currentInstance = null;
		try {
			if (commitNeeded) {
				PreparedStatement stmt = getConnection().prepareStatement("update clinical_trials.results_outcome set posting_date = ?, type = ?, title = ?, description = ?, time_frame = ?, safety_issue = ?, population = ? where id = ? and seqnum = ?");
				stmt.setString(1,postingDate);
				stmt.setString(2,type);
				stmt.setString(3,title);
				stmt.setString(4,description);
				stmt.setString(5,timeFrame);
				stmt.setString(6,safetyIssue);
				stmt.setString(7,population);
				stmt.setInt(8,ID);
				stmt.setInt(9,seqnum);
				stmt.executeUpdate();
				stmt.close();
			}
		} catch (SQLException e) {
			log.error("Error: IOException while writing to the user", e);
			throw new JspTagException("Error: IOException while writing to the user");
		} finally {
			clearServiceState();
			freeConnection();
		}
		return super.doEndTag();
	}

	public void insertEntity() throws JspException {
		try {
			if (seqnum == 0) {
				seqnum = Sequence.generateID();
				log.debug("generating new ResultsOutcome " + seqnum);
			}

			if (postingDate == null)
				postingDate = "";
			if (type == null)
				type = "";
			if (title == null)
				title = "";
			if (description == null)
				description = "";
			if (timeFrame == null)
				timeFrame = "";
			if (safetyIssue == null)
				safetyIssue = "";
			if (population == null)
				population = "";
			PreparedStatement stmt = getConnection().prepareStatement("insert into clinical_trials.results_outcome(id,seqnum,posting_date,type,title,description,time_frame,safety_issue,population) values (?,?,?,?,?,?,?,?,?)");
			stmt.setInt(1,ID);
			stmt.setInt(2,seqnum);
			stmt.setString(3,postingDate);
			stmt.setString(4,type);
			stmt.setString(5,title);
			stmt.setString(6,description);
			stmt.setString(7,timeFrame);
			stmt.setString(8,safetyIssue);
			stmt.setString(9,population);
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			log.error("Error: IOException while writing to the user", e);
			throw new JspTagException("Error: IOException while writing to the user");
		} finally {
			freeConnection();
		}
	}

	public int getID () {
		return ID;
	}

	public void setID (int ID) {
		this.ID = ID;
	}

	public int getActualID () {
		return ID;
	}

	public int getSeqnum () {
		return seqnum;
	}

	public void setSeqnum (int seqnum) {
		this.seqnum = seqnum;
	}

	public int getActualSeqnum () {
		return seqnum;
	}

	public String getPostingDate () {
		if (commitNeeded)
			return "";
		else
			return postingDate;
	}

	public void setPostingDate (String postingDate) {
		this.postingDate = postingDate;
		commitNeeded = true;
	}

	public String getActualPostingDate () {
		return postingDate;
	}

	public String getType () {
		if (commitNeeded)
			return "";
		else
			return type;
	}

	public void setType (String type) {
		this.type = type;
		commitNeeded = true;
	}

	public String getActualType () {
		return type;
	}

	public String getTitle () {
		if (commitNeeded)
			return "";
		else
			return title;
	}

	public void setTitle (String title) {
		this.title = title;
		commitNeeded = true;
	}

	public String getActualTitle () {
		return title;
	}

	public String getDescription () {
		if (commitNeeded)
			return "";
		else
			return description;
	}

	public void setDescription (String description) {
		this.description = description;
		commitNeeded = true;
	}

	public String getActualDescription () {
		return description;
	}

	public String getTimeFrame () {
		if (commitNeeded)
			return "";
		else
			return timeFrame;
	}

	public void setTimeFrame (String timeFrame) {
		this.timeFrame = timeFrame;
		commitNeeded = true;
	}

	public String getActualTimeFrame () {
		return timeFrame;
	}

	public String getSafetyIssue () {
		if (commitNeeded)
			return "";
		else
			return safetyIssue;
	}

	public void setSafetyIssue (String safetyIssue) {
		this.safetyIssue = safetyIssue;
		commitNeeded = true;
	}

	public String getActualSafetyIssue () {
		return safetyIssue;
	}

	public String getPopulation () {
		if (commitNeeded)
			return "";
		else
			return population;
	}

	public void setPopulation (String population) {
		this.population = population;
		commitNeeded = true;
	}

	public String getActualPopulation () {
		return population;
	}

	public static Integer IDValue() throws JspException {
		try {
			return currentInstance.getID();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function IDValue()");
		}
	}

	public static Integer seqnumValue() throws JspException {
		try {
			return currentInstance.getSeqnum();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function seqnumValue()");
		}
	}

	public static String postingDateValue() throws JspException {
		try {
			return currentInstance.getPostingDate();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function postingDateValue()");
		}
	}

	public static String typeValue() throws JspException {
		try {
			return currentInstance.getType();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function typeValue()");
		}
	}

	public static String titleValue() throws JspException {
		try {
			return currentInstance.getTitle();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function titleValue()");
		}
	}

	public static String descriptionValue() throws JspException {
		try {
			return currentInstance.getDescription();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function descriptionValue()");
		}
	}

	public static String timeFrameValue() throws JspException {
		try {
			return currentInstance.getTimeFrame();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function timeFrameValue()");
		}
	}

	public static String safetyIssueValue() throws JspException {
		try {
			return currentInstance.getSafetyIssue();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function safetyIssueValue()");
		}
	}

	public static String populationValue() throws JspException {
		try {
			return currentInstance.getPopulation();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function populationValue()");
		}
	}

	private void clearServiceState () {
		ID = 0;
		seqnum = 0;
		postingDate = null;
		type = null;
		title = null;
		description = null;
		timeFrame = null;
		safetyIssue = null;
		population = null;
		newRecord = false;
		commitNeeded = false;
		parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	}

}
