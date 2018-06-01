package edu.uiowa.slis.ClinicalTrialsTagLib.otherOutcome;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class OtherOutcomeID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(OtherOutcomeID.class);


	public int doStartTag() throws JspException {
		try {
			OtherOutcome theOtherOutcome = (OtherOutcome)findAncestorWithClass(this, OtherOutcome.class);
			if (!theOtherOutcome.commitNeeded) {
				pageContext.getOut().print(theOtherOutcome.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing OtherOutcome for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherOutcome for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			OtherOutcome theOtherOutcome = (OtherOutcome)findAncestorWithClass(this, OtherOutcome.class);
			return theOtherOutcome.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing OtherOutcome for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherOutcome for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			OtherOutcome theOtherOutcome = (OtherOutcome)findAncestorWithClass(this, OtherOutcome.class);
			theOtherOutcome.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing OtherOutcome for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherOutcome for ID tag ");
		}
	}

}
