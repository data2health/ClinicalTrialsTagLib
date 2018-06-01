package edu.uiowa.slis.ClinicalTrialsTagLib.location;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class LocationStatus extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(LocationStatus.class);


	public int doStartTag() throws JspException {
		try {
			Location theLocation = (Location)findAncestorWithClass(this, Location.class);
			if (!theLocation.commitNeeded) {
				pageContext.getOut().print(theLocation.getStatus());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Location for status tag ", e);
			throw new JspTagException("Error: Can't find enclosing Location for status tag ");
		}
		return SKIP_BODY;
	}

	public String getStatus() throws JspTagException {
		try {
			Location theLocation = (Location)findAncestorWithClass(this, Location.class);
			return theLocation.getStatus();
		} catch (Exception e) {
			log.error(" Can't find enclosing Location for status tag ", e);
			throw new JspTagException("Error: Can't find enclosing Location for status tag ");
		}
	}

	public void setStatus(String status) throws JspTagException {
		try {
			Location theLocation = (Location)findAncestorWithClass(this, Location.class);
			theLocation.setStatus(status);
		} catch (Exception e) {
			log.error("Can't find enclosing Location for status tag ", e);
			throw new JspTagException("Error: Can't find enclosing Location for status tag ");
		}
	}

}
