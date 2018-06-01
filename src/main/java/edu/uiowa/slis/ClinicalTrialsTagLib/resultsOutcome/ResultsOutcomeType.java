package edu.uiowa.slis.ClinicalTrialsTagLib.resultsOutcome;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsOutcomeType extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsOutcomeType.class);


	public int doStartTag() throws JspException {
		try {
			ResultsOutcome theResultsOutcome = (ResultsOutcome)findAncestorWithClass(this, ResultsOutcome.class);
			if (!theResultsOutcome.commitNeeded) {
				pageContext.getOut().print(theResultsOutcome.getType());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsOutcome for type tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsOutcome for type tag ");
		}
		return SKIP_BODY;
	}

	public String getType() throws JspTagException {
		try {
			ResultsOutcome theResultsOutcome = (ResultsOutcome)findAncestorWithClass(this, ResultsOutcome.class);
			return theResultsOutcome.getType();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsOutcome for type tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsOutcome for type tag ");
		}
	}

	public void setType(String type) throws JspTagException {
		try {
			ResultsOutcome theResultsOutcome = (ResultsOutcome)findAncestorWithClass(this, ResultsOutcome.class);
			theResultsOutcome.setType(type);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsOutcome for type tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsOutcome for type tag ");
		}
	}

}
