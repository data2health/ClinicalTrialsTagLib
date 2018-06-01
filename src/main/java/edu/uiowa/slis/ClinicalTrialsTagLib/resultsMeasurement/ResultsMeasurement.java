package edu.uiowa.slis.ClinicalTrialsTagLib.resultsMeasurement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import edu.uiowa.slis.ClinicalTrialsTagLib.resultsCategory.ResultsCategory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;
import edu.uiowa.slis.ClinicalTrialsTagLib.Sequence;

@SuppressWarnings("serial")
public class ResultsMeasurement extends ClinicalTrialsTagLibTagSupport {

	static ResultsMeasurement currentInstance = null;
	boolean commitNeeded = false;
	boolean newRecord = false;

	private static final Log log = LogFactory.getLog(ResultsMeasurement.class);

	Vector<ClinicalTrialsTagLibTagSupport> parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	int ID = 0;
	int seqnum = 0;
	int measeqnum = 0;
	int catseqnum = 0;
	String groupId = null;
	String value = null;
	String spread = null;
	String lowerLimit = null;
	String upperLimit = null;

	public int doStartTag() throws JspException {
		currentInstance = this;
		try {
			ResultsCategory theResultsCategory = (ResultsCategory)findAncestorWithClass(this, ResultsCategory.class);
			if (theResultsCategory!= null)
				parentEntities.addElement(theResultsCategory);

			if (theResultsCategory == null) {
			} else {
				ID = theResultsCategory.getID();
				seqnum = theResultsCategory.getSeqnum();
				measeqnum = theResultsCategory.getMeaseqnum();
			}

			ResultsMeasurementIterator theResultsMeasurementIterator = (ResultsMeasurementIterator)findAncestorWithClass(this, ResultsMeasurementIterator.class);

			if (theResultsMeasurementIterator != null) {
				ID = theResultsMeasurementIterator.getID();
				seqnum = theResultsMeasurementIterator.getSeqnum();
				measeqnum = theResultsMeasurementIterator.getMeaseqnum();
				groupId = theResultsMeasurementIterator.getGroupId();
				catseqnum = theResultsMeasurementIterator.getCatseqnum();
			}

			if (theResultsMeasurementIterator == null && theResultsCategory == null && groupId == null) {
				// no groupId was provided - the default is to assume that it is a new ResultsMeasurement and to generate a new groupId
				groupId = null;
				insertEntity();
			} else {
				// an iterator or groupId was provided as an attribute - we need to load a ResultsMeasurement from the database
				boolean found = false;
				PreparedStatement stmt = getConnection().prepareStatement("select value,spread,lower_limit,upper_limit from clinical_trials.results_measurement where id = ? and seqnum = ? and measeqnum = ? and catseqnum = ? and group_id = ?");
				stmt.setInt(1,ID);
				stmt.setInt(2,seqnum);
				stmt.setInt(3,measeqnum);
				stmt.setInt(4,catseqnum);
				stmt.setString(5,groupId);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					if (value == null)
						value = rs.getString(1);
					if (spread == null)
						spread = rs.getString(2);
					if (lowerLimit == null)
						lowerLimit = rs.getString(3);
					if (upperLimit == null)
						upperLimit = rs.getString(4);
					found = true;
				}
				stmt.close();

				if (!found) {
					insertEntity();
				}
			}
		} catch (SQLException e) {
			log.error("JDBC error retrieving groupId " + groupId, e);
			throw new JspTagException("Error: JDBC error retrieving groupId " + groupId);
		} finally {
			freeConnection();
		}
		return EVAL_PAGE;
	}

	public int doEndTag() throws JspException {
		currentInstance = null;
		try {
			if (commitNeeded) {
				PreparedStatement stmt = getConnection().prepareStatement("update clinical_trials.results_measurement set value = ?, spread = ?, lower_limit = ?, upper_limit = ? where id = ? and seqnum = ? and measeqnum = ? and catseqnum = ? and group_id = ?");
				stmt.setString(1,value);
				stmt.setString(2,spread);
				stmt.setString(3,lowerLimit);
				stmt.setString(4,upperLimit);
				stmt.setInt(5,ID);
				stmt.setInt(6,seqnum);
				stmt.setInt(7,measeqnum);
				stmt.setInt(8,catseqnum);
				stmt.setString(9,groupId);
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
			if (catseqnum == 0) {
				catseqnum = Sequence.generateID();
				log.debug("generating new ResultsMeasurement " + catseqnum);
			}

			if (value == null)
				value = "";
			if (spread == null)
				spread = "";
			if (lowerLimit == null)
				lowerLimit = "";
			if (upperLimit == null)
				upperLimit = "";
			PreparedStatement stmt = getConnection().prepareStatement("insert into clinical_trials.results_measurement(id,seqnum,measeqnum,catseqnum,group_id,value,spread,lower_limit,upper_limit) values (?,?,?,?,?,?,?,?,?)");
			stmt.setInt(1,ID);
			stmt.setInt(2,seqnum);
			stmt.setInt(3,measeqnum);
			stmt.setInt(4,catseqnum);
			stmt.setString(5,groupId);
			stmt.setString(6,value);
			stmt.setString(7,spread);
			stmt.setString(8,lowerLimit);
			stmt.setString(9,upperLimit);
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

	public int getMeaseqnum () {
		return measeqnum;
	}

	public void setMeaseqnum (int measeqnum) {
		this.measeqnum = measeqnum;
	}

	public int getActualMeaseqnum () {
		return measeqnum;
	}

	public int getCatseqnum () {
		return catseqnum;
	}

	public void setCatseqnum (int catseqnum) {
		this.catseqnum = catseqnum;
	}

	public int getActualCatseqnum () {
		return catseqnum;
	}

	public String getGroupId () {
		if (commitNeeded)
			return "";
		else
			return groupId;
	}

	public void setGroupId (String groupId) {
		this.groupId = groupId;
	}

	public String getActualGroupId () {
		return groupId;
	}

	public String getValue () {
		if (commitNeeded)
			return "";
		else
			return value;
	}

	public void setValue (String value) {
		this.value = value;
		commitNeeded = true;
	}

	public String getActualValue () {
		return value;
	}

	public String getSpread () {
		if (commitNeeded)
			return "";
		else
			return spread;
	}

	public void setSpread (String spread) {
		this.spread = spread;
		commitNeeded = true;
	}

	public String getActualSpread () {
		return spread;
	}

	public String getLowerLimit () {
		if (commitNeeded)
			return "";
		else
			return lowerLimit;
	}

	public void setLowerLimit (String lowerLimit) {
		this.lowerLimit = lowerLimit;
		commitNeeded = true;
	}

	public String getActualLowerLimit () {
		return lowerLimit;
	}

	public String getUpperLimit () {
		if (commitNeeded)
			return "";
		else
			return upperLimit;
	}

	public void setUpperLimit (String upperLimit) {
		this.upperLimit = upperLimit;
		commitNeeded = true;
	}

	public String getActualUpperLimit () {
		return upperLimit;
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

	public static Integer measeqnumValue() throws JspException {
		try {
			return currentInstance.getMeaseqnum();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function measeqnumValue()");
		}
	}

	public static Integer catseqnumValue() throws JspException {
		try {
			return currentInstance.getCatseqnum();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function catseqnumValue()");
		}
	}

	public static String groupIdValue() throws JspException {
		try {
			return currentInstance.getGroupId();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function groupIdValue()");
		}
	}

	public static String valueValue() throws JspException {
		try {
			return currentInstance.getValue();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function valueValue()");
		}
	}

	public static String spreadValue() throws JspException {
		try {
			return currentInstance.getSpread();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function spreadValue()");
		}
	}

	public static String lowerLimitValue() throws JspException {
		try {
			return currentInstance.getLowerLimit();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function lowerLimitValue()");
		}
	}

	public static String upperLimitValue() throws JspException {
		try {
			return currentInstance.getUpperLimit();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function upperLimitValue()");
		}
	}

	private void clearServiceState () {
		ID = 0;
		seqnum = 0;
		measeqnum = 0;
		catseqnum = 0;
		groupId = null;
		value = null;
		spread = null;
		lowerLimit = null;
		upperLimit = null;
		newRecord = false;
		commitNeeded = false;
		parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	}

}
