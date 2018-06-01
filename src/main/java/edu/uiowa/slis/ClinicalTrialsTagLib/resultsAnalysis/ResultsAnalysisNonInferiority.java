package edu.uiowa.slis.ClinicalTrialsTagLib.resultsAnalysis;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsAnalysisNonInferiority extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsAnalysisNonInferiority.class);


	public int doStartTag() throws JspException {
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			if (!theResultsAnalysis.commitNeeded) {
				pageContext.getOut().print(theResultsAnalysis.getNonInferiority());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsAnalysis for nonInferiority tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysis for nonInferiority tag ");
		}
		return SKIP_BODY;
	}

	public String getNonInferiority() throws JspTagException {
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			return theResultsAnalysis.getNonInferiority();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsAnalysis for nonInferiority tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysis for nonInferiority tag ");
		}
	}

	public void setNonInferiority(String nonInferiority) throws JspTagException {
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			theResultsAnalysis.setNonInferiority(nonInferiority);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsAnalysis for nonInferiority tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysis for nonInferiority tag ");
		}
	}

}
