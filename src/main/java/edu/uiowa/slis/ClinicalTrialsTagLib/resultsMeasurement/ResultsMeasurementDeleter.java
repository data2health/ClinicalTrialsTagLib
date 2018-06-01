package edu.uiowa.slis.ClinicalTrialsTagLib.resultsMeasurement;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;
import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibBodyTagSupport;
import edu.uiowa.slis.ClinicalTrialsTagLib.resultsCategory.ResultsCategory;

@SuppressWarnings("serial")
public class ResultsMeasurementDeleter extends ClinicalTrialsTagLibBodyTagSupport {
    int ID = 0;
    int seqnum = 0;
    int measeqnum = 0;
    String groupId = null;
    int catseqnum = 0;
    String value = null;
    String spread = null;
    String lowerLimit = null;
    String upperLimit = null;
	Vector<ClinicalTrialsTagLibTagSupport> parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	private static final Log log = LogFactory.getLog(ResultsMeasurementDeleter.class);


    ResultSet rs = null;
    String var = null;
    int rsCount = 0;

    public int doStartTag() throws JspException {
		ResultsCategory theResultsCategory = (ResultsCategory)findAncestorWithClass(this, ResultsCategory.class);
		if (theResultsCategory!= null)
			parentEntities.addElement(theResultsCategory);

		if (theResultsCategory == null) {
		} else {
			ID = theResultsCategory.getID();
			seqnum = theResultsCategory.getSeqnum();
			measeqnum = theResultsCategory.getMeaseqnum();
		}


        PreparedStatement stat;
        try {
            int webapp_keySeq = 1;
            stat = getConnection().prepareStatement("DELETE from clinical_trials.results_measurement where 1=1"
                                                        + (ID == 0 ? "" : " and id = ? ")
                                                        + (seqnum == 0 ? "" : " and seqnum = ? ")
                                                        + (measeqnum == 0 ? "" : " and measeqnum = ? ")
                                                        + (groupId == null ? "" : " and group_id = ? ")
                                                        + (catseqnum == 0 ? "" : " and catseqnum = ? "));
            if (ID != 0) stat.setInt(webapp_keySeq++, ID);
            if (seqnum != 0) stat.setInt(webapp_keySeq++, seqnum);
            if (measeqnum != 0) stat.setInt(webapp_keySeq++, measeqnum);
            if (groupId != null) stat.setString(webapp_keySeq++, groupId);
            if (catseqnum != 0) stat.setInt(webapp_keySeq++, catseqnum);
            stat.execute();

			webapp_keySeq = 1;
        } catch (SQLException e) {
            log.error("JDBC error generating ResultsMeasurement deleter", e);
            clearServiceState();
            throw new JspTagException("Error: JDBC error generating ResultsMeasurement deleter");
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
        seqnum = 0;
        measeqnum = 0;
        groupId = null;
        catseqnum = 0;
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
		return groupId;
	}

	public void setGroupId (String groupId) {
		this.groupId = groupId;
	}

	public String getActualGroupId () {
		return groupId;
	}
}
