package edu.uiowa.slis.ClinicalTrialsTagLib.baselineGroup;


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
import edu.uiowa.slis.ClinicalTrialsTagLib.clinicalResults.ClinicalResults;

@SuppressWarnings("serial")
public class BaselineGroupIterator extends ClinicalTrialsTagLibBodyTagSupport {
    int ID = 0;
    String groupId = null;
    String title = null;
    String description = null;
	Vector<ClinicalTrialsTagLibTagSupport> parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	private static final Log log = LogFactory.getLog(BaselineGroupIterator.class);


    PreparedStatement stat = null;
    ResultSet rs = null;
    String sortCriteria = null;
    int limitCriteria = 0;
    String var = null;
    int rsCount = 0;

	public static String baselineGroupCountByClinicalResults(String ID) throws JspTagException {
		int count = 0;
		BaselineGroupIterator theIterator = new BaselineGroupIterator();
		try {
			PreparedStatement stat = theIterator.getConnection().prepareStatement("SELECT count(*) from clinical_trials.baseline_group where 1=1"
						+ " and id = ?"
						);

			stat.setInt(1,Integer.parseInt(ID));
			ResultSet crs = stat.executeQuery();

			if (crs.next()) {
				count = crs.getInt(1);
			}
			stat.close();
		} catch (SQLException e) {
			log.error("JDBC error generating BaselineGroup iterator", e);
			throw new JspTagException("Error: JDBC error generating BaselineGroup iterator");
		} finally {
			theIterator.freeConnection();
		}
		return "" + count;
	}

	public static Boolean clinicalResultsHasBaselineGroup(String ID) throws JspTagException {
		return ! baselineGroupCountByClinicalResults(ID).equals("0");
	}

	public static Boolean baselineGroupExists (String ID, String groupId) throws JspTagException {
		int count = 0;
		BaselineGroupIterator theIterator = new BaselineGroupIterator();
		try {
			PreparedStatement stat = theIterator.getConnection().prepareStatement("SELECT count(*) from clinical_trials.baseline_group where 1=1"
						+ " and id = ?"
						+ " and group_id = ?"
						);

			stat.setInt(1,Integer.parseInt(ID));
			stat.setString(2,groupId);
			ResultSet crs = stat.executeQuery();

			if (crs.next()) {
				count = crs.getInt(1);
			}
			stat.close();
		} catch (SQLException e) {
			log.error("JDBC error generating BaselineGroup iterator", e);
			throw new JspTagException("Error: JDBC error generating BaselineGroup iterator");
		} finally {
			theIterator.freeConnection();
		}
		return count > 0;
	}

    public int doStartTag() throws JspException {
		ClinicalResults theClinicalResults = (ClinicalResults)findAncestorWithClass(this, ClinicalResults.class);
		if (theClinicalResults!= null)
			parentEntities.addElement(theClinicalResults);

		if (theClinicalResults == null) {
		} else {
			ID = theClinicalResults.getID();
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
            stat = getConnection().prepareStatement("SELECT clinical_trials.baseline_group.id, clinical_trials.baseline_group.group_id from " + generateFromClause() + " where 1=1"
                                                        + generateJoinCriteria()
                                                        + (ID == 0 ? "" : " and id = ?")
                                                        + " order by " + generateSortCriteria() + generateLimitCriteria());
            if (ID != 0) stat.setInt(webapp_keySeq++, ID);
            rs = stat.executeQuery();

            if (rs.next()) {
                ID = rs.getInt(1);
                groupId = rs.getString(2);
                pageContext.setAttribute(var, ++rsCount);
                return EVAL_BODY_INCLUDE;
            }
        } catch (SQLException e) {
            log.error("JDBC error generating BaselineGroup iterator: " + stat.toString(), e);
            clearServiceState();
            freeConnection();
            throw new JspTagException("Error: JDBC error generating BaselineGroup iterator: " + stat.toString());
        }

        return SKIP_BODY;
    }

    private String generateFromClause() {
       StringBuffer theBuffer = new StringBuffer("clinical_trials.baseline_group");
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
            return "id,group_id";
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
                pageContext.setAttribute(var, ++rsCount);
                return EVAL_BODY_AGAIN;
            }
        } catch (SQLException e) {
            log.error("JDBC error iterating across BaselineGroup", e);
            clearServiceState();
            freeConnection();
            throw new JspTagException("Error: JDBC error iterating across BaselineGroup");
        }
        return SKIP_BODY;
    }

    public int doEndTag() throws JspTagException, JspException {
        try {
            rs.close();
            stat.close();
        } catch (SQLException e) {
            log.error("JDBC error ending BaselineGroup iterator",e);
            throw new JspTagException("Error: JDBC error ending BaselineGroup iterator");
        } finally {
            clearServiceState();
            freeConnection();
        }
        return super.doEndTag();
    }

    private void clearServiceState() {
        ID = 0;
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
