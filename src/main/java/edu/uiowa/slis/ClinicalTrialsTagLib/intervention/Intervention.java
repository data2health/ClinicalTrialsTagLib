package edu.uiowa.slis.ClinicalTrialsTagLib.intervention;

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
public class Intervention extends ClinicalTrialsTagLibTagSupport {

	static Intervention currentInstance = null;
	boolean commitNeeded = false;
	boolean newRecord = false;

	private static final Log log = LogFactory.getLog(Intervention.class);

	Vector<ClinicalTrialsTagLibTagSupport> parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	int ID = 0;
	int seqnum = 0;
	String interventionType = null;
	String interventionName = null;
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

			InterventionIterator theInterventionIterator = (InterventionIterator)findAncestorWithClass(this, InterventionIterator.class);

			if (theInterventionIterator != null) {
				ID = theInterventionIterator.getID();
				seqnum = theInterventionIterator.getSeqnum();
			}

			if (theInterventionIterator == null && theClinicalStudy == null && seqnum == 0) {
				// no seqnum was provided - the default is to assume that it is a new Intervention and to generate a new seqnum
				seqnum = Sequence.generateID();
				insertEntity();
			} else {
				// an iterator or seqnum was provided as an attribute - we need to load a Intervention from the database
				boolean found = false;
				PreparedStatement stmt = getConnection().prepareStatement("select intervention_type,intervention_name,description from clinical_trials.intervention where id = ? and seqnum = ?");
				stmt.setInt(1,ID);
				stmt.setInt(2,seqnum);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					if (interventionType == null)
						interventionType = rs.getString(1);
					if (interventionName == null)
						interventionName = rs.getString(2);
					if (description == null)
						description = rs.getString(3);
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
				PreparedStatement stmt = getConnection().prepareStatement("update clinical_trials.intervention set intervention_type = ?, intervention_name = ?, description = ? where id = ? and seqnum = ?");
				stmt.setString(1,interventionType);
				stmt.setString(2,interventionName);
				stmt.setString(3,description);
				stmt.setInt(4,ID);
				stmt.setInt(5,seqnum);
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
				log.debug("generating new Intervention " + seqnum);
			}

			if (interventionType == null)
				interventionType = "";
			if (interventionName == null)
				interventionName = "";
			if (description == null)
				description = "";
			PreparedStatement stmt = getConnection().prepareStatement("insert into clinical_trials.intervention(id,seqnum,intervention_type,intervention_name,description) values (?,?,?,?,?)");
			stmt.setInt(1,ID);
			stmt.setInt(2,seqnum);
			stmt.setString(3,interventionType);
			stmt.setString(4,interventionName);
			stmt.setString(5,description);
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

	public String getInterventionType () {
		if (commitNeeded)
			return "";
		else
			return interventionType;
	}

	public void setInterventionType (String interventionType) {
		this.interventionType = interventionType;
		commitNeeded = true;
	}

	public String getActualInterventionType () {
		return interventionType;
	}

	public String getInterventionName () {
		if (commitNeeded)
			return "";
		else
			return interventionName;
	}

	public void setInterventionName (String interventionName) {
		this.interventionName = interventionName;
		commitNeeded = true;
	}

	public String getActualInterventionName () {
		return interventionName;
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

	public static String interventionTypeValue() throws JspException {
		try {
			return currentInstance.getInterventionType();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function interventionTypeValue()");
		}
	}

	public static String interventionNameValue() throws JspException {
		try {
			return currentInstance.getInterventionName();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function interventionNameValue()");
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
		interventionType = null;
		interventionName = null;
		description = null;
		newRecord = false;
		commitNeeded = false;
		parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	}

}
