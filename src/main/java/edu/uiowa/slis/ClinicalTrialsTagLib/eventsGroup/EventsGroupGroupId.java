package edu.uiowa.slis.ClinicalTrialsTagLib.eventsGroup;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class EventsGroupGroupId extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(EventsGroupGroupId.class);


	public int doStartTag() throws JspException {
		try {
			EventsGroup theEventsGroup = (EventsGroup)findAncestorWithClass(this, EventsGroup.class);
			if (!theEventsGroup.commitNeeded) {
				pageContext.getOut().print(theEventsGroup.getGroupId());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing EventsGroup for groupId tag ", e);
			throw new JspTagException("Error: Can't find enclosing EventsGroup for groupId tag ");
		}
		return SKIP_BODY;
	}

	public String getGroupId() throws JspTagException {
		try {
			EventsGroup theEventsGroup = (EventsGroup)findAncestorWithClass(this, EventsGroup.class);
			return theEventsGroup.getGroupId();
		} catch (Exception e) {
			log.error(" Can't find enclosing EventsGroup for groupId tag ", e);
			throw new JspTagException("Error: Can't find enclosing EventsGroup for groupId tag ");
		}
	}

	public void setGroupId(String groupId) throws JspTagException {
		try {
			EventsGroup theEventsGroup = (EventsGroup)findAncestorWithClass(this, EventsGroup.class);
			theEventsGroup.setGroupId(groupId);
		} catch (Exception e) {
			log.error("Can't find enclosing EventsGroup for groupId tag ", e);
			throw new JspTagException("Error: Can't find enclosing EventsGroup for groupId tag ");
		}
	}

}
