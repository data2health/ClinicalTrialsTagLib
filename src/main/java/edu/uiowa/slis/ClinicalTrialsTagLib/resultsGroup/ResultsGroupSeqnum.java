package edu.uiowa.slis.ClinicalTrialsTagLib.resultsGroup;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsGroupSeqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsGroupSeqnum.class);


	public int doStartTag() throws JspException {
		try {
			ResultsGroup theResultsGroup = (ResultsGroup)findAncestorWithClass(this, ResultsGroup.class);
			if (!theResultsGroup.commitNeeded) {
				pageContext.getOut().print(theResultsGroup.getSeqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsGroup for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsGroup for seqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getSeqnum() throws JspTagException {
		try {
			ResultsGroup theResultsGroup = (ResultsGroup)findAncestorWithClass(this, ResultsGroup.class);
			return theResultsGroup.getSeqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsGroup for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsGroup for seqnum tag ");
		}
	}

	public void setSeqnum(int seqnum) throws JspTagException {
		try {
			ResultsGroup theResultsGroup = (ResultsGroup)findAncestorWithClass(this, ResultsGroup.class);
			theResultsGroup.setSeqnum(seqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsGroup for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsGroup for seqnum tag ");
		}
	}

}
