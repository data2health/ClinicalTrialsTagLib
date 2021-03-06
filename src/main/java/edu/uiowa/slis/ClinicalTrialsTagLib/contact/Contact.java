package edu.uiowa.slis.ClinicalTrialsTagLib.contact;

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
public class Contact extends ClinicalTrialsTagLibTagSupport {

	static Contact currentInstance = null;
	boolean commitNeeded = false;
	boolean newRecord = false;

	private static final Log log = LogFactory.getLog(Contact.class);

	Vector<ClinicalTrialsTagLibTagSupport> parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	int ID = 0;
	int seqnum = 0;
	int locseqnum = 0;
	String firstName = null;
	String middleName = null;
	String lastName = null;
	String degrees = null;
	String phone = null;
	String phoneExt = null;
	String email = null;

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

			ContactIterator theContactIterator = (ContactIterator)findAncestorWithClass(this, ContactIterator.class);

			if (theContactIterator != null) {
				ID = theContactIterator.getID();
				seqnum = theContactIterator.getSeqnum();
				locseqnum = theContactIterator.getLocseqnum();
			}

			if (theContactIterator == null && theLocation == null && locseqnum == 0) {
				// no locseqnum was provided - the default is to assume that it is a new Contact and to generate a new locseqnum
				locseqnum = Sequence.generateID();
				insertEntity();
			} else {
				// an iterator or locseqnum was provided as an attribute - we need to load a Contact from the database
				boolean found = false;
				PreparedStatement stmt = getConnection().prepareStatement("select first_name,middle_name,last_name,degrees,phone,phone_ext,email from clinical_trials.contact where id = ? and seqnum = ? and locseqnum = ?");
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
					if (phone == null)
						phone = rs.getString(5);
					if (phoneExt == null)
						phoneExt = rs.getString(6);
					if (email == null)
						email = rs.getString(7);
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
				PreparedStatement stmt = getConnection().prepareStatement("update clinical_trials.contact set first_name = ?, middle_name = ?, last_name = ?, degrees = ?, phone = ?, phone_ext = ?, email = ? where id = ? and seqnum = ? and locseqnum = ?");
				stmt.setString(1,firstName);
				stmt.setString(2,middleName);
				stmt.setString(3,lastName);
				stmt.setString(4,degrees);
				stmt.setString(5,phone);
				stmt.setString(6,phoneExt);
				stmt.setString(7,email);
				stmt.setInt(8,ID);
				stmt.setInt(9,seqnum);
				stmt.setInt(10,locseqnum);
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
				log.debug("generating new Contact " + locseqnum);
			}

			if (firstName == null)
				firstName = "";
			if (middleName == null)
				middleName = "";
			if (lastName == null)
				lastName = "";
			if (degrees == null)
				degrees = "";
			if (phone == null)
				phone = "";
			if (phoneExt == null)
				phoneExt = "";
			if (email == null)
				email = "";
			PreparedStatement stmt = getConnection().prepareStatement("insert into clinical_trials.contact(id,seqnum,locseqnum,first_name,middle_name,last_name,degrees,phone,phone_ext,email) values (?,?,?,?,?,?,?,?,?,?)");
			stmt.setInt(1,ID);
			stmt.setInt(2,seqnum);
			stmt.setInt(3,locseqnum);
			stmt.setString(4,firstName);
			stmt.setString(5,middleName);
			stmt.setString(6,lastName);
			stmt.setString(7,degrees);
			stmt.setString(8,phone);
			stmt.setString(9,phoneExt);
			stmt.setString(10,email);
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

	public String getPhone () {
		if (commitNeeded)
			return "";
		else
			return phone;
	}

	public void setPhone (String phone) {
		this.phone = phone;
		commitNeeded = true;
	}

	public String getActualPhone () {
		return phone;
	}

	public String getPhoneExt () {
		if (commitNeeded)
			return "";
		else
			return phoneExt;
	}

	public void setPhoneExt (String phoneExt) {
		this.phoneExt = phoneExt;
		commitNeeded = true;
	}

	public String getActualPhoneExt () {
		return phoneExt;
	}

	public String getEmail () {
		if (commitNeeded)
			return "";
		else
			return email;
	}

	public void setEmail (String email) {
		this.email = email;
		commitNeeded = true;
	}

	public String getActualEmail () {
		return email;
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

	public static String phoneValue() throws JspException {
		try {
			return currentInstance.getPhone();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function phoneValue()");
		}
	}

	public static String phoneExtValue() throws JspException {
		try {
			return currentInstance.getPhoneExt();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function phoneExtValue()");
		}
	}

	public static String emailValue() throws JspException {
		try {
			return currentInstance.getEmail();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function emailValue()");
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
		phone = null;
		phoneExt = null;
		email = null;
		newRecord = false;
		commitNeeded = false;
		parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	}

}
