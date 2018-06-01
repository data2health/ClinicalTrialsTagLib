package edu.uiowa.slis.ClinicalTrialsTagLib.resultsAnalysisGroup;


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
import edu.uiowa.slis.ClinicalTrialsTagLib.resultsAnalysis.ResultsAnalysis;

@SuppressWarnings("serial")
public class ResultsAnalysisGroupDeleter extends ClinicalTrialsTagLibBodyTagSupport {
    int ID = 0;
    String groupId = null;
    int seqnum = 0;
    int anaseqnum = 0;
	Vector<ClinicalTrialsTagLibTagSupport> parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	private static final Log log = LogFactory.getLog(ResultsAnalysisGroupDeleter.class);


    ResultSet rs = null;
    String var = null;
    int rsCount = 0;

    public int doStartTag() throws JspException {
		ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
		if (theResultsAnalysis!= null)
			parentEntities.addElement(theResultsAnalysis);

		if (theResultsAnalysis == null) {
		} else {
			ID = theResultsAnalysis.getID();
			seqnum = theResultsAnalysis.getSeqnum();
			anaseqnum = theResultsAnalysis.getAnaseqnum();
		}


        PreparedStatement stat;
        try {
            int webapp_keySeq = 1;
            stat = getConnection().prepareStatement("DELETE from clinical_trials.results_analysis_group where 1=1"
                                                        + (ID == 0 ? "" : " and id = ? ")
                                                        + (groupId == null ? "" : " and group_id = ? ")
                                                        + (seqnum == 0 ? "" : " and seqnum = ? ")
                                                        + (anaseqnum == 0 ? "" : " and anaseqnum = ? "));
            if (ID != 0) stat.setInt(webapp_keySeq++, ID);
            if (groupId != null) stat.setString(webapp_keySeq++, groupId);
            if (seqnum != 0) stat.setInt(webapp_keySeq++, seqnum);
            if (anaseqnum != 0) stat.setInt(webapp_keySeq++, anaseqnum);
            stat.execute();

			webapp_keySeq = 1;
        } catch (SQLException e) {
            log.error("JDBC error generating ResultsAnalysisGroup deleter", e);
            clearServiceState();
            throw new JspTagException("Error: JDBC error generating ResultsAnalysisGroup deleter");
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
        groupId = null;
        seqnum = 0;
        anaseqnum = 0;
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

	public int getAnaseqnum () {
		return anaseqnum;
	}

	public void setAnaseqnum (int anaseqnum) {
		this.anaseqnum = anaseqnum;
	}

	public int getActualAnaseqnum () {
		return anaseqnum;
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
