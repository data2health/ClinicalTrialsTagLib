package edu.uiowa.slis.ClinicalTrialsTagLib.resultsMeasurement;


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
import edu.uiowa.slis.ClinicalTrialsTagLib.resultsCategory.ResultsCategory;

@SuppressWarnings("serial")
public class ResultsMeasurementIterator extends ClinicalTrialsTagLibBodyTagSupport {
    int ID = 0;
    int seqnum = 0;
    int measeqnum = 0;
    String groupId = null;
    int catseqnum = 0;
    String value = null;
    String spread = null;
    String lowerLimit = null;
    String upperLimit = null;
	Vector<ClinicalTrialsTagLibTagSupport> parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	private static final Log log = LogFactory.getLog(ResultsMeasurementIterator.class);


    PreparedStatement stat = null;
    ResultSet rs = null;
    String sortCriteria = null;
    int limitCriteria = 0;
    String var = null;
    int rsCount = 0;

	public static String resultsMeasurementCountByResultsCategory(String ID, String seqnum, String measeqnum) throws JspTagException {
		int count = 0;
		ResultsMeasurementIterator theIterator = new ResultsMeasurementIterator();
		try {
			PreparedStatement stat = theIterator.getConnection().prepareStatement("SELECT count(*) from clinical_trials.results_measurement where 1=1"
						+ " and id = ?"
						+ " and seqnum = ?"
						+ " and measeqnum = ?"
						);

			stat.setInt(1,Integer.parseInt(ID));
			stat.setInt(2,Integer.parseInt(seqnum));
			stat.setInt(3,Integer.parseInt(measeqnum));
			ResultSet crs = stat.executeQuery();

			if (crs.next()) {
				count = crs.getInt(1);
			}
			stat.close();
		} catch (SQLException e) {
			log.error("JDBC error generating ResultsMeasurement iterator", e);
			throw new JspTagException("Error: JDBC error generating ResultsMeasurement iterator");
		} finally {
			theIterator.freeConnection();
		}
		return "" + count;
	}

	public static Boolean resultsCategoryHasResultsMeasurement(String ID, String seqnum, String measeqnum) throws JspTagException {
		return ! resultsMeasurementCountByResultsCategory(ID, seqnum, measeqnum).equals("0");
	}

	public static Boolean resultsMeasurementExists (String ID, String seqnum, String measeqnum, String groupId, String catseqnum) throws JspTagException {
		int count = 0;
		ResultsMeasurementIterator theIterator = new ResultsMeasurementIterator();
		try {
			PreparedStatement stat = theIterator.getConnection().prepareStatement("SELECT count(*) from clinical_trials.results_measurement where 1=1"
						+ " and id = ?"
						+ " and seqnum = ?"
						+ " and measeqnum = ?"
						+ " and group_id = ?"
						+ " and catseqnum = ?"
						);

			stat.setInt(1,Integer.parseInt(ID));
			stat.setInt(2,Integer.parseInt(seqnum));
			stat.setInt(3,Integer.parseInt(measeqnum));
			stat.setString(4,groupId);
			stat.setInt(5,Integer.parseInt(catseqnum));
			ResultSet crs = stat.executeQuery();

			if (crs.next()) {
				count = crs.getInt(1);
			}
			stat.close();
		} catch (SQLException e) {
			log.error("JDBC error generating ResultsMeasurement iterator", e);
			throw new JspTagException("Error: JDBC error generating ResultsMeasurement iterator");
		} finally {
			theIterator.freeConnection();
		}
		return count > 0;
	}

