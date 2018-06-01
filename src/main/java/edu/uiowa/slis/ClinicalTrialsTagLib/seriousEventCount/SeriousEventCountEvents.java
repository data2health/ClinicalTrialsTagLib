package edu.uiowa.slis.ClinicalTrialsTagLib.seriousEventCount;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class SeriousEventCountEvents extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(SeriousEventCountEvents.class);


	public int doStartTag() throws JspException {
		try {
			SeriousEventCount theSeriousEventCount = (SeriousEventCount)findAncestorWithClass(this, SeriousEventCount.class);
			if (!theSeriousEventCount.commitNeeded) {
				pageContext.getOut().print(theSeriousEventCount.getEvents());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing SeriousEventCount for events tag ", e);
			throw new JspTagException("Error: Can't find enclosing SeriousEventCount for events tag ");
		}
		return SKIP_BODY;
	}

	public String getEvents() throws JspTagException {
		try {
			SeriousEventCount theSeriousEventCount = (SeriousEventCount)findAncestorWithClass(this, SeriousEventCount.class);
			return theSeriousEventCount.getEvents();
		} catch (Exception e) {
			log.error(" Can't find enclosing SeriousEventCount for events tag ", e);
			throw new JspTagException("Error: Can't find enclosing SeriousEventCount for events tag ");
		}
	}

	public void setEvents(String events) throws JspTagException {
		try {
			SeriousEventCount theSeriousEventCount = (SeriousEventCount)findAncestorWithClass(this, SeriousEventCount.class);
			theSeriousEventCount.setEvents(events);
		} catch (Exception e) {
			log.error("Can't find enclosing SeriousEventCount for events tag ", e);
			throw new JspTagException("Error: Can't find enclosing SeriousEventCount for events tag ");
		}
	}

}
