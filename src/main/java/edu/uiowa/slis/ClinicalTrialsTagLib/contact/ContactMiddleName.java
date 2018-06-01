package edu.uiowa.slis.ClinicalTrialsTagLib.contact;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ContactMiddleName extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ContactMiddleName.class);


	public int doStartTag() throws JspException {
		try {
			Contact theContact = (Contact)findAncestorWithClass(this, Contact.class);
			if (!theContact.commitNeeded) {
				pageContext.getOut().print(theContact.getMiddleName());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Contact for middleName tag ", e);
			throw new JspTagException("Error: Can't find enclosing Contact for middleName tag ");
		}
		return SKIP_BODY;
	}

	public String getMiddleName() throws JspTagException {
		try {
			Contact theContact = (Contact)findAncestorWithClass(this, Contact.class);
			return theContact.getMiddleName();
		} catch (Exception e) {
			log.error(" Can't find enclosing Contact for middleName tag ", e);
			throw new JspTagException("Error: Can't find enclosing Contact for middleName tag ");
		}
	}

	public void setMiddleName(String middleName) throws JspTagException {
		try {
			Contact theContact = (Contact)findAncestorWithClass(this, Contact.class);
			theContact.setMiddleName(middleName);
		} catch (Exception e) {
			log.error("Can't find enclosing Contact for middleName tag ", e);
			throw new JspTagException("Error: Can't find enclosing Contact for middleName tag ");
		}
	}

}
