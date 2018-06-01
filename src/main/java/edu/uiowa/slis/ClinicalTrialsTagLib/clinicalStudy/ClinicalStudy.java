package edu.uiowa.slis.ClinicalTrialsTagLib.clinicalStudy;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;
import edu.uiowa.slis.ClinicalTrialsTagLib.Sequence;

@SuppressWarnings("serial")
public class ClinicalStudy extends ClinicalTrialsTagLibTagSupport {

	static ClinicalStudy currentInstance = null;
	boolean commitNeeded = false;
	boolean newRecord = false;

	private static final Log log = LogFactory.getLog(ClinicalStudy.class);

	Vector<ClinicalTrialsTagLibTagSupport> parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	int ID = 0;
	String orgStudyId = null;
	String nctId = null;
	String briefTitle = null;
	String acronym = null;
	String officialTitle = null;
	String source = null;
	String overallStatus = null;
	String whyStopped = null;
	String phase = null;
	Date startDate = null;
	Date endDate = null;
	Date completionDate = null;
	Date primaryCompletionDate = null;
	String studyType = null;
	String studyDesign = null;
	String targetDuration = null;
	String numberOfArms = null;
	String numberOfGroups = null;
	String enrollment = null;
	String condition = null;
	String biospecRetention = null;
	Date verificationDate = null;
	Date lastchangedDate = null;
	Date firstreceivedDate = null;
	Date firstreceivedResultsDate = null;
	String isFdaRegulated = null;
	String isSection801 = null;
	String hasExpandedAccess = null;

