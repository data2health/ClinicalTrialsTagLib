package edu.uiowa.slis.ClinicalTrialsTagLib.resultsOutcome;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsOutcomeSafetyIssue extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsOutcomeSafetyIssue.class);


	public int doStartTag() throws JspException {
		try {
			ResultsOutcome theResultsOutcome = (ResultsOutcome)findAncestorWithClass(this, ResultsOutcome.class);
			if (!theResultsOutcome.commitNeeded) {
				pageContext.getOut().print(theResultsOutcome.getSafetyIssue());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsOutcome for safetyIssue tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsOutcome for safetyIssue tag ");
		}
		return SKIP_BODY;
	}

	public String getSafetyIssue() throws JspTagException {
		try {
			ResultsOutcome theResultsOutcome = (ResultsOutcome)findAncestorWithClass(this, ResultsOutcome.class);
			return theResultsOutcome.getSafetyIssue();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsOutcome for safetyIssue tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsOutcome for safetyIssue tag ");
		}
	}

	public void setSafetyIssue(String safetyIssue) throws JspTagException {
		try {
			ResultsOutcome theResultsOutcome = (ResultsOutcome)findAncestorWithClass(this, ResultsOutcome.class);
			theResultsOutcome.setSafetyIssue(safetyIssue);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsOutcome for safetyIssue tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsOutcome for safetyIssue tag ");
		}
	}

}
