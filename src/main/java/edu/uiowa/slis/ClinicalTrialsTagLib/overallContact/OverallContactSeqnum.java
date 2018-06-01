package edu.uiowa.slis.ClinicalTrialsTagLib.overallContact;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class OverallContactSeqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(OverallContactSeqnum.class);


	public int doStartTag() throws JspException {
		try {
			OverallContact theOverallContact = (OverallContact)findAncestorWithClass(this, OverallContact.class);
			if (!theOverallContact.commitNeeded) {
				pageContext.getOut().print(theOverallContact.getSeqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing OverallContact for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing OverallContact for seqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getSeqnum() throws JspTagException {
		try {
			OverallContact theOverallContact = (OverallContact)findAncestorWithClass(this, OverallContact.class);
			return theOverallContact.getSeqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing OverallContact for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing OverallContact for seqnum tag ");
		}
	}

	public void setSeqnum(int seqnum) throws JspTagException {
		try {
			OverallContact theOverallContact = (OverallContact)findAncestorWithClass(this, OverallContact.class);
			theOverallContact.setSeqnum(seqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing OverallContact for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing OverallContact for seqnum tag ");
		}
	}

}
