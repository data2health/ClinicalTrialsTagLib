package edu.uiowa.slis.ClinicalTrialsTagLib.resultsMeasurement;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsMeasurementLowerLimit extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsMeasurementLowerLimit.class);


	public int doStartTag() throws JspException {
		try {
			ResultsMeasurement theResultsMeasurement = (ResultsMeasurement)findAncestorWithClass(this, ResultsMeasurement.class);
			if (!theResultsMeasurement.commitNeeded) {
				pageContext.getOut().print(theResultsMeasurement.getLowerLimit());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsMeasurement for lowerLimit tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsMeasurement for lowerLimit tag ");
		}
		return SKIP_BODY;
	}

	public String getLowerLimit() throws JspTagException {
		try {
			ResultsMeasurement theResultsMeasurement = (ResultsMeasurement)findAncestorWithClass(this, ResultsMeasurement.class);
			return theResultsMeasurement.getLowerLimit();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsMeasurement for lowerLimit tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsMeasurement for lowerLimit tag ");
		}
	}

	public void setLowerLimit(String lowerLimit) throws JspTagException {
		try {
			ResultsMeasurement theResultsMeasurement = (ResultsMeasurement)findAncestorWithClass(this, ResultsMeasurement.class);
			theResultsMeasurement.setLowerLimit(lowerLimit);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsMeasurement for lowerLimit tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsMeasurement for lowerLimit tag ");
		}
	}

}
