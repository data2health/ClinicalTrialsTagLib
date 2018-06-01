package edu.uiowa.slis.ClinicalTrialsTagLib.otherEventCount;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class OtherEventCountEvents extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(OtherEventCountEvents.class);


	public int doStartTag() throws JspException {
		try {
			OtherEventCount theOtherEventCount = (OtherEventCount)findAncestorWithClass(this, OtherEventCount.class);
			if (!theOtherEventCount.commitNeeded) {
				pageContext.getOut().print(theOtherEventCount.getEvents());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing OtherEventCount for events tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherEventCount for events tag ");
		}
		return SKIP_BODY;
	}

	public String getEvents() throws JspTagException {
		try {
			OtherEventCount theOtherEventCount = (OtherEventCount)findAncestorWithClass(this, OtherEventCount.class);
			return theOtherEventCount.getEvents();
		} catch (Exception e) {
			log.error(" Can't find enclosing OtherEventCount for events tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherEventCount for events tag ");
		}
	}

	public void setEvents(String events) throws JspTagException {
		try {
			OtherEventCount theOtherEventCount = (OtherEventCount)findAncestorWithClass(this, OtherEventCount.class);
			theOtherEventCount.setEvents(events);
		} catch (Exception e) {
			log.error("Can't find enclosing OtherEventCount for events tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherEventCount for events tag ");
		}
	}

}
