package edu.uiowa.slis.ClinicalTrialsTagLib.baselineMeasurement;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class BaselineMeasurementLowerLimit extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(BaselineMeasurementLowerLimit.class);


	public int doStartTag() throws JspException {
		try {
			BaselineMeasurement theBaselineMeasurement = (BaselineMeasurement)findAncestorWithClass(this, BaselineMeasurement.class);
			if (!theBaselineMeasurement.commitNeeded) {
				pageContext.getOut().print(theBaselineMeasurement.getLowerLimit());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing BaselineMeasurement for lowerLimit tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineMeasurement for lowerLimit tag ");
		}
		return SKIP_BODY;
	}

	public String getLowerLimit() throws JspTagException {
		try {
			BaselineMeasurement theBaselineMeasurement = (BaselineMeasurement)findAncestorWithClass(this, BaselineMeasurement.class);
			return theBaselineMeasurement.getLowerLimit();
		} catch (Exception e) {
			log.error(" Can't find enclosing BaselineMeasurement for lowerLimit tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineMeasurement for lowerLimit tag ");
		}
	}

	public void setLowerLimit(String lowerLimit) throws JspTagException {
		try {
			BaselineMeasurement theBaselineMeasurement = (BaselineMeasurement)findAncestorWithClass(this, BaselineMeasurement.class);
			theBaselineMeasurement.setLowerLimit(lowerLimit);
		} catch (Exception e) {
			log.error("Can't find enclosing BaselineMeasurement for lowerLimit tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineMeasurement for lowerLimit tag ");
		}
	}

}
