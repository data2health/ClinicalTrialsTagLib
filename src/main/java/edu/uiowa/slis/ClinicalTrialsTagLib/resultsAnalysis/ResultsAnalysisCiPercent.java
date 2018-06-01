package edu.uiowa.slis.ClinicalTrialsTagLib.resultsAnalysis;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsAnalysisCiPercent extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsAnalysisCiPercent.class);


	public int doStartTag() throws JspException {
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			if (!theResultsAnalysis.commitNeeded) {
				pageContext.getOut().print(theResultsAnalysis.getCiPercent());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsAnalysis for ciPercent tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysis for ciPercent tag ");
		}
		return SKIP_BODY;
	}

	public String getCiPercent() throws JspTagException {
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			return theResultsAnalysis.getCiPercent();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsAnalysis for ciPercent tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysis for ciPercent tag ");
		}
	}

	public void setCiPercent(String ciPercent) throws JspTagException {
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			theResultsAnalysis.setCiPercent(ciPercent);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsAnalysis for ciPercent tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysis for ciPercent tag ");
		}
	}

}
