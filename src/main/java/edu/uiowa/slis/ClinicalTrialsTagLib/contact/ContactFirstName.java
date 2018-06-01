package edu.uiowa.slis.ClinicalTrialsTagLib.contact;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ContactFirstName extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ContactFirstName.class);


	public int doStartTag() throws JspException {
		try {
			Contact theContact = (Contact)findAncestorWithClass(this, Contact.class);
			if (!theContact.commitNeeded) {
				pageContext.getOut().print(theContact.getFirstName());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Contact for firstName tag ", e);
			throw new JspTagException("Error: Can't find enclosing Contact for firstName tag ");
		}
		return SKIP_BODY;
	}

	public String getFirstName() throws JspTagException {
		try {
			Contact theContact = (Contact)findAncestorWithClass(this, Contact.class);
			return theContact.getFirstName();
		} catch (Exception e) {
			log.error(" Can't find enclosing Contact for firstName tag ", e);
			throw new JspTagException("Error: Can't find enclosing Contact for firstName tag ");
		}
	}

	public void setFirstName(String firstName) throws JspTagException {
		try {
			Contact theContact = (Contact)findAncestorWithClass(this, Contact.class);
			theContact.setFirstName(firstName);
		} catch (Exception e) {
			log.error("Can't find enclosing Contact for firstName tag ", e);
			throw new JspTagException("Error: Can't find enclosing Contact for firstName tag ");
		}
	}

}
