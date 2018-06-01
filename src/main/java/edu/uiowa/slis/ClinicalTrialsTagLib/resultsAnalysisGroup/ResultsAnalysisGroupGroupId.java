package edu.uiowa.slis.ClinicalTrialsTagLib.resultsAnalysisGroup;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsAnalysisGroupGroupId extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsAnalysisGroupGroupId.class);


	public int doStartTag() throws JspException {
		try {
			ResultsAnalysisGroup theResultsAnalysisGroup = (ResultsAnalysisGroup)findAncestorWithClass(this, ResultsAnalysisGroup.class);
			if (!theResultsAnalysisGroup.commitNeeded) {
				pageContext.getOut().print(theResultsAnalysisGroup.getGroupId());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsAnalysisGroup for groupId tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysisGroup for groupId tag ");
		}
		return SKIP_BODY;
	}

	public String getGroupId() throws JspTagException {
		try {
			ResultsAnalysisGroup theResultsAnalysisGroup = (ResultsAnalysisGroup)findAncestorWithClass(this, ResultsAnalysisGroup.class);
			return theResultsAnalysisGroup.getGroupId();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsAnalysisGroup for groupId tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysisGroup for groupId tag ");
		}
	}

	public void setGroupId(String groupId) throws JspTagException {
		try {
			ResultsAnalysisGroup theResultsAnalysisGroup = (ResultsAnalysisGroup)findAncestorWithClass(this, ResultsAnalysisGroup.class);
			theResultsAnalysisGroup.setGroupId(groupId);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsAnalysisGroup for groupId tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysisGroup for groupId tag ");
		}
	}

}
