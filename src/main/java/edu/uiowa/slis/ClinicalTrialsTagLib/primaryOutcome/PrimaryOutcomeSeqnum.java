package edu.uiowa.slis.ClinicalTrialsTagLib.primaryOutcome;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class PrimaryOutcomeSeqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(PrimaryOutcomeSeqnum.class);


	public int doStartTag() throws JspException {
		try {
			PrimaryOutcome thePrimaryOutcome = (PrimaryOutcome)findAncestorWithClass(this, PrimaryOutcome.class);
			if (!thePrimaryOutcome.commitNeeded) {
				pageContext.getOut().print(thePrimaryOutcome.getSeqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing PrimaryOutcome for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing PrimaryOutcome for seqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getSeqnum() throws JspTagException {
		try {
			PrimaryOutcome thePrimaryOutcome = (PrimaryOutcome)findAncestorWithClass(this, PrimaryOutcome.class);
			return thePrimaryOutcome.getSeqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing PrimaryOutcome for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing PrimaryOutcome for seqnum tag ");
		}
	}

	public void setSeqnum(int seqnum) throws JspTagException {
		try {
			PrimaryOutcome thePrimaryOutcome = (PrimaryOutcome)findAncestorWithClass(this, PrimaryOutcome.class);
			thePrimaryOutcome.setSeqnum(seqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing PrimaryOutcome for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing PrimaryOutcome for seqnum tag ");
		}
	}

}
