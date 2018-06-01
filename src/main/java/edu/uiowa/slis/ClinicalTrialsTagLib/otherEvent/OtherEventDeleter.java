package edu.uiowa.slis.ClinicalTrialsTagLib.otherEvent;


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
import edu.uiowa.slis.ClinicalTrialsTagLib.otherCategory.OtherCategory;

@SuppressWarnings("serial")
public class OtherEventDeleter extends ClinicalTrialsTagLibBodyTagSupport {
    int ID = 0;
    String seqnum = null;
    int eveseqnum = 0;
    String subTitle = null;
    String description = null;
    String assessment = null;
	Vector<ClinicalTrialsTagLibTagSupport> parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	private static final Log log = LogFactory.getLog(OtherEventDeleter.class);


    ResultSet rs = null;
    String var = null;
    int rsCount = 0;

    public int doStartTag() throws JspException {
		OtherCategory theOtherCategory = (OtherCategory)findAncestorWithClass(this, OtherCategory.class);
		if (theOtherCategory!= null)
			parentEntities.addElement(theOtherCategory);

		if (theOtherCategory == null) {
		} else {
			ID = theOtherCategory.getID();
			seqnum = theOtherCategory.getSeqnum();
		}


        PreparedStatement stat;
        try {
            int webapp_keySeq = 1;
            stat = getConnection().prepareStatement("DELETE from clinical_trials.other_event where 1=1"
                                                        + (ID == 0 ? "" : " and id = ? ")
                                                        + (seqnum == null ? "" : " and seqnum = ? ")
                                                        + (eveseqnum == 0 ? "" : " and eveseqnum = ? "));
            if (ID != 0) stat.setInt(webapp_keySeq++, ID);
            if (seqnum != null) stat.setString(webapp_keySeq++, seqnum);
            if (eveseqnum != 0) stat.setInt(webapp_keySeq++, eveseqnum);
            stat.execute();

			webapp_keySeq = 1;
        } catch (SQLException e) {
            log.error("JDBC error generating OtherEvent deleter", e);
            clearServiceState();
            throw new JspTagException("Error: JDBC error generating OtherEvent deleter");
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
        seqnum = null;
        eveseqnum = 0;
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

	public String getSeqnum () {
		return seqnum;
	}

	public void setSeqnum (String seqnum) {
		this.seqnum = seqnum;
	}

	public String getActualSeqnum () {
		return seqnum;
	}

	public int getEveseqnum () {
		return eveseqnum;
	}

	public void setEveseqnum (int eveseqnum) {
		this.eveseqnum = eveseqnum;
	}

	public int getActualEveseqnum () {
		return eveseqnum;
	}
}
