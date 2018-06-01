package edu.uiowa.slis.ClinicalTrialsTagLib.interventionArmGroup;

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
public class InterventionArmGroup extends ClinicalTrialsTagLibTagSupport {

	static InterventionArmGroup currentInstance = null;
	boolean commitNeeded = false;
	boolean newRecord = false;

	private static final Log log = LogFactory.getLog(InterventionArmGroup.class);

	Vector<ClinicalTrialsTagLibTagSupport> parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	int ID = 0;
	int seqnum = 0;
	int intseqnum = 0;
	String armGroupLabel = null;

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

			InterventionArmGroupIterator theInterventionArmGroupIterator = (InterventionArmGroupIterator)findAncestorWithClass(this, InterventionArmGroupIterator.class);

			if (theInterventionArmGroupIterator != null) {
				ID = theInterventionArmGroupIterator.getID();
				seqnum = theInterventionArmGroupIterator.getSeqnum();
				intseqnum = theInterventionArmGroupIterator.getIntseqnum();
			}

			if (theInterventionArmGroupIterator == null && theIntervention == null && intseqnum == 0) {
				// no intseqnum was provided - the default is to assume that it is a new InterventionArmGroup and to generate a new intseqnum
				intseqnum = Sequence.generateID();
				insertEntity();
			} else {
				// an iterator or intseqnum was provided as an attribute - we need to load a InterventionArmGroup from the database
				boolean found = false;
				PreparedStatement stmt = getConnection().prepareStatement("select arm_group_label from clinical_trials.intervention_arm_group where id = ? and seqnum = ? and intseqnum = ?");
				stmt.setInt(1,ID);
				stmt.setInt(2,seqnum);
				stmt.setInt(3,intseqnum);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					if (armGroupLabel == null)
						armGroupLabel = rs.getString(1);
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
				PreparedStatement stmt = getConnection().prepareStatement("update clinical_trials.intervention_arm_group set arm_group_label = ? where id = ? and seqnum = ? and intseqnum = ?");
				stmt.setString(1,armGroupLabel);
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
				log.debug("generating new InterventionArmGroup " + intseqnum);
			}

			if (armGroupLabel == null)
				armGroupLabel = "";
			PreparedStatement stmt = getConnection().prepareStatement("insert into clinical_trials.intervention_arm_group(id,seqnum,intseqnum,arm_group_label) values (?,?,?,?)");
			stmt.setInt(1,ID);
			stmt.setInt(2,seqnum);
			stmt.setInt(3,intseqnum);
			stmt.setString(4,armGroupLabel);
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

	public String getArmGroupLabel () {
		if (commitNeeded)
			return "";
		else
			return armGroupLabel;
	}

	public void setArmGroupLabel (String armGroupLabel) {
		this.armGroupLabel = armGroupLabel;
		commitNeeded = true;
	}

	public String getActualArmGroupLabel () {
		return armGroupLabel;
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

	public static String armGroupLabelValue() throws JspException {
		try {
			return currentInstance.getArmGroupLabel();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function armGroupLabelValue()");
		}
	}

	private void clearServiceState () {
		ID = 0;
		seqnum = 0;
		intseqnum = 0;
		armGroupLabel = null;
		newRecord = false;
		commitNeeded = false;
		parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	}

}
