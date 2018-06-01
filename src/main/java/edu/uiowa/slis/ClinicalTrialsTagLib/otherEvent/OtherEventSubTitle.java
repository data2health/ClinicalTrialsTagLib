package edu.uiowa.slis.ClinicalTrialsTagLib.otherEvent;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class OtherEventSubTitle extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(OtherEventSubTitle.class);


	public int doStartTag() throws JspException {
		try {
			OtherEvent theOtherEvent = (OtherEvent)findAncestorWithClass(this, OtherEvent.class);
			if (!theOtherEvent.commitNeeded) {
				pageContext.getOut().print(theOtherEvent.getSubTitle());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing OtherEvent for subTitle tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherEvent for subTitle tag ");
		}
		return SKIP_BODY;
	}

	public String getSubTitle() throws JspTagException {
		try {
			OtherEvent theOtherEvent = (OtherEvent)findAncestorWithClass(this, OtherEvent.class);
			return theOtherEvent.getSubTitle();
		} catch (Exception e) {
			log.error(" Can't find enclosing OtherEvent for subTitle tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherEvent for subTitle tag ");
		}
	}

	public void setSubTitle(String subTitle) throws JspTagException {
		try {
			OtherEvent theOtherEvent = (OtherEvent)findAncestorWithClass(this, OtherEvent.class);
			theOtherEvent.setSubTitle(subTitle);
		} catch (Exception e) {
			log.error("Can't find enclosing OtherEvent for subTitle tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherEvent for subTitle tag ");
		}
	}

}
