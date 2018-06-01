package edu.uiowa.slis.ClinicalTrialsTagLib.seriousEvent;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class SeriousEventSeqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(SeriousEventSeqnum.class);


	public int doStartTag() throws JspException {
		try {
			SeriousEvent theSeriousEvent = (SeriousEvent)findAncestorWithClass(this, SeriousEvent.class);
			if (!theSeriousEvent.commitNeeded) {
				pageContext.getOut().print(theSeriousEvent.getSeqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing SeriousEvent for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing SeriousEvent for seqnum tag ");
		}
		return SKIP_BODY;
	}

	public String getSeqnum() throws JspTagException {
		try {
			SeriousEvent theSeriousEvent = (SeriousEvent)findAncestorWithClass(this, SeriousEvent.class);
			return theSeriousEvent.getSeqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing SeriousEvent for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing SeriousEvent for seqnum tag ");
		}
	}

	public void setSeqnum(String seqnum) throws JspTagException {
		try {
			SeriousEvent theSeriousEvent = (SeriousEvent)findAncestorWithClass(this, SeriousEvent.class);
			theSeriousEvent.setSeqnum(seqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing SeriousEvent for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing SeriousEvent for seqnum tag ");
		}
	}

}
