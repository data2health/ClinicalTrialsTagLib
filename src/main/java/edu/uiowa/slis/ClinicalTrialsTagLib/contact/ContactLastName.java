package edu.uiowa.slis.ClinicalTrialsTagLib.contact;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ContactLastName extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ContactLastName.class);


	public int doStartTag() throws JspException {
		try {
			Contact theContact = (Contact)findAncestorWithClass(this, Contact.class);
			if (!theContact.commitNeeded) {
				pageContext.getOut().print(theContact.getLastName());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Contact for lastName tag ", e);
			throw new JspTagException("Error: Can't find enclosing Contact for lastName tag ");
		}
		return SKIP_BODY;
	}

	public String getLastName() throws JspTagException {
		try {
			Contact theContact = (Contact)findAncestorWithClass(this, Contact.class);
			return theContact.getLastName();
		} catch (Exception e) {
			log.error(" Can't find enclosing Contact for lastName tag ", e);
			throw new JspTagException("Error: Can't find enclosing Contact for lastName tag ");
		}
	}

	public void setLastName(String lastName) throws JspTagException {
		try {
			Contact theContact = (Contact)findAncestorWithClass(this, Contact.class);
			theContact.setLastName(lastName);
		} catch (Exception e) {
			log.error("Can't find enclosing Contact for lastName tag ", e);
			throw new JspTagException("Error: Can't find enclosing Contact for lastName tag ");
		}
	}

}
