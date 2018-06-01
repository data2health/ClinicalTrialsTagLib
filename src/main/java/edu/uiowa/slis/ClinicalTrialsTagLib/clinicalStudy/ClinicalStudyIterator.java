package edu.uiowa.slis.ClinicalTrialsTagLib.clinicalStudy;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;
import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibBodyTagSupport;

@SuppressWarnings("serial")
public class ClinicalStudyIterator extends ClinicalTrialsTagLibBodyTagSupport {
    int ID = 0;
    String orgStudyId = null;
    String nctId = null;
    String briefTitle = null;
    String acronym = null;
    String officialTitle = null;
    String source = null;
    String overallStatus = null;
    String whyStopped = null;
    String phase = null;
    Date startDate = null;
    Date endDate = null;
    Date completionDate = null;
    Date primaryCompletionDate = null;
    String studyType = null;
    String studyDesign = null;
    String targetDuration = null;
    String numberOfArms = null;
    String numberOfGroups = null;
    String enrollment = null;
    String condition = null;
    String biospecRetention = null;
    Date verificationDate = null;
    Date lastchangedDate = null;
    Date firstreceivedDate = null;
    Date firstreceivedResultsDate = null;
    String isFdaRegulated = null;
    String isSection801 = null;
    String hasExpandedAccess = null;
	Vector<ClinicalTrialsTagLibTagSupport> parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	private static final Log log = LogFactory.getLog(ClinicalStudyIterator.class);


    PreparedStatement stat = null;
    ResultSet rs = null;
    String sortCriteria = null;
    int limitCriteria = 0;
    String var = null;
    int rsCount = 0;

	public static String clinicalStudyCount() throws JspTagException {
		int count = 0;
		ClinicalStudyIterator theIterator = new ClinicalStudyIterator();
		try {
			PreparedStatement stat = theIterator.getConnection().prepareStatement("SELECT count(*) from clinical_trials.clinical_study"
						);

			ResultSet crs = stat.executeQuery();

			if (crs.next()) {
				count = crs.getInt(1);
			}
			stat.close();
		} catch (SQLException e) {
			log.error("JDBC error generating ClinicalStudy iterator", e);
			throw new JspTagException("Error: JDBC error generating ClinicalStudy iterator");
		} finally {
			theIterator.freeConnection();
		}
		return "" + count;
	}

	public static Boolean clinicalStudyExists (String ID) throws JspTagException {
		int count = 0;
		ClinicalStudyIterator theIterator = new ClinicalStudyIterator();
		try {
			PreparedStatement stat = theIterator.getConnection().prepareStatement("SELECT count(*) from clinical_trials.clinical_study where 1=1"
						+ " and id = ?"
						);

			stat.setInt(1,Integer.parseInt(ID));
			ResultSet crs = stat.executeQuery();

			if (crs.next()) {
				count = crs.getInt(1);
			}
			stat.close();
		} catch (SQLException e) {
			log.error("JDBC error generating ClinicalStudy iterator", e);
			throw new JspTagException("Error: JDBC error generating ClinicalStudy iterator");
		} finally {
			theIterator.freeConnection();
		}
		return count > 0;
	}

    public int doStartTag() throws JspException {



      try {
            //run count query  
            int webapp_keySeq = 1;
            stat = getConnection().prepareStatement("SELECT count(*) from " + generateFromClause() + " where 1=1"
                                                        + generateJoinCriteria()
                                                        +  generateLimitCriteria());
            rs = stat.executeQuery();

            if (rs.next()) {
                pageContext.setAttribute(var+"Total", rs.getInt(1));
            }


            //run select id query  
            webapp_keySeq = 1;
            stat = getConnection().prepareStatement("SELECT clinical_trials.clinical_study.id from " + generateFromClause() + " where 1=1"
                                                        + generateJoinCriteria()
                                                        + " order by " + generateSortCriteria() + generateLimitCriteria());
            rs = stat.executeQuery();

            if (rs.next()) {
                ID = rs.getInt(1);
                pageContext.setAttribute(var, ++rsCount);
                return EVAL_BODY_INCLUDE;
            }
        } catch (SQLException e) {
            log.error("JDBC error generating ClinicalStudy iterator: " + stat.toString(), e);
            clearServiceState();
            freeConnection();
            throw new JspTagException("Error: JDBC error generating ClinicalStudy iterator: " + stat.toString());
        }

        return SKIP_BODY;
    }

    private String generateFromClause() {
       StringBuffer theBuffer = new StringBuffer("clinical_trials.clinical_study");
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
            return "id";
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
                pageContext.setAttribute(var, ++rsCount);
                return EVAL_BODY_AGAIN;
            }
        } catch (SQLException e) {
            log.error("JDBC error iterating across ClinicalStudy", e);
            clearServiceState();
            freeConnection();
            throw new JspTagException("Error: JDBC error iterating across ClinicalStudy");
        }
        return SKIP_BODY;
    }

    public int doEndTag() throws JspTagException, JspException {
        try {
            rs.close();
            stat.close();
        } catch (SQLException e) {
            log.error("JDBC error ending ClinicalStudy iterator",e);
            throw new JspTagException("Error: JDBC error ending ClinicalStudy iterator");
        } finally {
            clearServiceState();
            freeConnection();
        }
        return super.doEndTag();
    }

    private void clearServiceState() {
        ID = 0;
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
}
