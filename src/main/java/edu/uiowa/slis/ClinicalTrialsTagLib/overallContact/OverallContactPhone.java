package edu.uiowa.slis.ClinicalTrialsTagLib.overallContact;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class OverallContactPhone extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(OverallContactPhone.class);


	public int doStartTag() throws JspException {
		try {
			OverallContact theOverallContact = (OverallContact)findAncestorWithClass(this, OverallContact.class);
			if (!theOverallContact.commitNeeded) {
				pageContext.getOut().print(theOverallContact.getPhone());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing OverallContact for phone tag ", e);
			throw new JspTagException("Error: Can't find enclosing OverallContact for phone tag ");
		}
		return SKIP_BODY;
	}

	public String getPhone() throws JspTagException {
		try {
			OverallContact theOverallContact = (OverallContact)findAncestorWithClass(this, OverallContact.class);
			return theOverallContact.getPhone();
		} catch (Exception e) {
			log.error(" Can't find enclosing OverallContact for phone tag ", e);
			throw new JspTagException("Error: Can't find enclosing OverallContact for phone tag ");
		}
	}

	public void setPhone(String phone) throws JspTagException {
		try {
			OverallContact theOverallContact = (OverallContact)findAncestorWithClass(this, OverallContact.class);
			theOverallContact.setPhone(phone);
		} catch (Exception e) {
			log.error("Can't find enclosing OverallContact for phone tag ", e);
			throw new JspTagException("Error: Can't find enclosing OverallContact for phone tag ");
		}
	}

}
