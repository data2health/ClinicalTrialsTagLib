package edu.uiowa.slis.ClinicalTrialsTagLib.resultsAnalysis;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsAnalysisMethod extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsAnalysisMethod.class);


	public int doStartTag() throws JspException {
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			if (!theResultsAnalysis.commitNeeded) {
				pageContext.getOut().print(theResultsAnalysis.getMethod());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsAnalysis for method tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysis for method tag ");
		}
		return SKIP_BODY;
	}

	public String getMethod() throws JspTagException {
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			return theResultsAnalysis.getMethod();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsAnalysis for method tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysis for method tag ");
		}
	}

	public void setMethod(String method) throws JspTagException {
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			theResultsAnalysis.setMethod(method);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsAnalysis for method tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysis for method tag ");
		}
	}

}
