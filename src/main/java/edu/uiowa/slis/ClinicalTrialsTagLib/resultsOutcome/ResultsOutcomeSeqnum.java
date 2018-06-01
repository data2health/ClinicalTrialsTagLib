package edu.uiowa.slis.ClinicalTrialsTagLib.resultsOutcome;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsOutcomeSeqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsOutcomeSeqnum.class);


	public int doStartTag() throws JspException {
		try {
			ResultsOutcome theResultsOutcome = (ResultsOutcome)findAncestorWithClass(this, ResultsOutcome.class);
			if (!theResultsOutcome.commitNeeded) {
				pageContext.getOut().print(theResultsOutcome.getSeqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsOutcome for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsOutcome for seqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getSeqnum() throws JspTagException {
		try {
			ResultsOutcome theResultsOutcome = (ResultsOutcome)findAncestorWithClass(this, ResultsOutcome.class);
			return theResultsOutcome.getSeqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsOutcome for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsOutcome for seqnum tag ");
		}
	}

	public void setSeqnum(int seqnum) throws JspTagException {
		try {
			ResultsOutcome theResultsOutcome = (ResultsOutcome)findAncestorWithClass(this, ResultsOutcome.class);
			theResultsOutcome.setSeqnum(seqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsOutcome for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsOutcome for seqnum tag ");
		}
	}

}
