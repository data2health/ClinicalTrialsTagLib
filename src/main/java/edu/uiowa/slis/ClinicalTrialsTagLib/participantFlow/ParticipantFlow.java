package edu.uiowa.slis.ClinicalTrialsTagLib.participantFlow;

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
public class ParticipantFlow extends ClinicalTrialsTagLibTagSupport {

	static ParticipantFlow currentInstance = null;
	boolean commitNeeded = false;
	boolean newRecord = false;

	private static final Log log = LogFactory.getLog(ParticipantFlow.class);

	Vector<ClinicalTrialsTagLibTagSupport> parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	int ID = 0;
	String recruitmentDetails = null;
	String preAssignmentDetails = null;

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

			ParticipantFlowIterator theParticipantFlowIterator = (ParticipantFlowIterator)findAncestorWithClass(this, ParticipantFlowIterator.class);

			if (theParticipantFlowIterator != null) {
				ID = theParticipantFlowIterator.getID();
			}

			if (theParticipantFlowIterator == null && theClinicalResults == null && ID == 0) {
				// no ID was provided - the default is to assume that it is a new ParticipantFlow and to generate a new ID
				ID = Sequence.generateID();
				insertEntity();
			} else {
				// an iterator or ID was provided as an attribute - we need to load a ParticipantFlow from the database
				boolean found = false;
				PreparedStatement stmt = getConnection().prepareStatement("select recruitment_details,pre_assignment_details from clinical_trials.participant_flow where id = ?");
				stmt.setInt(1,ID);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					if (recruitmentDetails == null)
						recruitmentDetails = rs.getString(1);
					if (preAssignmentDetails == null)
						preAssignmentDetails = rs.getString(2);
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
				PreparedStatement stmt = getConnection().prepareStatement("update clinical_trials.participant_flow set recruitment_details = ?, pre_assignment_details = ? where id = ?");
				stmt.setString(1,recruitmentDetails);
				stmt.setString(2,preAssignmentDetails);
				stmt.setInt(3,ID);
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
			if (recruitmentDetails == null)
				recruitmentDetails = "";
			if (preAssignmentDetails == null)
				preAssignmentDetails = "";
			PreparedStatement stmt = getConnection().prepareStatement("insert into clinical_trials.participant_flow(id,recruitment_details,pre_assignment_details) values (?,?,?)");
			stmt.setInt(1,ID);
			stmt.setString(2,recruitmentDetails);
			stmt.setString(3,preAssignmentDetails);
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

	public String getRecruitmentDetails () {
		if (commitNeeded)
			return "";
		else
			return recruitmentDetails;
	}

	public void setRecruitmentDetails (String recruitmentDetails) {
		this.recruitmentDetails = recruitmentDetails;
		commitNeeded = true;
	}

	public String getActualRecruitmentDetails () {
		return recruitmentDetails;
	}

	public String getPreAssignmentDetails () {
		if (commitNeeded)
			return "";
		else
			return preAssignmentDetails;
	}

	public void setPreAssignmentDetails (String preAssignmentDetails) {
		this.preAssignmentDetails = preAssignmentDetails;
		commitNeeded = true;
	}

	public String getActualPreAssignmentDetails () {
		return preAssignmentDetails;
	}

	public static Integer IDValue() throws JspException {
		try {
			return currentInstance.getID();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function IDValue()");
		}
	}

	public static String recruitmentDetailsValue() throws JspException {
		try {
			return currentInstance.getRecruitmentDetails();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function recruitmentDetailsValue()");
		}
	}

	public static String preAssignmentDetailsValue() throws JspException {
		try {
			return currentInstance.getPreAssignmentDetails();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function preAssignmentDetailsValue()");
		}
	}

	private void clearServiceState () {
		ID = 0;
		recruitmentDetails = null;
		preAssignmentDetails = null;
		newRecord = false;
		commitNeeded = false;
		parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	}

}
