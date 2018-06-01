package edu.uiowa.slis.ClinicalTrialsTagLib.resultsOutcome;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsOutcomeDescription extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsOutcomeDescription.class);


	public int doStartTag() throws JspException {
		try {
			ResultsOutcome theResultsOutcome = (ResultsOutcome)findAncestorWithClass(this, ResultsOutcome.class);
			if (!theResultsOutcome.commitNeeded) {
				pageContext.getOut().print(theResultsOutcome.getDescription());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsOutcome for description tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsOutcome for description tag ");
		}
		return SKIP_BODY;
	}

	public String getDescription() throws JspTagException {
		try {
			ResultsOutcome theResultsOutcome = (ResultsOutcome)findAncestorWithClass(this, ResultsOutcome.class);
			return theResultsOutcome.getDescription();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsOutcome for description tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsOutcome for description tag ");
		}
	}

	public void setDescription(String description) throws JspTagException {
		try {
			ResultsOutcome theResultsOutcome = (ResultsOutcome)findAncestorWithClass(this, ResultsOutcome.class);
			theResultsOutcome.setDescription(description);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsOutcome for description tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsOutcome for description tag ");
		}
	}

}
