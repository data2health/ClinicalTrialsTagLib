package edu.uiowa.slis.ClinicalTrialsTagLib.baselineMeasurement;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class BaselineMeasurementSpread extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(BaselineMeasurementSpread.class);


	public int doStartTag() throws JspException {
		try {
			BaselineMeasurement theBaselineMeasurement = (BaselineMeasurement)findAncestorWithClass(this, BaselineMeasurement.class);
			if (!theBaselineMeasurement.commitNeeded) {
				pageContext.getOut().print(theBaselineMeasurement.getSpread());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing BaselineMeasurement for spread tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineMeasurement for spread tag ");
		}
		return SKIP_BODY;
	}

	public String getSpread() throws JspTagException {
		try {
			BaselineMeasurement theBaselineMeasurement = (BaselineMeasurement)findAncestorWithClass(this, BaselineMeasurement.class);
			return theBaselineMeasurement.getSpread();
		} catch (Exception e) {
			log.error(" Can't find enclosing BaselineMeasurement for spread tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineMeasurement for spread tag ");
		}
	}

	public void setSpread(String spread) throws JspTagException {
		try {
			BaselineMeasurement theBaselineMeasurement = (BaselineMeasurement)findAncestorWithClass(this, BaselineMeasurement.class);
			theBaselineMeasurement.setSpread(spread);
		} catch (Exception e) {
			log.error("Can't find enclosing BaselineMeasurement for spread tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineMeasurement for spread tag ");
		}
	}

}
