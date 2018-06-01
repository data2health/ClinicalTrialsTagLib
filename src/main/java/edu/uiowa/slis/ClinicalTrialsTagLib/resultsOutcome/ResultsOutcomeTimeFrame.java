package edu.uiowa.slis.ClinicalTrialsTagLib.resultsOutcome;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsOutcomeTimeFrame extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsOutcomeTimeFrame.class);


	public int doStartTag() throws JspException {
		try {
			ResultsOutcome theResultsOutcome = (ResultsOutcome)findAncestorWithClass(this, ResultsOutcome.class);
			if (!theResultsOutcome.commitNeeded) {
				pageContext.getOut().print(theResultsOutcome.getTimeFrame());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsOutcome for timeFrame tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsOutcome for timeFrame tag ");
		}
		return SKIP_BODY;
	}

	public String getTimeFrame() throws JspTagException {
		try {
			ResultsOutcome theResultsOutcome = (ResultsOutcome)findAncestorWithClass(this, ResultsOutcome.class);
			return theResultsOutcome.getTimeFrame();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsOutcome for timeFrame tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsOutcome for timeFrame tag ");
		}
	}

	public void setTimeFrame(String timeFrame) throws JspTagException {
		try {
			ResultsOutcome theResultsOutcome = (ResultsOutcome)findAncestorWithClass(this, ResultsOutcome.class);
			theResultsOutcome.setTimeFrame(timeFrame);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsOutcome for timeFrame tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsOutcome for timeFrame tag ");
		}
	}

}
