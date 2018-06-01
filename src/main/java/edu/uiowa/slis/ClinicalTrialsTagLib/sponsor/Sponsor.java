package edu.uiowa.slis.ClinicalTrialsTagLib.sponsor;

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
public class Sponsor extends ClinicalTrialsTagLibTagSupport {

	static Sponsor currentInstance = null;
	boolean commitNeeded = false;
	boolean newRecord = false;

	private static final Log log = LogFactory.getLog(Sponsor.class);

	Vector<ClinicalTrialsTagLibTagSupport> parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	int ID = 0;
	int seqnum = 0;
	String leadSponsorAgency = null;
	String leadSponsorAgencyClass = null;

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

			SponsorIterator theSponsorIterator = (SponsorIterator)findAncestorWithClass(this, SponsorIterator.class);

			if (theSponsorIterator != null) {
				ID = theSponsorIterator.getID();
				seqnum = theSponsorIterator.getSeqnum();
			}

			if (theSponsorIterator == null && theClinicalStudy == null && seqnum == 0) {
				// no seqnum was provided - the default is to assume that it is a new Sponsor and to generate a new seqnum
				seqnum = Sequence.generateID();
				insertEntity();
			} else {
				// an iterator or seqnum was provided as an attribute - we need to load a Sponsor from the database
				boolean found = false;
				PreparedStatement stmt = getConnection().prepareStatement("select lead_sponsor_agency,lead_sponsor_agency_class from clinical_trials.sponsor where id = ? and seqnum = ?");
				stmt.setInt(1,ID);
				stmt.setInt(2,seqnum);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					if (leadSponsorAgency == null)
						leadSponsorAgency = rs.getString(1);
					if (leadSponsorAgencyClass == null)
						leadSponsorAgencyClass = rs.getString(2);
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
				PreparedStatement stmt = getConnection().prepareStatement("update clinical_trials.sponsor set lead_sponsor_agency = ?, lead_sponsor_agency_class = ? where id = ? and seqnum = ?");
				stmt.setString(1,leadSponsorAgency);
				stmt.setString(2,leadSponsorAgencyClass);
				stmt.setInt(3,ID);
				stmt.setInt(4,seqnum);
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
				log.debug("generating new Sponsor " + seqnum);
			}

			if (leadSponsorAgency == null)
				leadSponsorAgency = "";
			if (leadSponsorAgencyClass == null)
				leadSponsorAgencyClass = "";
			PreparedStatement stmt = getConnection().prepareStatement("insert into clinical_trials.sponsor(id,seqnum,lead_sponsor_agency,lead_sponsor_agency_class) values (?,?,?,?)");
			stmt.setInt(1,ID);
			stmt.setInt(2,seqnum);
			stmt.setString(3,leadSponsorAgency);
			stmt.setString(4,leadSponsorAgencyClass);
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

	public String getLeadSponsorAgency () {
		if (commitNeeded)
			return "";
		else
			return leadSponsorAgency;
	}

	public void setLeadSponsorAgency (String leadSponsorAgency) {
		this.leadSponsorAgency = leadSponsorAgency;
		commitNeeded = true;
	}

	public String getActualLeadSponsorAgency () {
		return leadSponsorAgency;
	}

	public String getLeadSponsorAgencyClass () {
		if (commitNeeded)
			return "";
		else
			return leadSponsorAgencyClass;
	}

	public void setLeadSponsorAgencyClass (String leadSponsorAgencyClass) {
		this.leadSponsorAgencyClass = leadSponsorAgencyClass;
		commitNeeded = true;
	}

	public String getActualLeadSponsorAgencyClass () {
		return leadSponsorAgencyClass;
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

	public static String leadSponsorAgencyValue() throws JspException {
		try {
			return currentInstance.getLeadSponsorAgency();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function leadSponsorAgencyValue()");
		}
	}

	public static String leadSponsorAgencyClassValue() throws JspException {
		try {
			return currentInstance.getLeadSponsorAgencyClass();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function leadSponsorAgencyClassValue()");
		}
	}

	private void clearServiceState () {
		ID = 0;
		seqnum = 0;
		leadSponsorAgency = null;
		leadSponsorAgencyClass = null;
		newRecord = false;
		commitNeeded = false;
		parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	}

}
