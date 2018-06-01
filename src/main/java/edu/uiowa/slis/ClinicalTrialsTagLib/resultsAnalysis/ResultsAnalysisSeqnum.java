package edu.uiowa.slis.ClinicalTrialsTagLib.resultsAnalysis;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsAnalysisSeqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsAnalysisSeqnum.class);


	public int doStartTag() throws JspException {
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			if (!theResultsAnalysis.commitNeeded) {
				pageContext.getOut().print(theResultsAnalysis.getSeqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsAnalysis for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysis for seqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getSeqnum() throws JspTagException {
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			return theResultsAnalysis.getSeqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsAnalysis for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysis for seqnum tag ");
		}
	}

	public void setSeqnum(int seqnum) throws JspTagException {
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			theResultsAnalysis.setSeqnum(seqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsAnalysis for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysis for seqnum tag ");
		}
	}

}
