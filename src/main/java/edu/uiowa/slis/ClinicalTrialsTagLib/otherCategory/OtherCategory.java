package edu.uiowa.slis.ClinicalTrialsTagLib.otherCategory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import edu.uiowa.slis.ClinicalTrialsTagLib.reportedEvents.ReportedEvents;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;
import edu.uiowa.slis.ClinicalTrialsTagLib.Sequence;

@SuppressWarnings("serial")
public class OtherCategory extends ClinicalTrialsTagLibTagSupport {

	static OtherCategory currentInstance = null;
	boolean commitNeeded = false;
	boolean newRecord = false;

	private static final Log log = LogFactory.getLog(OtherCategory.class);

	Vector<ClinicalTrialsTagLibTagSupport> parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	int ID = 0;
	String seqnum = null;
	String title = null;

	public int doStartTag() throws JspException {
		currentInstance = this;
		try {
			ReportedEvents theReportedEvents = (ReportedEvents)findAncestorWithClass(this, ReportedEvents.class);
			if (theReportedEvents!= null)
				parentEntities.addElement(theReportedEvents);

			if (theReportedEvents == null) {
			} else {
				ID = theReportedEvents.getID();
			}

			OtherCategoryIterator theOtherCategoryIterator = (OtherCategoryIterator)findAncestorWithClass(this, OtherCategoryIterator.class);

			if (theOtherCategoryIterator != null) {
				ID = theOtherCategoryIterator.getID();
				seqnum = theOtherCategoryIterator.getSeqnum();
			}

			if (theOtherCategoryIterator == null && theReportedEvents == null && seqnum == null) {
				// no seqnum was provided - the default is to assume that it is a new OtherCategory and to generate a new seqnum
				seqnum = null;
				insertEntity();
			} else {
				// an iterator or seqnum was provided as an attribute - we need to load a OtherCategory from the database
				boolean found = false;
				PreparedStatement stmt = getConnection().prepareStatement("select title from clinical_trials.other_category where id = ? and seqnum = ?");
				stmt.setInt(1,ID);
				stmt.setString(2,seqnum);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					if (title == null)
						title = rs.getString(1);
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
				PreparedStatement stmt = getConnection().prepareStatement("update clinical_trials.other_category set title = ? where id = ? and seqnum = ?");
				stmt.setString(1,title);
				stmt.setInt(2,ID);
				stmt.setString(3,seqnum);
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
			if (title == null)
				title = "";
			PreparedStatement stmt = getConnection().prepareStatement("insert into clinical_trials.other_category(id,seqnum,title) values (?,?,?)");
			stmt.setInt(1,ID);
			stmt.setString(2,seqnum);
			stmt.setString(3,title);
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

	public String getTitle () {
		if (commitNeeded)
			return "";
		else
			return title;
	}

	public void setTitle (String title) {
		this.title = title;
		commitNeeded = true;
	}

	public String getActualTitle () {
		return title;
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

	public static String titleValue() throws JspException {
		try {
			return currentInstance.getTitle();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function titleValue()");
		}
	}

	private void clearServiceState () {
		ID = 0;
		seqnum = null;
		title = null;
		newRecord = false;
		commitNeeded = false;
		parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	}

}
