package edu.uiowa.slis.ClinicalTrialsTagLib.resultsAnalysis;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsAnalysisGroupsDesc extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsAnalysisGroupsDesc.class);


	public int doStartTag() throws JspException {
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			if (!theResultsAnalysis.commitNeeded) {
				pageContext.getOut().print(theResultsAnalysis.getGroupsDesc());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsAnalysis for groupsDesc tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysis for groupsDesc tag ");
		}
		return SKIP_BODY;
	}

	public String getGroupsDesc() throws JspTagException {
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			return theResultsAnalysis.getGroupsDesc();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsAnalysis for groupsDesc tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysis for groupsDesc tag ");
		}
	}

	public void setGroupsDesc(String groupsDesc) throws JspTagException {
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			theResultsAnalysis.setGroupsDesc(groupsDesc);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsAnalysis for groupsDesc tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysis for groupsDesc tag ");
		}
	}

}
