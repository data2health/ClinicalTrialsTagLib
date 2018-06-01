package edu.uiowa.slis.ClinicalTrialsTagLib.seriousEventCount;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class SeriousEventCountID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(SeriousEventCountID.class);


	public int doStartTag() throws JspException {
		try {
			SeriousEventCount theSeriousEventCount = (SeriousEventCount)findAncestorWithClass(this, SeriousEventCount.class);
			if (!theSeriousEventCount.commitNeeded) {
				pageContext.getOut().print(theSeriousEventCount.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing SeriousEventCount for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing SeriousEventCount for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			SeriousEventCount theSeriousEventCount = (SeriousEventCount)findAncestorWithClass(this, SeriousEventCount.class);
			return theSeriousEventCount.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing SeriousEventCount for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing SeriousEventCount for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			SeriousEventCount theSeriousEventCount = (SeriousEventCount)findAncestorWithClass(this, SeriousEventCount.class);
			theSeriousEventCount.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing SeriousEventCount for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing SeriousEventCount for ID tag ");
		}
	}

}
