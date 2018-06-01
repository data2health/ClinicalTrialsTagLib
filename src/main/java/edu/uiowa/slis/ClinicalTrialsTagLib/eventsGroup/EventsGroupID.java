package edu.uiowa.slis.ClinicalTrialsTagLib.eventsGroup;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class EventsGroupID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(EventsGroupID.class);


	public int doStartTag() throws JspException {
		try {
			EventsGroup theEventsGroup = (EventsGroup)findAncestorWithClass(this, EventsGroup.class);
			if (!theEventsGroup.commitNeeded) {
				pageContext.getOut().print(theEventsGroup.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing EventsGroup for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing EventsGroup for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			EventsGroup theEventsGroup = (EventsGroup)findAncestorWithClass(this, EventsGroup.class);
			return theEventsGroup.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing EventsGroup for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing EventsGroup for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			EventsGroup theEventsGroup = (EventsGroup)findAncestorWithClass(this, EventsGroup.class);
			theEventsGroup.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing EventsGroup for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing EventsGroup for ID tag ");
		}
	}

}
