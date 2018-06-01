package edu.uiowa.slis.ClinicalTrialsTagLib.resultsAnalysis;


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
import edu.uiowa.slis.ClinicalTrialsTagLib.resultsOutcome.ResultsOutcome;

@SuppressWarnings("serial")
public class ResultsAnalysisDeleter extends ClinicalTrialsTagLibBodyTagSupport {
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
	Vector<ClinicalTrialsTagLibTagSupport> parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	private static final Log log = LogFactory.getLog(ResultsAnalysisDeleter.class);


    ResultSet rs = null;
    String var = null;
    int rsCount = 0;

    public int doStartTag() throws JspException {
		ResultsOutcome theResultsOutcome = (ResultsOutcome)findAncestorWithClass(this, ResultsOutcome.class);
		if (theResultsOutcome!= null)
			parentEntities.addElement(theResultsOutcome);

		if (theResultsOutcome == null) {
		} else {
			ID = theResultsOutcome.getID();
			seqnum = theResultsOutcome.getSeqnum();
		}


        PreparedStatement stat;
        try {
            int webapp_keySeq = 1;
            stat = getConnection().prepareStatement("DELETE from clinical_trials.results_analysis where 1=1"
                                                        + (ID == 0 ? "" : " and id = ? ")
                                                        + (seqnum == 0 ? "" : " and seqnum = ? ")
                                                        + (anaseqnum == 0 ? "" : " and anaseqnum = ? "));
            if (ID != 0) stat.setInt(webapp_keySeq++, ID);
            if (seqnum != 0) stat.setInt(webapp_keySeq++, seqnum);
            if (anaseqnum != 0) stat.setInt(webapp_keySeq++, anaseqnum);
            stat.execute();

			webapp_keySeq = 1;
        } catch (SQLException e) {
            log.error("JDBC error generating ResultsAnalysis deleter", e);
            clearServiceState();
            throw new JspTagException("Error: JDBC error generating ResultsAnalysis deleter");
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
}
