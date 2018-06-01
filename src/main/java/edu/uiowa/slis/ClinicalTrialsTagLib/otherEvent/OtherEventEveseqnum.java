package edu.uiowa.slis.ClinicalTrialsTagLib.otherEvent;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class OtherEventEveseqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(OtherEventEveseqnum.class);


	public int doStartTag() throws JspException {
		try {
			OtherEvent theOtherEvent = (OtherEvent)findAncestorWithClass(this, OtherEvent.class);
			if (!theOtherEvent.commitNeeded) {
				pageContext.getOut().print(theOtherEvent.getEveseqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing OtherEvent for eveseqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherEvent for eveseqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getEveseqnum() throws JspTagException {
		try {
			OtherEvent theOtherEvent = (OtherEvent)findAncestorWithClass(this, OtherEvent.class);
			return theOtherEvent.getEveseqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing OtherEvent for eveseqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherEvent for eveseqnum tag ");
		}
	}

	public void setEveseqnum(int eveseqnum) throws JspTagException {
		try {
			OtherEvent theOtherEvent = (OtherEvent)findAncestorWithClass(this, OtherEvent.class);
			theOtherEvent.setEveseqnum(eveseqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing OtherEvent for eveseqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherEvent for eveseqnum tag ");
		}
	}

}
