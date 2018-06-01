package edu.uiowa.slis.ClinicalTrialsTagLib.participantFlowDropWithdrawParticipant;


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
import edu.uiowa.slis.ClinicalTrialsTagLib.participantFlowDropWithdraw.ParticipantFlowDropWithdraw;

@SuppressWarnings("serial")
public class ParticipantFlowDropWithdrawParticipantIterator extends ClinicalTrialsTagLibBodyTagSupport {
    int ID = 0;
    int seqnum = 0;
    int mileseqnum = 0;
    String groupId = null;
    String count = null;
	Vector<ClinicalTrialsTagLibTagSupport> parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	private static final Log log = LogFactory.getLog(ParticipantFlowDropWithdrawParticipantIterator.class);


    PreparedStatement stat = null;
    ResultSet rs = null;
    String sortCriteria = null;
    int limitCriteria = 0;
    String var = null;
    int rsCount = 0;

	public static String participantFlowDropWithdrawParticipantCountByParticipantFlowDropWithdraw(String ID, String seqnum, String mileseqnum) throws JspTagException {
		int count = 0;
		ParticipantFlowDropWithdrawParticipantIterator theIterator = new ParticipantFlowDropWithdrawParticipantIterator();
		try {
			PreparedStatement stat = theIterator.getConnection().prepareStatement("SELECT count(*) from clinical_trials.participant_flow_drop_withdraw_participant where 1=1"
						+ " and id = ?"
						+ " and seqnum = ?"
						+ " and mileseqnum = ?"
						);

			stat.setInt(1,Integer.parseInt(ID));
			stat.setInt(2,Integer.parseInt(seqnum));
			stat.setInt(3,Integer.parseInt(mileseqnum));
			ResultSet crs = stat.executeQuery();

			if (crs.next()) {
				count = crs.getInt(1);
			}
			stat.close();
		} catch (SQLException e) {
			log.error("JDBC error generating ParticipantFlowDropWithdrawParticipant iterator", e);
			throw new JspTagException("Error: JDBC error generating ParticipantFlowDropWithdrawParticipant iterator");
		} finally {
			theIterator.freeConnection();
		}
		return "" + count;
	}

	public static Boolean participantFlowDropWithdrawHasParticipantFlowDropWithdrawParticipant(String ID, String seqnum, String mileseqnum) throws JspTagException {
		return ! participantFlowDropWithdrawParticipantCountByParticipantFlowDropWithdraw(ID, seqnum, mileseqnum).equals("0");
	}

	public static Boolean participantFlowDropWithdrawParticipantExists (String ID, String seqnum, String mileseqnum, String groupId) throws JspTagException {
		int count = 0;
		ParticipantFlowDropWithdrawParticipantIterator theIterator = new ParticipantFlowDropWithdrawParticipantIterator();
		try {
			PreparedStatement stat = theIterator.getConnection().prepareStatement("SELECT count(*) from clinical_trials.participant_flow_drop_withdraw_participant where 1=1"
						+ " and id = ?"
						+ " and seqnum = ?"
						+ " and mileseqnum = ?"
						+ " and group_id = ?"
						);

			stat.setInt(1,Integer.parseInt(ID));
			stat.setInt(2,Integer.parseInt(seqnum));
			stat.setInt(3,Integer.parseInt(mileseqnum));
			stat.setString(4,groupId);
			ResultSet crs = stat.executeQuery();

			if (crs.next()) {
				count = crs.getInt(1);
			}
			stat.close();
		} catch (SQLException e) {
			log.error("JDBC error generating ParticipantFlowDropWithdrawParticipant iterator", e);
			throw new JspTagException("Error: JDBC error generating ParticipantFlowDropWithdrawParticipant iterator");
		} finally {
			theIterator.freeConnection();
		}
		return count > 0;
	}

