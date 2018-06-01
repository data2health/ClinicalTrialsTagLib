package edu.uiowa.slis.ClinicalTrialsTagLib.eligibility;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class EligibilitySeqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(EligibilitySeqnum.class);


	public int doStartTag() throws JspException {
		try {
			Eligibility theEligibility = (Eligibility)findAncestorWithClass(this, Eligibility.class);
			if (!theEligibility.commitNeeded) {
				pageContext.getOut().print(theEligibility.getSeqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Eligibility for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing Eligibility for seqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getSeqnum() throws JspTagException {
		try {
			Eligibility theEligibility = (Eligibility)findAncestorWithClass(this, Eligibility.class);
			return theEligibility.getSeqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing Eligibility for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing Eligibility for seqnum tag ");
		}
	}

	public void setSeqnum(int seqnum) throws JspTagException {
		try {
			Eligibility theEligibility = (Eligibility)findAncestorWithClass(this, Eligibility.class);
			theEligibility.setSeqnum(seqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing Eligibility for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing Eligibility for seqnum tag ");
		}
	}

}
