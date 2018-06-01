package edu.uiowa.slis.ClinicalTrialsTagLib.otherOutcome;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class OtherOutcomeTimeFrame extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(OtherOutcomeTimeFrame.class);


	public int doStartTag() throws JspException {
		try {
			OtherOutcome theOtherOutcome = (OtherOutcome)findAncestorWithClass(this, OtherOutcome.class);
			if (!theOtherOutcome.commitNeeded) {
				pageContext.getOut().print(theOtherOutcome.getTimeFrame());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing OtherOutcome for timeFrame tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherOutcome for timeFrame tag ");
		}
		return SKIP_BODY;
	}

	public String getTimeFrame() throws JspTagException {
		try {
			OtherOutcome theOtherOutcome = (OtherOutcome)findAncestorWithClass(this, OtherOutcome.class);
			return theOtherOutcome.getTimeFrame();
		} catch (Exception e) {
			log.error(" Can't find enclosing OtherOutcome for timeFrame tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherOutcome for timeFrame tag ");
		}
	}

	public void setTimeFrame(String timeFrame) throws JspTagException {
		try {
			OtherOutcome theOtherOutcome = (OtherOutcome)findAncestorWithClass(this, OtherOutcome.class);
			theOtherOutcome.setTimeFrame(timeFrame);
		} catch (Exception e) {
			log.error("Can't find enclosing OtherOutcome for timeFrame tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherOutcome for timeFrame tag ");
		}
	}

}
