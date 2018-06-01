package edu.uiowa.slis.ClinicalTrialsTagLib.secondaryOutcome;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class SecondaryOutcomeSeqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(SecondaryOutcomeSeqnum.class);


	public int doStartTag() throws JspException {
		try {
			SecondaryOutcome theSecondaryOutcome = (SecondaryOutcome)findAncestorWithClass(this, SecondaryOutcome.class);
			if (!theSecondaryOutcome.commitNeeded) {
				pageContext.getOut().print(theSecondaryOutcome.getSeqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing SecondaryOutcome for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing SecondaryOutcome for seqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getSeqnum() throws JspTagException {
		try {
			SecondaryOutcome theSecondaryOutcome = (SecondaryOutcome)findAncestorWithClass(this, SecondaryOutcome.class);
			return theSecondaryOutcome.getSeqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing SecondaryOutcome for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing SecondaryOutcome for seqnum tag ");
		}
	}

	public void setSeqnum(int seqnum) throws JspTagException {
		try {
			SecondaryOutcome theSecondaryOutcome = (SecondaryOutcome)findAncestorWithClass(this, SecondaryOutcome.class);
			theSecondaryOutcome.setSeqnum(seqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing SecondaryOutcome for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing SecondaryOutcome for seqnum tag ");
		}
	}

}
