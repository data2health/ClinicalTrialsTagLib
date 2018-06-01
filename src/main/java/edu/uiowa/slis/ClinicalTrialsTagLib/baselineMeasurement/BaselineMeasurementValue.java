package edu.uiowa.slis.ClinicalTrialsTagLib.baselineMeasurement;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class BaselineMeasurementValue extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(BaselineMeasurementValue.class);


	public int doStartTag() throws JspException {
		try {
			BaselineMeasurement theBaselineMeasurement = (BaselineMeasurement)findAncestorWithClass(this, BaselineMeasurement.class);
			if (!theBaselineMeasurement.commitNeeded) {
				pageContext.getOut().print(theBaselineMeasurement.getValue());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing BaselineMeasurement for value tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineMeasurement for value tag ");
		}
		return SKIP_BODY;
	}

	public String getValue() throws JspTagException {
		try {
			BaselineMeasurement theBaselineMeasurement = (BaselineMeasurement)findAncestorWithClass(this, BaselineMeasurement.class);
			return theBaselineMeasurement.getValue();
		} catch (Exception e) {
			log.error(" Can't find enclosing BaselineMeasurement for value tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineMeasurement for value tag ");
		}
	}

	public void setValue(String value) throws JspTagException {
		try {
			BaselineMeasurement theBaselineMeasurement = (BaselineMeasurement)findAncestorWithClass(this, BaselineMeasurement.class);
			theBaselineMeasurement.setValue(value);
		} catch (Exception e) {
			log.error("Can't find enclosing BaselineMeasurement for value tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineMeasurement for value tag ");
		}
	}

}