    public int doStartTag() throws JspException {
		ResultsCategory theResultsCategory = (ResultsCategory)findAncestorWithClass(this, ResultsCategory.class);
		if (theResultsCategory!= null)
			parentEntities.addElement(theResultsCategory);

		if (theResultsCategory == null) {
		} else {
			ID = theResultsCategory.getID();
			seqnum = theResultsCategory.getSeqnum();
			measeqnum = theResultsCategory.getMeaseqnum();
		}


      try {
            //run count query  
            int webapp_keySeq = 1;
            stat = getConnection().prepareStatement("SELECT count(*) from " + generateFromClause() + " where 1=1"
                                                        + generateJoinCriteria()
                                                        + (ID == 0 ? "" : " and id = ?")
                                                        + (seqnum == 0 ? "" : " and seqnum = ?")
                                                        + (measeqnum == 0 ? "" : " and measeqnum = ?")
                                                        +  generateLimitCriteria());
            if (ID != 0) stat.setInt(webapp_keySeq++, ID);
            if (seqnum != 0) stat.setInt(webapp_keySeq++, seqnum);
            if (measeqnum != 0) stat.setInt(webapp_keySeq++, measeqnum);
            rs = stat.executeQuery();

            if (rs.next()) {
                pageContext.setAttribute(var+"Total", rs.getInt(1));
            }


            //run select id query  
            webapp_keySeq = 1;
            stat = getConnection().prepareStatement("SELECT clinical_trials.results_measurement.id, clinical_trials.results_measurement.seqnum, clinical_trials.results_measurement.measeqnum, clinical_trials.results_measurement.group_id, clinical_trials.results_measurement.catseqnum from " + generateFromClause() + " where 1=1"
                                                        + generateJoinCriteria()
                                                        + (ID == 0 ? "" : " and id = ?")
                                                        + (seqnum == 0 ? "" : " and seqnum = ?")
                                                        + (measeqnum == 0 ? "" : " and measeqnum = ?")
                                                        + " order by " + generateSortCriteria() + generateLimitCriteria());
            if (ID != 0) stat.setInt(webapp_keySeq++, ID);
            if (seqnum != 0) stat.setInt(webapp_keySeq++, seqnum);
            if (measeqnum != 0) stat.setInt(webapp_keySeq++, measeqnum);
            rs = stat.executeQuery();

            if (rs.next()) {
                ID = rs.getInt(1);
                seqnum = rs.getInt(2);
                measeqnum = rs.getInt(3);
                groupId = rs.getString(4);
                catseqnum = rs.getInt(5);
                pageContext.setAttribute(var, ++rsCount);
                return EVAL_BODY_INCLUDE;
            }
        } catch (SQLException e) {
            log.error("JDBC error generating ResultsMeasurement iterator: " + stat.toString(), e);
            clearServiceState();
            freeConnection();
            throw new JspTagException("Error: JDBC error generating ResultsMeasurement iterator: " + stat.toString());
        }

        return SKIP_BODY;
    }

    private String generateFromClause() {
       StringBuffer theBuffer = new StringBuffer("clinical_trials.results_measurement");
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
            return "id,seqnum,measeqnum,group_id,catseqnum";
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
                measeqnum = rs.getInt(3);
                groupId = rs.getString(4);
                catseqnum = rs.getInt(5);
                pageContext.setAttribute(var, ++rsCount);
                return EVAL_BODY_AGAIN;
            }
        } catch (SQLException e) {
            log.error("JDBC error iterating across ResultsMeasurement", e);
            clearServiceState();
            freeConnection();
            throw new JspTagException("Error: JDBC error iterating across ResultsMeasurement");
        }
        return SKIP_BODY;
    }

    public int doEndTag() throws JspTagException, JspException {
        try {
            rs.close();
            stat.close();
        } catch (SQLException e) {
            log.error("JDBC error ending ResultsMeasurement iterator",e);
            throw new JspTagException("Error: JDBC error ending ResultsMeasurement iterator");
        } finally {
            clearServiceState();
            freeConnection();
        }
        return super.doEndTag();
    }

    private void clearServiceState() {
        ID = 0;
        seqnum = 0;
        measeqnum = 0;
        groupId = null;
        catseqnum = 0;
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

	public int getMeaseqnum () {
		return measeqnum;
	}

	public void setMeaseqnum (int measeqnum) {
		this.measeqnum = measeqnum;
	}

	public int getActualMeaseqnum () {
		return measeqnum;
	}

	public int getCatseqnum () {
		return catseqnum;
	}

	public void setCatseqnum (int catseqnum) {
		this.catseqnum = catseqnum;
	}

	public int getActualCatseqnum () {
		return catseqnum;
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
