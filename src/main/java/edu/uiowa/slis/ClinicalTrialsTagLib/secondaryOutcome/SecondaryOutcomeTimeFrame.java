package edu.uiowa.slis.ClinicalTrialsTagLib.secondaryOutcome;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class SecondaryOutcomeTimeFrame extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(SecondaryOutcomeTimeFrame.class);


	public int doStartTag() throws JspException {
		try {
			SecondaryOutcome theSecondaryOutcome = (SecondaryOutcome)findAncestorWithClass(this, SecondaryOutcome.class);
			if (!theSecondaryOutcome.commitNeeded) {
				pageContext.getOut().print(theSecondaryOutcome.getTimeFrame());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing SecondaryOutcome for timeFrame tag ", e);
			throw new JspTagException("Error: Can't find enclosing SecondaryOutcome for timeFrame tag ");
		}
		return SKIP_BODY;
	}

	public String getTimeFrame() throws JspTagException {
		try {
			SecondaryOutcome theSecondaryOutcome = (SecondaryOutcome)findAncestorWithClass(this, SecondaryOutcome.class);
			return theSecondaryOutcome.getTimeFrame();
		} catch (Exception e) {
			log.error(" Can't find enclosing SecondaryOutcome for timeFrame tag ", e);
			throw new JspTagException("Error: Can't find enclosing SecondaryOutcome for timeFrame tag ");
		}
	}

	public void setTimeFrame(String timeFrame) throws JspTagException {
		try {
			SecondaryOutcome theSecondaryOutcome = (SecondaryOutcome)findAncestorWithClass(this, SecondaryOutcome.class);
			theSecondaryOutcome.setTimeFrame(timeFrame);
		} catch (Exception e) {
			log.error("Can't find enclosing SecondaryOutcome for timeFrame tag ", e);
			throw new JspTagException("Error: Can't find enclosing SecondaryOutcome for timeFrame tag ");
		}
	}

}
