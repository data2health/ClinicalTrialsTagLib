package edu.uiowa.slis.ClinicalTrialsTagLib.primaryOutcome;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import edu.uiowa.slis.ClinicalTrialsTagLib.clinicalStudy.ClinicalStudy;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;
import edu.uiowa.slis.ClinicalTrialsTagLib.Sequence;

@SuppressWarnings("serial")
public class PrimaryOutcome extends ClinicalTrialsTagLibTagSupport {

	static PrimaryOutcome currentInstance = null;
	boolean commitNeeded = false;
	boolean newRecord = false;

	private static final Log log = LogFactory.getLog(PrimaryOutcome.class);

	Vector<ClinicalTrialsTagLibTagSupport> parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	int ID = 0;
	int seqnum = 0;
	String measure = null;
	String timeFrame = null;
	String safetyIssue = null;
	String description = null;

	public int doStartTag() throws JspException {
		currentInstance = this;
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			if (theClinicalStudy!= null)
				parentEntities.addElement(theClinicalStudy);

			if (theClinicalStudy == null) {
			} else {
				ID = theClinicalStudy.getID();
			}

			PrimaryOutcomeIterator thePrimaryOutcomeIterator = (PrimaryOutcomeIterator)findAncestorWithClass(this, PrimaryOutcomeIterator.class);

			if (thePrimaryOutcomeIterator != null) {
				ID = thePrimaryOutcomeIterator.getID();
				seqnum = thePrimaryOutcomeIterator.getSeqnum();
			}

			if (thePrimaryOutcomeIterator == null && theClinicalStudy == null && seqnum == 0) {
				// no seqnum was provided - the default is to assume that it is a new PrimaryOutcome and to generate a new seqnum
				seqnum = Sequence.generateID();
				insertEntity();
			} else {
				// an iterator or seqnum was provided as an attribute - we need to load a PrimaryOutcome from the database
				boolean found = false;
				PreparedStatement stmt = getConnection().prepareStatement("select measure,time_frame,safety_issue,description from clinical_trials.primary_outcome where id = ? and seqnum = ?");
				stmt.setInt(1,ID);
				stmt.setInt(2,seqnum);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					if (measure == null)
						measure = rs.getString(1);
					if (timeFrame == null)
						timeFrame = rs.getString(2);
					if (safetyIssue == null)
						safetyIssue = rs.getString(3);
					if (description == null)
						description = rs.getString(4);
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
				PreparedStatement stmt = getConnection().prepareStatement("update clinical_trials.primary_outcome set measure = ?, time_frame = ?, safety_issue = ?, description = ? where id = ? and seqnum = ?");
				stmt.setString(1,measure);
				stmt.setString(2,timeFrame);
				stmt.setString(3,safetyIssue);
				stmt.setString(4,description);
				stmt.setInt(5,ID);
				stmt.setInt(6,seqnum);
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
				log.debug("generating new PrimaryOutcome " + seqnum);
			}

			if (measure == null)
				measure = "";
			if (timeFrame == null)
				timeFrame = "";
			if (safetyIssue == null)
				safetyIssue = "";
			if (description == null)
				description = "";
			PreparedStatement stmt = getConnection().prepareStatement("insert into clinical_trials.primary_outcome(id,seqnum,measure,time_frame,safety_issue,description) values (?,?,?,?,?,?)");
			stmt.setInt(1,ID);
			stmt.setInt(2,seqnum);
			stmt.setString(3,measure);
			stmt.setString(4,timeFrame);
			stmt.setString(5,safetyIssue);
			stmt.setString(6,description);
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

	public String getMeasure () {
		if (commitNeeded)
			return "";
		else
			return measure;
	}

	public void setMeasure (String measure) {
		this.measure = measure;
		commitNeeded = true;
	}

	public String getActualMeasure () {
		return measure;
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

	public static String measureValue() throws JspException {
		try {
			return currentInstance.getMeasure();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function measureValue()");
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

	public static String descriptionValue() throws JspException {
		try {
			return currentInstance.getDescription();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function descriptionValue()");
		}
	}

	private void clearServiceState () {
		ID = 0;
		seqnum = 0;
		measure = null;
		timeFrame = null;
		safetyIssue = null;
		description = null;
		newRecord = false;
		commitNeeded = false;
		parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	}

}
