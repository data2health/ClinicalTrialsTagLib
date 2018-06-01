package edu.uiowa.slis.ClinicalTrialsTagLib.overallContact;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class OverallContactFirstName extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(OverallContactFirstName.class);


	public int doStartTag() throws JspException {
		try {
			OverallContact theOverallContact = (OverallContact)findAncestorWithClass(this, OverallContact.class);
			if (!theOverallContact.commitNeeded) {
				pageContext.getOut().print(theOverallContact.getFirstName());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing OverallContact for firstName tag ", e);
			throw new JspTagException("Error: Can't find enclosing OverallContact for firstName tag ");
		}
		return SKIP_BODY;
	}

	public String getFirstName() throws JspTagException {
		try {
			OverallContact theOverallContact = (OverallContact)findAncestorWithClass(this, OverallContact.class);
			return theOverallContact.getFirstName();
		} catch (Exception e) {
			log.error(" Can't find enclosing OverallContact for firstName tag ", e);
			throw new JspTagException("Error: Can't find enclosing OverallContact for firstName tag ");
		}
	}

	public void setFirstName(String firstName) throws JspTagException {
		try {
			OverallContact theOverallContact = (OverallContact)findAncestorWithClass(this, OverallContact.class);
			theOverallContact.setFirstName(firstName);
		} catch (Exception e) {
			log.error("Can't find enclosing OverallContact for firstName tag ", e);
			throw new JspTagException("Error: Can't find enclosing OverallContact for firstName tag ");
		}
	}

}
