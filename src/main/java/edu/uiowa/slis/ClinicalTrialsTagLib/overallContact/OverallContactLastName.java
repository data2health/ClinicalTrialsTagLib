package edu.uiowa.slis.ClinicalTrialsTagLib.overallContact;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class OverallContactLastName extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(OverallContactLastName.class);


	public int doStartTag() throws JspException {
		try {
			OverallContact theOverallContact = (OverallContact)findAncestorWithClass(this, OverallContact.class);
			if (!theOverallContact.commitNeeded) {
				pageContext.getOut().print(theOverallContact.getLastName());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing OverallContact for lastName tag ", e);
			throw new JspTagException("Error: Can't find enclosing OverallContact for lastName tag ");
		}
		return SKIP_BODY;
	}

	public String getLastName() throws JspTagException {
		try {
			OverallContact theOverallContact = (OverallContact)findAncestorWithClass(this, OverallContact.class);
			return theOverallContact.getLastName();
		} catch (Exception e) {
			log.error(" Can't find enclosing OverallContact for lastName tag ", e);
			throw new JspTagException("Error: Can't find enclosing OverallContact for lastName tag ");
		}
	}

	public void setLastName(String lastName) throws JspTagException {
		try {
			OverallContact theOverallContact = (OverallContact)findAncestorWithClass(this, OverallContact.class);
			theOverallContact.setLastName(lastName);
		} catch (Exception e) {
			log.error("Can't find enclosing OverallContact for lastName tag ", e);
			throw new JspTagException("Error: Can't find enclosing OverallContact for lastName tag ");
		}
	}

}
