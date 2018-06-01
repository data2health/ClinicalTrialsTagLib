package edu.uiowa.slis.ClinicalTrialsTagLib.briefSummary;


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
import edu.uiowa.slis.ClinicalTrialsTagLib.clinicalStudy.ClinicalStudy;

@SuppressWarnings("serial")
public class BriefSummaryDeleter extends ClinicalTrialsTagLibBodyTagSupport {
    int ID = 0;
    int seqnum = 0;
    String textblock = null;
	Vector<ClinicalTrialsTagLibTagSupport> parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	private static final Log log = LogFactory.getLog(BriefSummaryDeleter.class);


    ResultSet rs = null;
    String var = null;
    int rsCount = 0;

    public int doStartTag() throws JspException {
		ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
		if (theClinicalStudy!= null)
			parentEntities.addElement(theClinicalStudy);

		if (theClinicalStudy == null) {
		} else {
			ID = theClinicalStudy.getID();
		}


        PreparedStatement stat;
        try {
            int webapp_keySeq = 1;
            stat = getConnection().prepareStatement("DELETE from clinical_trials.brief_summary where 1=1"
                                                        + (ID == 0 ? "" : " and id = ? ")
                                                        + (seqnum == 0 ? "" : " and seqnum = ? "));
            if (ID != 0) stat.setInt(webapp_keySeq++, ID);
            if (seqnum != 0) stat.setInt(webapp_keySeq++, seqnum);
            stat.execute();

			webapp_keySeq = 1;
        } catch (SQLException e) {
            log.error("JDBC error generating BriefSummary deleter", e);
            clearServiceState();
            throw new JspTagException("Error: JDBC error generating BriefSummary deleter");
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
}
