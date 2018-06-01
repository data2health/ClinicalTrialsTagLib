package edu.uiowa.slis.ClinicalTrialsTagLib.secondaryOutcome;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class SecondaryOutcomeDescription extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(SecondaryOutcomeDescription.class);


	public int doStartTag() throws JspException {
		try {
			SecondaryOutcome theSecondaryOutcome = (SecondaryOutcome)findAncestorWithClass(this, SecondaryOutcome.class);
			if (!theSecondaryOutcome.commitNeeded) {
				pageContext.getOut().print(theSecondaryOutcome.getDescription());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing SecondaryOutcome for description tag ", e);
			throw new JspTagException("Error: Can't find enclosing SecondaryOutcome for description tag ");
		}
		return SKIP_BODY;
	}

	public String getDescription() throws JspTagException {
		try {
			SecondaryOutcome theSecondaryOutcome = (SecondaryOutcome)findAncestorWithClass(this, SecondaryOutcome.class);
			return theSecondaryOutcome.getDescription();
		} catch (Exception e) {
			log.error(" Can't find enclosing SecondaryOutcome for description tag ", e);
			throw new JspTagException("Error: Can't find enclosing SecondaryOutcome for description tag ");
		}
	}

	public void setDescription(String description) throws JspTagException {
		try {
			SecondaryOutcome theSecondaryOutcome = (SecondaryOutcome)findAncestorWithClass(this, SecondaryOutcome.class);
			theSecondaryOutcome.setDescription(description);
		} catch (Exception e) {
			log.error("Can't find enclosing SecondaryOutcome for description tag ", e);
			throw new JspTagException("Error: Can't find enclosing SecondaryOutcome for description tag ");
		}
	}

}
