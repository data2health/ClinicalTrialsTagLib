package edu.uiowa.slis.ClinicalTrialsTagLib.baselineMeasurement;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class BaselineMeasurementUpperLimit extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(BaselineMeasurementUpperLimit.class);


	public int doStartTag() throws JspException {
		try {
			BaselineMeasurement theBaselineMeasurement = (BaselineMeasurement)findAncestorWithClass(this, BaselineMeasurement.class);
			if (!theBaselineMeasurement.commitNeeded) {
				pageContext.getOut().print(theBaselineMeasurement.getUpperLimit());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing BaselineMeasurement for upperLimit tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineMeasurement for upperLimit tag ");
		}
		return SKIP_BODY;
	}

	public String getUpperLimit() throws JspTagException {
		try {
			BaselineMeasurement theBaselineMeasurement = (BaselineMeasurement)findAncestorWithClass(this, BaselineMeasurement.class);
			return theBaselineMeasurement.getUpperLimit();
		} catch (Exception e) {
			log.error(" Can't find enclosing BaselineMeasurement for upperLimit tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineMeasurement for upperLimit tag ");
		}
	}

	public void setUpperLimit(String upperLimit) throws JspTagException {
		try {
			BaselineMeasurement theBaselineMeasurement = (BaselineMeasurement)findAncestorWithClass(this, BaselineMeasurement.class);
			theBaselineMeasurement.setUpperLimit(upperLimit);
		} catch (Exception e) {
			log.error("Can't find enclosing BaselineMeasurement for upperLimit tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineMeasurement for upperLimit tag ");
		}
	}

}
