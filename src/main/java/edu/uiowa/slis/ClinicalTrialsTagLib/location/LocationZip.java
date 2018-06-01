package edu.uiowa.slis.ClinicalTrialsTagLib.location;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class LocationZip extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(LocationZip.class);


	public int doStartTag() throws JspException {
		try {
			Location theLocation = (Location)findAncestorWithClass(this, Location.class);
			if (!theLocation.commitNeeded) {
				pageContext.getOut().print(theLocation.getZip());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Location for zip tag ", e);
			throw new JspTagException("Error: Can't find enclosing Location for zip tag ");
		}
		return SKIP_BODY;
	}

	public String getZip() throws JspTagException {
		try {
			Location theLocation = (Location)findAncestorWithClass(this, Location.class);
			return theLocation.getZip();
		} catch (Exception e) {
			log.error(" Can't find enclosing Location for zip tag ", e);
			throw new JspTagException("Error: Can't find enclosing Location for zip tag ");
		}
	}

	public void setZip(String zip) throws JspTagException {
		try {
			Location theLocation = (Location)findAncestorWithClass(this, Location.class);
			theLocation.setZip(zip);
		} catch (Exception e) {
			log.error("Can't find enclosing Location for zip tag ", e);
			throw new JspTagException("Error: Can't find enclosing Location for zip tag ");
		}
	}

}
