package edu.uiowa.slis.ClinicalTrialsTagLib.otherEvent;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class OtherEventSeqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(OtherEventSeqnum.class);


	public int doStartTag() throws JspException {
		try {
			OtherEvent theOtherEvent = (OtherEvent)findAncestorWithClass(this, OtherEvent.class);
			if (!theOtherEvent.commitNeeded) {
				pageContext.getOut().print(theOtherEvent.getSeqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing OtherEvent for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherEvent for seqnum tag ");
		}
		return SKIP_BODY;
	}

	public String getSeqnum() throws JspTagException {
		try {
			OtherEvent theOtherEvent = (OtherEvent)findAncestorWithClass(this, OtherEvent.class);
			return theOtherEvent.getSeqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing OtherEvent for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherEvent for seqnum tag ");
		}
	}

	public void setSeqnum(String seqnum) throws JspTagException {
		try {
			OtherEvent theOtherEvent = (OtherEvent)findAncestorWithClass(this, OtherEvent.class);
			theOtherEvent.setSeqnum(seqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing OtherEvent for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherEvent for seqnum tag ");
		}
	}

}
