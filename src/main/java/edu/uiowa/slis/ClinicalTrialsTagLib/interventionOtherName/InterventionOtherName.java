package edu.uiowa.slis.ClinicalTrialsTagLib.interventionOtherName;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import edu.uiowa.slis.ClinicalTrialsTagLib.intervention.Intervention;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;
import edu.uiowa.slis.ClinicalTrialsTagLib.Sequence;

@SuppressWarnings("serial")
public class InterventionOtherName extends ClinicalTrialsTagLibTagSupport {

	static InterventionOtherName currentInstance = null;
	boolean commitNeeded = false;
	boolean newRecord = false;

	private static final Log log = LogFactory.getLog(InterventionOtherName.class);

	Vector<ClinicalTrialsTagLibTagSupport> parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	int ID = 0;
	int seqnum = 0;
	int intseqnum = 0;
	String otherName = null;

	public int doStartTag() throws JspException {
		currentInstance = this;
		try {
			Intervention theIntervention = (Intervention)findAncestorWithClass(this, Intervention.class);
			if (theIntervention!= null)
				parentEntities.addElement(theIntervention);

			if (theIntervention == null) {
			} else {
				ID = theIntervention.getID();
				seqnum = theIntervention.getSeqnum();
			}

			InterventionOtherNameIterator theInterventionOtherNameIterator = (InterventionOtherNameIterator)findAncestorWithClass(this, InterventionOtherNameIterator.class);

			if (theInterventionOtherNameIterator != null) {
				ID = theInterventionOtherNameIterator.getID();
				seqnum = theInterventionOtherNameIterator.getSeqnum();
				intseqnum = theInterventionOtherNameIterator.getIntseqnum();
			}

			if (theInterventionOtherNameIterator == null && theIntervention == null && intseqnum == 0) {
				// no intseqnum was provided - the default is to assume that it is a new InterventionOtherName and to generate a new intseqnum
				intseqnum = Sequence.generateID();
				insertEntity();
			} else {
				// an iterator or intseqnum was provided as an attribute - we need to load a InterventionOtherName from the database
				boolean found = false;
				PreparedStatement stmt = getConnection().prepareStatement("select other_name from clinical_trials.intervention_other_name where id = ? and seqnum = ? and intseqnum = ?");
				stmt.setInt(1,ID);
				stmt.setInt(2,seqnum);
				stmt.setInt(3,intseqnum);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					if (otherName == null)
						otherName = rs.getString(1);
					found = true;
				}
				stmt.close();

				if (!found) {
					insertEntity();
				}
			}
		} catch (SQLException e) {
			log.error("JDBC error retrieving intseqnum " + intseqnum, e);
			throw new JspTagException("Error: JDBC error retrieving intseqnum " + intseqnum);
		} finally {
			freeConnection();
		}
		return EVAL_PAGE;
	}

	public int doEndTag() throws JspException {
		currentInstance = null;
		try {
			if (commitNeeded) {
				PreparedStatement stmt = getConnection().prepareStatement("update clinical_trials.intervention_other_name set other_name = ? where id = ? and seqnum = ? and intseqnum = ?");
				stmt.setString(1,otherName);
				stmt.setInt(2,ID);
				stmt.setInt(3,seqnum);
				stmt.setInt(4,intseqnum);
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
			if (intseqnum == 0) {
				intseqnum = Sequence.generateID();
				log.debug("generating new InterventionOtherName " + intseqnum);
			}

			if (otherName == null)
				otherName = "";
			PreparedStatement stmt = getConnection().prepareStatement("insert into clinical_trials.intervention_other_name(id,seqnum,intseqnum,other_name) values (?,?,?,?)");
			stmt.setInt(1,ID);
			stmt.setInt(2,seqnum);
			stmt.setInt(3,intseqnum);
			stmt.setString(4,otherName);
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

	public int getIntseqnum () {
		return intseqnum;
	}

	public void setIntseqnum (int intseqnum) {
		this.intseqnum = intseqnum;
	}

	public int getActualIntseqnum () {
		return intseqnum;
	}

	public String getOtherName () {
		if (commitNeeded)
			return "";
		else
			return otherName;
	}

	public void setOtherName (String otherName) {
		this.otherName = otherName;
		commitNeeded = true;
	}

	public String getActualOtherName () {
		return otherName;
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

	public static Integer intseqnumValue() throws JspException {
		try {
			return currentInstance.getIntseqnum();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function intseqnumValue()");
		}
	}

	public static String otherNameValue() throws JspException {
		try {
			return currentInstance.getOtherName();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function otherNameValue()");
		}
	}

	private void clearServiceState () {
		ID = 0;
		seqnum = 0;
		intseqnum = 0;
		otherName = null;
		newRecord = false;
		commitNeeded = false;
		parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	}

}
