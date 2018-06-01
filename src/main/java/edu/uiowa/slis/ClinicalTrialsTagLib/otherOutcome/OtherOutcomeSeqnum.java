package edu.uiowa.slis.ClinicalTrialsTagLib.otherOutcome;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class OtherOutcomeSeqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(OtherOutcomeSeqnum.class);


	public int doStartTag() throws JspException {
		try {
			OtherOutcome theOtherOutcome = (OtherOutcome)findAncestorWithClass(this, OtherOutcome.class);
			if (!theOtherOutcome.commitNeeded) {
				pageContext.getOut().print(theOtherOutcome.getSeqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing OtherOutcome for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherOutcome for seqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getSeqnum() throws JspTagException {
		try {
			OtherOutcome theOtherOutcome = (OtherOutcome)findAncestorWithClass(this, OtherOutcome.class);
			return theOtherOutcome.getSeqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing OtherOutcome for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherOutcome for seqnum tag ");
		}
	}

	public void setSeqnum(int seqnum) throws JspTagException {
		try {
			OtherOutcome theOtherOutcome = (OtherOutcome)findAncestorWithClass(this, OtherOutcome.class);
			theOtherOutcome.setSeqnum(seqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing OtherOutcome for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherOutcome for seqnum tag ");
		}
	}

}
