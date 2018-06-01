package edu.uiowa.slis.ClinicalTrialsTagLib.otherOutcome;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class OtherOutcomeDescription extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(OtherOutcomeDescription.class);


	public int doStartTag() throws JspException {
		try {
			OtherOutcome theOtherOutcome = (OtherOutcome)findAncestorWithClass(this, OtherOutcome.class);
			if (!theOtherOutcome.commitNeeded) {
				pageContext.getOut().print(theOtherOutcome.getDescription());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing OtherOutcome for description tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherOutcome for description tag ");
		}
		return SKIP_BODY;
	}

	public String getDescription() throws JspTagException {
		try {
			OtherOutcome theOtherOutcome = (OtherOutcome)findAncestorWithClass(this, OtherOutcome.class);
			return theOtherOutcome.getDescription();
		} catch (Exception e) {
			log.error(" Can't find enclosing OtherOutcome for description tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherOutcome for description tag ");
		}
	}

	public void setDescription(String description) throws JspTagException {
		try {
			OtherOutcome theOtherOutcome = (OtherOutcome)findAncestorWithClass(this, OtherOutcome.class);
			theOtherOutcome.setDescription(description);
		} catch (Exception e) {
			log.error("Can't find enclosing OtherOutcome for description tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherOutcome for description tag ");
		}
	}

}
