package edu.uiowa.slis.ClinicalTrialsTagLib.contact;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ContactSeqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ContactSeqnum.class);


	public int doStartTag() throws JspException {
		try {
			Contact theContact = (Contact)findAncestorWithClass(this, Contact.class);
			if (!theContact.commitNeeded) {
				pageContext.getOut().print(theContact.getSeqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Contact for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing Contact for seqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getSeqnum() throws JspTagException {
		try {
			Contact theContact = (Contact)findAncestorWithClass(this, Contact.class);
			return theContact.getSeqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing Contact for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing Contact for seqnum tag ");
		}
	}

	public void setSeqnum(int seqnum) throws JspTagException {
		try {
			Contact theContact = (Contact)findAncestorWithClass(this, Contact.class);
			theContact.setSeqnum(seqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing Contact for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing Contact for seqnum tag ");
		}
	}

}
