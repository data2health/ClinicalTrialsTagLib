package edu.uiowa.slis.ClinicalTrialsTagLib.secondaryOutcome;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class SecondaryOutcomeSafetyIssue extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(SecondaryOutcomeSafetyIssue.class);


	public int doStartTag() throws JspException {
		try {
			SecondaryOutcome theSecondaryOutcome = (SecondaryOutcome)findAncestorWithClass(this, SecondaryOutcome.class);
			if (!theSecondaryOutcome.commitNeeded) {
				pageContext.getOut().print(theSecondaryOutcome.getSafetyIssue());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing SecondaryOutcome for safetyIssue tag ", e);
			throw new JspTagException("Error: Can't find enclosing SecondaryOutcome for safetyIssue tag ");
		}
		return SKIP_BODY;
	}

	public String getSafetyIssue() throws JspTagException {
		try {
			SecondaryOutcome theSecondaryOutcome = (SecondaryOutcome)findAncestorWithClass(this, SecondaryOutcome.class);
			return theSecondaryOutcome.getSafetyIssue();
		} catch (Exception e) {
			log.error(" Can't find enclosing SecondaryOutcome for safetyIssue tag ", e);
			throw new JspTagException("Error: Can't find enclosing SecondaryOutcome for safetyIssue tag ");
		}
	}

	public void setSafetyIssue(String safetyIssue) throws JspTagException {
		try {
			SecondaryOutcome theSecondaryOutcome = (SecondaryOutcome)findAncestorWithClass(this, SecondaryOutcome.class);
			theSecondaryOutcome.setSafetyIssue(safetyIssue);
		} catch (Exception e) {
			log.error("Can't find enclosing SecondaryOutcome for safetyIssue tag ", e);
			throw new JspTagException("Error: Can't find enclosing SecondaryOutcome for safetyIssue tag ");
		}
	}

}
