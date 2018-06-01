package edu.uiowa.slis.ClinicalTrialsTagLib.contact;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ContactPhoneExt extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ContactPhoneExt.class);


	public int doStartTag() throws JspException {
		try {
			Contact theContact = (Contact)findAncestorWithClass(this, Contact.class);
			if (!theContact.commitNeeded) {
				pageContext.getOut().print(theContact.getPhoneExt());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Contact for phoneExt tag ", e);
			throw new JspTagException("Error: Can't find enclosing Contact for phoneExt tag ");
		}
		return SKIP_BODY;
	}

	public String getPhoneExt() throws JspTagException {
		try {
			Contact theContact = (Contact)findAncestorWithClass(this, Contact.class);
			return theContact.getPhoneExt();
		} catch (Exception e) {
			log.error(" Can't find enclosing Contact for phoneExt tag ", e);
			throw new JspTagException("Error: Can't find enclosing Contact for phoneExt tag ");
		}
	}

	public void setPhoneExt(String phoneExt) throws JspTagException {
		try {
			Contact theContact = (Contact)findAncestorWithClass(this, Contact.class);
			theContact.setPhoneExt(phoneExt);
		} catch (Exception e) {
			log.error("Can't find enclosing Contact for phoneExt tag ", e);
			throw new JspTagException("Error: Can't find enclosing Contact for phoneExt tag ");
		}
	}

}