	public int doStartTag() throws JspException {
		currentInstance = this;
		try {


			ClinicalStudyIterator theClinicalStudyIterator = (ClinicalStudyIterator)findAncestorWithClass(this, ClinicalStudyIterator.class);

			if (theClinicalStudyIterator != null) {
				ID = theClinicalStudyIterator.getID();
			}

			if (theClinicalStudyIterator == null && ID == 0) {
				// no ID was provided - the default is to assume that it is a new ClinicalStudy and to generate a new ID
				ID = Sequence.generateID();
				insertEntity();
			} else {
				// an iterator or ID was provided as an attribute - we need to load a ClinicalStudy from the database
				boolean found = false;
				PreparedStatement stmt = getConnection().prepareStatement("select org_study_id,nct_id,brief_title,acronym,official_title,source,overall_status,why_stopped,phase,start_date,end_date,completion_date,primary_completion_date,study_type,study_design,target_duration,number_of_arms,number_of_groups,enrollment,condition,biospec_retention,verification_date,lastchanged_date,firstreceived_date,firstreceived_results_date,is_fda_regulated,is_section_801,has_expanded_access from clinical_trials.clinical_study where id = ?");
				stmt.setInt(1,ID);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					if (orgStudyId == null)
						orgStudyId = rs.getString(1);
					if (nctId == null)
						nctId = rs.getString(2);
					if (briefTitle == null)
						briefTitle = rs.getString(3);
					if (acronym == null)
						acronym = rs.getString(4);
					if (officialTitle == null)
						officialTitle = rs.getString(5);
					if (source == null)
						source = rs.getString(6);
					if (overallStatus == null)
						overallStatus = rs.getString(7);
					if (whyStopped == null)
						whyStopped = rs.getString(8);
					if (phase == null)
						phase = rs.getString(9);
					if (startDate == null)
						startDate = rs.getDate(10);
					if (endDate == null)
						endDate = rs.getDate(11);
					if (completionDate == null)
						completionDate = rs.getDate(12);
					if (primaryCompletionDate == null)
						primaryCompletionDate = rs.getDate(13);
					if (studyType == null)
						studyType = rs.getString(14);
					if (studyDesign == null)
						studyDesign = rs.getString(15);
					if (targetDuration == null)
						targetDuration = rs.getString(16);
					if (numberOfArms == null)
						numberOfArms = rs.getString(17);
					if (numberOfGroups == null)
						numberOfGroups = rs.getString(18);
					if (enrollment == null)
						enrollment = rs.getString(19);
					if (condition == null)
						condition = rs.getString(20);
					if (biospecRetention == null)
						biospecRetention = rs.getString(21);
					if (verificationDate == null)
						verificationDate = rs.getDate(22);
					if (lastchangedDate == null)
						lastchangedDate = rs.getDate(23);
					if (firstreceivedDate == null)
						firstreceivedDate = rs.getDate(24);
					if (firstreceivedResultsDate == null)
						firstreceivedResultsDate = rs.getDate(25);
					if (isFdaRegulated == null)
						isFdaRegulated = rs.getString(26);
					if (isSection801 == null)
						isSection801 = rs.getString(27);
					if (hasExpandedAccess == null)
						hasExpandedAccess = rs.getString(28);
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
				PreparedStatement stmt = getConnection().prepareStatement("update clinical_trials.clinical_study set org_study_id = ?, nct_id = ?, brief_title = ?, acronym = ?, official_title = ?, source = ?, overall_status = ?, why_stopped = ?, phase = ?, start_date = ?, end_date = ?, completion_date = ?, primary_completion_date = ?, study_type = ?, study_design = ?, target_duration = ?, number_of_arms = ?, number_of_groups = ?, enrollment = ?, condition = ?, biospec_retention = ?, verification_date = ?, lastchanged_date = ?, firstreceived_date = ?, firstreceived_results_date = ?, is_fda_regulated = ?, is_section_801 = ?, has_expanded_access = ? where id = ?");
				stmt.setString(1,orgStudyId);
				stmt.setString(2,nctId);
				stmt.setString(3,briefTitle);
				stmt.setString(4,acronym);
				stmt.setString(5,officialTitle);
				stmt.setString(6,source);
				stmt.setString(7,overallStatus);
				stmt.setString(8,whyStopped);
				stmt.setString(9,phase);
				stmt.setDate(10,startDate == null ? null : new java.sql.Date(startDate.getTime()));
				stmt.setDate(11,endDate == null ? null : new java.sql.Date(endDate.getTime()));
				stmt.setDate(12,completionDate == null ? null : new java.sql.Date(completionDate.getTime()));
				stmt.setDate(13,primaryCompletionDate == null ? null : new java.sql.Date(primaryCompletionDate.getTime()));
				stmt.setString(14,studyType);
				stmt.setString(15,studyDesign);
				stmt.setString(16,targetDuration);
				stmt.setString(17,numberOfArms);
				stmt.setString(18,numberOfGroups);
				stmt.setString(19,enrollment);
				stmt.setString(20,condition);
				stmt.setString(21,biospecRetention);
				stmt.setDate(22,verificationDate == null ? null : new java.sql.Date(verificationDate.getTime()));
				stmt.setDate(23,lastchangedDate == null ? null : new java.sql.Date(lastchangedDate.getTime()));
				stmt.setDate(24,firstreceivedDate == null ? null : new java.sql.Date(firstreceivedDate.getTime()));
				stmt.setDate(25,firstreceivedResultsDate == null ? null : new java.sql.Date(firstreceivedResultsDate.getTime()));
				stmt.setString(26,isFdaRegulated);
				stmt.setString(27,isSection801);
				stmt.setString(28,hasExpandedAccess);
				stmt.setInt(29,ID);
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
			if (ID == 0) {
				ID = Sequence.generateID();
				log.debug("generating new ClinicalStudy " + ID);
			}

			if (orgStudyId == null)
				orgStudyId = "";
			if (nctId == null)
				nctId = "";
			if (briefTitle == null)
				briefTitle = "";
			if (acronym == null)
				acronym = "";
			if (officialTitle == null)
				officialTitle = "";
			if (source == null)
				source = "";
			if (overallStatus == null)
				overallStatus = "";
			if (whyStopped == null)
				whyStopped = "";
			if (phase == null)
				phase = "";
			if (studyType == null)
				studyType = "";
			if (studyDesign == null)
				studyDesign = "";
			if (targetDuration == null)
				targetDuration = "";
			if (numberOfArms == null)
				numberOfArms = "";
			if (numberOfGroups == null)
				numberOfGroups = "";
			if (enrollment == null)
				enrollment = "";
			if (condition == null)
				condition = "";
			if (biospecRetention == null)
				biospecRetention = "";
			if (isFdaRegulated == null)
				isFdaRegulated = "";
			if (isSection801 == null)
				isSection801 = "";
			if (hasExpandedAccess == null)
				hasExpandedAccess = "";
			PreparedStatement stmt = getConnection().prepareStatement("insert into clinical_trials.clinical_study(id,org_study_id,nct_id,brief_title,acronym,official_title,source,overall_status,why_stopped,phase,start_date,end_date,completion_date,primary_completion_date,study_type,study_design,target_duration,number_of_arms,number_of_groups,enrollment,condition,biospec_retention,verification_date,lastchanged_date,firstreceived_date,firstreceived_results_date,is_fda_regulated,is_section_801,has_expanded_access) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			stmt.setInt(1,ID);
			stmt.setString(2,orgStudyId);
			stmt.setString(3,nctId);
			stmt.setString(4,briefTitle);
			stmt.setString(5,acronym);
			stmt.setString(6,officialTitle);
			stmt.setString(7,source);
			stmt.setString(8,overallStatus);
			stmt.setString(9,whyStopped);
			stmt.setString(10,phase);
			stmt.setDate(11,startDate == null ? null : new java.sql.Date(startDate.getTime()));
			stmt.setDate(12,endDate == null ? null : new java.sql.Date(endDate.getTime()));
			stmt.setDate(13,completionDate == null ? null : new java.sql.Date(completionDate.getTime()));
			stmt.setDate(14,primaryCompletionDate == null ? null : new java.sql.Date(primaryCompletionDate.getTime()));
			stmt.setString(15,studyType);
			stmt.setString(16,studyDesign);
			stmt.setString(17,targetDuration);
			stmt.setString(18,numberOfArms);
			stmt.setString(19,numberOfGroups);
			stmt.setString(20,enrollment);
			stmt.setString(21,condition);
			stmt.setString(22,biospecRetention);
			stmt.setDate(23,verificationDate == null ? null : new java.sql.Date(verificationDate.getTime()));
			stmt.setDate(24,lastchangedDate == null ? null : new java.sql.Date(lastchangedDate.getTime()));
			stmt.setDate(25,firstreceivedDate == null ? null : new java.sql.Date(firstreceivedDate.getTime()));
			stmt.setDate(26,firstreceivedResultsDate == null ? null : new java.sql.Date(firstreceivedResultsDate.getTime()));
			stmt.setString(27,isFdaRegulated);
			stmt.setString(28,isSection801);
			stmt.setString(29,hasExpandedAccess);
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

	public String getOrgStudyId () {
		if (commitNeeded)
			return "";
		else
			return orgStudyId;
	}

	public void setOrgStudyId (String orgStudyId) {
		this.orgStudyId = orgStudyId;
		commitNeeded = true;
	}

	public String getActualOrgStudyId () {
		return orgStudyId;
	}

	public String getNctId () {
		if (commitNeeded)
			return "";
		else
			return nctId;
	}

	public void setNctId (String nctId) {
		this.nctId = nctId;
		commitNeeded = true;
	}

	public String getActualNctId () {
		return nctId;
	}

	public String getBriefTitle () {
		if (commitNeeded)
			return "";
		else
			return briefTitle;
	}

	public void setBriefTitle (String briefTitle) {
		this.briefTitle = briefTitle;
		commitNeeded = true;
	}

	public String getActualBriefTitle () {
		return briefTitle;
	}

	public String getAcronym () {
		if (commitNeeded)
			return "";
		else
			return acronym;
	}

	public void setAcronym (String acronym) {
		this.acronym = acronym;
		commitNeeded = true;
	}

	public String getActualAcronym () {
		return acronym;
	}

	public String getOfficialTitle () {
		if (commitNeeded)
			return "";
		else
			return officialTitle;
	}

	public void setOfficialTitle (String officialTitle) {
		this.officialTitle = officialTitle;
		commitNeeded = true;
	}

	public String getActualOfficialTitle () {
		return officialTitle;
	}

	public String getSource () {
		if (commitNeeded)
			return "";
		else
			return source;
	}

	public void setSource (String source) {
		this.source = source;
		commitNeeded = true;
	}

	public String getActualSource () {
		return source;
	}

	public String getOverallStatus () {
		if (commitNeeded)
			return "";
		else
			return overallStatus;
	}

	public void setOverallStatus (String overallStatus) {
		this.overallStatus = overallStatus;
		commitNeeded = true;
	}

	public String getActualOverallStatus () {
		return overallStatus;
	}

	public String getWhyStopped () {
		if (commitNeeded)
			return "";
		else
			return whyStopped;
	}

	public void setWhyStopped (String whyStopped) {
		this.whyStopped = whyStopped;
		commitNeeded = true;
	}

	public String getActualWhyStopped () {
		return whyStopped;
	}

	public String getPhase () {
		if (commitNeeded)
			return "";
		else
			return phase;
	}

	public void setPhase (String phase) {
		this.phase = phase;
		commitNeeded = true;
	}

	public String getActualPhase () {
		return phase;
	}

	public Date getStartDate () {
		return startDate;
	}

	public void setStartDate (Date startDate) {
		this.startDate = startDate;
		commitNeeded = true;
	}

	public Date getActualStartDate () {
		return startDate;
	}

	public void setStartDateToNow ( ) {
		this.startDate = new java.util.Date();
		commitNeeded = true;
	}

	public Date getEndDate () {
		return endDate;
	}

	public void setEndDate (Date endDate) {
		this.endDate = endDate;
		commitNeeded = true;
	}

	public Date getActualEndDate () {
		return endDate;
	}

	public void setEndDateToNow ( ) {
		this.endDate = new java.util.Date();
		commitNeeded = true;
	}

	public Date getCompletionDate () {
		return completionDate;
	}

	public void setCompletionDate (Date completionDate) {
		this.completionDate = completionDate;
		commitNeeded = true;
	}

	public Date getActualCompletionDate () {
		return completionDate;
	}

	public void setCompletionDateToNow ( ) {
		this.completionDate = new java.util.Date();
		commitNeeded = true;
	}

	public Date getPrimaryCompletionDate () {
		return primaryCompletionDate;
	}

	public void setPrimaryCompletionDate (Date primaryCompletionDate) {
		this.primaryCompletionDate = primaryCompletionDate;
		commitNeeded = true;
	}

	public Date getActualPrimaryCompletionDate () {
		return primaryCompletionDate;
	}

	public void setPrimaryCompletionDateToNow ( ) {
		this.primaryCompletionDate = new java.util.Date();
		commitNeeded = true;
	}

	public String getStudyType () {
		if (commitNeeded)
			return "";
		else
			return studyType;
	}

	public void setStudyType (String studyType) {
		this.studyType = studyType;
		commitNeeded = true;
	}

	public String getActualStudyType () {
		return studyType;
	}

	public String getStudyDesign () {
		if (commitNeeded)
			return "";
		else
			return studyDesign;
	}

	public void setStudyDesign (String studyDesign) {
		this.studyDesign = studyDesign;
		commitNeeded = true;
	}

	public String getActualStudyDesign () {
		return studyDesign;
	}

	public String getTargetDuration () {
		if (commitNeeded)
			return "";
		else
			return targetDuration;
	}

	public void setTargetDuration (String targetDuration) {
		this.targetDuration = targetDuration;
		commitNeeded = true;
	}

	public String getActualTargetDuration () {
		return targetDuration;
	}

	public String getNumberOfArms () {
		if (commitNeeded)
			return "";
		else
			return numberOfArms;
	}

	public void setNumberOfArms (String numberOfArms) {
		this.numberOfArms = numberOfArms;
		commitNeeded = true;
	}

	public String getActualNumberOfArms () {
		return numberOfArms;
	}

	public String getNumberOfGroups () {
		if (commitNeeded)
			return "";
		else
			return numberOfGroups;
	}

	public void setNumberOfGroups (String numberOfGroups) {
		this.numberOfGroups = numberOfGroups;
		commitNeeded = true;
	}

	public String getActualNumberOfGroups () {
		return numberOfGroups;
	}

	public String getEnrollment () {
		if (commitNeeded)
			return "";
		else
			return enrollment;
	}

	public void setEnrollment (String enrollment) {
		this.enrollment = enrollment;
		commitNeeded = true;
	}

	public String getActualEnrollment () {
		return enrollment;
	}

	public String getCondition () {
		if (commitNeeded)
			return "";
		else
			return condition;
	}

	public void setCondition (String condition) {
		this.condition = condition;
		commitNeeded = true;
	}

	public String getActualCondition () {
		return condition;
	}

	public String getBiospecRetention () {
		if (commitNeeded)
			return "";
		else
			return biospecRetention;
	}

	public void setBiospecRetention (String biospecRetention) {
		this.biospecRetention = biospecRetention;
		commitNeeded = true;
	}

	public String getActualBiospecRetention () {
		return biospecRetention;
	}

	public Date getVerificationDate () {
		return verificationDate;
	}

	public void setVerificationDate (Date verificationDate) {
		this.verificationDate = verificationDate;
		commitNeeded = true;
	}

	public Date getActualVerificationDate () {
		return verificationDate;
	}

	public void setVerificationDateToNow ( ) {
		this.verificationDate = new java.util.Date();
		commitNeeded = true;
	}

	public Date getLastchangedDate () {
		return lastchangedDate;
	}

	public void setLastchangedDate (Date lastchangedDate) {
		this.lastchangedDate = lastchangedDate;
		commitNeeded = true;
	}

	public Date getActualLastchangedDate () {
		return lastchangedDate;
	}

	public void setLastchangedDateToNow ( ) {
		this.lastchangedDate = new java.util.Date();
		commitNeeded = true;
	}

	public Date getFirstreceivedDate () {
		return firstreceivedDate;
	}

	public void setFirstreceivedDate (Date firstreceivedDate) {
		this.firstreceivedDate = firstreceivedDate;
		commitNeeded = true;
	}

	public Date getActualFirstreceivedDate () {
		return firstreceivedDate;
	}

	public void setFirstreceivedDateToNow ( ) {
		this.firstreceivedDate = new java.util.Date();
		commitNeeded = true;
	}

	public Date getFirstreceivedResultsDate () {
		return firstreceivedResultsDate;
	}

	public void setFirstreceivedResultsDate (Date firstreceivedResultsDate) {
		this.firstreceivedResultsDate = firstreceivedResultsDate;
		commitNeeded = true;
	}

	public Date getActualFirstreceivedResultsDate () {
		return firstreceivedResultsDate;
	}

	public void setFirstreceivedResultsDateToNow ( ) {
		this.firstreceivedResultsDate = new java.util.Date();
		commitNeeded = true;
	}

	public String getIsFdaRegulated () {
		if (commitNeeded)
			return "";
		else
			return isFdaRegulated;
	}

	public void setIsFdaRegulated (String isFdaRegulated) {
		this.isFdaRegulated = isFdaRegulated;
		commitNeeded = true;
	}

	public String getActualIsFdaRegulated () {
		return isFdaRegulated;
	}

	public String getIsSection801 () {
		if (commitNeeded)
			return "";
		else
			return isSection801;
	}

	public void setIsSection801 (String isSection801) {
		this.isSection801 = isSection801;
		commitNeeded = true;
	}

	public String getActualIsSection801 () {
		return isSection801;
	}

	public String getHasExpandedAccess () {
		if (commitNeeded)
			return "";
		else
			return hasExpandedAccess;
	}

	public void setHasExpandedAccess (String hasExpandedAccess) {
		this.hasExpandedAccess = hasExpandedAccess;
		commitNeeded = true;
	}

	public String getActualHasExpandedAccess () {
		return hasExpandedAccess;
	}

	public static Integer IDValue() throws JspException {
		try {
			return currentInstance.getID();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function IDValue()");
		}
	}

	public static String orgStudyIdValue() throws JspException {
		try {
			return currentInstance.getOrgStudyId();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function orgStudyIdValue()");
		}
	}

	public static String nctIdValue() throws JspException {
		try {
			return currentInstance.getNctId();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function nctIdValue()");
		}
	}

	public static String briefTitleValue() throws JspException {
		try {
			return currentInstance.getBriefTitle();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function briefTitleValue()");
		}
	}

	public static String acronymValue() throws JspException {
		try {
			return currentInstance.getAcronym();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function acronymValue()");
		}
	}

	public static String officialTitleValue() throws JspException {
		try {
			return currentInstance.getOfficialTitle();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function officialTitleValue()");
		}
	}

	public static String sourceValue() throws JspException {
		try {
			return currentInstance.getSource();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function sourceValue()");
		}
	}

	public static String overallStatusValue() throws JspException {
		try {
			return currentInstance.getOverallStatus();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function overallStatusValue()");
		}
	}

	public static String whyStoppedValue() throws JspException {
		try {
			return currentInstance.getWhyStopped();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function whyStoppedValue()");
		}
	}

	public static String phaseValue() throws JspException {
		try {
			return currentInstance.getPhase();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function phaseValue()");
		}
	}

	public static Date startDateValue() throws JspException {
		try {
			return currentInstance.getStartDate();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function startDateValue()");
		}
	}

	public static Date endDateValue() throws JspException {
		try {
			return currentInstance.getEndDate();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function endDateValue()");
		}
	}

	public static Date completionDateValue() throws JspException {
		try {
			return currentInstance.getCompletionDate();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function completionDateValue()");
		}
	}

	public static Date primaryCompletionDateValue() throws JspException {
		try {
			return currentInstance.getPrimaryCompletionDate();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function primaryCompletionDateValue()");
		}
	}

	public static String studyTypeValue() throws JspException {
		try {
			return currentInstance.getStudyType();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function studyTypeValue()");
		}
	}

	public static String studyDesignValue() throws JspException {
		try {
			return currentInstance.getStudyDesign();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function studyDesignValue()");
		}
	}

	public static String targetDurationValue() throws JspException {
		try {
			return currentInstance.getTargetDuration();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function targetDurationValue()");
		}
	}

	public static String numberOfArmsValue() throws JspException {
		try {
			return currentInstance.getNumberOfArms();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function numberOfArmsValue()");
		}
	}

	public static String numberOfGroupsValue() throws JspException {
		try {
			return currentInstance.getNumberOfGroups();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function numberOfGroupsValue()");
		}
	}

	public static String enrollmentValue() throws JspException {
		try {
			return currentInstance.getEnrollment();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function enrollmentValue()");
		}
	}

	public static String conditionValue() throws JspException {
		try {
			return currentInstance.getCondition();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function conditionValue()");
		}
	}

	public static String biospecRetentionValue() throws JspException {
		try {
			return currentInstance.getBiospecRetention();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function biospecRetentionValue()");
		}
	}

	public static Date verificationDateValue() throws JspException {
		try {
			return currentInstance.getVerificationDate();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function verificationDateValue()");
		}
	}

	public static Date lastchangedDateValue() throws JspException {
		try {
			return currentInstance.getLastchangedDate();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function lastchangedDateValue()");
		}
	}

	public static Date firstreceivedDateValue() throws JspException {
		try {
			return currentInstance.getFirstreceivedDate();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function firstreceivedDateValue()");
		}
	}

	public static Date firstreceivedResultsDateValue() throws JspException {
		try {
			return currentInstance.getFirstreceivedResultsDate();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function firstreceivedResultsDateValue()");
		}
	}

	public static String isFdaRegulatedValue() throws JspException {
		try {
			return currentInstance.getIsFdaRegulated();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function isFdaRegulatedValue()");
		}
	}

	public static String isSection801Value() throws JspException {
		try {
			return currentInstance.getIsSection801();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function isSection801Value()");
		}
	}

	public static String hasExpandedAccessValue() throws JspException {
		try {
			return currentInstance.getHasExpandedAccess();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function hasExpandedAccessValue()");
		}
	}

	private void clearServiceState () {
		ID = 0;
		orgStudyId = null;
		nctId = null;
		briefTitle = null;
		acronym = null;
		officialTitle = null;
		source = null;
		overallStatus = null;
		whyStopped = null;
		phase = null;
		startDate = null;
		endDate = null;
		completionDate = null;
		primaryCompletionDate = null;
		studyType = null;
		studyDesign = null;
		targetDuration = null;
		numberOfArms = null;
		numberOfGroups = null;
		enrollment = null;
		condition = null;
		biospecRetention = null;
		verificationDate = null;
		lastchangedDate = null;
		firstreceivedDate = null;
		firstreceivedResultsDate = null;
		isFdaRegulated = null;
		isSection801 = null;
		hasExpandedAccess = null;
		newRecord = false;
		commitNeeded = false;
		parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	}

}
