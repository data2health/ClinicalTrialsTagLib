package edu.uiowa.slis.ClinicalTrialsTagLib.resultsAnalysis;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsAnalysisNonInferiorityDesc extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsAnalysisNonInferiorityDesc.class);


	public int doStartTag() throws JspException {
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			if (!theResultsAnalysis.commitNeeded) {
				pageContext.getOut().print(theResultsAnalysis.getNonInferiorityDesc());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsAnalysis for nonInferiorityDesc tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysis for nonInferiorityDesc tag ");
		}
		return SKIP_BODY;
	}

	public String getNonInferiorityDesc() throws JspTagException {
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			return theResultsAnalysis.getNonInferiorityDesc();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsAnalysis for nonInferiorityDesc tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysis for nonInferiorityDesc tag ");
		}
	}

	public void setNonInferiorityDesc(String nonInferiorityDesc) throws JspTagException {
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			theResultsAnalysis.setNonInferiorityDesc(nonInferiorityDesc);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsAnalysis for nonInferiorityDesc tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysis for nonInferiorityDesc tag ");
		}
	}

}
