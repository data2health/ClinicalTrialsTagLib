package edu.uiowa.slis.ClinicalTrialsTagLib.overallContact;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class OverallContactID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(OverallContactID.class);


	public int doStartTag() throws JspException {
		try {
			OverallContact theOverallContact = (OverallContact)findAncestorWithClass(this, OverallContact.class);
			if (!theOverallContact.commitNeeded) {
				pageContext.getOut().print(theOverallContact.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing OverallContact for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing OverallContact for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			OverallContact theOverallContact = (OverallContact)findAncestorWithClass(this, OverallContact.class);
			return theOverallContact.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing OverallContact for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing OverallContact for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			OverallContact theOverallContact = (OverallContact)findAncestorWithClass(this, OverallContact.class);
			theOverallContact.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing OverallContact for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing OverallContact for ID tag ");
		}
	}

}
