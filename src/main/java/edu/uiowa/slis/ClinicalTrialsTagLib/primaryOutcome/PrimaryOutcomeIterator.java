package edu.uiowa.slis.ClinicalTrialsTagLib.primaryOutcome;


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
public class PrimaryOutcomeIterator extends ClinicalTrialsTagLibBodyTagSupport {
    int ID = 0;
    int seqnum = 0;
    String measure = null;
    String timeFrame = null;
    String safetyIssue = null;
    String description = null;
	Vector<ClinicalTrialsTagLibTagSupport> parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	private static final Log log = LogFactory.getLog(PrimaryOutcomeIterator.class);


    PreparedStatement stat = null;
    ResultSet rs = null;
    String sortCriteria = null;
    int limitCriteria = 0;
    String var = null;
    int rsCount = 0;

	public static String primaryOutcomeCountByClinicalStudy(String ID) throws JspTagException {
		int count = 0;
		PrimaryOutcomeIterator theIterator = new PrimaryOutcomeIterator();
		try {
			PreparedStatement stat = theIterator.getConnection().prepareStatement("SELECT count(*) from clinical_trials.primary_outcome where 1=1"
						+ " and id = ?"
						);

			stat.setInt(1,Integer.parseInt(ID));
			ResultSet crs = stat.executeQuery();

			if (crs.next()) {
				count = crs.getInt(1);
			}
			stat.close();
		} catch (SQLException e) {
			log.error("JDBC error generating PrimaryOutcome iterator", e);
			throw new JspTagException("Error: JDBC error generating PrimaryOutcome iterator");
		} finally {
			theIterator.freeConnection();
		}
		return "" + count;
	}

	public static Boolean clinicalStudyHasPrimaryOutcome(String ID) throws JspTagException {
		return ! primaryOutcomeCountByClinicalStudy(ID).equals("0");
	}

	public static Boolean primaryOutcomeExists (String ID, String seqnum) throws JspTagException {
		int count = 0;
		PrimaryOutcomeIterator theIterator = new PrimaryOutcomeIterator();
		try {
			PreparedStatement stat = theIterator.getConnection().prepareStatement("SELECT count(*) from clinical_trials.primary_outcome where 1=1"
						+ " and id = ?"
						+ " and seqnum = ?"
						);

			stat.setInt(1,Integer.parseInt(ID));
			stat.setInt(2,Integer.parseInt(seqnum));
			ResultSet crs = stat.executeQuery();

			if (crs.next()) {
				count = crs.getInt(1);
			}
			stat.close();
		} catch (SQLException e) {
			log.error("JDBC error generating PrimaryOutcome iterator", e);
			throw new JspTagException("Error: JDBC error generating PrimaryOutcome iterator");
		} finally {
			theIterator.freeConnection();
		}
		return count > 0;
	}

    public int doStartTag() throws JspException {
		ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
		if (theClinicalStudy!= null)
			parentEntities.addElement(theClinicalStudy);

		if (theClinicalStudy == null) {
		} else {
			ID = theClinicalStudy.getID();
		}


      try {
            //run count query  
            int webapp_keySeq = 1;
            stat = getConnection().prepareStatement("SELECT count(*) from " + generateFromClause() + " where 1=1"
                                                        + generateJoinCriteria()
                                                        + (ID == 0 ? "" : " and id = ?")
                                                        +  generateLimitCriteria());
            if (ID != 0) stat.setInt(webapp_keySeq++, ID);
            rs = stat.executeQuery();

            if (rs.next()) {
                pageContext.setAttribute(var+"Total", rs.getInt(1));
            }


            //run select id query  
            webapp_keySeq = 1;
            stat = getConnection().prepareStatement("SELECT clinical_trials.primary_outcome.id, clinical_trials.primary_outcome.seqnum from " + generateFromClause() + " where 1=1"
                                                        + generateJoinCriteria()
                                                        + (ID == 0 ? "" : " and id = ?")
                                                        + " order by " + generateSortCriteria() + generateLimitCriteria());
            if (ID != 0) stat.setInt(webapp_keySeq++, ID);
            rs = stat.executeQuery();

            if (rs.next()) {
                ID = rs.getInt(1);
                seqnum = rs.getInt(2);
                pageContext.setAttribute(var, ++rsCount);
                return EVAL_BODY_INCLUDE;
            }
        } catch (SQLException e) {
            log.error("JDBC error generating PrimaryOutcome iterator: " + stat.toString(), e);
            clearServiceState();
            freeConnection();
            throw new JspTagException("Error: JDBC error generating PrimaryOutcome iterator: " + stat.toString());
        }

        return SKIP_BODY;
    }

    private String generateFromClause() {
       StringBuffer theBuffer = new StringBuffer("clinical_trials.primary_outcome");
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
            return "id,seqnum";
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
                pageContext.setAttribute(var, ++rsCount);
                return EVAL_BODY_AGAIN;
            }
        } catch (SQLException e) {
            log.error("JDBC error iterating across PrimaryOutcome", e);
            clearServiceState();
            freeConnection();
            throw new JspTagException("Error: JDBC error iterating across PrimaryOutcome");
        }
        return SKIP_BODY;
    }

    public int doEndTag() throws JspTagException, JspException {
        try {
            rs.close();
            stat.close();
        } catch (SQLException e) {
            log.error("JDBC error ending PrimaryOutcome iterator",e);
            throw new JspTagException("Error: JDBC error ending PrimaryOutcome iterator");
        } finally {
            clearServiceState();
            freeConnection();
        }
        return super.doEndTag();
    }

    private void clearServiceState() {
        ID = 0;
        seqnum = 0;
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
}