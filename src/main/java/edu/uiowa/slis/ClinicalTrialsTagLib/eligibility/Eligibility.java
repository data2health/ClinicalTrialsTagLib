package edu.uiowa.slis.ClinicalTrialsTagLib.eligibility;

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
public class Eligibility extends ClinicalTrialsTagLibTagSupport {

	static Eligibility currentInstance = null;
	boolean commitNeeded = false;
	boolean newRecord = false;

	private static final Log log = LogFactory.getLog(Eligibility.class);

	Vector<ClinicalTrialsTagLibTagSupport> parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	int ID = 0;
	int seqnum = 0;
	String samplingMethod = null;
	String gender = null;
	String minimumAge = null;
	String maximumAge = null;
	String healthyVolunteers = null;

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

			EligibilityIterator theEligibilityIterator = (EligibilityIterator)findAncestorWithClass(this, EligibilityIterator.class);

			if (theEligibilityIterator != null) {
				ID = theEligibilityIterator.getID();
				seqnum = theEligibilityIterator.getSeqnum();
			}

			if (theEligibilityIterator == null && theClinicalStudy == null && seqnum == 0) {
				// no seqnum was provided - the default is to assume that it is a new Eligibility and to generate a new seqnum
				seqnum = Sequence.generateID();
				insertEntity();
			} else {
				// an iterator or seqnum was provided as an attribute - we need to load a Eligibility from the database
				boolean found = false;
				PreparedStatement stmt = getConnection().prepareStatement("select sampling_method,gender,minimum_age,maximum_age,healthy_volunteers from clinical_trials.eligibility where id = ? and seqnum = ?");
				stmt.setInt(1,ID);
				stmt.setInt(2,seqnum);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					if (samplingMethod == null)
						samplingMethod = rs.getString(1);
					if (gender == null)
						gender = rs.getString(2);
					if (minimumAge == null)
						minimumAge = rs.getString(3);
					if (maximumAge == null)
						maximumAge = rs.getString(4);
					if (healthyVolunteers == null)
						healthyVolunteers = rs.getString(5);
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
				PreparedStatement stmt = getConnection().prepareStatement("update clinical_trials.eligibility set sampling_method = ?, gender = ?, minimum_age = ?, maximum_age = ?, healthy_volunteers = ? where id = ? and seqnum = ?");
				stmt.setString(1,samplingMethod);
				stmt.setString(2,gender);
				stmt.setString(3,minimumAge);
				stmt.setString(4,maximumAge);
				stmt.setString(5,healthyVolunteers);
				stmt.setInt(6,ID);
				stmt.setInt(7,seqnum);
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
				log.debug("generating new Eligibility " + seqnum);
			}

			if (samplingMethod == null)
				samplingMethod = "";
			if (gender == null)
				gender = "";
			if (minimumAge == null)
				minimumAge = "";
			if (maximumAge == null)
				maximumAge = "";
			if (healthyVolunteers == null)
				healthyVolunteers = "";
			PreparedStatement stmt = getConnection().prepareStatement("insert into clinical_trials.eligibility(id,seqnum,sampling_method,gender,minimum_age,maximum_age,healthy_volunteers) values (?,?,?,?,?,?,?)");
			stmt.setInt(1,ID);
			stmt.setInt(2,seqnum);
			stmt.setString(3,samplingMethod);
			stmt.setString(4,gender);
			stmt.setString(5,minimumAge);
			stmt.setString(6,maximumAge);
			stmt.setString(7,healthyVolunteers);
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

	public String getSamplingMethod () {
		if (commitNeeded)
			return "";
		else
			return samplingMethod;
	}

	public void setSamplingMethod (String samplingMethod) {
		this.samplingMethod = samplingMethod;
		commitNeeded = true;
	}

	public String getActualSamplingMethod () {
		return samplingMethod;
	}

	public String getGender () {
		if (commitNeeded)
			return "";
		else
			return gender;
	}

	public void setGender (String gender) {
		this.gender = gender;
		commitNeeded = true;
	}

	public String getActualGender () {
		return gender;
	}

	public String getMinimumAge () {
		if (commitNeeded)
			return "";
		else
			return minimumAge;
	}

	public void setMinimumAge (String minimumAge) {
		this.minimumAge = minimumAge;
		commitNeeded = true;
	}

	public String getActualMinimumAge () {
		return minimumAge;
	}

	public String getMaximumAge () {
		if (commitNeeded)
			return "";
		else
			return maximumAge;
	}

	public void setMaximumAge (String maximumAge) {
		this.maximumAge = maximumAge;
		commitNeeded = true;
	}

	public String getActualMaximumAge () {
		return maximumAge;
	}

	public String getHealthyVolunteers () {
		if (commitNeeded)
			return "";
		else
			return healthyVolunteers;
	}

	public void setHealthyVolunteers (String healthyVolunteers) {
		this.healthyVolunteers = healthyVolunteers;
		commitNeeded = true;
	}

	public String getActualHealthyVolunteers () {
		return healthyVolunteers;
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

	public static String samplingMethodValue() throws JspException {
		try {
			return currentInstance.getSamplingMethod();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function samplingMethodValue()");
		}
	}

	public static String genderValue() throws JspException {
		try {
			return currentInstance.getGender();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function genderValue()");
		}
	}

	public static String minimumAgeValue() throws JspException {
		try {
			return currentInstance.getMinimumAge();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function minimumAgeValue()");
		}
	}

	public static String maximumAgeValue() throws JspException {
		try {
			return currentInstance.getMaximumAge();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function maximumAgeValue()");
		}
	}

	public static String healthyVolunteersValue() throws JspException {
		try {
			return currentInstance.getHealthyVolunteers();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function healthyVolunteersValue()");
		}
	}

	private void clearServiceState () {
		ID = 0;
		seqnum = 0;
		samplingMethod = null;
		gender = null;
		minimumAge = null;
		maximumAge = null;
		healthyVolunteers = null;
		newRecord = false;
		commitNeeded = false;
		parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	}

}
