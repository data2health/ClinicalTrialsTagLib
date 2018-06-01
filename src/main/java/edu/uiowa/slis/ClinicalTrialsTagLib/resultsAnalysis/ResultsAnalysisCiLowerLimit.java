package edu.uiowa.slis.ClinicalTrialsTagLib.resultsAnalysis;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsAnalysisCiLowerLimit extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsAnalysisCiLowerLimit.class);


	public int doStartTag() throws JspException {
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			if (!theResultsAnalysis.commitNeeded) {
				pageContext.getOut().print(theResultsAnalysis.getCiLowerLimit());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsAnalysis for ciLowerLimit tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysis for ciLowerLimit tag ");
		}
		return SKIP_BODY;
	}

	public String getCiLowerLimit() throws JspTagException {
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			return theResultsAnalysis.getCiLowerLimit();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsAnalysis for ciLowerLimit tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysis for ciLowerLimit tag ");
		}
	}

	public void setCiLowerLimit(String ciLowerLimit) throws JspTagException {
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			theResultsAnalysis.setCiLowerLimit(ciLowerLimit);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsAnalysis for ciLowerLimit tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysis for ciLowerLimit tag ");
		}
	}

}
