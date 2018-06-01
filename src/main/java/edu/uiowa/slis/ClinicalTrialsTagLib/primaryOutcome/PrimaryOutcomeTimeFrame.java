package edu.uiowa.slis.ClinicalTrialsTagLib.primaryOutcome;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class PrimaryOutcomeTimeFrame extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(PrimaryOutcomeTimeFrame.class);


	public int doStartTag() throws JspException {
		try {
			PrimaryOutcome thePrimaryOutcome = (PrimaryOutcome)findAncestorWithClass(this, PrimaryOutcome.class);
			if (!thePrimaryOutcome.commitNeeded) {
				pageContext.getOut().print(thePrimaryOutcome.getTimeFrame());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing PrimaryOutcome for timeFrame tag ", e);
			throw new JspTagException("Error: Can't find enclosing PrimaryOutcome for timeFrame tag ");
		}
		return SKIP_BODY;
	}

	public String getTimeFrame() throws JspTagException {
		try {
			PrimaryOutcome thePrimaryOutcome = (PrimaryOutcome)findAncestorWithClass(this, PrimaryOutcome.class);
			return thePrimaryOutcome.getTimeFrame();
		} catch (Exception e) {
			log.error(" Can't find enclosing PrimaryOutcome for timeFrame tag ", e);
			throw new JspTagException("Error: Can't find enclosing PrimaryOutcome for timeFrame tag ");
		}
	}

	public void setTimeFrame(String timeFrame) throws JspTagException {
		try {
			PrimaryOutcome thePrimaryOutcome = (PrimaryOutcome)findAncestorWithClass(this, PrimaryOutcome.class);
			thePrimaryOutcome.setTimeFrame(timeFrame);
		} catch (Exception e) {
			log.error("Can't find enclosing PrimaryOutcome for timeFrame tag ", e);
			throw new JspTagException("Error: Can't find enclosing PrimaryOutcome for timeFrame tag ");
		}
	}

}
