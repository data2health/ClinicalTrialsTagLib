package edu.uiowa.slis.ClinicalTrialsTagLib.reportedEvents;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ReportedEventsDescription extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ReportedEventsDescription.class);


	public int doStartTag() throws JspException {
		try {
			ReportedEvents theReportedEvents = (ReportedEvents)findAncestorWithClass(this, ReportedEvents.class);
			if (!theReportedEvents.commitNeeded) {
				pageContext.getOut().print(theReportedEvents.getDescription());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ReportedEvents for description tag ", e);
			throw new JspTagException("Error: Can't find enclosing ReportedEvents for description tag ");
		}
		return SKIP_BODY;
	}

	public String getDescription() throws JspTagException {
		try {
			ReportedEvents theReportedEvents = (ReportedEvents)findAncestorWithClass(this, ReportedEvents.class);
			return theReportedEvents.getDescription();
		} catch (Exception e) {
			log.error(" Can't find enclosing ReportedEvents for description tag ", e);
			throw new JspTagException("Error: Can't find enclosing ReportedEvents for description tag ");
		}
	}

	public void setDescription(String description) throws JspTagException {
		try {
			ReportedEvents theReportedEvents = (ReportedEvents)findAncestorWithClass(this, ReportedEvents.class);
			theReportedEvents.setDescription(description);
		} catch (Exception e) {
			log.error("Can't find enclosing ReportedEvents for description tag ", e);
			throw new JspTagException("Error: Can't find enclosing ReportedEvents for description tag ");
		}
	}

}
