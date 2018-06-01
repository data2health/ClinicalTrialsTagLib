package edu.uiowa.slis.ClinicalTrialsTagLib.location;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class LocationState extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(LocationState.class);


	public int doStartTag() throws JspException {
		try {
			Location theLocation = (Location)findAncestorWithClass(this, Location.class);
			if (!theLocation.commitNeeded) {
				pageContext.getOut().print(theLocation.getState());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Location for state tag ", e);
			throw new JspTagException("Error: Can't find enclosing Location for state tag ");
		}
		return SKIP_BODY;
	}

	public String getState() throws JspTagException {
		try {
			Location theLocation = (Location)findAncestorWithClass(this, Location.class);
			return theLocation.getState();
		} catch (Exception e) {
			log.error(" Can't find enclosing Location for state tag ", e);
			throw new JspTagException("Error: Can't find enclosing Location for state tag ");
		}
	}

	public void setState(String state) throws JspTagException {
		try {
			Location theLocation = (Location)findAncestorWithClass(this, Location.class);
			theLocation.setState(state);
		} catch (Exception e) {
			log.error("Can't find enclosing Location for state tag ", e);
			throw new JspTagException("Error: Can't find enclosing Location for state tag ");
		}
	}

}
