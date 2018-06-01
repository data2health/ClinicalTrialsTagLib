package edu.uiowa.slis.ClinicalTrialsTagLib.collaborator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import edu.uiowa.slis.ClinicalTrialsTagLib.sponsor.Sponsor;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;
import edu.uiowa.slis.ClinicalTrialsTagLib.Sequence;

@SuppressWarnings("serial")
public class Collaborator extends ClinicalTrialsTagLibTagSupport {

	static Collaborator currentInstance = null;
	boolean commitNeeded = false;
	boolean newRecord = false;

	private static final Log log = LogFactory.getLog(Collaborator.class);

	Vector<ClinicalTrialsTagLibTagSupport> parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	int ID = 0;
	int seqnum = 0;
	int collseqnum = 0;
	String agency = null;
	String agencyClass = null;

	public int doStartTag() throws JspException {
		currentInstance = this;
		try {
			Sponsor theSponsor = (Sponsor)findAncestorWithClass(this, Sponsor.class);
			if (theSponsor!= null)
				parentEntities.addElement(theSponsor);

			if (theSponsor == null) {
			} else {
				ID = theSponsor.getID();
				seqnum = theSponsor.getSeqnum();
			}

			CollaboratorIterator theCollaboratorIterator = (CollaboratorIterator)findAncestorWithClass(this, CollaboratorIterator.class);

			if (theCollaboratorIterator != null) {
				ID = theCollaboratorIterator.getID();
				seqnum = theCollaboratorIterator.getSeqnum();
				collseqnum = theCollaboratorIterator.getCollseqnum();
			}

			if (theCollaboratorIterator == null && theSponsor == null && collseqnum == 0) {
				// no collseqnum was provided - the default is to assume that it is a new Collaborator and to generate a new collseqnum
				collseqnum = Sequence.generateID();
				insertEntity();
			} else {
				// an iterator or collseqnum was provided as an attribute - we need to load a Collaborator from the database
				boolean found = false;
				PreparedStatement stmt = getConnection().prepareStatement("select agency,agency_class from clinical_trials.collaborator where id = ? and seqnum = ? and collseqnum = ?");
				stmt.setInt(1,ID);
				stmt.setInt(2,seqnum);
				stmt.setInt(3,collseqnum);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					if (agency == null)
						agency = rs.getString(1);
					if (agencyClass == null)
						agencyClass = rs.getString(2);
					found = true;
				}
				stmt.close();

				if (!found) {
					insertEntity();
				}
			}
		} catch (SQLException e) {
			log.error("JDBC error retrieving collseqnum " + collseqnum, e);
			throw new JspTagException("Error: JDBC error retrieving collseqnum " + collseqnum);
		} finally {
			freeConnection();
		}
		return EVAL_PAGE;
	}

	public int doEndTag() throws JspException {
		currentInstance = null;
		try {
			if (commitNeeded) {
				PreparedStatement stmt = getConnection().prepareStatement("update clinical_trials.collaborator set agency = ?, agency_class = ? where id = ? and seqnum = ? and collseqnum = ?");
				stmt.setString(1,agency);
				stmt.setString(2,agencyClass);
				stmt.setInt(3,ID);
				stmt.setInt(4,seqnum);
				stmt.setInt(5,collseqnum);
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
			if (collseqnum == 0) {
				collseqnum = Sequence.generateID();
				log.debug("generating new Collaborator " + collseqnum);
			}

			if (agency == null)
				agency = "";
			if (agencyClass == null)
				agencyClass = "";
			PreparedStatement stmt = getConnection().prepareStatement("insert into clinical_trials.collaborator(id,seqnum,collseqnum,agency,agency_class) values (?,?,?,?,?)");
			stmt.setInt(1,ID);
			stmt.setInt(2,seqnum);
			stmt.setInt(3,collseqnum);
			stmt.setString(4,agency);
			stmt.setString(5,agencyClass);
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

	public int getCollseqnum () {
		return collseqnum;
	}

	public void setCollseqnum (int collseqnum) {
		this.collseqnum = collseqnum;
	}

	public int getActualCollseqnum () {
		return collseqnum;
	}

	public String getAgency () {
		if (commitNeeded)
			return "";
		else
			return agency;
	}

	public void setAgency (String agency) {
		this.agency = agency;
		commitNeeded = true;
	}

	public String getActualAgency () {
		return agency;
	}

	public String getAgencyClass () {
		if (commitNeeded)
			return "";
		else
			return agencyClass;
	}

	public void setAgencyClass (String agencyClass) {
		this.agencyClass = agencyClass;
		commitNeeded = true;
	}

	public String getActualAgencyClass () {
		return agencyClass;
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

	public static Integer collseqnumValue() throws JspException {
		try {
			return currentInstance.getCollseqnum();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function collseqnumValue()");
		}
	}

	public static String agencyValue() throws JspException {
		try {
			return currentInstance.getAgency();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function agencyValue()");
		}
	}

	public static String agencyClassValue() throws JspException {
		try {
			return currentInstance.getAgencyClass();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function agencyClassValue()");
		}
	}

	private void clearServiceState () {
		ID = 0;
		seqnum = 0;
		collseqnum = 0;
		agency = null;
		agencyClass = null;
		newRecord = false;
		commitNeeded = false;
		parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	}

}
