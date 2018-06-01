package edu.uiowa.slis.ClinicalTrialsTagLib.resultsOutcome;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsOutcomePostingDate extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsOutcomePostingDate.class);


	public int doStartTag() throws JspException {
		try {
			ResultsOutcome theResultsOutcome = (ResultsOutcome)findAncestorWithClass(this, ResultsOutcome.class);
			if (!theResultsOutcome.commitNeeded) {
				pageContext.getOut().print(theResultsOutcome.getPostingDate());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsOutcome for postingDate tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsOutcome for postingDate tag ");
		}
		return SKIP_BODY;
	}

	public String getPostingDate() throws JspTagException {
		try {
			ResultsOutcome theResultsOutcome = (ResultsOutcome)findAncestorWithClass(this, ResultsOutcome.class);
			return theResultsOutcome.getPostingDate();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsOutcome for postingDate tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsOutcome for postingDate tag ");
		}
	}

	public void setPostingDate(String postingDate) throws JspTagException {
		try {
			ResultsOutcome theResultsOutcome = (ResultsOutcome)findAncestorWithClass(this, ResultsOutcome.class);
			theResultsOutcome.setPostingDate(postingDate);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsOutcome for postingDate tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsOutcome for postingDate tag ");
		}
	}

}
