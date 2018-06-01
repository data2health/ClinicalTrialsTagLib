package edu.uiowa.slis.ClinicalTrialsTagLib.baselineMeasurement;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class BaselineMeasurementMeaseqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(BaselineMeasurementMeaseqnum.class);


	public int doStartTag() throws JspException {
		try {
			BaselineMeasurement theBaselineMeasurement = (BaselineMeasurement)findAncestorWithClass(this, BaselineMeasurement.class);
			if (!theBaselineMeasurement.commitNeeded) {
				pageContext.getOut().print(theBaselineMeasurement.getMeaseqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing BaselineMeasurement for measeqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineMeasurement for measeqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getMeaseqnum() throws JspTagException {
		try {
			BaselineMeasurement theBaselineMeasurement = (BaselineMeasurement)findAncestorWithClass(this, BaselineMeasurement.class);
			return theBaselineMeasurement.getMeaseqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing BaselineMeasurement for measeqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineMeasurement for measeqnum tag ");
		}
	}

	public void setMeaseqnum(int measeqnum) throws JspTagException {
		try {
			BaselineMeasurement theBaselineMeasurement = (BaselineMeasurement)findAncestorWithClass(this, BaselineMeasurement.class);
			theBaselineMeasurement.setMeaseqnum(measeqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing BaselineMeasurement for measeqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineMeasurement for measeqnum tag ");
		}
	}

}
