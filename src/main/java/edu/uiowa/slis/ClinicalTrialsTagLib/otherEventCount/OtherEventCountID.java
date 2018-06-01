package edu.uiowa.slis.ClinicalTrialsTagLib.otherEventCount;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class OtherEventCountID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(OtherEventCountID.class);


	public int doStartTag() throws JspException {
		try {
			OtherEventCount theOtherEventCount = (OtherEventCount)findAncestorWithClass(this, OtherEventCount.class);
			if (!theOtherEventCount.commitNeeded) {
				pageContext.getOut().print(theOtherEventCount.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing OtherEventCount for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherEventCount for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			OtherEventCount theOtherEventCount = (OtherEventCount)findAncestorWithClass(this, OtherEventCount.class);
			return theOtherEventCount.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing OtherEventCount for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherEventCount for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			OtherEventCount theOtherEventCount = (OtherEventCount)findAncestorWithClass(this, OtherEventCount.class);
			theOtherEventCount.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing OtherEventCount for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherEventCount for ID tag ");
		}
	}

}
