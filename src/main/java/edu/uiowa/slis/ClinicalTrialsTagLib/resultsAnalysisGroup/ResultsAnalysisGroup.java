package edu.uiowa.slis.ClinicalTrialsTagLib.resultsAnalysisGroup;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import edu.uiowa.slis.ClinicalTrialsTagLib.resultsAnalysis.ResultsAnalysis;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;
import edu.uiowa.slis.ClinicalTrialsTagLib.Sequence;

@SuppressWarnings("serial")
public class ResultsAnalysisGroup extends ClinicalTrialsTagLibTagSupport {

	static ResultsAnalysisGroup currentInstance = null;
	boolean commitNeeded = false;
	boolean newRecord = false;

	private static final Log log = LogFactory.getLog(ResultsAnalysisGroup.class);

	Vector<ClinicalTrialsTagLibTagSupport> parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	int ID = 0;
	int seqnum = 0;
	int anaseqnum = 0;
	String groupId = null;

	public int doStartTag() throws JspException {
		currentInstance = this;
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			if (theResultsAnalysis!= null)
				parentEntities.addElement(theResultsAnalysis);

			if (theResultsAnalysis == null) {
			} else {
				ID = theResultsAnalysis.getID();
				seqnum = theResultsAnalysis.getSeqnum();
				anaseqnum = theResultsAnalysis.getAnaseqnum();
			}

			ResultsAnalysisGroupIterator theResultsAnalysisGroupIterator = (ResultsAnalysisGroupIterator)findAncestorWithClass(this, ResultsAnalysisGroupIterator.class);

			if (theResultsAnalysisGroupIterator != null) {
				ID = theResultsAnalysisGroupIterator.getID();
				groupId = theResultsAnalysisGroupIterator.getGroupId();
				seqnum = theResultsAnalysisGroupIterator.getSeqnum();
				anaseqnum = theResultsAnalysisGroupIterator.getAnaseqnum();
			}

			if (theResultsAnalysisGroupIterator == null && theResultsAnalysis == null && groupId == null) {
				// no groupId was provided - the default is to assume that it is a new ResultsAnalysisGroup and to generate a new groupId
				groupId = null;
				insertEntity();
			} else {
				// an iterator or groupId was provided as an attribute - we need to load a ResultsAnalysisGroup from the database
				boolean found = false;
				PreparedStatement stmt = getConnection().prepareStatement("select 1 from clinical_trials.results_analysis_group where id = ? and seqnum = ? and anaseqnum = ? and group_id = ?");
				stmt.setInt(1,ID);
				stmt.setInt(2,seqnum);
				stmt.setInt(3,anaseqnum);
				stmt.setString(4,groupId);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					found = true;
				}
				stmt.close();

				if (!found) {
					insertEntity();
				}
			}
		} catch (SQLException e) {
			log.error("JDBC error retrieving groupId " + groupId, e);
			throw new JspTagException("Error: JDBC error retrieving groupId " + groupId);
		} finally {
			freeConnection();
		}
		return EVAL_PAGE;
	}

	public int doEndTag() throws JspException {
		currentInstance = null;
		try {
			if (commitNeeded) {
				PreparedStatement stmt = getConnection().prepareStatement("update clinical_trials.results_analysis_group set where id = ? and seqnum = ? and anaseqnum = ? and group_id = ?");
				stmt.setInt(1,ID);
				stmt.setInt(2,seqnum);
				stmt.setInt(3,anaseqnum);
				stmt.setString(4,groupId);
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
			PreparedStatement stmt = getConnection().prepareStatement("insert into clinical_trials.results_analysis_group(id,seqnum,anaseqnum,group_id) values (?,?,?,?)");
			stmt.setInt(1,ID);
			stmt.setInt(2,seqnum);
			stmt.setInt(3,anaseqnum);
			stmt.setString(4,groupId);
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

	public int getAnaseqnum () {
		return anaseqnum;
	}

	public void setAnaseqnum (int anaseqnum) {
		this.anaseqnum = anaseqnum;
	}

	public int getActualAnaseqnum () {
		return anaseqnum;
	}

	public String getGroupId () {
		if (commitNeeded)
			return "";
		else
			return groupId;
	}

	public void setGroupId (String groupId) {
		this.groupId = groupId;
	}

	public String getActualGroupId () {
		return groupId;
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

	public static Integer anaseqnumValue() throws JspException {
		try {
			return currentInstance.getAnaseqnum();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function anaseqnumValue()");
		}
	}

	public static String groupIdValue() throws JspException {
		try {
			return currentInstance.getGroupId();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function groupIdValue()");
		}
	}

	private void clearServiceState () {
		ID = 0;
		seqnum = 0;
		anaseqnum = 0;
		groupId = null;
		newRecord = false;
		commitNeeded = false;
		parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	}

}