    public int doStartTag() throws JspException {
		ParticipantFlowDropWithdraw theParticipantFlowDropWithdraw = (ParticipantFlowDropWithdraw)findAncestorWithClass(this, ParticipantFlowDropWithdraw.class);
		if (theParticipantFlowDropWithdraw!= null)
			parentEntities.addElement(theParticipantFlowDropWithdraw);

		if (theParticipantFlowDropWithdraw == null) {
		} else {
			ID = theParticipantFlowDropWithdraw.getID();
			seqnum = theParticipantFlowDropWithdraw.getSeqnum();
			mileseqnum = theParticipantFlowDropWithdraw.getMileseqnum();
		}


      try {
            //run count query  
            int webapp_keySeq = 1;
            stat = getConnection().prepareStatement("SELECT count(*) from " + generateFromClause() + " where 1=1"
                                                        + generateJoinCriteria()
                                                        + (ID == 0 ? "" : " and id = ?")
                                                        + (seqnum == 0 ? "" : " and seqnum = ?")
                                                        + (mileseqnum == 0 ? "" : " and mileseqnum = ?")
                                                        +  generateLimitCriteria());
            if (ID != 0) stat.setInt(webapp_keySeq++, ID);
            if (seqnum != 0) stat.setInt(webapp_keySeq++, seqnum);
            if (mileseqnum != 0) stat.setInt(webapp_keySeq++, mileseqnum);
            rs = stat.executeQuery();

            if (rs.next()) {
                pageContext.setAttribute(var+"Total", rs.getInt(1));
            }


            //run select id query  
            webapp_keySeq = 1;
            stat = getConnection().prepareStatement("SELECT clinical_trials.participant_flow_drop_withdraw_participant.id, clinical_trials.participant_flow_drop_withdraw_participant.seqnum, clinical_trials.participant_flow_drop_withdraw_participant.mileseqnum, clinical_trials.participant_flow_drop_withdraw_participant.group_id from " + generateFromClause() + " where 1=1"
                                                        + generateJoinCriteria()
                                                        + (ID == 0 ? "" : " and id = ?")
                                                        + (seqnum == 0 ? "" : " and seqnum = ?")
                                                        + (mileseqnum == 0 ? "" : " and mileseqnum = ?")
                                                        + " order by " + generateSortCriteria() + generateLimitCriteria());
            if (ID != 0) stat.setInt(webapp_keySeq++, ID);
            if (seqnum != 0) stat.setInt(webapp_keySeq++, seqnum);
            if (mileseqnum != 0) stat.setInt(webapp_keySeq++, mileseqnum);
            rs = stat.executeQuery();

            if (rs.next()) {
                ID = rs.getInt(1);
                seqnum = rs.getInt(2);
                mileseqnum = rs.getInt(3);
                groupId = rs.getString(4);
                pageContext.setAttribute(var, ++rsCount);
                return EVAL_BODY_INCLUDE;
            }
        } catch (SQLException e) {
            log.error("JDBC error generating ParticipantFlowDropWithdrawParticipant iterator: " + stat.toString(), e);
            clearServiceState();
            freeConnection();
            throw new JspTagException("Error: JDBC error generating ParticipantFlowDropWithdrawParticipant iterator: " + stat.toString());
        }

        return SKIP_BODY;
    }

    private String generateFromClause() {
       StringBuffer theBuffer = new StringBuffer("clinical_trials.participant_flow_drop_withdraw_participant");
      return theBuffer.toString();
    }

    private String generateJoinCriteria() {
       StringBuffer theBuffer = new StringBuffer();
      return theBuffer.toString();
    }

    private String generateSortCriteria() {
        if (sortCriteria != null) {
            return sortCriteria;
        } else {
            return "id,seqnum,mileseqnum,group_id";
        }
    }

    private String generateLimitCriteria() {
        if (limitCriteria > 0) {
            return " limit " + limitCriteria;
        } else {
            return "";
        }
    }

    public int doAfterBody() throws JspTagException {
        try {
            if (rs.next()) {
                ID = rs.getInt(1);
                seqnum = rs.getInt(2);
                mileseqnum = rs.getInt(3);
                groupId = rs.getString(4);
                pageContext.setAttribute(var, ++rsCount);
                return EVAL_BODY_AGAIN;
            }
        } catch (SQLException e) {
            log.error("JDBC error iterating across ParticipantFlowDropWithdrawParticipant", e);
            clearServiceState();
            freeConnection();
            throw new JspTagException("Error: JDBC error iterating across ParticipantFlowDropWithdrawParticipant");
        }
        return SKIP_BODY;
    }

    public int doEndTag() throws JspTagException, JspException {
        try {
            rs.close();
            stat.close();
        } catch (SQLException e) {
            log.error("JDBC error ending ParticipantFlowDropWithdrawParticipant iterator",e);
            throw new JspTagException("Error: JDBC error ending ParticipantFlowDropWithdrawParticipant iterator");
        } finally {
            clearServiceState();
            freeConnection();
        }
        return super.doEndTag();
    }

    private void clearServiceState() {
        ID = 0;
        seqnum = 0;
        mileseqnum = 0;
        groupId = null;
        parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

        this.rs = null;
        this.stat = null;
        this.sortCriteria = null;
        this.var = null;
        this.rsCount = 0;
    }

    public String getSortCriteria() {
        return sortCriteria;
    }

    public void setSortCriteria(String sortCriteria) {
        this.sortCriteria = sortCriteria;
    }

    public int getLimitCriteria() {
        return limitCriteria;
    }

    public void setLimitCriteria(int limitCriteria) {
        this.limitCriteria = limitCriteria;
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
