package edu.uiowa.slis.ClinicalTrialsTagLib.participantFlowMilestoneParticipant;


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
import edu.uiowa.slis.ClinicalTrialsTagLib.participantFlowMilestone.ParticipantFlowMilestone;

@SuppressWarnings("serial")
public class ParticipantFlowMilestoneParticipantDeleter extends ClinicalTrialsTagLibBodyTagSupport {
    int ID = 0;
    int seqnum = 0;
    int mileseqnum = 0;
    String groupId = null;
    String count = null;
	Vector<ClinicalTrialsTagLibTagSupport> parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	private static final Log log = LogFactory.getLog(ParticipantFlowMilestoneParticipantDeleter.class);


    ResultSet rs = null;
    String var = null;
    int rsCount = 0;

    public int doStartTag() throws JspException {
		ParticipantFlowMilestone theParticipantFlowMilestone = (ParticipantFlowMilestone)findAncestorWithClass(this, ParticipantFlowMilestone.class);
		if (theParticipantFlowMilestone!= null)
			parentEntities.addElement(theParticipantFlowMilestone);

		if (theParticipantFlowMilestone == null) {
		} else {
			ID = theParticipantFlowMilestone.getID();
			seqnum = theParticipantFlowMilestone.getSeqnum();
			mileseqnum = theParticipantFlowMilestone.getMileseqnum();
		}


        PreparedStatement stat;
        try {
            int webapp_keySeq = 1;
            stat = getConnection().prepareStatement("DELETE from clinical_trials.participant_flow_milestone_participant where 1=1"
                                                        + (ID == 0 ? "" : " and id = ? ")
                                                        + (seqnum == 0 ? "" : " and seqnum = ? ")
                                                        + (mileseqnum == 0 ? "" : " and mileseqnum = ? ")
                                                        + (groupId == null ? "" : " and group_id = ? "));
            if (ID != 0) stat.setInt(webapp_keySeq++, ID);
            if (seqnum != 0) stat.setInt(webapp_keySeq++, seqnum);
            if (mileseqnum != 0) stat.setInt(webapp_keySeq++, mileseqnum);
            if (groupId != null) stat.setString(webapp_keySeq++, groupId);
            stat.execute();

			webapp_keySeq = 1;
        } catch (SQLException e) {
            log.error("JDBC error generating ParticipantFlowMilestoneParticipant deleter", e);
            clearServiceState();
            throw new JspTagException("Error: JDBC error generating ParticipantFlowMilestoneParticipant deleter");
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
        mileseqnum = 0;
        groupId = null;
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

	public int getMileseqnum () {
		return mileseqnum;
	}

	public void setMileseqnum (int mileseqnum) {
		this.mileseqnum = mileseqnum;
	}

	public int getActualMileseqnum () {
		return mileseqnum;
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
