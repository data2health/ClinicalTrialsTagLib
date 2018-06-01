package edu.uiowa.slis.ClinicalTrialsTagLib.seriousCategory;


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
import edu.uiowa.slis.ClinicalTrialsTagLib.reportedEvents.ReportedEvents;

@SuppressWarnings("serial")
public class SeriousCategoryDeleter extends ClinicalTrialsTagLibBodyTagSupport {
    int ID = 0;
    String seqnum = null;
    String title = null;
	Vector<ClinicalTrialsTagLibTagSupport> parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	private static final Log log = LogFactory.getLog(SeriousCategoryDeleter.class);


    ResultSet rs = null;
    String var = null;
    int rsCount = 0;

    public int doStartTag() throws JspException {
		ReportedEvents theReportedEvents = (ReportedEvents)findAncestorWithClass(this, ReportedEvents.class);
		if (theReportedEvents!= null)
			parentEntities.addElement(theReportedEvents);

		if (theReportedEvents == null) {
		} else {
			ID = theReportedEvents.getID();
		}


        PreparedStatement stat;
        try {
            int webapp_keySeq = 1;
            stat = getConnection().prepareStatement("DELETE from clinical_trials.serious_category where 1=1"
                                                        + (ID == 0 ? "" : " and id = ? ")
                                                        + (seqnum == null ? "" : " and seqnum = ? "));
            if (ID != 0) stat.setInt(webapp_keySeq++, ID);
            if (seqnum != null) stat.setString(webapp_keySeq++, seqnum);
            stat.execute();

			webapp_keySeq = 1;
        } catch (SQLException e) {
            log.error("JDBC error generating SeriousCategory deleter", e);
            clearServiceState();
            throw new JspTagException("Error: JDBC error generating SeriousCategory deleter");
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
}
