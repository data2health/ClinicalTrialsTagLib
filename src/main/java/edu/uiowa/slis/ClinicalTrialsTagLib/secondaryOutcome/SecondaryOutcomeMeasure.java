package edu.uiowa.slis.ClinicalTrialsTagLib.secondaryOutcome;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class SecondaryOutcomeMeasure extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(SecondaryOutcomeMeasure.class);


	public int doStartTag() throws JspException {
		try {
			SecondaryOutcome theSecondaryOutcome = (SecondaryOutcome)findAncestorWithClass(this, SecondaryOutcome.class);
			if (!theSecondaryOutcome.commitNeeded) {
				pageContext.getOut().print(theSecondaryOutcome.getMeasure());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing SecondaryOutcome for measure tag ", e);
			throw new JspTagException("Error: Can't find enclosing SecondaryOutcome for measure tag ");
		}
		return SKIP_BODY;
	}

	public String getMeasure() throws JspTagException {
		try {
			SecondaryOutcome theSecondaryOutcome = (SecondaryOutcome)findAncestorWithClass(this, SecondaryOutcome.class);
			return theSecondaryOutcome.getMeasure();
		} catch (Exception e) {
			log.error(" Can't find enclosing SecondaryOutcome for measure tag ", e);
			throw new JspTagException("Error: Can't find enclosing SecondaryOutcome for measure tag ");
		}
	}

	public void setMeasure(String measure) throws JspTagException {
		try {
			SecondaryOutcome theSecondaryOutcome = (SecondaryOutcome)findAncestorWithClass(this, SecondaryOutcome.class);
			theSecondaryOutcome.setMeasure(measure);
		} catch (Exception e) {
			log.error("Can't find enclosing SecondaryOutcome for measure tag ", e);
			throw new JspTagException("Error: Can't find enclosing SecondaryOutcome for measure tag ");
		}
	}

}
