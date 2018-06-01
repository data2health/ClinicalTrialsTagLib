package edu.uiowa.slis.ClinicalTrialsTagLib.seriousEvent;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class SeriousEventAssessment extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(SeriousEventAssessment.class);


	public int doStartTag() throws JspException {
		try {
			SeriousEvent theSeriousEvent = (SeriousEvent)findAncestorWithClass(this, SeriousEvent.class);
			if (!theSeriousEvent.commitNeeded) {
				pageContext.getOut().print(theSeriousEvent.getAssessment());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing SeriousEvent for assessment tag ", e);
			throw new JspTagException("Error: Can't find enclosing SeriousEvent for assessment tag ");
		}
		return SKIP_BODY;
	}

	public String getAssessment() throws JspTagException {
		try {
			SeriousEvent theSeriousEvent = (SeriousEvent)findAncestorWithClass(this, SeriousEvent.class);
			return theSeriousEvent.getAssessment();
		} catch (Exception e) {
			log.error(" Can't find enclosing SeriousEvent for assessment tag ", e);
			throw new JspTagException("Error: Can't find enclosing SeriousEvent for assessment tag ");
		}
	}

	public void setAssessment(String assessment) throws JspTagException {
		try {
			SeriousEvent theSeriousEvent = (SeriousEvent)findAncestorWithClass(this, SeriousEvent.class);
			theSeriousEvent.setAssessment(assessment);
		} catch (Exception e) {
			log.error("Can't find enclosing SeriousEvent for assessment tag ", e);
			throw new JspTagException("Error: Can't find enclosing SeriousEvent for assessment tag ");
		}
	}

}
