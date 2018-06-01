package edu.uiowa.slis.ClinicalTrialsTagLib.contact;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ContactLocseqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ContactLocseqnum.class);


	public int doStartTag() throws JspException {
		try {
			Contact theContact = (Contact)findAncestorWithClass(this, Contact.class);
			if (!theContact.commitNeeded) {
				pageContext.getOut().print(theContact.getLocseqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Contact for locseqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing Contact for locseqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getLocseqnum() throws JspTagException {
		try {
			Contact theContact = (Contact)findAncestorWithClass(this, Contact.class);
			return theContact.getLocseqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing Contact for locseqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing Contact for locseqnum tag ");
		}
	}

	public void setLocseqnum(int locseqnum) throws JspTagException {
		try {
			Contact theContact = (Contact)findAncestorWithClass(this, Contact.class);
			theContact.setLocseqnum(locseqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing Contact for locseqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing Contact for locseqnum tag ");
		}
	}

}
