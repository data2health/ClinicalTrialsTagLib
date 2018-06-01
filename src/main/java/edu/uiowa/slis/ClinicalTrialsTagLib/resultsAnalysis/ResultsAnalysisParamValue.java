package edu.uiowa.slis.ClinicalTrialsTagLib.resultsAnalysis;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsAnalysisParamValue extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsAnalysisParamValue.class);


	public int doStartTag() throws JspException {
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			if (!theResultsAnalysis.commitNeeded) {
				pageContext.getOut().print(theResultsAnalysis.getParamValue());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsAnalysis for paramValue tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysis for paramValue tag ");
		}
		return SKIP_BODY;
	}

	public String getParamValue() throws JspTagException {
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			return theResultsAnalysis.getParamValue();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsAnalysis for paramValue tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysis for paramValue tag ");
		}
	}

	public void setParamValue(String paramValue) throws JspTagException {
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			theResultsAnalysis.setParamValue(paramValue);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsAnalysis for paramValue tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysis for paramValue tag ");
		}
	}

}
