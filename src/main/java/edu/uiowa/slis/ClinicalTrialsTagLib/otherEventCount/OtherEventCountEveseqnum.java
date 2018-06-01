package edu.uiowa.slis.ClinicalTrialsTagLib.otherEventCount;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class OtherEventCountEveseqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(OtherEventCountEveseqnum.class);


	public int doStartTag() throws JspException {
		try {
			OtherEventCount theOtherEventCount = (OtherEventCount)findAncestorWithClass(this, OtherEventCount.class);
			if (!theOtherEventCount.commitNeeded) {
				pageContext.getOut().print(theOtherEventCount.getEveseqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing OtherEventCount for eveseqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherEventCount for eveseqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getEveseqnum() throws JspTagException {
		try {
			OtherEventCount theOtherEventCount = (OtherEventCount)findAncestorWithClass(this, OtherEventCount.class);
			return theOtherEventCount.getEveseqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing OtherEventCount for eveseqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherEventCount for eveseqnum tag ");
		}
	}

	public void setEveseqnum(int eveseqnum) throws JspTagException {
		try {
			OtherEventCount theOtherEventCount = (OtherEventCount)findAncestorWithClass(this, OtherEventCount.class);
			theOtherEventCount.setEveseqnum(eveseqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing OtherEventCount for eveseqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherEventCount for eveseqnum tag ");
		}
	}

}
