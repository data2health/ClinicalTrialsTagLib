package edu.uiowa.slis.ClinicalTrialsTagLib.seriousEventCount;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class SeriousEventCountSeqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(SeriousEventCountSeqnum.class);


	public int doStartTag() throws JspException {
		try {
			SeriousEventCount theSeriousEventCount = (SeriousEventCount)findAncestorWithClass(this, SeriousEventCount.class);
			if (!theSeriousEventCount.commitNeeded) {
				pageContext.getOut().print(theSeriousEventCount.getSeqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing SeriousEventCount for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing SeriousEventCount for seqnum tag ");
		}
		return SKIP_BODY;
	}

	public String getSeqnum() throws JspTagException {
		try {
			SeriousEventCount theSeriousEventCount = (SeriousEventCount)findAncestorWithClass(this, SeriousEventCount.class);
			return theSeriousEventCount.getSeqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing SeriousEventCount for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing SeriousEventCount for seqnum tag ");
		}
	}

	public void setSeqnum(String seqnum) throws JspTagException {
		try {
			SeriousEventCount theSeriousEventCount = (SeriousEventCount)findAncestorWithClass(this, SeriousEventCount.class);
			theSeriousEventCount.setSeqnum(seqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing SeriousEventCount for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing SeriousEventCount for seqnum tag ");
		}
	}

}
