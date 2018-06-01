package edu.uiowa.slis.ClinicalTrialsTagLib.otherEvent;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class OtherEventDescription extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(OtherEventDescription.class);


	public int doStartTag() throws JspException {
		try {
			OtherEvent theOtherEvent = (OtherEvent)findAncestorWithClass(this, OtherEvent.class);
			if (!theOtherEvent.commitNeeded) {
				pageContext.getOut().print(theOtherEvent.getDescription());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing OtherEvent for description tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherEvent for description tag ");
		}
		return SKIP_BODY;
	}

	public String getDescription() throws JspTagException {
		try {
			OtherEvent theOtherEvent = (OtherEvent)findAncestorWithClass(this, OtherEvent.class);
			return theOtherEvent.getDescription();
		} catch (Exception e) {
			log.error(" Can't find enclosing OtherEvent for description tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherEvent for description tag ");
		}
	}

	public void setDescription(String description) throws JspTagException {
		try {
			OtherEvent theOtherEvent = (OtherEvent)findAncestorWithClass(this, OtherEvent.class);
			theOtherEvent.setDescription(description);
		} catch (Exception e) {
			log.error("Can't find enclosing OtherEvent for description tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherEvent for description tag ");
		}
	}

}
