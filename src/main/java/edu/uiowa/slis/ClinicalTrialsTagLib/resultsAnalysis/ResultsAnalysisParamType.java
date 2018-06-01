package edu.uiowa.slis.ClinicalTrialsTagLib.resultsAnalysis;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsAnalysisParamType extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsAnalysisParamType.class);


	public int doStartTag() throws JspException {
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			if (!theResultsAnalysis.commitNeeded) {
				pageContext.getOut().print(theResultsAnalysis.getParamType());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsAnalysis for paramType tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysis for paramType tag ");
		}
		return SKIP_BODY;
	}

	public String getParamType() throws JspTagException {
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			return theResultsAnalysis.getParamType();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsAnalysis for paramType tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysis for paramType tag ");
		}
	}

	public void setParamType(String paramType) throws JspTagException {
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			theResultsAnalysis.setParamType(paramType);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsAnalysis for paramType tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysis for paramType tag ");
		}
	}

}
