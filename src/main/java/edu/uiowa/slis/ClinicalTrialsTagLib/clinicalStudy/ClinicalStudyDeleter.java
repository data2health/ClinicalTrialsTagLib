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
import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibBodyTagSupport;

@SuppressWarnings("serial")
public class ClinicalStudyDeleter extends ClinicalTrialsTagLibBodyTagSupport {
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
	Vector<ClinicalTrialsTagLibTagSupport> parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	private static final Log log = LogFactory.getLog(ClinicalStudyDeleter.class);


    ResultSet rs = null;
    String var = null;
    int rsCount = 0;

    public int doStartTag() throws JspException {



        PreparedStatement stat;
        try {
            int webapp_keySeq = 1;
            stat = getConnection().prepareStatement("DELETE from clinical_trials.clinical_study where 1=1"
                                                        + (ID == 0 ? "" : " and id = ? "));
            if (ID != 0) stat.setInt(webapp_keySeq++, ID);
            stat.execute();

			webapp_keySeq = 1;
        } catch (SQLException e) {
            log.error("JDBC error generating ClinicalStudy deleter", e);
            clearServiceState();
            throw new JspTagException("Error: JDBC error generating ClinicalStudy deleter");
        } finally {
            freeConnection();
        }

        return SKIP_BODY;
    }

	public int doEndTag() throws JspException {
		clearServiceState();
		return super.doEndTag();
	}

    private void clearServiceState() {
        ID = 0;
        parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

        this.rs = null;
        this.var = null;
        this.rsCount = 0;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
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
}
