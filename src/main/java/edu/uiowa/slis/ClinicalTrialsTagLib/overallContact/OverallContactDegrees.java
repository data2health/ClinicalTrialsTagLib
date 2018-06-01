package edu.uiowa.slis.ClinicalTrialsTagLib.overallContact;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class OverallContactDegrees extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(OverallContactDegrees.class);


	public int doStartTag() throws JspException {
		try {
			OverallContact theOverallContact = (OverallContact)findAncestorWithClass(this, OverallContact.class);
			if (!theOverallContact.commitNeeded) {
				pageContext.getOut().print(theOverallContact.getDegrees());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing OverallContact for degrees tag ", e);
			throw new JspTagException("Error: Can't find enclosing OverallContact for degrees tag ");
		}
		return SKIP_BODY;
	}

	public String getDegrees() throws JspTagException {
		try {
			OverallContact theOverallContact = (OverallContact)findAncestorWithClass(this, OverallContact.class);
			return theOverallContact.getDegrees();
		} catch (Exception e) {
			log.error(" Can't find enclosing OverallContact for degrees tag ", e);
			throw new JspTagException("Error: Can't find enclosing OverallContact for degrees tag ");
		}
	}

	public void setDegrees(String degrees) throws JspTagException {
		try {
			OverallContact theOverallContact = (OverallContact)findAncestorWithClass(this, OverallContact.class);
			theOverallContact.setDegrees(degrees);
		} catch (Exception e) {
			log.error("Can't find enclosing OverallContact for degrees tag ", e);
			throw new JspTagException("Error: Can't find enclosing OverallContact for degrees tag ");
		}
	}

}
