package edu.uiowa.slis.ClinicalTrialsTagLib.contact;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ContactEmail extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ContactEmail.class);


	public int doStartTag() throws JspException {
		try {
			Contact theContact = (Contact)findAncestorWithClass(this, Contact.class);
			if (!theContact.commitNeeded) {
				pageContext.getOut().print(theContact.getEmail());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Contact for email tag ", e);
			throw new JspTagException("Error: Can't find enclosing Contact for email tag ");
		}
		return SKIP_BODY;
	}

	public String getEmail() throws JspTagException {
		try {
			Contact theContact = (Contact)findAncestorWithClass(this, Contact.class);
			return theContact.getEmail();
		} catch (Exception e) {
			log.error(" Can't find enclosing Contact for email tag ", e);
			throw new JspTagException("Error: Can't find enclosing Contact for email tag ");
		}
	}

	public void setEmail(String email) throws JspTagException {
		try {
			Contact theContact = (Contact)findAncestorWithClass(this, Contact.class);
			theContact.setEmail(email);
		} catch (Exception e) {
			log.error("Can't find enclosing Contact for email tag ", e);
			throw new JspTagException("Error: Can't find enclosing Contact for email tag ");
		}
	}

}
