package edu.uiowa.slis.ClinicalTrialsTagLib.resultsOutcome;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsOutcomeID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsOutcomeID.class);


	public int doStartTag() throws JspException {
		try {
			ResultsOutcome theResultsOutcome = (ResultsOutcome)findAncestorWithClass(this, ResultsOutcome.class);
			if (!theResultsOutcome.commitNeeded) {
				pageContext.getOut().print(theResultsOutcome.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsOutcome for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsOutcome for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			ResultsOutcome theResultsOutcome = (ResultsOutcome)findAncestorWithClass(this, ResultsOutcome.class);
			return theResultsOutcome.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsOutcome for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsOutcome for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			ResultsOutcome theResultsOutcome = (ResultsOutcome)findAncestorWithClass(this, ResultsOutcome.class);
			theResultsOutcome.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsOutcome for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsOutcome for ID tag ");
		}
	}

}
