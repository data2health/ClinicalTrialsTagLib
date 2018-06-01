package edu.uiowa.slis.ClinicalTrialsTagLib.location;

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
public class Location extends ClinicalTrialsTagLibTagSupport {

	static Location currentInstance = null;
	boolean commitNeeded = false;
	boolean newRecord = false;

	private static final Log log = LogFactory.getLog(Location.class);

	Vector<ClinicalTrialsTagLibTagSupport> parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	int ID = 0;
	int seqnum = 0;
	String name = null;
	String city = null;
	String state = null;
	String zip = null;
	String country = null;
	String status = null;

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

			LocationIterator theLocationIterator = (LocationIterator)findAncestorWithClass(this, LocationIterator.class);

			if (theLocationIterator != null) {
				ID = theLocationIterator.getID();
				seqnum = theLocationIterator.getSeqnum();
			}

			if (theLocationIterator == null && theClinicalStudy == null && seqnum == 0) {
				// no seqnum was provided - the default is to assume that it is a new Location and to generate a new seqnum
				seqnum = Sequence.generateID();
				insertEntity();
			} else {
				// an iterator or seqnum was provided as an attribute - we need to load a Location from the database
				boolean found = false;
				PreparedStatement stmt = getConnection().prepareStatement("select name,city,state,zip,country,status from clinical_trials.location where id = ? and seqnum = ?");
				stmt.setInt(1,ID);
				stmt.setInt(2,seqnum);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					if (name == null)
						name = rs.getString(1);
					if (city == null)
						city = rs.getString(2);
					if (state == null)
						state = rs.getString(3);
					if (zip == null)
						zip = rs.getString(4);
					if (country == null)
						country = rs.getString(5);
					if (status == null)
						status = rs.getString(6);
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
				PreparedStatement stmt = getConnection().prepareStatement("update clinical_trials.location set name = ?, city = ?, state = ?, zip = ?, country = ?, status = ? where id = ? and seqnum = ?");
				stmt.setString(1,name);
				stmt.setString(2,city);
				stmt.setString(3,state);
				stmt.setString(4,zip);
				stmt.setString(5,country);
				stmt.setString(6,status);
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
				log.debug("generating new Location " + seqnum);
			}

			if (name == null)
				name = "";
			if (city == null)
				city = "";
			if (state == null)
				state = "";
			if (zip == null)
				zip = "";
			if (country == null)
				country = "";
			if (status == null)
				status = "";
			PreparedStatement stmt = getConnection().prepareStatement("insert into clinical_trials.location(id,seqnum,name,city,state,zip,country,status) values (?,?,?,?,?,?,?,?)");
			stmt.setInt(1,ID);
			stmt.setInt(2,seqnum);
			stmt.setString(3,name);
			stmt.setString(4,city);
			stmt.setString(5,state);
			stmt.setString(6,zip);
			stmt.setString(7,country);
			stmt.setString(8,status);
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

	public String getName () {
		if (commitNeeded)
			return "";
		else
			return name;
	}

	public void setName (String name) {
		this.name = name;
		commitNeeded = true;
	}

	public String getActualName () {
		return name;
	}

	public String getCity () {
		if (commitNeeded)
			return "";
		else
			return city;
	}

	public void setCity (String city) {
		this.city = city;
		commitNeeded = true;
	}

	public String getActualCity () {
		return city;
	}

	public String getState () {
		if (commitNeeded)
			return "";
		else
			return state;
	}

	public void setState (String state) {
		this.state = state;
		commitNeeded = true;
	}

	public String getActualState () {
		return state;
	}

	public String getZip () {
		if (commitNeeded)
			return "";
		else
			return zip;
	}

	public void setZip (String zip) {
		this.zip = zip;
		commitNeeded = true;
	}

	public String getActualZip () {
		return zip;
	}

	public String getCountry () {
		if (commitNeeded)
			return "";
		else
			return country;
	}

	public void setCountry (String country) {
		this.country = country;
		commitNeeded = true;
	}

	public String getActualCountry () {
		return country;
	}

	public String getStatus () {
		if (commitNeeded)
			return "";
		else
			return status;
	}

	public void setStatus (String status) {
		this.status = status;
		commitNeeded = true;
	}

	public String getActualStatus () {
		return status;
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

	public static String nameValue() throws JspException {
		try {
			return currentInstance.getName();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function nameValue()");
		}
	}

	public static String cityValue() throws JspException {
		try {
			return currentInstance.getCity();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function cityValue()");
		}
	}

	public static String stateValue() throws JspException {
		try {
			return currentInstance.getState();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function stateValue()");
		}
	}

	public static String zipValue() throws JspException {
		try {
			return currentInstance.getZip();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function zipValue()");
		}
	}

	public static String countryValue() throws JspException {
		try {
			return currentInstance.getCountry();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function countryValue()");
		}
	}

	public static String statusValue() throws JspException {
		try {
			return currentInstance.getStatus();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function statusValue()");
		}
	}

	private void clearServiceState () {
		ID = 0;
		seqnum = 0;
		name = null;
		city = null;
		state = null;
		zip = null;
		country = null;
		status = null;
		newRecord = false;
		commitNeeded = false;
		parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	}

}
