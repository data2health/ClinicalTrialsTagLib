package edu.uiowa.slis.ClinicalTrialsTagLib.contact;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ContactID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ContactID.class);


	public int doStartTag() throws JspException {
		try {
			Contact theContact = (Contact)findAncestorWithClass(this, Contact.class);
			if (!theContact.commitNeeded) {
				pageContext.getOut().print(theContact.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Contact for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing Contact for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			Contact theContact = (Contact)findAncestorWithClass(this, Contact.class);
			return theContact.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing Contact for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing Contact for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			Contact theContact = (Contact)findAncestorWithClass(this, Contact.class);
			theContact.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing Contact for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing Contact for ID tag ");
		}
	}

}
