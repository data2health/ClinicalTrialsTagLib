package edu.uiowa.slis.ClinicalTrialsTagLib.location;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class LocationCity extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(LocationCity.class);


	public int doStartTag() throws JspException {
		try {
			Location theLocation = (Location)findAncestorWithClass(this, Location.class);
			if (!theLocation.commitNeeded) {
				pageContext.getOut().print(theLocation.getCity());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Location for city tag ", e);
			throw new JspTagException("Error: Can't find enclosing Location for city tag ");
		}
		return SKIP_BODY;
	}

	public String getCity() throws JspTagException {
		try {
			Location theLocation = (Location)findAncestorWithClass(this, Location.class);
			return theLocation.getCity();
		} catch (Exception e) {
			log.error(" Can't find enclosing Location for city tag ", e);
			throw new JspTagException("Error: Can't find enclosing Location for city tag ");
		}
	}

	public void setCity(String city) throws JspTagException {
		try {
			Location theLocation = (Location)findAncestorWithClass(this, Location.class);
			theLocation.setCity(city);
		} catch (Exception e) {
			log.error("Can't find enclosing Location for city tag ", e);
			throw new JspTagException("Error: Can't find enclosing Location for city tag ");
		}
	}

}
