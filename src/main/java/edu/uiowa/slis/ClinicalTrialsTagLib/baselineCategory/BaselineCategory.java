package edu.uiowa.slis.ClinicalTrialsTagLib.baselineCategory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import edu.uiowa.slis.ClinicalTrialsTagLib.baselineMeasure.BaselineMeasure;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;
import edu.uiowa.slis.ClinicalTrialsTagLib.Sequence;

@SuppressWarnings("serial")
public class BaselineCategory extends ClinicalTrialsTagLibTagSupport {

	static BaselineCategory currentInstance = null;
	boolean commitNeeded = false;
	boolean newRecord = false;

	private static final Log log = LogFactory.getLog(BaselineCategory.class);

	Vector<ClinicalTrialsTagLibTagSupport> parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	int ID = 0;
	int seqnum = 0;
	int measeqnum = 0;
	String subTitle = null;

	public int doStartTag() throws JspException {
		currentInstance = this;
		try {
			BaselineMeasure theBaselineMeasure = (BaselineMeasure)findAncestorWithClass(this, BaselineMeasure.class);
			if (theBaselineMeasure!= null)
				parentEntities.addElement(theBaselineMeasure);

			if (theBaselineMeasure == null) {
			} else {
				ID = theBaselineMeasure.getID();
				seqnum = theBaselineMeasure.getSeqnum();
			}

			BaselineCategoryIterator theBaselineCategoryIterator = (BaselineCategoryIterator)findAncestorWithClass(this, BaselineCategoryIterator.class);

			if (theBaselineCategoryIterator != null) {
				ID = theBaselineCategoryIterator.getID();
				seqnum = theBaselineCategoryIterator.getSeqnum();
				measeqnum = theBaselineCategoryIterator.getMeaseqnum();
			}

			if (theBaselineCategoryIterator == null && theBaselineMeasure == null && measeqnum == 0) {
				// no measeqnum was provided - the default is to assume that it is a new BaselineCategory and to generate a new measeqnum
				measeqnum = Sequence.generateID();
				insertEntity();
			} else {
				// an iterator or measeqnum was provided as an attribute - we need to load a BaselineCategory from the database
				boolean found = false;
				PreparedStatement stmt = getConnection().prepareStatement("select sub_title from clinical_trials.baseline_category where id = ? and seqnum = ? and measeqnum = ?");
				stmt.setInt(1,ID);
				stmt.setInt(2,seqnum);
				stmt.setInt(3,measeqnum);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					if (subTitle == null)
						subTitle = rs.getString(1);
					found = true;
				}
				stmt.close();

				if (!found) {
					insertEntity();
				}
			}
		} catch (SQLException e) {
			log.error("JDBC error retrieving measeqnum " + measeqnum, e);
			throw new JspTagException("Error: JDBC error retrieving measeqnum " + measeqnum);
		} finally {
			freeConnection();
		}
		return EVAL_PAGE;
	}

	public int doEndTag() throws JspException {
		currentInstance = null;
		try {
			if (commitNeeded) {
				PreparedStatement stmt = getConnection().prepareStatement("update clinical_trials.baseline_category set sub_title = ? where id = ? and seqnum = ? and measeqnum = ?");
				stmt.setString(1,subTitle);
				stmt.setInt(2,ID);
				stmt.setInt(3,seqnum);
				stmt.setInt(4,measeqnum);
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
			if (measeqnum == 0) {
				measeqnum = Sequence.generateID();
				log.debug("generating new BaselineCategory " + measeqnum);
			}

			if (subTitle == null)
				subTitle = "";
			PreparedStatement stmt = getConnection().prepareStatement("insert into clinical_trials.baseline_category(id,seqnum,measeqnum,sub_title) values (?,?,?,?)");
			stmt.setInt(1,ID);
			stmt.setInt(2,seqnum);
			stmt.setInt(3,measeqnum);
			stmt.setString(4,subTitle);
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

	public int getMeaseqnum () {
		return measeqnum;
	}

	public void setMeaseqnum (int measeqnum) {
		this.measeqnum = measeqnum;
	}

	public int getActualMeaseqnum () {
		return measeqnum;
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

	public static Integer measeqnumValue() throws JspException {
		try {
			return currentInstance.getMeaseqnum();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function measeqnumValue()");
		}
	}

	public static String subTitleValue() throws JspException {
		try {
			return currentInstance.getSubTitle();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function subTitleValue()");
		}
	}

	private void clearServiceState () {
		ID = 0;
		seqnum = 0;
		measeqnum = 0;
		subTitle = null;
		newRecord = false;
		commitNeeded = false;
		parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	}

}
