package edu.uiowa.slis.ClinicalTrialsTagLib.reportedEvents;

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
public class ReportedEvents extends ClinicalTrialsTagLibTagSupport {

	static ReportedEvents currentInstance = null;
	boolean commitNeeded = false;
	boolean newRecord = false;

	private static final Log log = LogFactory.getLog(ReportedEvents.class);

	Vector<ClinicalTrialsTagLibTagSupport> parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	int ID = 0;
	String timeFrame = null;
	String description = null;
	String seriousFrequencyThreshold = null;
	String seriousDefaultVocab = null;
	String seriousDefaultAssessment = null;
	String otherFrequencyThreshold = null;
	String otherDefaultVocab = null;
	String otherDefaultAssessment = null;

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

			ReportedEventsIterator theReportedEventsIterator = (ReportedEventsIterator)findAncestorWithClass(this, ReportedEventsIterator.class);

			if (theReportedEventsIterator != null) {
				ID = theReportedEventsIterator.getID();
			}

			if (theReportedEventsIterator == null && theClinicalResults == null && ID == 0) {
				// no ID was provided - the default is to assume that it is a new ReportedEvents and to generate a new ID
				ID = Sequence.generateID();
				insertEntity();
			} else {
				// an iterator or ID was provided as an attribute - we need to load a ReportedEvents from the database
				boolean found = false;
				PreparedStatement stmt = getConnection().prepareStatement("select time_frame,description,serious_frequency_threshold,serious_default_vocab,serious_default_assessment,other_frequency_threshold,other_default_vocab,other_default_assessment from clinical_trials.reported_events where id = ?");
				stmt.setInt(1,ID);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					if (timeFrame == null)
						timeFrame = rs.getString(1);
					if (description == null)
						description = rs.getString(2);
					if (seriousFrequencyThreshold == null)
						seriousFrequencyThreshold = rs.getString(3);
					if (seriousDefaultVocab == null)
						seriousDefaultVocab = rs.getString(4);
					if (seriousDefaultAssessment == null)
						seriousDefaultAssessment = rs.getString(5);
					if (otherFrequencyThreshold == null)
						otherFrequencyThreshold = rs.getString(6);
					if (otherDefaultVocab == null)
						otherDefaultVocab = rs.getString(7);
					if (otherDefaultAssessment == null)
						otherDefaultAssessment = rs.getString(8);
					found = true;
				}
				stmt.close();

