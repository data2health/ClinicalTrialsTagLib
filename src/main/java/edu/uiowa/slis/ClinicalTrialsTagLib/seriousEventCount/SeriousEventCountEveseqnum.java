package edu.uiowa.slis.ClinicalTrialsTagLib.seriousEventCount;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class SeriousEventCountEveseqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(SeriousEventCountEveseqnum.class);


	public int doStartTag() throws JspException {
		try {
			SeriousEventCount theSeriousEventCount = (SeriousEventCount)findAncestorWithClass(this, SeriousEventCount.class);
			if (!theSeriousEventCount.commitNeeded) {
				pageContext.getOut().print(theSeriousEventCount.getEveseqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing SeriousEventCount for eveseqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing SeriousEventCount for eveseqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getEveseqnum() throws JspTagException {
		try {
			SeriousEventCount theSeriousEventCount = (SeriousEventCount)findAncestorWithClass(this, SeriousEventCount.class);
			return theSeriousEventCount.getEveseqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing SeriousEventCount for eveseqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing SeriousEventCount for eveseqnum tag ");
		}
	}

	public void setEveseqnum(int eveseqnum) throws JspTagException {
		try {
			SeriousEventCount theSeriousEventCount = (SeriousEventCount)findAncestorWithClass(this, SeriousEventCount.class);
			theSeriousEventCount.setEveseqnum(eveseqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing SeriousEventCount for eveseqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing SeriousEventCount for eveseqnum tag ");
		}
	}

}
