package edu.uiowa.slis.ClinicalTrialsTagLib.resultsAnalysisGroup;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsAnalysisGroupSeqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsAnalysisGroupSeqnum.class);


	public int doStartTag() throws JspException {
		try {
			ResultsAnalysisGroup theResultsAnalysisGroup = (ResultsAnalysisGroup)findAncestorWithClass(this, ResultsAnalysisGroup.class);
			if (!theResultsAnalysisGroup.commitNeeded) {
				pageContext.getOut().print(theResultsAnalysisGroup.getSeqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsAnalysisGroup for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysisGroup for seqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getSeqnum() throws JspTagException {
		try {
			ResultsAnalysisGroup theResultsAnalysisGroup = (ResultsAnalysisGroup)findAncestorWithClass(this, ResultsAnalysisGroup.class);
			return theResultsAnalysisGroup.getSeqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsAnalysisGroup for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysisGroup for seqnum tag ");
		}
	}

	public void setSeqnum(int seqnum) throws JspTagException {
		try {
			ResultsAnalysisGroup theResultsAnalysisGroup = (ResultsAnalysisGroup)findAncestorWithClass(this, ResultsAnalysisGroup.class);
			theResultsAnalysisGroup.setSeqnum(seqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsAnalysisGroup for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysisGroup for seqnum tag ");
		}
	}

}
