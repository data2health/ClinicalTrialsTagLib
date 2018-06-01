package edu.uiowa.slis.ClinicalTrialsTagLib.official;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import edu.uiowa.slis.ClinicalTrialsTagLib.location.Location;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;
import edu.uiowa.slis.ClinicalTrialsTagLib.Sequence;

@SuppressWarnings("serial")
public class Official extends ClinicalTrialsTagLibTagSupport {

	static Official currentInstance = null;
	boolean commitNeeded = false;
	boolean newRecord = false;

	private static final Log log = LogFactory.getLog(Official.class);

	Vector<ClinicalTrialsTagLibTagSupport> parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	int ID = 0;
	int seqnum = 0;
	int locseqnum = 0;
	String firstName = null;
	String middleName = null;
	String lastName = null;
	String degrees = null;
	String role = null;
	String affiliation = null;

	public int doStartTag() throws JspException {
		currentInstance = this;
		try {
			Location theLocation = (Location)findAncestorWithClass(this, Location.class);
			if (theLocation!= null)
				parentEntities.addElement(theLocation);

			if (theLocation == null) {
			} else {
				ID = theLocation.getID();
				seqnum = theLocation.getSeqnum();
			}

			OfficialIterator theOfficialIterator = (OfficialIterator)findAncestorWithClass(this, OfficialIterator.class);

			if (theOfficialIterator != null) {
				ID = theOfficialIterator.getID();
				seqnum = theOfficialIterator.getSeqnum();
				locseqnum = theOfficialIterator.getLocseqnum();
			}

			if (theOfficialIterator == null && theLocation == null && locseqnum == 0) {
				// no locseqnum was provided - the default is to assume that it is a new Official and to generate a new locseqnum
				locseqnum = Sequence.generateID();
				insertEntity();
			} else {
				// an iterator or locseqnum was provided as an attribute - we need to load a Official from the database
				boolean found = false;
				PreparedStatement stmt = getConnection().prepareStatement("select first_name,middle_name,last_name,degrees,role,affiliation from clinical_trials.official where id = ? and seqnum = ? and locseqnum = ?");
				stmt.setInt(1,ID);
				stmt.setInt(2,seqnum);
				stmt.setInt(3,locseqnum);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					if (firstName == null)
						firstName = rs.getString(1);
					if (middleName == null)
						middleName = rs.getString(2);
					if (lastName == null)
						lastName = rs.getString(3);
					if (degrees == null)
						degrees = rs.getString(4);
					if (role == null)
						role = rs.getString(5);
					if (affiliation == null)
						affiliation = rs.getString(6);
					found = true;
				}
				stmt.close();

				if (!found) {
					insertEntity();
				}
			}
		} catch (SQLException e) {
			log.error("JDBC error retrieving locseqnum " + locseqnum, e);
			throw new JspTagException("Error: JDBC error retrieving locseqnum " + locseqnum);
		} finally {
			freeConnection();
		}
		return EVAL_PAGE;
	}

	public int doEndTag() throws JspException {
		currentInstance = null;
		try {
			if (commitNeeded) {
				PreparedStatement stmt = getConnection().prepareStatement("update clinical_trials.official set first_name = ?, middle_name = ?, last_name = ?, degrees = ?, role = ?, affiliation = ? where id = ? and seqnum = ? and locseqnum = ?");
				stmt.setString(1,firstName);
				stmt.setString(2,middleName);
				stmt.setString(3,lastName);
				stmt.setString(4,degrees);
				stmt.setString(5,role);
				stmt.setString(6,affiliation);
				stmt.setInt(7,ID);
				stmt.setInt(8,seqnum);
				stmt.setInt(9,locseqnum);
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
			if (locseqnum == 0) {
				locseqnum = Sequence.generateID();
				log.debug("generating new Official " + locseqnum);
			}

			if (firstName == null)
				firstName = "";
			if (middleName == null)
				middleName = "";
			if (lastName == null)
				lastName = "";
			if (degrees == null)
				degrees = "";
			if (role == null)
				role = "";
			if (affiliation == null)
				affiliation = "";
			PreparedStatement stmt = getConnection().prepareStatement("insert into clinical_trials.official(id,seqnum,locseqnum,first_name,middle_name,last_name,degrees,role,affiliation) values (?,?,?,?,?,?,?,?,?)");
			stmt.setInt(1,ID);
			stmt.setInt(2,seqnum);
			stmt.setInt(3,locseqnum);
			stmt.setString(4,firstName);
			stmt.setString(5,middleName);
			stmt.setString(6,lastName);
			stmt.setString(7,degrees);
			stmt.setString(8,role);
			stmt.setString(9,affiliation);
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

	public int getLocseqnum () {
		return locseqnum;
	}

	public void setLocseqnum (int locseqnum) {
		this.locseqnum = locseqnum;
	}

	public int getActualLocseqnum () {
		return locseqnum;
	}

	public String getFirstName () {
		if (commitNeeded)
			return "";
		else
			return firstName;
	}

	public void setFirstName (String firstName) {
		this.firstName = firstName;
		commitNeeded = true;
	}

	public String getActualFirstName () {
		return firstName;
	}

	public String getMiddleName () {
		if (commitNeeded)
			return "";
		else
			return middleName;
	}

	public void setMiddleName (String middleName) {
		this.middleName = middleName;
		commitNeeded = true;
	}

	public String getActualMiddleName () {
		return middleName;
	}

	public String getLastName () {
		if (commitNeeded)
			return "";
		else
			return lastName;
	}

	public void setLastName (String lastName) {
		this.lastName = lastName;
		commitNeeded = true;
	}

	public String getActualLastName () {
		return lastName;
	}

	public String getDegrees () {
		if (commitNeeded)
			return "";
		else
			return degrees;
	}

	public void setDegrees (String degrees) {
		this.degrees = degrees;
		commitNeeded = true;
	}

	public String getActualDegrees () {
		return degrees;
	}

	public String getRole () {
		if (commitNeeded)
			return "";
		else
			return role;
	}

	public void setRole (String role) {
		this.role = role;
		commitNeeded = true;
	}

	public String getActualRole () {
		return role;
	}

	public String getAffiliation () {
		if (commitNeeded)
			return "";
		else
			return affiliation;
	}

	public void setAffiliation (String affiliation) {
		this.affiliation = affiliation;
		commitNeeded = true;
	}

	public String getActualAffiliation () {
		return affiliation;
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

	public static Integer locseqnumValue() throws JspException {
		try {
			return currentInstance.getLocseqnum();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function locseqnumValue()");
		}
	}

	public static String firstNameValue() throws JspException {
		try {
			return currentInstance.getFirstName();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function firstNameValue()");
		}
	}

	public static String middleNameValue() throws JspException {
		try {
			return currentInstance.getMiddleName();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function middleNameValue()");
		}
	}

	public static String lastNameValue() throws JspException {
		try {
			return currentInstance.getLastName();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function lastNameValue()");
		}
	}

	public static String degreesValue() throws JspException {
		try {
			return currentInstance.getDegrees();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function degreesValue()");
		}
	}

	public static String roleValue() throws JspException {
		try {
			return currentInstance.getRole();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function roleValue()");
		}
	}

	public static String affiliationValue() throws JspException {
		try {
			return currentInstance.getAffiliation();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function affiliationValue()");
		}
	}

	private void clearServiceState () {
		ID = 0;
		seqnum = 0;
		locseqnum = 0;
		firstName = null;
		middleName = null;
		lastName = null;
		degrees = null;
		role = null;
		affiliation = null;
		newRecord = false;
		commitNeeded = false;
		parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	}

}
