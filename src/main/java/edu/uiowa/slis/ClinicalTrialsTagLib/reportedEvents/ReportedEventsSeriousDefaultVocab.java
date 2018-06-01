package edu.uiowa.slis.ClinicalTrialsTagLib.reportedEvents;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ReportedEventsSeriousDefaultVocab extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ReportedEventsSeriousDefaultVocab.class);


	public int doStartTag() throws JspException {
		try {
			ReportedEvents theReportedEvents = (ReportedEvents)findAncestorWithClass(this, ReportedEvents.class);
			if (!theReportedEvents.commitNeeded) {
				pageContext.getOut().print(theReportedEvents.getSeriousDefaultVocab());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ReportedEvents for seriousDefaultVocab tag ", e);
			throw new JspTagException("Error: Can't find enclosing ReportedEvents for seriousDefaultVocab tag ");
		}
		return SKIP_BODY;
	}

	public String getSeriousDefaultVocab() throws JspTagException {
		try {
			ReportedEvents theReportedEvents = (ReportedEvents)findAncestorWithClass(this, ReportedEvents.class);
			return theReportedEvents.getSeriousDefaultVocab();
		} catch (Exception e) {
			log.error(" Can't find enclosing ReportedEvents for seriousDefaultVocab tag ", e);
			throw new JspTagException("Error: Can't find enclosing ReportedEvents for seriousDefaultVocab tag ");
		}
	}

	public void setSeriousDefaultVocab(String seriousDefaultVocab) throws JspTagException {
		try {
			ReportedEvents theReportedEvents = (ReportedEvents)findAncestorWithClass(this, ReportedEvents.class);
			theReportedEvents.setSeriousDefaultVocab(seriousDefaultVocab);
		} catch (Exception e) {
			log.error("Can't find enclosing ReportedEvents for seriousDefaultVocab tag ", e);
			throw new JspTagException("Error: Can't find enclosing ReportedEvents for seriousDefaultVocab tag ");
		}
	}

}
