package edu.uiowa.slis.ClinicalTrialsTagLib.secondaryOutcome;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class SecondaryOutcomeID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(SecondaryOutcomeID.class);


	public int doStartTag() throws JspException {
		try {
			SecondaryOutcome theSecondaryOutcome = (SecondaryOutcome)findAncestorWithClass(this, SecondaryOutcome.class);
			if (!theSecondaryOutcome.commitNeeded) {
				pageContext.getOut().print(theSecondaryOutcome.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing SecondaryOutcome for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing SecondaryOutcome for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			SecondaryOutcome theSecondaryOutcome = (SecondaryOutcome)findAncestorWithClass(this, SecondaryOutcome.class);
			return theSecondaryOutcome.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing SecondaryOutcome for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing SecondaryOutcome for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			SecondaryOutcome theSecondaryOutcome = (SecondaryOutcome)findAncestorWithClass(this, SecondaryOutcome.class);
			theSecondaryOutcome.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing SecondaryOutcome for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing SecondaryOutcome for ID tag ");
		}
	}

}
