package edu.uiowa.slis.ClinicalTrialsTagLib.overallContact;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class OverallContactPhoneExt extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(OverallContactPhoneExt.class);


	public int doStartTag() throws JspException {
		try {
			OverallContact theOverallContact = (OverallContact)findAncestorWithClass(this, OverallContact.class);
			if (!theOverallContact.commitNeeded) {
				pageContext.getOut().print(theOverallContact.getPhoneExt());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing OverallContact for phoneExt tag ", e);
			throw new JspTagException("Error: Can't find enclosing OverallContact for phoneExt tag ");
		}
		return SKIP_BODY;
	}

	public String getPhoneExt() throws JspTagException {
		try {
			OverallContact theOverallContact = (OverallContact)findAncestorWithClass(this, OverallContact.class);
			return theOverallContact.getPhoneExt();
		} catch (Exception e) {
			log.error(" Can't find enclosing OverallContact for phoneExt tag ", e);
			throw new JspTagException("Error: Can't find enclosing OverallContact for phoneExt tag ");
		}
	}

	public void setPhoneExt(String phoneExt) throws JspTagException {
		try {
			OverallContact theOverallContact = (OverallContact)findAncestorWithClass(this, OverallContact.class);
			theOverallContact.setPhoneExt(phoneExt);
		} catch (Exception e) {
			log.error("Can't find enclosing OverallContact for phoneExt tag ", e);
			throw new JspTagException("Error: Can't find enclosing OverallContact for phoneExt tag ");
		}
	}

}
