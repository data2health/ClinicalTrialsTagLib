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
public class ResultsAnalysisGroupIterator extends ClinicalTrialsTagLibBodyTagSupport {
    int ID = 0;
    String groupId = null;
    int seqnum = 0;
    int anaseqnum = 0;
	Vector<ClinicalTrialsTagLibTagSupport> parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	private static final Log log = LogFactory.getLog(ResultsAnalysisGroupIterator.class);


    PreparedStatement stat = null;
    ResultSet rs = null;
    String sortCriteria = null;
    int limitCriteria = 0;
    String var = null;
    int rsCount = 0;

	public static String resultsAnalysisGroupCountByResultsAnalysis(String ID, String seqnum, String anaseqnum) throws JspTagException {
		int count = 0;
		ResultsAnalysisGroupIterator theIterator = new ResultsAnalysisGroupIterator();
		try {
			PreparedStatement stat = theIterator.getConnection().prepareStatement("SELECT count(*) from clinical_trials.results_analysis_group where 1=1"
						+ " and id = ?"
						+ " and seqnum = ?"
						+ " and anaseqnum = ?"
						);

			stat.setInt(1,Integer.parseInt(ID));
			stat.setInt(2,Integer.parseInt(seqnum));
			stat.setInt(3,Integer.parseInt(anaseqnum));
			ResultSet crs = stat.executeQuery();

			if (crs.next()) {
				count = crs.getInt(1);
			}
			stat.close();
		} catch (SQLException e) {
			log.error("JDBC error generating ResultsAnalysisGroup iterator", e);
			throw new JspTagException("Error: JDBC error generating ResultsAnalysisGroup iterator");
		} finally {
			theIterator.freeConnection();
		}
		return "" + count;
	}

	public static Boolean resultsAnalysisHasResultsAnalysisGroup(String ID, String seqnum, String anaseqnum) throws JspTagException {
		return ! resultsAnalysisGroupCountByResultsAnalysis(ID, seqnum, anaseqnum).equals("0");
	}

	public static Boolean resultsAnalysisGroupExists (String ID, String groupId, String seqnum, String anaseqnum) throws JspTagException {
		int count = 0;
		ResultsAnalysisGroupIterator theIterator = new ResultsAnalysisGroupIterator();
		try {
			PreparedStatement stat = theIterator.getConnection().prepareStatement("SELECT count(*) from clinical_trials.results_analysis_group where 1=1"
						+ " and id = ?"
						+ " and group_id = ?"
						+ " and seqnum = ?"
						+ " and anaseqnum = ?"
						);

			stat.setInt(1,Integer.parseInt(ID));
			stat.setString(2,groupId);
			stat.setInt(3,Integer.parseInt(seqnum));
			stat.setInt(4,Integer.parseInt(anaseqnum));
			ResultSet crs = stat.executeQuery();

			if (crs.next()) {
				count = crs.getInt(1);
			}
			stat.close();
		} catch (SQLException e) {
			log.error("JDBC error generating ResultsAnalysisGroup iterator", e);
			throw new JspTagException("Error: JDBC error generating ResultsAnalysisGroup iterator");
		} finally {
			theIterator.freeConnection();
		}
		return count > 0;
	}

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


      try {
            //run count query  
            int webapp_keySeq = 1;
            stat = getConnection().prepareStatement("SELECT count(*) from " + generateFromClause() + " where 1=1"
                                                        + generateJoinCriteria()
                                                        + (ID == 0 ? "" : " and id = ?")
                                                        + (seqnum == 0 ? "" : " and seqnum = ?")
                                                        + (anaseqnum == 0 ? "" : " and anaseqnum = ?")
                                                        +  generateLimitCriteria());
            if (ID != 0) stat.setInt(webapp_keySeq++, ID);
            if (seqnum != 0) stat.setInt(webapp_keySeq++, seqnum);
            if (anaseqnum != 0) stat.setInt(webapp_keySeq++, anaseqnum);
            rs = stat.executeQuery();

            if (rs.next()) {
                pageContext.setAttribute(var+"Total", rs.getInt(1));
            }


            //run select id query  
            webapp_keySeq = 1;
            stat = getConnection().prepareStatement("SELECT clinical_trials.results_analysis_group.id, clinical_trials.results_analysis_group.group_id, clinical_trials.results_analysis_group.seqnum, clinical_trials.results_analysis_group.anaseqnum from " + generateFromClause() + " where 1=1"
                                                        + generateJoinCriteria()
                                                        + (ID == 0 ? "" : " and id = ?")
                                                        + (seqnum == 0 ? "" : " and seqnum = ?")
                                                        + (anaseqnum == 0 ? "" : " and anaseqnum = ?")
                                                        + " order by " + generateSortCriteria() + generateLimitCriteria());
            if (ID != 0) stat.setInt(webapp_keySeq++, ID);
            if (seqnum != 0) stat.setInt(webapp_keySeq++, seqnum);
            if (anaseqnum != 0) stat.setInt(webapp_keySeq++, anaseqnum);
            rs = stat.executeQuery();

            if (rs.next()) {
                ID = rs.getInt(1);
                groupId = rs.getString(2);
                seqnum = rs.getInt(3);
                anaseqnum = rs.getInt(4);
                pageContext.setAttribute(var, ++rsCount);
                return EVAL_BODY_INCLUDE;
            }
        } catch (SQLException e) {
            log.error("JDBC error generating ResultsAnalysisGroup iterator: " + stat.toString(), e);
            clearServiceState();
            freeConnection();
            throw new JspTagException("Error: JDBC error generating ResultsAnalysisGroup iterator: " + stat.toString());
        }

        return SKIP_BODY;
    }

    private String generateFromClause() {
       StringBuffer theBuffer = new StringBuffer("clinical_trials.results_analysis_group");
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
            return "id,group_id,seqnum,anaseqnum";
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
                groupId = rs.getString(2);
                seqnum = rs.getInt(3);
                anaseqnum = rs.getInt(4);
                pageContext.setAttribute(var, ++rsCount);
                return EVAL_BODY_AGAIN;
            }
        } catch (SQLException e) {
            log.error("JDBC error iterating across ResultsAnalysisGroup", e);
            clearServiceState();
            freeConnection();
            throw new JspTagException("Error: JDBC error iterating across ResultsAnalysisGroup");
        }
        return SKIP_BODY;
    }

    public int doEndTag() throws JspTagException, JspException {
        try {
            rs.close();
            stat.close();
        } catch (SQLException e) {
            log.error("JDBC error ending ResultsAnalysisGroup iterator",e);
            throw new JspTagException("Error: JDBC error ending ResultsAnalysisGroup iterator");
        } finally {
            clearServiceState();
            freeConnection();
        }
        return super.doEndTag();
    }

    private void clearServiceState() {
        ID = 0;
        groupId = null;
        seqnum = 0;
        anaseqnum = 0;
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
