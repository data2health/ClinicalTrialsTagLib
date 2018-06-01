package edu.uiowa.slis.ClinicalTrialsTagLib.resultsAnalysis;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsAnalysisCiUpperIlmit extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsAnalysisCiUpperIlmit.class);


	public int doStartTag() throws JspException {
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			if (!theResultsAnalysis.commitNeeded) {
				pageContext.getOut().print(theResultsAnalysis.getCiUpperIlmit());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsAnalysis for ciUpperIlmit tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysis for ciUpperIlmit tag ");
		}
		return SKIP_BODY;
	}

	public String getCiUpperIlmit() throws JspTagException {
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			return theResultsAnalysis.getCiUpperIlmit();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsAnalysis for ciUpperIlmit tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysis for ciUpperIlmit tag ");
		}
	}

	public void setCiUpperIlmit(String ciUpperIlmit) throws JspTagException {
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			theResultsAnalysis.setCiUpperIlmit(ciUpperIlmit);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsAnalysis for ciUpperIlmit tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysis for ciUpperIlmit tag ");
		}
	}

}
