package edu.uiowa.slis.ClinicalTrialsTagLib.contact;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ContactDegrees extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ContactDegrees.class);


	public int doStartTag() throws JspException {
		try {
			Contact theContact = (Contact)findAncestorWithClass(this, Contact.class);
			if (!theContact.commitNeeded) {
				pageContext.getOut().print(theContact.getDegrees());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Contact for degrees tag ", e);
			throw new JspTagException("Error: Can't find enclosing Contact for degrees tag ");
		}
		return SKIP_BODY;
	}

	public String getDegrees() throws JspTagException {
		try {
			Contact theContact = (Contact)findAncestorWithClass(this, Contact.class);
			return theContact.getDegrees();
		} catch (Exception e) {
			log.error(" Can't find enclosing Contact for degrees tag ", e);
			throw new JspTagException("Error: Can't find enclosing Contact for degrees tag ");
		}
	}

	public void setDegrees(String degrees) throws JspTagException {
		try {
			Contact theContact = (Contact)findAncestorWithClass(this, Contact.class);
			theContact.setDegrees(degrees);
		} catch (Exception e) {
			log.error("Can't find enclosing Contact for degrees tag ", e);
			throw new JspTagException("Error: Can't find enclosing Contact for degrees tag ");
		}
	}

}
