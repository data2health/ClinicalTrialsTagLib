package edu.uiowa.slis.ClinicalTrialsTagLib.seriousEvent;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class SeriousEventDescription extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(SeriousEventDescription.class);


	public int doStartTag() throws JspException {
		try {
			SeriousEvent theSeriousEvent = (SeriousEvent)findAncestorWithClass(this, SeriousEvent.class);
			if (!theSeriousEvent.commitNeeded) {
				pageContext.getOut().print(theSeriousEvent.getDescription());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing SeriousEvent for description tag ", e);
			throw new JspTagException("Error: Can't find enclosing SeriousEvent for description tag ");
		}
		return SKIP_BODY;
	}

	public String getDescription() throws JspTagException {
		try {
			SeriousEvent theSeriousEvent = (SeriousEvent)findAncestorWithClass(this, SeriousEvent.class);
			return theSeriousEvent.getDescription();
		} catch (Exception e) {
			log.error(" Can't find enclosing SeriousEvent for description tag ", e);
			throw new JspTagException("Error: Can't find enclosing SeriousEvent for description tag ");
		}
	}

	public void setDescription(String description) throws JspTagException {
		try {
			SeriousEvent theSeriousEvent = (SeriousEvent)findAncestorWithClass(this, SeriousEvent.class);
			theSeriousEvent.setDescription(description);
		} catch (Exception e) {
			log.error("Can't find enclosing SeriousEvent for description tag ", e);
			throw new JspTagException("Error: Can't find enclosing SeriousEvent for description tag ");
		}
	}

}
