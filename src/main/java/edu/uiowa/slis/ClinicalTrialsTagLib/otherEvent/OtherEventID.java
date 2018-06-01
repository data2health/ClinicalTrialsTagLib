package edu.uiowa.slis.ClinicalTrialsTagLib.otherEvent;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class OtherEventID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(OtherEventID.class);


	public int doStartTag() throws JspException {
		try {
			OtherEvent theOtherEvent = (OtherEvent)findAncestorWithClass(this, OtherEvent.class);
			if (!theOtherEvent.commitNeeded) {
				pageContext.getOut().print(theOtherEvent.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing OtherEvent for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherEvent for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			OtherEvent theOtherEvent = (OtherEvent)findAncestorWithClass(this, OtherEvent.class);
			return theOtherEvent.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing OtherEvent for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherEvent for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			OtherEvent theOtherEvent = (OtherEvent)findAncestorWithClass(this, OtherEvent.class);
			theOtherEvent.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing OtherEvent for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherEvent for ID tag ");
		}
	}

}
