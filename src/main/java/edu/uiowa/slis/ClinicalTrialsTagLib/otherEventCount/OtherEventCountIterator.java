package edu.uiowa.slis.ClinicalTrialsTagLib.otherEventCount;


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
import edu.uiowa.slis.ClinicalTrialsTagLib.otherEvent.OtherEvent;

@SuppressWarnings("serial")
public class OtherEventCountIterator extends ClinicalTrialsTagLibBodyTagSupport {
    int ID = 0;
    String seqnum = null;
    int eveseqnum = 0;
    String groupId = null;
    String subjectsAffected = null;
    String subjectsAtRisk = null;
    String events = null;
	Vector<ClinicalTrialsTagLibTagSupport> parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	private static final Log log = LogFactory.getLog(OtherEventCountIterator.class);


    PreparedStatement stat = null;
    ResultSet rs = null;
    String sortCriteria = null;
    int limitCriteria = 0;
    String var = null;
    int rsCount = 0;

	public static String otherEventCountCountByOtherEvent(String ID, String seqnum, String eveseqnum) throws JspTagException {
		int count = 0;
		OtherEventCountIterator theIterator = new OtherEventCountIterator();
		try {
			PreparedStatement stat = theIterator.getConnection().prepareStatement("SELECT count(*) from clinical_trials.other_event_count where 1=1"
						+ " and id = ?"
						+ " and seqnum = ?"
						+ " and eveseqnum = ?"
						);

			stat.setInt(1,Integer.parseInt(ID));
			stat.setString(2,seqnum);
			stat.setInt(3,Integer.parseInt(eveseqnum));
			ResultSet crs = stat.executeQuery();

			if (crs.next()) {
				count = crs.getInt(1);
			}
			stat.close();
		} catch (SQLException e) {
			log.error("JDBC error generating OtherEventCount iterator", e);
			throw new JspTagException("Error: JDBC error generating OtherEventCount iterator");
		} finally {
			theIterator.freeConnection();
		}
		return "" + count;
	}

	public static Boolean otherEventHasOtherEventCount(String ID, String seqnum, String eveseqnum) throws JspTagException {
		return ! otherEventCountCountByOtherEvent(ID, seqnum, eveseqnum).equals("0");
	}

	public static Boolean otherEventCountExists (String ID, String seqnum, String eveseqnum, String groupId) throws JspTagException {
		int count = 0;
		OtherEventCountIterator theIterator = new OtherEventCountIterator();
		try {
			PreparedStatement stat = theIterator.getConnection().prepareStatement("SELECT count(*) from clinical_trials.other_event_count where 1=1"
						+ " and id = ?"
						+ " and seqnum = ?"
						+ " and eveseqnum = ?"
						+ " and group_id = ?"
						);

			stat.setInt(1,Integer.parseInt(ID));
			stat.setString(2,seqnum);
			stat.setInt(3,Integer.parseInt(eveseqnum));
			stat.setString(4,groupId);
			ResultSet crs = stat.executeQuery();

			if (crs.next()) {
				count = crs.getInt(1);
			}
			stat.close();
		} catch (SQLException e) {
			log.error("JDBC error generating OtherEventCount iterator", e);
			throw new JspTagException("Error: JDBC error generating OtherEventCount iterator");
		} finally {
			theIterator.freeConnection();
		}
		return count > 0;
	}

    public int doStartTag() throws JspException {
		OtherEvent theOtherEvent = (OtherEvent)findAncestorWithClass(this, OtherEvent.class);
		if (theOtherEvent!= null)
			parentEntities.addElement(theOtherEvent);

		if (theOtherEvent == null) {
		} else {
			ID = theOtherEvent.getID();
			seqnum = theOtherEvent.getSeqnum();
			eveseqnum = theOtherEvent.getEveseqnum();
		}


      try {
            //run count query  
            int webapp_keySeq = 1;
            stat = getConnection().prepareStatement("SELECT count(*) from " + generateFromClause() + " where 1=1"
                                                        + generateJoinCriteria()
                                                        + (ID == 0 ? "" : " and id = ?")
                                                        + (seqnum == null ? "" : " and seqnum = ?")
                                                        + (eveseqnum == 0 ? "" : " and eveseqnum = ?")
                                                        +  generateLimitCriteria());
            if (ID != 0) stat.setInt(webapp_keySeq++, ID);
            if (seqnum != null) stat.setString(webapp_keySeq++, seqnum);
            if (eveseqnum != 0) stat.setInt(webapp_keySeq++, eveseqnum);
            rs = stat.executeQuery();

            if (rs.next()) {
                pageContext.setAttribute(var+"Total", rs.getInt(1));
            }


            //run select id query  
            webapp_keySeq = 1;
            stat = getConnection().prepareStatement("SELECT clinical_trials.other_event_count.id, clinical_trials.other_event_count.seqnum, clinical_trials.other_event_count.eveseqnum, clinical_trials.other_event_count.group_id from " + generateFromClause() + " where 1=1"
                                                        + generateJoinCriteria()
                                                        + (ID == 0 ? "" : " and id = ?")
                                                        + (seqnum == null ? "" : " and seqnum = ?")
                                                        + (eveseqnum == 0 ? "" : " and eveseqnum = ?")
                                                        + " order by " + generateSortCriteria() + generateLimitCriteria());
            if (ID != 0) stat.setInt(webapp_keySeq++, ID);
            if (seqnum != null) stat.setString(webapp_keySeq++, seqnum);
            if (eveseqnum != 0) stat.setInt(webapp_keySeq++, eveseqnum);
            rs = stat.executeQuery();

            if (rs.next()) {
                ID = rs.getInt(1);
                seqnum = rs.getString(2);
                eveseqnum = rs.getInt(3);
                groupId = rs.getString(4);
                pageContext.setAttribute(var, ++rsCount);
                return EVAL_BODY_INCLUDE;
            }
        } catch (SQLException e) {
            log.error("JDBC error generating OtherEventCount iterator: " + stat.toString(), e);
            clearServiceState();
            freeConnection();
            throw new JspTagException("Error: JDBC error generating OtherEventCount iterator: " + stat.toString());
        }

        return SKIP_BODY;
    }

    private String generateFromClause() {
       StringBuffer theBuffer = new StringBuffer("clinical_trials.other_event_count");
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
            return "id,seqnum,eveseqnum,group_id";
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
                seqnum = rs.getString(2);
                eveseqnum = rs.getInt(3);
                groupId = rs.getString(4);
                pageContext.setAttribute(var, ++rsCount);
                return EVAL_BODY_AGAIN;
            }
        } catch (SQLException e) {
            log.error("JDBC error iterating across OtherEventCount", e);
            clearServiceState();
            freeConnection();
            throw new JspTagException("Error: JDBC error iterating across OtherEventCount");
        }
        return SKIP_BODY;
    }

    public int doEndTag() throws JspTagException, JspException {
        try {
            rs.close();
            stat.close();
        } catch (SQLException e) {
            log.error("JDBC error ending OtherEventCount iterator",e);
            throw new JspTagException("Error: JDBC error ending OtherEventCount iterator");
        } finally {
            clearServiceState();
            freeConnection();
        }
        return super.doEndTag();
    }

    private void clearServiceState() {
        ID = 0;
        seqnum = null;
        eveseqnum = 0;
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
