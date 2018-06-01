package edu.uiowa.slis.ClinicalTrialsTagLib.otherEvent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import edu.uiowa.slis.ClinicalTrialsTagLib.otherCategory.OtherCategory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;
import edu.uiowa.slis.ClinicalTrialsTagLib.Sequence;

@SuppressWarnings("serial")
public class OtherEvent extends ClinicalTrialsTagLibTagSupport {

	static OtherEvent currentInstance = null;
	boolean commitNeeded = false;
	boolean newRecord = false;

	private static final Log log = LogFactory.getLog(OtherEvent.class);

	Vector<ClinicalTrialsTagLibTagSupport> parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	int ID = 0;
	String seqnum = null;
	int eveseqnum = 0;
	String subTitle = null;
	String description = null;
	String assessment = null;

	public int doStartTag() throws JspException {
		currentInstance = this;
		try {
			OtherCategory theOtherCategory = (OtherCategory)findAncestorWithClass(this, OtherCategory.class);
			if (theOtherCategory!= null)
				parentEntities.addElement(theOtherCategory);

			if (theOtherCategory == null) {
			} else {
				ID = theOtherCategory.getID();
				seqnum = theOtherCategory.getSeqnum();
			}

			OtherEventIterator theOtherEventIterator = (OtherEventIterator)findAncestorWithClass(this, OtherEventIterator.class);

			if (theOtherEventIterator != null) {
				ID = theOtherEventIterator.getID();
				seqnum = theOtherEventIterator.getSeqnum();
				eveseqnum = theOtherEventIterator.getEveseqnum();
			}

			if (theOtherEventIterator == null && theOtherCategory == null && eveseqnum == 0) {
				// no eveseqnum was provided - the default is to assume that it is a new OtherEvent and to generate a new eveseqnum
				eveseqnum = Sequence.generateID();
				insertEntity();
			} else {
				// an iterator or eveseqnum was provided as an attribute - we need to load a OtherEvent from the database
				boolean found = false;
				PreparedStatement stmt = getConnection().prepareStatement("select sub_title,description,assessment from clinical_trials.other_event where id = ? and seqnum = ? and eveseqnum = ?");
				stmt.setInt(1,ID);
				stmt.setString(2,seqnum);
				stmt.setInt(3,eveseqnum);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					if (subTitle == null)
						subTitle = rs.getString(1);
					if (description == null)
						description = rs.getString(2);
					if (assessment == null)
						assessment = rs.getString(3);
					found = true;
				}
				stmt.close();

				if (!found) {
					insertEntity();
				}
			}
		} catch (SQLException e) {
			log.error("JDBC error retrieving eveseqnum " + eveseqnum, e);
			throw new JspTagException("Error: JDBC error retrieving eveseqnum " + eveseqnum);
		} finally {
			freeConnection();
		}
		return EVAL_PAGE;
	}

	public int doEndTag() throws JspException {
		currentInstance = null;
		try {
			if (commitNeeded) {
				PreparedStatement stmt = getConnection().prepareStatement("update clinical_trials.other_event set sub_title = ?, description = ?, assessment = ? where id = ? and seqnum = ? and eveseqnum = ?");
				stmt.setString(1,subTitle);
				stmt.setString(2,description);
				stmt.setString(3,assessment);
				stmt.setInt(4,ID);
				stmt.setString(5,seqnum);
				stmt.setInt(6,eveseqnum);
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
			if (eveseqnum == 0) {
				eveseqnum = Sequence.generateID();
				log.debug("generating new OtherEvent " + eveseqnum);
			}

			if (subTitle == null)
				subTitle = "";
			if (description == null)
				description = "";
			if (assessment == null)
				assessment = "";
			PreparedStatement stmt = getConnection().prepareStatement("insert into clinical_trials.other_event(id,seqnum,eveseqnum,sub_title,description,assessment) values (?,?,?,?,?,?)");
			stmt.setInt(1,ID);
			stmt.setString(2,seqnum);
			stmt.setInt(3,eveseqnum);
			stmt.setString(4,subTitle);
			stmt.setString(5,description);
			stmt.setString(6,assessment);
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

	public String getSeqnum () {
		if (commitNeeded)
			return "";
		else
			return seqnum;
	}

	public void setSeqnum (String seqnum) {
		this.seqnum = seqnum;
	}

	public String getActualSeqnum () {
		return seqnum;
	}

	public int getEveseqnum () {
		return eveseqnum;
	}

	public void setEveseqnum (int eveseqnum) {
		this.eveseqnum = eveseqnum;
	}

	public int getActualEveseqnum () {
		return eveseqnum;
	}

	public String getSubTitle () {
		if (commitNeeded)
			return "";
		else
			return subTitle;
	}

	public void setSubTitle (String subTitle) {
		this.subTitle = subTitle;
		commitNeeded = true;
	}

	public String getActualSubTitle () {
		return subTitle;
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

	public String getAssessment () {
		if (commitNeeded)
			return "";
		else
			return assessment;
	}

	public void setAssessment (String assessment) {
		this.assessment = assessment;
		commitNeeded = true;
	}

	public String getActualAssessment () {
		return assessment;
	}

	public static Integer IDValue() throws JspException {
		try {
			return currentInstance.getID();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function IDValue()");
		}
	}

	public static String seqnumValue() throws JspException {
		try {
			return currentInstance.getSeqnum();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function seqnumValue()");
		}
	}

	public static Integer eveseqnumValue() throws JspException {
		try {
			return currentInstance.getEveseqnum();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function eveseqnumValue()");
		}
	}

	public static String subTitleValue() throws JspException {
		try {
			return currentInstance.getSubTitle();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function subTitleValue()");
		}
	}

	public static String descriptionValue() throws JspException {
		try {
			return currentInstance.getDescription();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function descriptionValue()");
		}
	}

	public static String assessmentValue() throws JspException {
		try {
			return currentInstance.getAssessment();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function assessmentValue()");
		}
	}

	private void clearServiceState () {
		ID = 0;
		seqnum = null;
		eveseqnum = 0;
		subTitle = null;
		description = null;
		assessment = null;
		newRecord = false;
		commitNeeded = false;
		parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	}

}
