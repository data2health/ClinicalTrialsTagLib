package edu.uiowa.slis.ClinicalTrialsTagLib.resultsAnalysis;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import edu.uiowa.slis.ClinicalTrialsTagLib.resultsOutcome.ResultsOutcome;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;
import edu.uiowa.slis.ClinicalTrialsTagLib.Sequence;

@SuppressWarnings("serial")
public class ResultsAnalysis extends ClinicalTrialsTagLibTagSupport {

	static ResultsAnalysis currentInstance = null;
	boolean commitNeeded = false;
	boolean newRecord = false;

	private static final Log log = LogFactory.getLog(ResultsAnalysis.class);

	Vector<ClinicalTrialsTagLibTagSupport> parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	int ID = 0;
	int seqnum = 0;
	int anaseqnum = 0;
	String groupsDesc = null;
	String nonInferiority = null;
	String nonInferiorityDesc = null;
	String pValue = null;
	String pValueDesc = null;
	String method = null;
	String methodDesc = null;
	String paramType = null;
	String paramValue = null;
	String ciPercent = null;
	String ciNSides = null;
	String ciLowerLimit = null;
	String ciUpperIlmit = null;
	String estimateDesc = null;
	String dispersionType = null;
	String dispersionValue = null;

	public int doStartTag() throws JspException {
		currentInstance = this;
		try {
			ResultsOutcome theResultsOutcome = (ResultsOutcome)findAncestorWithClass(this, ResultsOutcome.class);
			if (theResultsOutcome!= null)
				parentEntities.addElement(theResultsOutcome);

			if (theResultsOutcome == null) {
			} else {
				ID = theResultsOutcome.getID();
				seqnum = theResultsOutcome.getSeqnum();
			}

			ResultsAnalysisIterator theResultsAnalysisIterator = (ResultsAnalysisIterator)findAncestorWithClass(this, ResultsAnalysisIterator.class);

			if (theResultsAnalysisIterator != null) {
				ID = theResultsAnalysisIterator.getID();
				seqnum = theResultsAnalysisIterator.getSeqnum();
				anaseqnum = theResultsAnalysisIterator.getAnaseqnum();
			}

			if (theResultsAnalysisIterator == null && theResultsOutcome == null && anaseqnum == 0) {
				// no anaseqnum was provided - the default is to assume that it is a new ResultsAnalysis and to generate a new anaseqnum
				anaseqnum = Sequence.generateID();
				insertEntity();
			} else {
				// an iterator or anaseqnum was provided as an attribute - we need to load a ResultsAnalysis from the database
				boolean found = false;
				PreparedStatement stmt = getConnection().prepareStatement("select groups_desc,non_inferiority,non_inferiority_desc,p_value,p_value_desc,method,method_desc,param_type,param_value,ci_percent,ci_n_sides,ci_lower_limit,ci_upper_ilmit,estimate_desc,dispersion_type,dispersion_value from clinical_trials.results_analysis where id = ? and seqnum = ? and anaseqnum = ?");
				stmt.setInt(1,ID);
				stmt.setInt(2,seqnum);
				stmt.setInt(3,anaseqnum);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					if (groupsDesc == null)
						groupsDesc = rs.getString(1);
					if (nonInferiority == null)
						nonInferiority = rs.getString(2);
					if (nonInferiorityDesc == null)
						nonInferiorityDesc = rs.getString(3);
					if (pValue == null)
						pValue = rs.getString(4);
					if (pValueDesc == null)
						pValueDesc = rs.getString(5);
					if (method == null)
						method = rs.getString(6);
					if (methodDesc == null)
						methodDesc = rs.getString(7);
					if (paramType == null)
						paramType = rs.getString(8);
					if (paramValue == null)
						paramValue = rs.getString(9);
					if (ciPercent == null)
						ciPercent = rs.getString(10);
					if (ciNSides == null)
						ciNSides = rs.getString(11);
					if (ciLowerLimit == null)
						ciLowerLimit = rs.getString(12);
					if (ciUpperIlmit == null)
						ciUpperIlmit = rs.getString(13);
					if (estimateDesc == null)
						estimateDesc = rs.getString(14);
					if (dispersionType == null)
						dispersionType = rs.getString(15);
					if (dispersionValue == null)
						dispersionValue = rs.getString(16);
					found = true;
				}
				stmt.close();

				if (!found) {
					insertEntity();
				}
			}
		} catch (SQLException e) {
			log.error("JDBC error retrieving anaseqnum " + anaseqnum, e);
			throw new JspTagException("Error: JDBC error retrieving anaseqnum " + anaseqnum);
		} finally {
			freeConnection();
		}
		return EVAL_PAGE;
	}

	public int doEndTag() throws JspException {
		currentInstance = null;
		try {
			if (commitNeeded) {
				PreparedStatement stmt = getConnection().prepareStatement("update clinical_trials.results_analysis set groups_desc = ?, non_inferiority = ?, non_inferiority_desc = ?, p_value = ?, p_value_desc = ?, method = ?, method_desc = ?, param_type = ?, param_value = ?, ci_percent = ?, ci_n_sides = ?, ci_lower_limit = ?, ci_upper_ilmit = ?, estimate_desc = ?, dispersion_type = ?, dispersion_value = ? where id = ? and seqnum = ? and anaseqnum = ?");
				stmt.setString(1,groupsDesc);
				stmt.setString(2,nonInferiority);
				stmt.setString(3,nonInferiorityDesc);
				stmt.setString(4,pValue);
				stmt.setString(5,pValueDesc);
				stmt.setString(6,method);
				stmt.setString(7,methodDesc);
				stmt.setString(8,paramType);
				stmt.setString(9,paramValue);
				stmt.setString(10,ciPercent);
				stmt.setString(11,ciNSides);
				stmt.setString(12,ciLowerLimit);
				stmt.setString(13,ciUpperIlmit);
				stmt.setString(14,estimateDesc);
				stmt.setString(15,dispersionType);
				stmt.setString(16,dispersionValue);
				stmt.setInt(17,ID);
				stmt.setInt(18,seqnum);
				stmt.setInt(19,anaseqnum);
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
			if (anaseqnum == 0) {
				anaseqnum = Sequence.generateID();
				log.debug("generating new ResultsAnalysis " + anaseqnum);
			}

			if (groupsDesc == null)
				groupsDesc = "";
			if (nonInferiority == null)
				nonInferiority = "";
			if (nonInferiorityDesc == null)
				nonInferiorityDesc = "";
			if (pValue == null)
				pValue = "";
			if (pValueDesc == null)
				pValueDesc = "";
			if (method == null)
				method = "";
			if (methodDesc == null)
				methodDesc = "";
			if (paramType == null)
				paramType = "";
			if (paramValue == null)
				paramValue = "";
			if (ciPercent == null)
				ciPercent = "";
			if (ciNSides == null)
				ciNSides = "";
			if (ciLowerLimit == null)
				ciLowerLimit = "";
			if (ciUpperIlmit == null)
				ciUpperIlmit = "";
			if (estimateDesc == null)
				estimateDesc = "";
			if (dispersionType == null)
				dispersionType = "";
			if (dispersionValue == null)
				dispersionValue = "";
			PreparedStatement stmt = getConnection().prepareStatement("insert into clinical_trials.results_analysis(id,seqnum,anaseqnum,groups_desc,non_inferiority,non_inferiority_desc,p_value,p_value_desc,method,method_desc,param_type,param_value,ci_percent,ci_n_sides,ci_lower_limit,ci_upper_ilmit,estimate_desc,dispersion_type,dispersion_value) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			stmt.setInt(1,ID);
			stmt.setInt(2,seqnum);
			stmt.setInt(3,anaseqnum);
			stmt.setString(4,groupsDesc);
			stmt.setString(5,nonInferiority);
			stmt.setString(6,nonInferiorityDesc);
			stmt.setString(7,pValue);
			stmt.setString(8,pValueDesc);
			stmt.setString(9,method);
			stmt.setString(10,methodDesc);
			stmt.setString(11,paramType);
			stmt.setString(12,paramValue);
			stmt.setString(13,ciPercent);
			stmt.setString(14,ciNSides);
			stmt.setString(15,ciLowerLimit);
			stmt.setString(16,ciUpperIlmit);
			stmt.setString(17,estimateDesc);
			stmt.setString(18,dispersionType);
			stmt.setString(19,dispersionValue);
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

	public int getAnaseqnum () {
		return anaseqnum;
	}

	public void setAnaseqnum (int anaseqnum) {
		this.anaseqnum = anaseqnum;
	}

	public int getActualAnaseqnum () {
		return anaseqnum;
	}

	public String getGroupsDesc () {
		if (commitNeeded)
			return "";
		else
			return groupsDesc;
	}

	public void setGroupsDesc (String groupsDesc) {
		this.groupsDesc = groupsDesc;
		commitNeeded = true;
	}

	public String getActualGroupsDesc () {
		return groupsDesc;
	}

	public String getNonInferiority () {
		if (commitNeeded)
			return "";
		else
			return nonInferiority;
	}

	public void setNonInferiority (String nonInferiority) {
		this.nonInferiority = nonInferiority;
		commitNeeded = true;
	}

	public String getActualNonInferiority () {
		return nonInferiority;
	}

	public String getNonInferiorityDesc () {
		if (commitNeeded)
			return "";
		else
			return nonInferiorityDesc;
	}

	public void setNonInferiorityDesc (String nonInferiorityDesc) {
		this.nonInferiorityDesc = nonInferiorityDesc;
		commitNeeded = true;
	}

	public String getActualNonInferiorityDesc () {
		return nonInferiorityDesc;
	}

	public String getPValue () {
		if (commitNeeded)
			return "";
		else
			return pValue;
	}

	public void setPValue (String pValue) {
		this.pValue = pValue;
		commitNeeded = true;
	}

	public String getActualPValue () {
		return pValue;
	}

	public String getPValueDesc () {
		if (commitNeeded)
			return "";
		else
			return pValueDesc;
	}

	public void setPValueDesc (String pValueDesc) {
		this.pValueDesc = pValueDesc;
		commitNeeded = true;
	}

	public String getActualPValueDesc () {
		return pValueDesc;
	}

	public String getMethod () {
		if (commitNeeded)
			return "";
		else
			return method;
	}

	public void setMethod (String method) {
		this.method = method;
		commitNeeded = true;
	}

	public String getActualMethod () {
		return method;
	}

	public String getMethodDesc () {
		if (commitNeeded)
			return "";
		else
			return methodDesc;
	}

	public void setMethodDesc (String methodDesc) {
		this.methodDesc = methodDesc;
		commitNeeded = true;
	}

	public String getActualMethodDesc () {
		return methodDesc;
	}

	public String getParamType () {
		if (commitNeeded)
			return "";
		else
			return paramType;
	}

	public void setParamType (String paramType) {
		this.paramType = paramType;
		commitNeeded = true;
	}

	public String getActualParamType () {
		return paramType;
	}

	public String getParamValue () {
		if (commitNeeded)
			return "";
		else
			return paramValue;
	}

	public void setParamValue (String paramValue) {
		this.paramValue = paramValue;
		commitNeeded = true;
	}

	public String getActualParamValue () {
		return paramValue;
	}

	public String getCiPercent () {
		if (commitNeeded)
			return "";
		else
			return ciPercent;
	}

	public void setCiPercent (String ciPercent) {
		this.ciPercent = ciPercent;
		commitNeeded = true;
	}

	public String getActualCiPercent () {
		return ciPercent;
	}

	public String getCiNSides () {
		if (commitNeeded)
			return "";
		else
			return ciNSides;
	}

	public void setCiNSides (String ciNSides) {
		this.ciNSides = ciNSides;
		commitNeeded = true;
	}

	public String getActualCiNSides () {
		return ciNSides;
	}

	public String getCiLowerLimit () {
		if (commitNeeded)
			return "";
		else
			return ciLowerLimit;
	}

	public void setCiLowerLimit (String ciLowerLimit) {
		this.ciLowerLimit = ciLowerLimit;
		commitNeeded = true;
	}

	public String getActualCiLowerLimit () {
		return ciLowerLimit;
	}

	public String getCiUpperIlmit () {
		if (commitNeeded)
			return "";
		else
			return ciUpperIlmit;
	}

	public void setCiUpperIlmit (String ciUpperIlmit) {
		this.ciUpperIlmit = ciUpperIlmit;
		commitNeeded = true;
	}

	public String getActualCiUpperIlmit () {
		return ciUpperIlmit;
	}

	public String getEstimateDesc () {
		if (commitNeeded)
			return "";
		else
			return estimateDesc;
	}

	public void setEstimateDesc (String estimateDesc) {
		this.estimateDesc = estimateDesc;
		commitNeeded = true;
	}

	public String getActualEstimateDesc () {
		return estimateDesc;
	}

	public String getDispersionType () {
		if (commitNeeded)
			return "";
		else
			return dispersionType;
	}

	public void setDispersionType (String dispersionType) {
		this.dispersionType = dispersionType;
		commitNeeded = true;
	}

	public String getActualDispersionType () {
		return dispersionType;
	}

	public String getDispersionValue () {
		if (commitNeeded)
			return "";
		else
			return dispersionValue;
	}

	public void setDispersionValue (String dispersionValue) {
		this.dispersionValue = dispersionValue;
		commitNeeded = true;
	}

	public String getActualDispersionValue () {
		return dispersionValue;
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

	public static Integer anaseqnumValue() throws JspException {
		try {
			return currentInstance.getAnaseqnum();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function anaseqnumValue()");
		}
	}

	public static String groupsDescValue() throws JspException {
		try {
			return currentInstance.getGroupsDesc();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function groupsDescValue()");
		}
	}

	public static String nonInferiorityValue() throws JspException {
		try {
			return currentInstance.getNonInferiority();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function nonInferiorityValue()");
		}
	}

	public static String nonInferiorityDescValue() throws JspException {
		try {
			return currentInstance.getNonInferiorityDesc();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function nonInferiorityDescValue()");
		}
	}

	public static String pValueValue() throws JspException {
		try {
			return currentInstance.getPValue();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function pValueValue()");
		}
	}

	public static String pValueDescValue() throws JspException {
		try {
			return currentInstance.getPValueDesc();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function pValueDescValue()");
		}
	}

	public static String methodValue() throws JspException {
		try {
			return currentInstance.getMethod();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function methodValue()");
		}
	}

	public static String methodDescValue() throws JspException {
		try {
			return currentInstance.getMethodDesc();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function methodDescValue()");
		}
	}

	public static String paramTypeValue() throws JspException {
		try {
			return currentInstance.getParamType();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function paramTypeValue()");
		}
	}

	public static String paramValueValue() throws JspException {
		try {
			return currentInstance.getParamValue();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function paramValueValue()");
		}
	}

	public static String ciPercentValue() throws JspException {
		try {
			return currentInstance.getCiPercent();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function ciPercentValue()");
		}
	}

	public static String ciNSidesValue() throws JspException {
		try {
			return currentInstance.getCiNSides();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function ciNSidesValue()");
		}
	}

	public static String ciLowerLimitValue() throws JspException {
		try {
			return currentInstance.getCiLowerLimit();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function ciLowerLimitValue()");
		}
	}

	public static String ciUpperIlmitValue() throws JspException {
		try {
			return currentInstance.getCiUpperIlmit();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function ciUpperIlmitValue()");
		}
	}

	public static String estimateDescValue() throws JspException {
		try {
			return currentInstance.getEstimateDesc();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function estimateDescValue()");
		}
	}

	public static String dispersionTypeValue() throws JspException {
		try {
			return currentInstance.getDispersionType();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function dispersionTypeValue()");
		}
	}

	public static String dispersionValueValue() throws JspException {
		try {
			return currentInstance.getDispersionValue();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function dispersionValueValue()");
		}
	}

	private void clearServiceState () {
		ID = 0;
		seqnum = 0;
		anaseqnum = 0;
		groupsDesc = null;
		nonInferiority = null;
		nonInferiorityDesc = null;
		pValue = null;
		pValueDesc = null;
		method = null;
		methodDesc = null;
		paramType = null;
		paramValue = null;
		ciPercent = null;
		ciNSides = null;
		ciLowerLimit = null;
		ciUpperIlmit = null;
		estimateDesc = null;
		dispersionType = null;
		dispersionValue = null;
		newRecord = false;
		commitNeeded = false;
		parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	}

}
