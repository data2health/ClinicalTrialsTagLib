package edu.uiowa.slis.ClinicalTrialsTagLib.eventsGroup;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class EventsGroupTitle extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(EventsGroupTitle.class);


	public int doStartTag() throws JspException {
		try {
			EventsGroup theEventsGroup = (EventsGroup)findAncestorWithClass(this, EventsGroup.class);
			if (!theEventsGroup.commitNeeded) {
				pageContext.getOut().print(theEventsGroup.getTitle());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing EventsGroup for title tag ", e);
			throw new JspTagException("Error: Can't find enclosing EventsGroup for title tag ");
		}
		return SKIP_BODY;
	}

	public String getTitle() throws JspTagException {
		try {
			EventsGroup theEventsGroup = (EventsGroup)findAncestorWithClass(this, EventsGroup.class);
			return theEventsGroup.getTitle();
		} catch (Exception e) {
			log.error(" Can't find enclosing EventsGroup for title tag ", e);
			throw new JspTagException("Error: Can't find enclosing EventsGroup for title tag ");
		}
	}

	public void setTitle(String title) throws JspTagException {
		try {
			EventsGroup theEventsGroup = (EventsGroup)findAncestorWithClass(this, EventsGroup.class);
			theEventsGroup.setTitle(title);
		} catch (Exception e) {
			log.error("Can't find enclosing EventsGroup for title tag ", e);
			throw new JspTagException("Error: Can't find enclosing EventsGroup for title tag ");
		}
	}

}
