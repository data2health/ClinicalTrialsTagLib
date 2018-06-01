package edu.uiowa.slis.ClinicalTrialsTagLib.primaryOutcome;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class PrimaryOutcomeID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(PrimaryOutcomeID.class);


	public int doStartTag() throws JspException {
		try {
			PrimaryOutcome thePrimaryOutcome = (PrimaryOutcome)findAncestorWithClass(this, PrimaryOutcome.class);
			if (!thePrimaryOutcome.commitNeeded) {
				pageContext.getOut().print(thePrimaryOutcome.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing PrimaryOutcome for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing PrimaryOutcome for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			PrimaryOutcome thePrimaryOutcome = (PrimaryOutcome)findAncestorWithClass(this, PrimaryOutcome.class);
			return thePrimaryOutcome.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing PrimaryOutcome for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing PrimaryOutcome for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			PrimaryOutcome thePrimaryOutcome = (PrimaryOutcome)findAncestorWithClass(this, PrimaryOutcome.class);
			thePrimaryOutcome.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing PrimaryOutcome for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing PrimaryOutcome for ID tag ");
		}
	}

}
