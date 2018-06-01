package edu.uiowa.slis.ClinicalTrialsTagLib.resultsReference;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsReferenceSeqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsReferenceSeqnum.class);


	public int doStartTag() throws JspException {
		try {
			ResultsReference theResultsReference = (ResultsReference)findAncestorWithClass(this, ResultsReference.class);
			if (!theResultsReference.commitNeeded) {
				pageContext.getOut().print(theResultsReference.getSeqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsReference for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsReference for seqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getSeqnum() throws JspTagException {
		try {
			ResultsReference theResultsReference = (ResultsReference)findAncestorWithClass(this, ResultsReference.class);
			return theResultsReference.getSeqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsReference for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsReference for seqnum tag ");
		}
	}

	public void setSeqnum(int seqnum) throws JspTagException {
		try {
			ResultsReference theResultsReference = (ResultsReference)findAncestorWithClass(this, ResultsReference.class);
			theResultsReference.setSeqnum(seqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsReference for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsReference for seqnum tag ");
		}
	}

}
