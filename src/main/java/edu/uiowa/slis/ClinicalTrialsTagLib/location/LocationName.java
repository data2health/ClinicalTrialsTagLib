package edu.uiowa.slis.ClinicalTrialsTagLib.location;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class LocationName extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(LocationName.class);


	public int doStartTag() throws JspException {
		try {
			Location theLocation = (Location)findAncestorWithClass(this, Location.class);
			if (!theLocation.commitNeeded) {
				pageContext.getOut().print(theLocation.getName());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Location for name tag ", e);
			throw new JspTagException("Error: Can't find enclosing Location for name tag ");
		}
		return SKIP_BODY;
	}

	public String getName() throws JspTagException {
		try {
			Location theLocation = (Location)findAncestorWithClass(this, Location.class);
			return theLocation.getName();
		} catch (Exception e) {
			log.error(" Can't find enclosing Location for name tag ", e);
			throw new JspTagException("Error: Can't find enclosing Location for name tag ");
		}
	}

	public void setName(String name) throws JspTagException {
		try {
			Location theLocation = (Location)findAncestorWithClass(this, Location.class);
			theLocation.setName(name);
		} catch (Exception e) {
			log.error("Can't find enclosing Location for name tag ", e);
			throw new JspTagException("Error: Can't find enclosing Location for name tag ");
		}
	}

}
