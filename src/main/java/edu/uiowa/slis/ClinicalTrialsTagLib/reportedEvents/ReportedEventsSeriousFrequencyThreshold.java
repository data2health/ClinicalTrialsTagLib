package edu.uiowa.slis.ClinicalTrialsTagLib.reportedEvents;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ReportedEventsSeriousFrequencyThreshold extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ReportedEventsSeriousFrequencyThreshold.class);


	public int doStartTag() throws JspException {
		try {
			ReportedEvents theReportedEvents = (ReportedEvents)findAncestorWithClass(this, ReportedEvents.class);
			if (!theReportedEvents.commitNeeded) {
				pageContext.getOut().print(theReportedEvents.getSeriousFrequencyThreshold());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ReportedEvents for seriousFrequencyThreshold tag ", e);
			throw new JspTagException("Error: Can't find enclosing ReportedEvents for seriousFrequencyThreshold tag ");
		}
		return SKIP_BODY;
	}

	public String getSeriousFrequencyThreshold() throws JspTagException {
		try {
			ReportedEvents theReportedEvents = (ReportedEvents)findAncestorWithClass(this, ReportedEvents.class);
			return theReportedEvents.getSeriousFrequencyThreshold();
		} catch (Exception e) {
			log.error(" Can't find enclosing ReportedEvents for seriousFrequencyThreshold tag ", e);
			throw new JspTagException("Error: Can't find enclosing ReportedEvents for seriousFrequencyThreshold tag ");
		}
	}

	public void setSeriousFrequencyThreshold(String seriousFrequencyThreshold) throws JspTagException {
		try {
			ReportedEvents theReportedEvents = (ReportedEvents)findAncestorWithClass(this, ReportedEvents.class);
			theReportedEvents.setSeriousFrequencyThreshold(seriousFrequencyThreshold);
		} catch (Exception e) {
			log.error("Can't find enclosing ReportedEvents for seriousFrequencyThreshold tag ", e);
			throw new JspTagException("Error: Can't find enclosing ReportedEvents for seriousFrequencyThreshold tag ");
		}
	}

}
