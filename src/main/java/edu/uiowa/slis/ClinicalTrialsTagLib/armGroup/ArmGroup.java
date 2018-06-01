package edu.uiowa.slis.ClinicalTrialsTagLib.armGroup;

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
public class ArmGroup extends ClinicalTrialsTagLibTagSupport {

	static ArmGroup currentInstance = null;
	boolean commitNeeded = false;
	boolean newRecord = false;

	private static final Log log = LogFactory.getLog(ArmGroup.class);

	Vector<ClinicalTrialsTagLibTagSupport> parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	int ID = 0;
	int seqnum = 0;
	String armGroupLabel = null;
	String armGroupType = null;
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

			ArmGroupIterator theArmGroupIterator = (ArmGroupIterator)findAncestorWithClass(this, ArmGroupIterator.class);

			if (theArmGroupIterator != null) {
				ID = theArmGroupIterator.getID();
				seqnum = theArmGroupIterator.getSeqnum();
			}

			if (theArmGroupIterator == null && theClinicalStudy == null && seqnum == 0) {
				// no seqnum was provided - the default is to assume that it is a new ArmGroup and to generate a new seqnum
				seqnum = Sequence.generateID();
				insertEntity();
			} else {
				// an iterator or seqnum was provided as an attribute - we need to load a ArmGroup from the database
				boolean found = false;
				PreparedStatement stmt = getConnection().prepareStatement("select arm_group_label,arm_group_type,description from clinical_trials.arm_group where id = ? and seqnum = ?");
				stmt.setInt(1,ID);
				stmt.setInt(2,seqnum);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					if (armGroupLabel == null)
						armGroupLabel = rs.getString(1);
					if (armGroupType == null)
						armGroupType = rs.getString(2);
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
				PreparedStatement stmt = getConnection().prepareStatement("update clinical_trials.arm_group set arm_group_label = ?, arm_group_type = ?, description = ? where id = ? and seqnum = ?");
				stmt.setString(1,armGroupLabel);
				stmt.setString(2,armGroupType);
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
				log.debug("generating new ArmGroup " + seqnum);
			}

			if (armGroupLabel == null)
				armGroupLabel = "";
			if (armGroupType == null)
				armGroupType = "";
			if (description == null)
				description = "";
			PreparedStatement stmt = getConnection().prepareStatement("insert into clinical_trials.arm_group(id,seqnum,arm_group_label,arm_group_type,description) values (?,?,?,?,?)");
			stmt.setInt(1,ID);
			stmt.setInt(2,seqnum);
			stmt.setString(3,armGroupLabel);
			stmt.setString(4,armGroupType);
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

	public String getArmGroupType () {
		if (commitNeeded)
			return "";
		else
			return armGroupType;
	}

	public void setArmGroupType (String armGroupType) {
		this.armGroupType = armGroupType;
		commitNeeded = true;
	}

	public String getActualArmGroupType () {
		return armGroupType;
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

	public static String armGroupLabelValue() throws JspException {
		try {
			return currentInstance.getArmGroupLabel();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function armGroupLabelValue()");
		}
	}

	public static String armGroupTypeValue() throws JspException {
		try {
			return currentInstance.getArmGroupType();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function armGroupTypeValue()");
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
		armGroupLabel = null;
		armGroupType = null;
		description = null;
		newRecord = false;
		commitNeeded = false;
		parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	}

}
