package edu.uiowa.slis.ClinicalTrialsTagLib.otherOutcome;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class OtherOutcomeSafetyIssue extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(OtherOutcomeSafetyIssue.class);


	public int doStartTag() throws JspException {
		try {
			OtherOutcome theOtherOutcome = (OtherOutcome)findAncestorWithClass(this, OtherOutcome.class);
			if (!theOtherOutcome.commitNeeded) {
				pageContext.getOut().print(theOtherOutcome.getSafetyIssue());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing OtherOutcome for safetyIssue tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherOutcome for safetyIssue tag ");
		}
		return SKIP_BODY;
	}

	public String getSafetyIssue() throws JspTagException {
		try {
			OtherOutcome theOtherOutcome = (OtherOutcome)findAncestorWithClass(this, OtherOutcome.class);
			return theOtherOutcome.getSafetyIssue();
		} catch (Exception e) {
			log.error(" Can't find enclosing OtherOutcome for safetyIssue tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherOutcome for safetyIssue tag ");
		}
	}

	public void setSafetyIssue(String safetyIssue) throws JspTagException {
		try {
			OtherOutcome theOtherOutcome = (OtherOutcome)findAncestorWithClass(this, OtherOutcome.class);
			theOtherOutcome.setSafetyIssue(safetyIssue);
		} catch (Exception e) {
			log.error("Can't find enclosing OtherOutcome for safetyIssue tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherOutcome for safetyIssue tag ");
		}
	}

}
