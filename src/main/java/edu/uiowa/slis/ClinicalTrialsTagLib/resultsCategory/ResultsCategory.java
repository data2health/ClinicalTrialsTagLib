package edu.uiowa.slis.ClinicalTrialsTagLib.resultsCategory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import edu.uiowa.slis.ClinicalTrialsTagLib.resultsMeasure.ResultsMeasure;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;
import edu.uiowa.slis.ClinicalTrialsTagLib.Sequence;

@SuppressWarnings("serial")
public class ResultsCategory extends ClinicalTrialsTagLibTagSupport {

	static ResultsCategory currentInstance = null;
	boolean commitNeeded = false;
	boolean newRecord = false;

	private static final Log log = LogFactory.getLog(ResultsCategory.class);

	Vector<ClinicalTrialsTagLibTagSupport> parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	int ID = 0;
	int seqnum = 0;
	int measeqnum = 0;
	int catseqnum = 0;
	String subTitle = null;

	public int doStartTag() throws JspException {
		currentInstance = this;
		try {
			ResultsMeasure theResultsMeasure = (ResultsMeasure)findAncestorWithClass(this, ResultsMeasure.class);
			if (theResultsMeasure!= null)
				parentEntities.addElement(theResultsMeasure);

			if (theResultsMeasure == null) {
			} else {
				ID = theResultsMeasure.getID();
				seqnum = theResultsMeasure.getSeqnum();
				measeqnum = theResultsMeasure.getMeaseqnum();
			}

			ResultsCategoryIterator theResultsCategoryIterator = (ResultsCategoryIterator)findAncestorWithClass(this, ResultsCategoryIterator.class);

			if (theResultsCategoryIterator != null) {
				ID = theResultsCategoryIterator.getID();
				seqnum = theResultsCategoryIterator.getSeqnum();
				measeqnum = theResultsCategoryIterator.getMeaseqnum();
			}

			if (theResultsCategoryIterator == null && theResultsMeasure == null && ID == 0) {
				// no ID was provided - the default is to assume that it is a new ResultsCategory and to generate a new ID
				ID = Sequence.generateID();
				insertEntity();
			} else {
				// an iterator or ID was provided as an attribute - we need to load a ResultsCategory from the database
				boolean found = false;
				PreparedStatement stmt = getConnection().prepareStatement("select catseqnum,sub_title from clinical_trials.results_category where id = ? and seqnum = ? and measeqnum = ?");
				stmt.setInt(1,ID);
				stmt.setInt(2,seqnum);
				stmt.setInt(3,measeqnum);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					if (catseqnum == 0)
						catseqnum = rs.getInt(1);
					if (subTitle == null)
						subTitle = rs.getString(2);
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
				PreparedStatement stmt = getConnection().prepareStatement("update clinical_trials.results_category set catseqnum = ?, sub_title = ? where id = ? and seqnum = ? and measeqnum = ?");
				stmt.setInt(1,catseqnum);
				stmt.setString(2,subTitle);
				stmt.setInt(3,ID);
				stmt.setInt(4,seqnum);
				stmt.setInt(5,measeqnum);
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
			if (subTitle == null)
				subTitle = "";
			PreparedStatement stmt = getConnection().prepareStatement("insert into clinical_trials.results_category(id,seqnum,measeqnum,catseqnum,sub_title) values (?,?,?,?,?)");
			stmt.setInt(1,ID);
			stmt.setInt(2,seqnum);
			stmt.setInt(3,measeqnum);
			stmt.setInt(4,catseqnum);
			stmt.setString(5,subTitle);
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

	public int getCatseqnum () {
		return catseqnum;
	}

	public void setCatseqnum (int catseqnum) {
		this.catseqnum = catseqnum;
		commitNeeded = true;
	}

	public int getActualCatseqnum () {
		return catseqnum;
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

	public static Integer catseqnumValue() throws JspException {
		try {
			return currentInstance.getCatseqnum();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function catseqnumValue()");
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
		catseqnum = 0;
		subTitle = null;
		newRecord = false;
		commitNeeded = false;
		parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	}

}
