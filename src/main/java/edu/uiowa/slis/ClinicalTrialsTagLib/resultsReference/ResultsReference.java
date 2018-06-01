package edu.uiowa.slis.ClinicalTrialsTagLib.resultsReference;

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
public class ResultsReference extends ClinicalTrialsTagLibTagSupport {

	static ResultsReference currentInstance = null;
	boolean commitNeeded = false;
	boolean newRecord = false;

	private static final Log log = LogFactory.getLog(ResultsReference.class);

	Vector<ClinicalTrialsTagLibTagSupport> parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	int ID = 0;
	int seqnum = 0;
	String citation = null;
	String pmid = null;

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

			ResultsReferenceIterator theResultsReferenceIterator = (ResultsReferenceIterator)findAncestorWithClass(this, ResultsReferenceIterator.class);

			if (theResultsReferenceIterator != null) {
				ID = theResultsReferenceIterator.getID();
				seqnum = theResultsReferenceIterator.getSeqnum();
			}

			if (theResultsReferenceIterator == null && theClinicalStudy == null && seqnum == 0) {
				// no seqnum was provided - the default is to assume that it is a new ResultsReference and to generate a new seqnum
				seqnum = Sequence.generateID();
				insertEntity();
			} else {
				// an iterator or seqnum was provided as an attribute - we need to load a ResultsReference from the database
				boolean found = false;
				PreparedStatement stmt = getConnection().prepareStatement("select citation,pmid from clinical_trials.results_reference where id = ? and seqnum = ?");
				stmt.setInt(1,ID);
				stmt.setInt(2,seqnum);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					if (citation == null)
						citation = rs.getString(1);
					if (pmid == null)
						pmid = rs.getString(2);
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
				PreparedStatement stmt = getConnection().prepareStatement("update clinical_trials.results_reference set citation = ?, pmid = ? where id = ? and seqnum = ?");
				stmt.setString(1,citation);
				stmt.setString(2,pmid);
				stmt.setInt(3,ID);
				stmt.setInt(4,seqnum);
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
				log.debug("generating new ResultsReference " + seqnum);
			}

			if (citation == null)
				citation = "";
			if (pmid == null)
				pmid = "";
			PreparedStatement stmt = getConnection().prepareStatement("insert into clinical_trials.results_reference(id,seqnum,citation,pmid) values (?,?,?,?)");
			stmt.setInt(1,ID);
			stmt.setInt(2,seqnum);
			stmt.setString(3,citation);
			stmt.setString(4,pmid);
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

	public String getCitation () {
		if (commitNeeded)
			return "";
		else
			return citation;
	}

	public void setCitation (String citation) {
		this.citation = citation;
		commitNeeded = true;
	}

	public String getActualCitation () {
		return citation;
	}

	public String getPmid () {
		if (commitNeeded)
			return "";
		else
			return pmid;
	}

	public void setPmid (String pmid) {
		this.pmid = pmid;
		commitNeeded = true;
	}

	public String getActualPmid () {
		return pmid;
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

	public static String citationValue() throws JspException {
		try {
			return currentInstance.getCitation();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function citationValue()");
		}
	}

	public static String pmidValue() throws JspException {
		try {
			return currentInstance.getPmid();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function pmidValue()");
		}
	}

	private void clearServiceState () {
		ID = 0;
		seqnum = 0;
		citation = null;
		pmid = null;
		newRecord = false;
		commitNeeded = false;
		parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	}

}
