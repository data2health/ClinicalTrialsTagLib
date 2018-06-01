package edu.uiowa.slis.ClinicalTrialsTagLib.seriousEvent;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class SeriousEventSubTitle extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(SeriousEventSubTitle.class);


	public int doStartTag() throws JspException {
		try {
			SeriousEvent theSeriousEvent = (SeriousEvent)findAncestorWithClass(this, SeriousEvent.class);
			if (!theSeriousEvent.commitNeeded) {
				pageContext.getOut().print(theSeriousEvent.getSubTitle());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing SeriousEvent for subTitle tag ", e);
			throw new JspTagException("Error: Can't find enclosing SeriousEvent for subTitle tag ");
		}
		return SKIP_BODY;
	}

	public String getSubTitle() throws JspTagException {
		try {
			SeriousEvent theSeriousEvent = (SeriousEvent)findAncestorWithClass(this, SeriousEvent.class);
			return theSeriousEvent.getSubTitle();
		} catch (Exception e) {
			log.error(" Can't find enclosing SeriousEvent for subTitle tag ", e);
			throw new JspTagException("Error: Can't find enclosing SeriousEvent for subTitle tag ");
		}
	}

	public void setSubTitle(String subTitle) throws JspTagException {
		try {
			SeriousEvent theSeriousEvent = (SeriousEvent)findAncestorWithClass(this, SeriousEvent.class);
			theSeriousEvent.setSubTitle(subTitle);
		} catch (Exception e) {
			log.error("Can't find enclosing SeriousEvent for subTitle tag ", e);
			throw new JspTagException("Error: Can't find enclosing SeriousEvent for subTitle tag ");
		}
	}

}
