package edu.uiowa.slis.ClinicalTrialsTagLib.reportedEvents;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ReportedEventsTimeFrame extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ReportedEventsTimeFrame.class);


	public int doStartTag() throws JspException {
		try {
			ReportedEvents theReportedEvents = (ReportedEvents)findAncestorWithClass(this, ReportedEvents.class);
			if (!theReportedEvents.commitNeeded) {
				pageContext.getOut().print(theReportedEvents.getTimeFrame());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ReportedEvents for timeFrame tag ", e);
			throw new JspTagException("Error: Can't find enclosing ReportedEvents for timeFrame tag ");
		}
		return SKIP_BODY;
	}

	public String getTimeFrame() throws JspTagException {
		try {
			ReportedEvents theReportedEvents = (ReportedEvents)findAncestorWithClass(this, ReportedEvents.class);
			return theReportedEvents.getTimeFrame();
		} catch (Exception e) {
			log.error(" Can't find enclosing ReportedEvents for timeFrame tag ", e);
			throw new JspTagException("Error: Can't find enclosing ReportedEvents for timeFrame tag ");
		}
	}

	public void setTimeFrame(String timeFrame) throws JspTagException {
		try {
			ReportedEvents theReportedEvents = (ReportedEvents)findAncestorWithClass(this, ReportedEvents.class);
			theReportedEvents.setTimeFrame(timeFrame);
		} catch (Exception e) {
			log.error("Can't find enclosing ReportedEvents for timeFrame tag ", e);
			throw new JspTagException("Error: Can't find enclosing ReportedEvents for timeFrame tag ");
		}
	}

}
