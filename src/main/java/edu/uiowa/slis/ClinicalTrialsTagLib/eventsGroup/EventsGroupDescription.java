package edu.uiowa.slis.ClinicalTrialsTagLib.eventsGroup;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class EventsGroupDescription extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(EventsGroupDescription.class);


	public int doStartTag() throws JspException {
		try {
			EventsGroup theEventsGroup = (EventsGroup)findAncestorWithClass(this, EventsGroup.class);
			if (!theEventsGroup.commitNeeded) {
				pageContext.getOut().print(theEventsGroup.getDescription());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing EventsGroup for description tag ", e);
			throw new JspTagException("Error: Can't find enclosing EventsGroup for description tag ");
		}
		return SKIP_BODY;
	}

	public String getDescription() throws JspTagException {
		try {
			EventsGroup theEventsGroup = (EventsGroup)findAncestorWithClass(this, EventsGroup.class);
			return theEventsGroup.getDescription();
		} catch (Exception e) {
			log.error(" Can't find enclosing EventsGroup for description tag ", e);
			throw new JspTagException("Error: Can't find enclosing EventsGroup for description tag ");
		}
	}

	public void setDescription(String description) throws JspTagException {
		try {
			EventsGroup theEventsGroup = (EventsGroup)findAncestorWithClass(this, EventsGroup.class);
			theEventsGroup.setDescription(description);
		} catch (Exception e) {
			log.error("Can't find enclosing EventsGroup for description tag ", e);
			throw new JspTagException("Error: Can't find enclosing EventsGroup for description tag ");
		}
	}

}
