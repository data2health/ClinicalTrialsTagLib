package edu.uiowa.slis.ClinicalTrialsTagLib.primaryOutcome;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class PrimaryOutcomeMeasure extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(PrimaryOutcomeMeasure.class);


	public int doStartTag() throws JspException {
		try {
			PrimaryOutcome thePrimaryOutcome = (PrimaryOutcome)findAncestorWithClass(this, PrimaryOutcome.class);
			if (!thePrimaryOutcome.commitNeeded) {
				pageContext.getOut().print(thePrimaryOutcome.getMeasure());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing PrimaryOutcome for measure tag ", e);
			throw new JspTagException("Error: Can't find enclosing PrimaryOutcome for measure tag ");
		}
		return SKIP_BODY;
	}

	public String getMeasure() throws JspTagException {
		try {
			PrimaryOutcome thePrimaryOutcome = (PrimaryOutcome)findAncestorWithClass(this, PrimaryOutcome.class);
			return thePrimaryOutcome.getMeasure();
		} catch (Exception e) {
			log.error(" Can't find enclosing PrimaryOutcome for measure tag ", e);
			throw new JspTagException("Error: Can't find enclosing PrimaryOutcome for measure tag ");
		}
	}

	public void setMeasure(String measure) throws JspTagException {
		try {
			PrimaryOutcome thePrimaryOutcome = (PrimaryOutcome)findAncestorWithClass(this, PrimaryOutcome.class);
			thePrimaryOutcome.setMeasure(measure);
		} catch (Exception e) {
			log.error("Can't find enclosing PrimaryOutcome for measure tag ", e);
			throw new JspTagException("Error: Can't find enclosing PrimaryOutcome for measure tag ");
		}
	}

}
