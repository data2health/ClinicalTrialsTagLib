package edu.uiowa.slis.ClinicalTrialsTagLib.seriousEvent;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class SeriousEventEveseqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(SeriousEventEveseqnum.class);


	public int doStartTag() throws JspException {
		try {
			SeriousEvent theSeriousEvent = (SeriousEvent)findAncestorWithClass(this, SeriousEvent.class);
			if (!theSeriousEvent.commitNeeded) {
				pageContext.getOut().print(theSeriousEvent.getEveseqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing SeriousEvent for eveseqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing SeriousEvent for eveseqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getEveseqnum() throws JspTagException {
		try {
			SeriousEvent theSeriousEvent = (SeriousEvent)findAncestorWithClass(this, SeriousEvent.class);
			return theSeriousEvent.getEveseqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing SeriousEvent for eveseqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing SeriousEvent for eveseqnum tag ");
		}
	}

	public void setEveseqnum(int eveseqnum) throws JspTagException {
		try {
			SeriousEvent theSeriousEvent = (SeriousEvent)findAncestorWithClass(this, SeriousEvent.class);
			theSeriousEvent.setEveseqnum(eveseqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing SeriousEvent for eveseqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing SeriousEvent for eveseqnum tag ");
		}
	}

}
