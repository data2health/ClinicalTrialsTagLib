package edu.uiowa.slis.ClinicalTrialsTagLib.clinicalResults;

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
public class ClinicalResults extends ClinicalTrialsTagLibTagSupport {

	static ClinicalResults currentInstance = null;
	boolean commitNeeded = false;
	boolean newRecord = false;

	private static final Log log = LogFactory.getLog(ClinicalResults.class);

	Vector<ClinicalTrialsTagLibTagSupport> parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	int ID = 0;
	String piEmployee = null;
	String restrictiveAgreement = null;
	String contactNameOrTitle = null;
	String contactOrganization = null;
	String contactPhone = null;
	String contactEmail = null;

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

			ClinicalResultsIterator theClinicalResultsIterator = (ClinicalResultsIterator)findAncestorWithClass(this, ClinicalResultsIterator.class);

			if (theClinicalResultsIterator != null) {
				ID = theClinicalResultsIterator.getID();
			}

			if (theClinicalResultsIterator == null && theClinicalStudy == null && ID == 0) {
				// no ID was provided - the default is to assume that it is a new ClinicalResults and to generate a new ID
				ID = Sequence.generateID();
				insertEntity();
			} else {
				// an iterator or ID was provided as an attribute - we need to load a ClinicalResults from the database
				boolean found = false;
				PreparedStatement stmt = getConnection().prepareStatement("select pi_employee,restrictive_agreement,contact_name_or_title,contact_organization,contact_phone,contact_email from clinical_trials.clinical_results where id = ?");
				stmt.setInt(1,ID);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					if (piEmployee == null)
						piEmployee = rs.getString(1);
					if (restrictiveAgreement == null)
						restrictiveAgreement = rs.getString(2);
					if (contactNameOrTitle == null)
						contactNameOrTitle = rs.getString(3);
					if (contactOrganization == null)
						contactOrganization = rs.getString(4);
					if (contactPhone == null)
						contactPhone = rs.getString(5);
					if (contactEmail == null)
						contactEmail = rs.getString(6);
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
				PreparedStatement stmt = getConnection().prepareStatement("update clinical_trials.clinical_results set pi_employee = ?, restrictive_agreement = ?, contact_name_or_title = ?, contact_organization = ?, contact_phone = ?, contact_email = ? where id = ?");
				stmt.setString(1,piEmployee);
				stmt.setString(2,restrictiveAgreement);
				stmt.setString(3,contactNameOrTitle);
				stmt.setString(4,contactOrganization);
				stmt.setString(5,contactPhone);
				stmt.setString(6,contactEmail);
				stmt.setInt(7,ID);
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
			if (piEmployee == null)
				piEmployee = "";
			if (restrictiveAgreement == null)
				restrictiveAgreement = "";
			if (contactNameOrTitle == null)
				contactNameOrTitle = "";
			if (contactOrganization == null)
				contactOrganization = "";
			if (contactPhone == null)
				contactPhone = "";
			if (contactEmail == null)
				contactEmail = "";
			PreparedStatement stmt = getConnection().prepareStatement("insert into clinical_trials.clinical_results(id,pi_employee,restrictive_agreement,contact_name_or_title,contact_organization,contact_phone,contact_email) values (?,?,?,?,?,?,?)");
			stmt.setInt(1,ID);
			stmt.setString(2,piEmployee);
			stmt.setString(3,restrictiveAgreement);
			stmt.setString(4,contactNameOrTitle);
			stmt.setString(5,contactOrganization);
			stmt.setString(6,contactPhone);
			stmt.setString(7,contactEmail);
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

	public String getPiEmployee () {
		if (commitNeeded)
			return "";
		else
			return piEmployee;
	}

	public void setPiEmployee (String piEmployee) {
		this.piEmployee = piEmployee;
		commitNeeded = true;
	}

	public String getActualPiEmployee () {
		return piEmployee;
	}

	public String getRestrictiveAgreement () {
		if (commitNeeded)
			return "";
		else
			return restrictiveAgreement;
	}

	public void setRestrictiveAgreement (String restrictiveAgreement) {
		this.restrictiveAgreement = restrictiveAgreement;
		commitNeeded = true;
	}

	public String getActualRestrictiveAgreement () {
		return restrictiveAgreement;
	}

	public String getContactNameOrTitle () {
		if (commitNeeded)
			return "";
		else
			return contactNameOrTitle;
	}

	public void setContactNameOrTitle (String contactNameOrTitle) {
		this.contactNameOrTitle = contactNameOrTitle;
		commitNeeded = true;
	}

	public String getActualContactNameOrTitle () {
		return contactNameOrTitle;
	}

	public String getContactOrganization () {
		if (commitNeeded)
			return "";
		else
			return contactOrganization;
	}

	public void setContactOrganization (String contactOrganization) {
		this.contactOrganization = contactOrganization;
		commitNeeded = true;
	}

	public String getActualContactOrganization () {
		return contactOrganization;
	}

	public String getContactPhone () {
		if (commitNeeded)
			return "";
		else
			return contactPhone;
	}

	public void setContactPhone (String contactPhone) {
		this.contactPhone = contactPhone;
		commitNeeded = true;
	}

	public String getActualContactPhone () {
		return contactPhone;
	}

	public String getContactEmail () {
		if (commitNeeded)
			return "";
		else
			return contactEmail;
	}

	public void setContactEmail (String contactEmail) {
		this.contactEmail = contactEmail;
		commitNeeded = true;
	}

	public String getActualContactEmail () {
		return contactEmail;
	}

	public static Integer IDValue() throws JspException {
		try {
			return currentInstance.getID();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function IDValue()");
		}
	}

	public static String piEmployeeValue() throws JspException {
		try {
			return currentInstance.getPiEmployee();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function piEmployeeValue()");
		}
	}

	public static String restrictiveAgreementValue() throws JspException {
		try {
			return currentInstance.getRestrictiveAgreement();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function restrictiveAgreementValue()");
		}
	}

	public static String contactNameOrTitleValue() throws JspException {
		try {
			return currentInstance.getContactNameOrTitle();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function contactNameOrTitleValue()");
		}
	}

	public static String contactOrganizationValue() throws JspException {
		try {
			return currentInstance.getContactOrganization();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function contactOrganizationValue()");
		}
	}

	public static String contactPhoneValue() throws JspException {
		try {
			return currentInstance.getContactPhone();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function contactPhoneValue()");
		}
	}

	public static String contactEmailValue() throws JspException {
		try {
			return currentInstance.getContactEmail();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function contactEmailValue()");
		}
	}

	private void clearServiceState () {
		ID = 0;
		piEmployee = null;
		restrictiveAgreement = null;
		contactNameOrTitle = null;
		contactOrganization = null;
		contactPhone = null;
		contactEmail = null;
		newRecord = false;
		commitNeeded = false;
		parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	}

}
