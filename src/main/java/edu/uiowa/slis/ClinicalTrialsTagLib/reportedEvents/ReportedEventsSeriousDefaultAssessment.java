package edu.uiowa.slis.ClinicalTrialsTagLib.reportedEvents;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ReportedEventsSeriousDefaultAssessment extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ReportedEventsSeriousDefaultAssessment.class);


	public int doStartTag() throws JspException {
		try {
			ReportedEvents theReportedEvents = (ReportedEvents)findAncestorWithClass(this, ReportedEvents.class);
			if (!theReportedEvents.commitNeeded) {
				pageContext.getOut().print(theReportedEvents.getSeriousDefaultAssessment());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ReportedEvents for seriousDefaultAssessment tag ", e);
			throw new JspTagException("Error: Can't find enclosing ReportedEvents for seriousDefaultAssessment tag ");
		}
		return SKIP_BODY;
	}

	public String getSeriousDefaultAssessment() throws JspTagException {
		try {
			ReportedEvents theReportedEvents = (ReportedEvents)findAncestorWithClass(this, ReportedEvents.class);
			return theReportedEvents.getSeriousDefaultAssessment();
		} catch (Exception e) {
			log.error(" Can't find enclosing ReportedEvents for seriousDefaultAssessment tag ", e);
			throw new JspTagException("Error: Can't find enclosing ReportedEvents for seriousDefaultAssessment tag ");
		}
	}

	public void setSeriousDefaultAssessment(String seriousDefaultAssessment) throws JspTagException {
		try {
			ReportedEvents theReportedEvents = (ReportedEvents)findAncestorWithClass(this, ReportedEvents.class);
			theReportedEvents.setSeriousDefaultAssessment(seriousDefaultAssessment);
		} catch (Exception e) {
			log.error("Can't find enclosing ReportedEvents for seriousDefaultAssessment tag ", e);
			throw new JspTagException("Error: Can't find enclosing ReportedEvents for seriousDefaultAssessment tag ");
		}
	}

}
