package edu.uiowa.slis.ClinicalTrialsTagLib.resultsOutcome;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsOutcomePopulation extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsOutcomePopulation.class);


	public int doStartTag() throws JspException {
		try {
			ResultsOutcome theResultsOutcome = (ResultsOutcome)findAncestorWithClass(this, ResultsOutcome.class);
			if (!theResultsOutcome.commitNeeded) {
				pageContext.getOut().print(theResultsOutcome.getPopulation());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsOutcome for population tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsOutcome for population tag ");
		}
		return SKIP_BODY;
	}

	public String getPopulation() throws JspTagException {
		try {
			ResultsOutcome theResultsOutcome = (ResultsOutcome)findAncestorWithClass(this, ResultsOutcome.class);
			return theResultsOutcome.getPopulation();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsOutcome for population tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsOutcome for population tag ");
		}
	}

	public void setPopulation(String population) throws JspTagException {
		try {
			ResultsOutcome theResultsOutcome = (ResultsOutcome)findAncestorWithClass(this, ResultsOutcome.class);
			theResultsOutcome.setPopulation(population);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsOutcome for population tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsOutcome for population tag ");
		}
	}

}
