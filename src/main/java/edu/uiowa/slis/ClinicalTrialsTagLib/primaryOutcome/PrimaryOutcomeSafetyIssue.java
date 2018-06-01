package edu.uiowa.slis.ClinicalTrialsTagLib.primaryOutcome;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class PrimaryOutcomeSafetyIssue extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(PrimaryOutcomeSafetyIssue.class);


	public int doStartTag() throws JspException {
		try {
			PrimaryOutcome thePrimaryOutcome = (PrimaryOutcome)findAncestorWithClass(this, PrimaryOutcome.class);
			if (!thePrimaryOutcome.commitNeeded) {
				pageContext.getOut().print(thePrimaryOutcome.getSafetyIssue());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing PrimaryOutcome for safetyIssue tag ", e);
			throw new JspTagException("Error: Can't find enclosing PrimaryOutcome for safetyIssue tag ");
		}
		return SKIP_BODY;
	}

	public String getSafetyIssue() throws JspTagException {
		try {
			PrimaryOutcome thePrimaryOutcome = (PrimaryOutcome)findAncestorWithClass(this, PrimaryOutcome.class);
			return thePrimaryOutcome.getSafetyIssue();
		} catch (Exception e) {
			log.error(" Can't find enclosing PrimaryOutcome for safetyIssue tag ", e);
			throw new JspTagException("Error: Can't find enclosing PrimaryOutcome for safetyIssue tag ");
		}
	}

	public void setSafetyIssue(String safetyIssue) throws JspTagException {
		try {
			PrimaryOutcome thePrimaryOutcome = (PrimaryOutcome)findAncestorWithClass(this, PrimaryOutcome.class);
			thePrimaryOutcome.setSafetyIssue(safetyIssue);
		} catch (Exception e) {
			log.error("Can't find enclosing PrimaryOutcome for safetyIssue tag ", e);
			throw new JspTagException("Error: Can't find enclosing PrimaryOutcome for safetyIssue tag ");
		}
	}

}
