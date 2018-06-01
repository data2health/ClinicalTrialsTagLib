package edu.uiowa.slis.ClinicalTrialsTagLib.otherOutcome;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class OtherOutcomeMeasure extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(OtherOutcomeMeasure.class);


	public int doStartTag() throws JspException {
		try {
			OtherOutcome theOtherOutcome = (OtherOutcome)findAncestorWithClass(this, OtherOutcome.class);
			if (!theOtherOutcome.commitNeeded) {
				pageContext.getOut().print(theOtherOutcome.getMeasure());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing OtherOutcome for measure tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherOutcome for measure tag ");
		}
		return SKIP_BODY;
	}

	public String getMeasure() throws JspTagException {
		try {
			OtherOutcome theOtherOutcome = (OtherOutcome)findAncestorWithClass(this, OtherOutcome.class);
			return theOtherOutcome.getMeasure();
		} catch (Exception e) {
			log.error(" Can't find enclosing OtherOutcome for measure tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherOutcome for measure tag ");
		}
	}

	public void setMeasure(String measure) throws JspTagException {
		try {
			OtherOutcome theOtherOutcome = (OtherOutcome)findAncestorWithClass(this, OtherOutcome.class);
			theOtherOutcome.setMeasure(measure);
		} catch (Exception e) {
			log.error("Can't find enclosing OtherOutcome for measure tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherOutcome for measure tag ");
		}
	}

}
