package edu.uiowa.slis.ClinicalTrialsTagLib.overallContact;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class OverallContactEmail extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(OverallContactEmail.class);


	public int doStartTag() throws JspException {
		try {
			OverallContact theOverallContact = (OverallContact)findAncestorWithClass(this, OverallContact.class);
			if (!theOverallContact.commitNeeded) {
				pageContext.getOut().print(theOverallContact.getEmail());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing OverallContact for email tag ", e);
			throw new JspTagException("Error: Can't find enclosing OverallContact for email tag ");
		}
		return SKIP_BODY;
	}

	public String getEmail() throws JspTagException {
		try {
			OverallContact theOverallContact = (OverallContact)findAncestorWithClass(this, OverallContact.class);
			return theOverallContact.getEmail();
		} catch (Exception e) {
			log.error(" Can't find enclosing OverallContact for email tag ", e);
			throw new JspTagException("Error: Can't find enclosing OverallContact for email tag ");
		}
	}

	public void setEmail(String email) throws JspTagException {
		try {
			OverallContact theOverallContact = (OverallContact)findAncestorWithClass(this, OverallContact.class);
			theOverallContact.setEmail(email);
		} catch (Exception e) {
			log.error("Can't find enclosing OverallContact for email tag ", e);
			throw new JspTagException("Error: Can't find enclosing OverallContact for email tag ");
		}
	}

}
