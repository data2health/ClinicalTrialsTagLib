package edu.uiowa.slis.ClinicalTrialsTagLib.reportedEvents;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ReportedEventsOtherDefaultAssessment extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ReportedEventsOtherDefaultAssessment.class);


	public int doStartTag() throws JspException {
		try {
			ReportedEvents theReportedEvents = (ReportedEvents)findAncestorWithClass(this, ReportedEvents.class);
			if (!theReportedEvents.commitNeeded) {
				pageContext.getOut().print(theReportedEvents.getOtherDefaultAssessment());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ReportedEvents for otherDefaultAssessment tag ", e);
			throw new JspTagException("Error: Can't find enclosing ReportedEvents for otherDefaultAssessment tag ");
		}
		return SKIP_BODY;
	}

	public String getOtherDefaultAssessment() throws JspTagException {
		try {
			ReportedEvents theReportedEvents = (ReportedEvents)findAncestorWithClass(this, ReportedEvents.class);
			return theReportedEvents.getOtherDefaultAssessment();
		} catch (Exception e) {
			log.error(" Can't find enclosing ReportedEvents for otherDefaultAssessment tag ", e);
			throw new JspTagException("Error: Can't find enclosing ReportedEvents for otherDefaultAssessment tag ");
		}
	}

	public void setOtherDefaultAssessment(String otherDefaultAssessment) throws JspTagException {
		try {
			ReportedEvents theReportedEvents = (ReportedEvents)findAncestorWithClass(this, ReportedEvents.class);
			theReportedEvents.setOtherDefaultAssessment(otherDefaultAssessment);
		} catch (Exception e) {
			log.error("Can't find enclosing ReportedEvents for otherDefaultAssessment tag ", e);
			throw new JspTagException("Error: Can't find enclosing ReportedEvents for otherDefaultAssessment tag ");
		}
	}

}
