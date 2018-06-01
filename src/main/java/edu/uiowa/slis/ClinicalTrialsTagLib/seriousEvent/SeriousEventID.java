package edu.uiowa.slis.ClinicalTrialsTagLib.seriousEvent;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class SeriousEventID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(SeriousEventID.class);


	public int doStartTag() throws JspException {
		try {
			SeriousEvent theSeriousEvent = (SeriousEvent)findAncestorWithClass(this, SeriousEvent.class);
			if (!theSeriousEvent.commitNeeded) {
				pageContext.getOut().print(theSeriousEvent.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing SeriousEvent for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing SeriousEvent for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			SeriousEvent theSeriousEvent = (SeriousEvent)findAncestorWithClass(this, SeriousEvent.class);
			return theSeriousEvent.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing SeriousEvent for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing SeriousEvent for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			SeriousEvent theSeriousEvent = (SeriousEvent)findAncestorWithClass(this, SeriousEvent.class);
			theSeriousEvent.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing SeriousEvent for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing SeriousEvent for ID tag ");
		}
	}

}
