package edu.uiowa.slis.ClinicalTrialsTagLib.resultsMeasurement;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsMeasurementUpperLimit extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsMeasurementUpperLimit.class);


	public int doStartTag() throws JspException {
		try {
			ResultsMeasurement theResultsMeasurement = (ResultsMeasurement)findAncestorWithClass(this, ResultsMeasurement.class);
			if (!theResultsMeasurement.commitNeeded) {
				pageContext.getOut().print(theResultsMeasurement.getUpperLimit());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsMeasurement for upperLimit tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsMeasurement for upperLimit tag ");
		}
		return SKIP_BODY;
	}

	public String getUpperLimit() throws JspTagException {
		try {
			ResultsMeasurement theResultsMeasurement = (ResultsMeasurement)findAncestorWithClass(this, ResultsMeasurement.class);
			return theResultsMeasurement.getUpperLimit();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsMeasurement for upperLimit tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsMeasurement for upperLimit tag ");
		}
	}

	public void setUpperLimit(String upperLimit) throws JspTagException {
		try {
			ResultsMeasurement theResultsMeasurement = (ResultsMeasurement)findAncestorWithClass(this, ResultsMeasurement.class);
			theResultsMeasurement.setUpperLimit(upperLimit);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsMeasurement for upperLimit tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsMeasurement for upperLimit tag ");
		}
	}

}
