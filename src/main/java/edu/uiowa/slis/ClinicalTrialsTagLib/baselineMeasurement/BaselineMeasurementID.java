package edu.uiowa.slis.ClinicalTrialsTagLib.baselineMeasurement;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class BaselineMeasurementID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(BaselineMeasurementID.class);


	public int doStartTag() throws JspException {
		try {
			BaselineMeasurement theBaselineMeasurement = (BaselineMeasurement)findAncestorWithClass(this, BaselineMeasurement.class);
			if (!theBaselineMeasurement.commitNeeded) {
				pageContext.getOut().print(theBaselineMeasurement.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing BaselineMeasurement for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineMeasurement for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			BaselineMeasurement theBaselineMeasurement = (BaselineMeasurement)findAncestorWithClass(this, BaselineMeasurement.class);
			return theBaselineMeasurement.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing BaselineMeasurement for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineMeasurement for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			BaselineMeasurement theBaselineMeasurement = (BaselineMeasurement)findAncestorWithClass(this, BaselineMeasurement.class);
			theBaselineMeasurement.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing BaselineMeasurement for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineMeasurement for ID tag ");
		}
	}

}
