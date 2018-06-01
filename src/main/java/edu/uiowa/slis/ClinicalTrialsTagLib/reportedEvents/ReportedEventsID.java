package edu.uiowa.slis.ClinicalTrialsTagLib.reportedEvents;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ReportedEventsID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ReportedEventsID.class);


	public int doStartTag() throws JspException {
		try {
			ReportedEvents theReportedEvents = (ReportedEvents)findAncestorWithClass(this, ReportedEvents.class);
			if (!theReportedEvents.commitNeeded) {
				pageContext.getOut().print(theReportedEvents.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ReportedEvents for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ReportedEvents for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			ReportedEvents theReportedEvents = (ReportedEvents)findAncestorWithClass(this, ReportedEvents.class);
			return theReportedEvents.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing ReportedEvents for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ReportedEvents for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			ReportedEvents theReportedEvents = (ReportedEvents)findAncestorWithClass(this, ReportedEvents.class);
			theReportedEvents.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing ReportedEvents for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ReportedEvents for ID tag ");
		}
	}

}
