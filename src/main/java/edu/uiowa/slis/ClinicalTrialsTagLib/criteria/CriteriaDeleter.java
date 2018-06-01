package edu.uiowa.slis.ClinicalTrialsTagLib.criteria;


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
import edu.uiowa.slis.ClinicalTrialsTagLib.eligibility.Eligibility;

@SuppressWarnings("serial")
public class CriteriaDeleter extends ClinicalTrialsTagLibBodyTagSupport {
    int ID = 0;
    int seqnum = 0;
    int elseqnum = 0;
    String textblock = null;
	Vector<ClinicalTrialsTagLibTagSupport> parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	private static final Log log = LogFactory.getLog(CriteriaDeleter.class);


    ResultSet rs = null;
    String var = null;
    int rsCount = 0;

    public int doStartTag() throws JspException {
		Eligibility theEligibility = (Eligibility)findAncestorWithClass(this, Eligibility.class);
		if (theEligibility!= null)
			parentEntities.addElement(theEligibility);

		if (theEligibility == null) {
		} else {
			ID = theEligibility.getID();
			seqnum = theEligibility.getSeqnum();
		}


        PreparedStatement stat;
        try {
            int webapp_keySeq = 1;
            stat = getConnection().prepareStatement("DELETE from clinical_trials.criteria where 1=1"
                                                        + (ID == 0 ? "" : " and id = ? ")
                                                        + (seqnum == 0 ? "" : " and seqnum = ? ")
                                                        + (elseqnum == 0 ? "" : " and elseqnum = ? "));
            if (ID != 0) stat.setInt(webapp_keySeq++, ID);
            if (seqnum != 0) stat.setInt(webapp_keySeq++, seqnum);
            if (elseqnum != 0) stat.setInt(webapp_keySeq++, elseqnum);
            stat.execute();

			webapp_keySeq = 1;
        } catch (SQLException e) {
            log.error("JDBC error generating Criteria deleter", e);
            clearServiceState();
            throw new JspTagException("Error: JDBC error generating Criteria deleter");
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
        elseqnum = 0;
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

	public int getElseqnum () {
		return elseqnum;
	}

	public void setElseqnum (int elseqnum) {
		this.elseqnum = elseqnum;
	}

	public int getActualElseqnum () {
		return elseqnum;
	}
}