				if (!found) {
					insertEntity();
				}
			}
		} catch (SQLException e) {
			log.error("JDBC error retrieving ID " + ID, e);
			throw new JspTagException("Error: JDBC error retrieving ID " + ID);
		} finally {
			freeConnection();
		}
		return EVAL_PAGE;
	}

	public int doEndTag() throws JspException {
		currentInstance = null;
		try {
			if (commitNeeded) {
				PreparedStatement stmt = getConnection().prepareStatement("update clinical_trials.reported_events set time_frame = ?, description = ?, serious_frequency_threshold = ?, serious_default_vocab = ?, serious_default_assessment = ?, other_frequency_threshold = ?, other_default_vocab = ?, other_default_assessment = ? where id = ?");
				stmt.setString(1,timeFrame);
				stmt.setString(2,description);
				stmt.setString(3,seriousFrequencyThreshold);
				stmt.setString(4,seriousDefaultVocab);
				stmt.setString(5,seriousDefaultAssessment);
				stmt.setString(6,otherFrequencyThreshold);
				stmt.setString(7,otherDefaultVocab);
				stmt.setString(8,otherDefaultAssessment);
				stmt.setInt(9,ID);
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
			if (timeFrame == null)
				timeFrame = "";
			if (description == null)
				description = "";
			if (seriousFrequencyThreshold == null)
				seriousFrequencyThreshold = "";
			if (seriousDefaultVocab == null)
				seriousDefaultVocab = "";
			if (seriousDefaultAssessment == null)
				seriousDefaultAssessment = "";
			if (otherFrequencyThreshold == null)
				otherFrequencyThreshold = "";
			if (otherDefaultVocab == null)
				otherDefaultVocab = "";
			if (otherDefaultAssessment == null)
				otherDefaultAssessment = "";
			PreparedStatement stmt = getConnection().prepareStatement("insert into clinical_trials.reported_events(id,time_frame,description,serious_frequency_threshold,serious_default_vocab,serious_default_assessment,other_frequency_threshold,other_default_vocab,other_default_assessment) values (?,?,?,?,?,?,?,?,?)");
			stmt.setInt(1,ID);
			stmt.setString(2,timeFrame);
			stmt.setString(3,description);
			stmt.setString(4,seriousFrequencyThreshold);
			stmt.setString(5,seriousDefaultVocab);
			stmt.setString(6,seriousDefaultAssessment);
			stmt.setString(7,otherFrequencyThreshold);
			stmt.setString(8,otherDefaultVocab);
			stmt.setString(9,otherDefaultAssessment);
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

	public String getSeriousFrequencyThreshold () {
		if (commitNeeded)
			return "";
		else
			return seriousFrequencyThreshold;
	}

	public void setSeriousFrequencyThreshold (String seriousFrequencyThreshold) {
		this.seriousFrequencyThreshold = seriousFrequencyThreshold;
		commitNeeded = true;
	}

	public String getActualSeriousFrequencyThreshold () {
		return seriousFrequencyThreshold;
	}

	public String getSeriousDefaultVocab () {
		if (commitNeeded)
			return "";
		else
			return seriousDefaultVocab;
	}

	public void setSeriousDefaultVocab (String seriousDefaultVocab) {
		this.seriousDefaultVocab = seriousDefaultVocab;
		commitNeeded = true;
	}

	public String getActualSeriousDefaultVocab () {
		return seriousDefaultVocab;
	}

	public String getSeriousDefaultAssessment () {
		if (commitNeeded)
			return "";
		else
			return seriousDefaultAssessment;
	}

	public void setSeriousDefaultAssessment (String seriousDefaultAssessment) {
		this.seriousDefaultAssessment = seriousDefaultAssessment;
		commitNeeded = true;
	}

	public String getActualSeriousDefaultAssessment () {
		return seriousDefaultAssessment;
	}

	public String getOtherFrequencyThreshold () {
		if (commitNeeded)
			return "";
		else
			return otherFrequencyThreshold;
	}

	public void setOtherFrequencyThreshold (String otherFrequencyThreshold) {
		this.otherFrequencyThreshold = otherFrequencyThreshold;
		commitNeeded = true;
	}

	public String getActualOtherFrequencyThreshold () {
		return otherFrequencyThreshold;
	}

	public String getOtherDefaultVocab () {
		if (commitNeeded)
			return "";
		else
			return otherDefaultVocab;
	}

	public void setOtherDefaultVocab (String otherDefaultVocab) {
		this.otherDefaultVocab = otherDefaultVocab;
		commitNeeded = true;
	}

	public String getActualOtherDefaultVocab () {
		return otherDefaultVocab;
	}

	public String getOtherDefaultAssessment () {
		if (commitNeeded)
			return "";
		else
			return otherDefaultAssessment;
	}

	public void setOtherDefaultAssessment (String otherDefaultAssessment) {
		this.otherDefaultAssessment = otherDefaultAssessment;
		commitNeeded = true;
	}

	public String getActualOtherDefaultAssessment () {
		return otherDefaultAssessment;
	}

	public static Integer IDValue() throws JspException {
		try {
			return currentInstance.getID();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function IDValue()");
		}
	}

	public static String timeFrameValue() throws JspException {
		try {
			return currentInstance.getTimeFrame();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function timeFrameValue()");
		}
	}

	public static String descriptionValue() throws JspException {
		try {
			return currentInstance.getDescription();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function descriptionValue()");
		}
	}

	public static String seriousFrequencyThresholdValue() throws JspException {
		try {
			return currentInstance.getSeriousFrequencyThreshold();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function seriousFrequencyThresholdValue()");
		}
	}

	public static String seriousDefaultVocabValue() throws JspException {
		try {
			return currentInstance.getSeriousDefaultVocab();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function seriousDefaultVocabValue()");
		}
	}

	public static String seriousDefaultAssessmentValue() throws JspException {
		try {
			return currentInstance.getSeriousDefaultAssessment();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function seriousDefaultAssessmentValue()");
		}
	}

	public static String otherFrequencyThresholdValue() throws JspException {
		try {
			return currentInstance.getOtherFrequencyThreshold();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function otherFrequencyThresholdValue()");
		}
	}

	public static String otherDefaultVocabValue() throws JspException {
		try {
			return currentInstance.getOtherDefaultVocab();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function otherDefaultVocabValue()");
		}
	}

	public static String otherDefaultAssessmentValue() throws JspException {
		try {
			return currentInstance.getOtherDefaultAssessment();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function otherDefaultAssessmentValue()");
		}
	}

	private void clearServiceState () {
		ID = 0;
		timeFrame = null;
		description = null;
		seriousFrequencyThreshold = null;
		seriousDefaultVocab = null;
		seriousDefaultAssessment = null;
		otherFrequencyThreshold = null;
		otherDefaultVocab = null;
		otherDefaultAssessment = null;
		newRecord = false;
		commitNeeded = false;
		parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	}

}
