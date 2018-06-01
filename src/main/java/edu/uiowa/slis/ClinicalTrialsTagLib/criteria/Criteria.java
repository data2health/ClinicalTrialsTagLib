package edu.uiowa.slis.ClinicalTrialsTagLib.criteria;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import edu.uiowa.slis.ClinicalTrialsTagLib.eligibility.Eligibility;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;
import edu.uiowa.slis.ClinicalTrialsTagLib.Sequence;

@SuppressWarnings("serial")
public class Criteria extends ClinicalTrialsTagLibTagSupport {

	static Criteria currentInstance = null;
	boolean commitNeeded = false;
	boolean newRecord = false;

	private static final Log log = LogFactory.getLog(Criteria.class);

	Vector<ClinicalTrialsTagLibTagSupport> parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	int ID = 0;
	int seqnum = 0;
	int elseqnum = 0;
	String textblock = null;

	public int doStartTag() throws JspException {
		currentInstance = this;
		try {
			Eligibility theEligibility = (Eligibility)findAncestorWithClass(this, Eligibility.class);
			if (theEligibility!= null)
				parentEntities.addElement(theEligibility);

			if (theEligibility == null) {
			} else {
				ID = theEligibility.getID();
				seqnum = theEligibility.getSeqnum();
			}

			CriteriaIterator theCriteriaIterator = (CriteriaIterator)findAncestorWithClass(this, CriteriaIterator.class);

			if (theCriteriaIterator != null) {
				ID = theCriteriaIterator.getID();
				seqnum = theCriteriaIterator.getSeqnum();
				elseqnum = theCriteriaIterator.getElseqnum();
			}

			if (theCriteriaIterator == null && theEligibility == null && elseqnum == 0) {
				// no elseqnum was provided - the default is to assume that it is a new Criteria and to generate a new elseqnum
				elseqnum = Sequence.generateID();
				insertEntity();
			} else {
				// an iterator or elseqnum was provided as an attribute - we need to load a Criteria from the database
				boolean found = false;
				PreparedStatement stmt = getConnection().prepareStatement("select textblock from clinical_trials.criteria where id = ? and seqnum = ? and elseqnum = ?");
				stmt.setInt(1,ID);
				stmt.setInt(2,seqnum);
				stmt.setInt(3,elseqnum);
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
			log.error("JDBC error retrieving elseqnum " + elseqnum, e);
			throw new JspTagException("Error: JDBC error retrieving elseqnum " + elseqnum);
		} finally {
			freeConnection();
		}
		return EVAL_PAGE;
	}

	public int doEndTag() throws JspException {
		currentInstance = null;
		try {
			if (commitNeeded) {
				PreparedStatement stmt = getConnection().prepareStatement("update clinical_trials.criteria set textblock = ? where id = ? and seqnum = ? and elseqnum = ?");
				stmt.setString(1,textblock);
				stmt.setInt(2,ID);
				stmt.setInt(3,seqnum);
				stmt.setInt(4,elseqnum);
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
			if (elseqnum == 0) {
				elseqnum = Sequence.generateID();
				log.debug("generating new Criteria " + elseqnum);
			}

			if (textblock == null)
				textblock = "";
			PreparedStatement stmt = getConnection().prepareStatement("insert into clinical_trials.criteria(id,seqnum,elseqnum,textblock) values (?,?,?,?)");
			stmt.setInt(1,ID);
			stmt.setInt(2,seqnum);
			stmt.setInt(3,elseqnum);
			stmt.setString(4,textblock);
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

	public int getElseqnum () {
		return elseqnum;
	}

	public void setElseqnum (int elseqnum) {
		this.elseqnum = elseqnum;
	}

	public int getActualElseqnum () {
		return elseqnum;
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

	public static Integer elseqnumValue() throws JspException {
		try {
			return currentInstance.getElseqnum();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function elseqnumValue()");
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
		elseqnum = 0;
		textblock = null;
		newRecord = false;
		commitNeeded = false;
		parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	}

}
