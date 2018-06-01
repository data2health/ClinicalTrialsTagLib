package edu.uiowa.slis.ClinicalTrialsTagLib.reportedEvents;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ReportedEventsOtherDefaultVocab extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ReportedEventsOtherDefaultVocab.class);


	public int doStartTag() throws JspException {
		try {
			ReportedEvents theReportedEvents = (ReportedEvents)findAncestorWithClass(this, ReportedEvents.class);
			if (!theReportedEvents.commitNeeded) {
				pageContext.getOut().print(theReportedEvents.getOtherDefaultVocab());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ReportedEvents for otherDefaultVocab tag ", e);
			throw new JspTagException("Error: Can't find enclosing ReportedEvents for otherDefaultVocab tag ");
		}
		return SKIP_BODY;
	}

	public String getOtherDefaultVocab() throws JspTagException {
		try {
			ReportedEvents theReportedEvents = (ReportedEvents)findAncestorWithClass(this, ReportedEvents.class);
			return theReportedEvents.getOtherDefaultVocab();
		} catch (Exception e) {
			log.error(" Can't find enclosing ReportedEvents for otherDefaultVocab tag ", e);
			throw new JspTagException("Error: Can't find enclosing ReportedEvents for otherDefaultVocab tag ");
		}
	}

	public void setOtherDefaultVocab(String otherDefaultVocab) throws JspTagException {
		try {
			ReportedEvents theReportedEvents = (ReportedEvents)findAncestorWithClass(this, ReportedEvents.class);
			theReportedEvents.setOtherDefaultVocab(otherDefaultVocab);
		} catch (Exception e) {
			log.error("Can't find enclosing ReportedEvents for otherDefaultVocab tag ", e);
			throw new JspTagException("Error: Can't find enclosing ReportedEvents for otherDefaultVocab tag ");
		}
	}

}
