package edu.uiowa.slis.ClinicalTrialsTagLib.primaryOutcome;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class PrimaryOutcomeDescription extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(PrimaryOutcomeDescription.class);


	public int doStartTag() throws JspException {
		try {
			PrimaryOutcome thePrimaryOutcome = (PrimaryOutcome)findAncestorWithClass(this, PrimaryOutcome.class);
			if (!thePrimaryOutcome.commitNeeded) {
				pageContext.getOut().print(thePrimaryOutcome.getDescription());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing PrimaryOutcome for description tag ", e);
			throw new JspTagException("Error: Can't find enclosing PrimaryOutcome for description tag ");
		}
		return SKIP_BODY;
	}

	public String getDescription() throws JspTagException {
		try {
			PrimaryOutcome thePrimaryOutcome = (PrimaryOutcome)findAncestorWithClass(this, PrimaryOutcome.class);
			return thePrimaryOutcome.getDescription();
		} catch (Exception e) {
			log.error(" Can't find enclosing PrimaryOutcome for description tag ", e);
			throw new JspTagException("Error: Can't find enclosing PrimaryOutcome for description tag ");
		}
	}

	public void setDescription(String description) throws JspTagException {
		try {
			PrimaryOutcome thePrimaryOutcome = (PrimaryOutcome)findAncestorWithClass(this, PrimaryOutcome.class);
			thePrimaryOutcome.setDescription(description);
		} catch (Exception e) {
			log.error("Can't find enclosing PrimaryOutcome for description tag ", e);
			throw new JspTagException("Error: Can't find enclosing PrimaryOutcome for description tag ");
		}
	}

}
