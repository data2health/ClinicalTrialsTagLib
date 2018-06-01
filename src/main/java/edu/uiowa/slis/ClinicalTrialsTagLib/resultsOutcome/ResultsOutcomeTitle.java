package edu.uiowa.slis.ClinicalTrialsTagLib.resultsOutcome;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsOutcomeTitle extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsOutcomeTitle.class);


	public int doStartTag() throws JspException {
		try {
			ResultsOutcome theResultsOutcome = (ResultsOutcome)findAncestorWithClass(this, ResultsOutcome.class);
			if (!theResultsOutcome.commitNeeded) {
				pageContext.getOut().print(theResultsOutcome.getTitle());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsOutcome for title tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsOutcome for title tag ");
		}
		return SKIP_BODY;
	}

	public String getTitle() throws JspTagException {
		try {
			ResultsOutcome theResultsOutcome = (ResultsOutcome)findAncestorWithClass(this, ResultsOutcome.class);
			return theResultsOutcome.getTitle();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsOutcome for title tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsOutcome for title tag ");
		}
	}

	public void setTitle(String title) throws JspTagException {
		try {
			ResultsOutcome theResultsOutcome = (ResultsOutcome)findAncestorWithClass(this, ResultsOutcome.class);
			theResultsOutcome.setTitle(title);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsOutcome for title tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsOutcome for title tag ");
		}
	}

}
