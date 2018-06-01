package edu.uiowa.slis.ClinicalTrialsTagLib.location;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class LocationCountry extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(LocationCountry.class);


	public int doStartTag() throws JspException {
		try {
			Location theLocation = (Location)findAncestorWithClass(this, Location.class);
			if (!theLocation.commitNeeded) {
				pageContext.getOut().print(theLocation.getCountry());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Location for country tag ", e);
			throw new JspTagException("Error: Can't find enclosing Location for country tag ");
		}
		return SKIP_BODY;
	}

	public String getCountry() throws JspTagException {
		try {
			Location theLocation = (Location)findAncestorWithClass(this, Location.class);
			return theLocation.getCountry();
		} catch (Exception e) {
			log.error(" Can't find enclosing Location for country tag ", e);
			throw new JspTagException("Error: Can't find enclosing Location for country tag ");
		}
	}

	public void setCountry(String country) throws JspTagException {
		try {
			Location theLocation = (Location)findAncestorWithClass(this, Location.class);
			theLocation.setCountry(country);
		} catch (Exception e) {
			log.error("Can't find enclosing Location for country tag ", e);
			throw new JspTagException("Error: Can't find enclosing Location for country tag ");
		}
	}

}
