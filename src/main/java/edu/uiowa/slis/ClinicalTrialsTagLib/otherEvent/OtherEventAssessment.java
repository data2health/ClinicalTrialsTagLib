package edu.uiowa.slis.ClinicalTrialsTagLib.otherEvent;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class OtherEventAssessment extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(OtherEventAssessment.class);


	public int doStartTag() throws JspException {
		try {
			OtherEvent theOtherEvent = (OtherEvent)findAncestorWithClass(this, OtherEvent.class);
			if (!theOtherEvent.commitNeeded) {
				pageContext.getOut().print(theOtherEvent.getAssessment());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing OtherEvent for assessment tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherEvent for assessment tag ");
		}
		return SKIP_BODY;
	}

	public String getAssessment() throws JspTagException {
		try {
			OtherEvent theOtherEvent = (OtherEvent)findAncestorWithClass(this, OtherEvent.class);
			return theOtherEvent.getAssessment();
		} catch (Exception e) {
			log.error(" Can't find enclosing OtherEvent for assessment tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherEvent for assessment tag ");
		}
	}

	public void setAssessment(String assessment) throws JspTagException {
		try {
			OtherEvent theOtherEvent = (OtherEvent)findAncestorWithClass(this, OtherEvent.class);
			theOtherEvent.setAssessment(assessment);
		} catch (Exception e) {
			log.error("Can't find enclosing OtherEvent for assessment tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherEvent for assessment tag ");
		}
	}

}
