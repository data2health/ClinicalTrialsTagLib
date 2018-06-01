package edu.uiowa.slis.ClinicalTrialsTagLib.briefSummary;

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
public class BriefSummary extends ClinicalTrialsTagLibTagSupport {

	static BriefSummary currentInstance = null;
	boolean commitNeeded = false;
	boolean newRecord = false;

	private static final Log log = LogFactory.getLog(BriefSummary.class);

	Vector<ClinicalTrialsTagLibTagSupport> parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	int ID = 0;
	int seqnum = 0;
	String textblock = null;

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

			BriefSummaryIterator theBriefSummaryIterator = (BriefSummaryIterator)findAncestorWithClass(this, BriefSummaryIterator.class);

			if (theBriefSummaryIterator != null) {
				ID = theBriefSummaryIterator.getID();
				seqnum = theBriefSummaryIterator.getSeqnum();
			}

			if (theBriefSummaryIterator == null && theClinicalStudy == null && seqnum == 0) {
				// no seqnum was provided - the default is to assume that it is a new BriefSummary and to generate a new seqnum
				seqnum = Sequence.generateID();
				insertEntity();
			} else {
				// an iterator or seqnum was provided as an attribute - we need to load a BriefSummary from the database
				boolean found = false;
				PreparedStatement stmt = getConnection().prepareStatement("select textblock from clinical_trials.brief_summary where id = ? and seqnum = ?");
				stmt.setInt(1,ID);
				stmt.setInt(2,seqnum);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					if (textblock == null)
						textblock = rs.getString(1);
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
				PreparedStatement stmt = getConnection().prepareStatement("update clinical_trials.brief_summary set textblock = ? where id = ? and seqnum = ?");
				stmt.setString(1,textblock);
				stmt.setInt(2,ID);
				stmt.setInt(3,seqnum);
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
				log.debug("generating new BriefSummary " + seqnum);
			}

			if (textblock == null)
				textblock = "";
			PreparedStatement stmt = getConnection().prepareStatement("insert into clinical_trials.brief_summary(id,seqnum,textblock) values (?,?,?)");
			stmt.setInt(1,ID);
			stmt.setInt(2,seqnum);
			stmt.setString(3,textblock);
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

	public String getTextblock () {
		if (commitNeeded)
			return "";
		else
			return textblock;
	}

	public void setTextblock (String textblock) {
		this.textblock = textblock;
		commitNeeded = true;
	}

	public String getActualTextblock () {
		return textblock;
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

	public static String textblockValue() throws JspException {
		try {
			return currentInstance.getTextblock();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function textblockValue()");
		}
	}

	private void clearServiceState () {
		ID = 0;
		seqnum = 0;
		textblock = null;
		newRecord = false;
		commitNeeded = false;
		parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	}

}
