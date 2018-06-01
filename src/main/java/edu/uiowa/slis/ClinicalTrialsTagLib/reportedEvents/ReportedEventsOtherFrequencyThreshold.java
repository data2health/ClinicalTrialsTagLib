package edu.uiowa.slis.ClinicalTrialsTagLib.reportedEvents;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ReportedEventsOtherFrequencyThreshold extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ReportedEventsOtherFrequencyThreshold.class);


	public int doStartTag() throws JspException {
		try {
			ReportedEvents theReportedEvents = (ReportedEvents)findAncestorWithClass(this, ReportedEvents.class);
			if (!theReportedEvents.commitNeeded) {
				pageContext.getOut().print(theReportedEvents.getOtherFrequencyThreshold());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ReportedEvents for otherFrequencyThreshold tag ", e);
			throw new JspTagException("Error: Can't find enclosing ReportedEvents for otherFrequencyThreshold tag ");
		}
		return SKIP_BODY;
	}

	public String getOtherFrequencyThreshold() throws JspTagException {
		try {
			ReportedEvents theReportedEvents = (ReportedEvents)findAncestorWithClass(this, ReportedEvents.class);
			return theReportedEvents.getOtherFrequencyThreshold();
		} catch (Exception e) {
			log.error(" Can't find enclosing ReportedEvents for otherFrequencyThreshold tag ", e);
			throw new JspTagException("Error: Can't find enclosing ReportedEvents for otherFrequencyThreshold tag ");
		}
	}

	public void setOtherFrequencyThreshold(String otherFrequencyThreshold) throws JspTagException {
		try {
			ReportedEvents theReportedEvents = (ReportedEvents)findAncestorWithClass(this, ReportedEvents.class);
			theReportedEvents.setOtherFrequencyThreshold(otherFrequencyThreshold);
		} catch (Exception e) {
			log.error("Can't find enclosing ReportedEvents for otherFrequencyThreshold tag ", e);
			throw new JspTagException("Error: Can't find enclosing ReportedEvents for otherFrequencyThreshold tag ");
		}
	}

}
