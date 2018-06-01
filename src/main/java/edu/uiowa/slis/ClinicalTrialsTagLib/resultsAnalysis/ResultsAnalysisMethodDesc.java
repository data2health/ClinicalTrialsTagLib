package edu.uiowa.slis.ClinicalTrialsTagLib.resultsAnalysis;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsAnalysisMethodDesc extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsAnalysisMethodDesc.class);


	public int doStartTag() throws JspException {
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			if (!theResultsAnalysis.commitNeeded) {
				pageContext.getOut().print(theResultsAnalysis.getMethodDesc());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsAnalysis for methodDesc tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysis for methodDesc tag ");
		}
		return SKIP_BODY;
	}

	public String getMethodDesc() throws JspTagException {
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			return theResultsAnalysis.getMethodDesc();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsAnalysis for methodDesc tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysis for methodDesc tag ");
		}
	}

	public void setMethodDesc(String methodDesc) throws JspTagException {
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			theResultsAnalysis.setMethodDesc(methodDesc);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsAnalysis for methodDesc tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysis for methodDesc tag ");
		}
	}

}
