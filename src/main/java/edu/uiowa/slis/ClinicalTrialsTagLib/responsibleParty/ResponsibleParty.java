package edu.uiowa.slis.ClinicalTrialsTagLib.responsibleParty;

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
public class ResponsibleParty extends ClinicalTrialsTagLibTagSupport {

	static ResponsibleParty currentInstance = null;
	boolean commitNeeded = false;
	boolean newRecord = false;

	private static final Log log = LogFactory.getLog(ResponsibleParty.class);

	Vector<ClinicalTrialsTagLibTagSupport> parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	int ID = 0;
	int seqnum = 0;
	String nameTitle = null;
	String organization = null;
	String responsiblePartyType = null;
	String investigatorAffiliation = null;
	String investigatorFullName = null;
	String investigatorTitle = null;

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

			ResponsiblePartyIterator theResponsiblePartyIterator = (ResponsiblePartyIterator)findAncestorWithClass(this, ResponsiblePartyIterator.class);

			if (theResponsiblePartyIterator != null) {
				ID = theResponsiblePartyIterator.getID();
				seqnum = theResponsiblePartyIterator.getSeqnum();
			}

			if (theResponsiblePartyIterator == null && theClinicalStudy == null && seqnum == 0) {
				// no seqnum was provided - the default is to assume that it is a new ResponsibleParty and to generate a new seqnum
				seqnum = Sequence.generateID();
				insertEntity();
			} else {
				// an iterator or seqnum was provided as an attribute - we need to load a ResponsibleParty from the database
				boolean found = false;
				PreparedStatement stmt = getConnection().prepareStatement("select name_title,organization,responsible_party_type,investigator_affiliation,investigator_full_name,investigator_title from clinical_trials.responsible_party where id = ? and seqnum = ?");
				stmt.setInt(1,ID);
				stmt.setInt(2,seqnum);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					if (nameTitle == null)
						nameTitle = rs.getString(1);
					if (organization == null)
						organization = rs.getString(2);
					if (responsiblePartyType == null)
						responsiblePartyType = rs.getString(3);
					if (investigatorAffiliation == null)
						investigatorAffiliation = rs.getString(4);
					if (investigatorFullName == null)
						investigatorFullName = rs.getString(5);
					if (investigatorTitle == null)
						investigatorTitle = rs.getString(6);
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
				PreparedStatement stmt = getConnection().prepareStatement("update clinical_trials.responsible_party set name_title = ?, organization = ?, responsible_party_type = ?, investigator_affiliation = ?, investigator_full_name = ?, investigator_title = ? where id = ? and seqnum = ?");
				stmt.setString(1,nameTitle);
				stmt.setString(2,organization);
				stmt.setString(3,responsiblePartyType);
				stmt.setString(4,investigatorAffiliation);
				stmt.setString(5,investigatorFullName);
				stmt.setString(6,investigatorTitle);
				stmt.setInt(7,ID);
				stmt.setInt(8,seqnum);
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
				log.debug("generating new ResponsibleParty " + seqnum);
			}

			if (nameTitle == null)
				nameTitle = "";
			if (organization == null)
				organization = "";
			if (responsiblePartyType == null)
				responsiblePartyType = "";
			if (investigatorAffiliation == null)
				investigatorAffiliation = "";
			if (investigatorFullName == null)
				investigatorFullName = "";
			if (investigatorTitle == null)
				investigatorTitle = "";
			PreparedStatement stmt = getConnection().prepareStatement("insert into clinical_trials.responsible_party(id,seqnum,name_title,organization,responsible_party_type,investigator_affiliation,investigator_full_name,investigator_title) values (?,?,?,?,?,?,?,?)");
			stmt.setInt(1,ID);
			stmt.setInt(2,seqnum);
			stmt.setString(3,nameTitle);
			stmt.setString(4,organization);
			stmt.setString(5,responsiblePartyType);
			stmt.setString(6,investigatorAffiliation);
			stmt.setString(7,investigatorFullName);
			stmt.setString(8,investigatorTitle);
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

	public String getNameTitle () {
		if (commitNeeded)
			return "";
		else
			return nameTitle;
	}

	public void setNameTitle (String nameTitle) {
		this.nameTitle = nameTitle;
		commitNeeded = true;
	}

	public String getActualNameTitle () {
		return nameTitle;
	}

	public String getOrganization () {
		if (commitNeeded)
			return "";
		else
			return organization;
	}

	public void setOrganization (String organization) {
		this.organization = organization;
		commitNeeded = true;
	}

	public String getActualOrganization () {
		return organization;
	}

	public String getResponsiblePartyType () {
		if (commitNeeded)
			return "";
		else
			return responsiblePartyType;
	}

	public void setResponsiblePartyType (String responsiblePartyType) {
		this.responsiblePartyType = responsiblePartyType;
		commitNeeded = true;
	}

	public String getActualResponsiblePartyType () {
		return responsiblePartyType;
	}

	public String getInvestigatorAffiliation () {
		if (commitNeeded)
			return "";
		else
			return investigatorAffiliation;
	}

	public void setInvestigatorAffiliation (String investigatorAffiliation) {
		this.investigatorAffiliation = investigatorAffiliation;
		commitNeeded = true;
	}

	public String getActualInvestigatorAffiliation () {
		return investigatorAffiliation;
	}

	public String getInvestigatorFullName () {
		if (commitNeeded)
			return "";
		else
			return investigatorFullName;
	}

	public void setInvestigatorFullName (String investigatorFullName) {
		this.investigatorFullName = investigatorFullName;
		commitNeeded = true;
	}

	public String getActualInvestigatorFullName () {
		return investigatorFullName;
	}

	public String getInvestigatorTitle () {
		if (commitNeeded)
			return "";
		else
			return investigatorTitle;
	}

	public void setInvestigatorTitle (String investigatorTitle) {
		this.investigatorTitle = investigatorTitle;
		commitNeeded = true;
	}

	public String getActualInvestigatorTitle () {
		return investigatorTitle;
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

	public static String nameTitleValue() throws JspException {
		try {
			return currentInstance.getNameTitle();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function nameTitleValue()");
		}
	}

	public static String organizationValue() throws JspException {
		try {
			return currentInstance.getOrganization();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function organizationValue()");
		}
	}

	public static String responsiblePartyTypeValue() throws JspException {
		try {
			return currentInstance.getResponsiblePartyType();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function responsiblePartyTypeValue()");
		}
	}

	public static String investigatorAffiliationValue() throws JspException {
		try {
			return currentInstance.getInvestigatorAffiliation();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function investigatorAffiliationValue()");
		}
	}

	public static String investigatorFullNameValue() throws JspException {
		try {
			return currentInstance.getInvestigatorFullName();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function investigatorFullNameValue()");
		}
	}

	public static String investigatorTitleValue() throws JspException {
		try {
			return currentInstance.getInvestigatorTitle();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function investigatorTitleValue()");
		}
	}

	private void clearServiceState () {
		ID = 0;
		seqnum = 0;
		nameTitle = null;
		organization = null;
		responsiblePartyType = null;
		investigatorAffiliation = null;
		investigatorFullName = null;
		investigatorTitle = null;
		newRecord = false;
		commitNeeded = false;
		parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	}

}
