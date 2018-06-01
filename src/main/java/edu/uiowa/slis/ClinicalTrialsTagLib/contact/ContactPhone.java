package edu.uiowa.slis.ClinicalTrialsTagLib.contact;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ContactPhone extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ContactPhone.class);


	public int doStartTag() throws JspException {
		try {
			Contact theContact = (Contact)findAncestorWithClass(this, Contact.class);
			if (!theContact.commitNeeded) {
				pageContext.getOut().print(theContact.getPhone());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Contact for phone tag ", e);
			throw new JspTagException("Error: Can't find enclosing Contact for phone tag ");
		}
		return SKIP_BODY;
	}

	public String getPhone() throws JspTagException {
		try {
			Contact theContact = (Contact)findAncestorWithClass(this, Contact.class);
			return theContact.getPhone();
		} catch (Exception e) {
			log.error(" Can't find enclosing Contact for phone tag ", e);
			throw new JspTagException("Error: Can't find enclosing Contact for phone tag ");
		}
	}

	public void setPhone(String phone) throws JspTagException {
		try {
			Contact theContact = (Contact)findAncestorWithClass(this, Contact.class);
			theContact.setPhone(phone);
		} catch (Exception e) {
			log.error("Can't find enclosing Contact for phone tag ", e);
			throw new JspTagException("Error: Can't find enclosing Contact for phone tag ");
		}
	}

}
