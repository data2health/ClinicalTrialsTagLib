package edu.uiowa.slis.ClinicalTrialsTagLib.overallContact;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class OverallContactMiddleName extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(OverallContactMiddleName.class);


	public int doStartTag() throws JspException {
		try {
			OverallContact theOverallContact = (OverallContact)findAncestorWithClass(this, OverallContact.class);
			if (!theOverallContact.commitNeeded) {
				pageContext.getOut().print(theOverallContact.getMiddleName());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing OverallContact for middleName tag ", e);
			throw new JspTagException("Error: Can't find enclosing OverallContact for middleName tag ");
		}
		return SKIP_BODY;
	}

	public String getMiddleName() throws JspTagException {
		try {
			OverallContact theOverallContact = (OverallContact)findAncestorWithClass(this, OverallContact.class);
			return theOverallContact.getMiddleName();
		} catch (Exception e) {
			log.error(" Can't find enclosing OverallContact for middleName tag ", e);
			throw new JspTagException("Error: Can't find enclosing OverallContact for middleName tag ");
		}
	}

	public void setMiddleName(String middleName) throws JspTagException {
		try {
			OverallContact theOverallContact = (OverallContact)findAncestorWithClass(this, OverallContact.class);
			theOverallContact.setMiddleName(middleName);
		} catch (Exception e) {
			log.error("Can't find enclosing OverallContact for middleName tag ", e);
			throw new JspTagException("Error: Can't find enclosing OverallContact for middleName tag ");
		}
	}

}
