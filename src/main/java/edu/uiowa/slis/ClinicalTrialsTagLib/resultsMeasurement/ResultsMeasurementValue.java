package edu.uiowa.slis.ClinicalTrialsTagLib.resultsMeasurement;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsMeasurementValue extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsMeasurementValue.class);


	public int doStartTag() throws JspException {
		try {
			ResultsMeasurement theResultsMeasurement = (ResultsMeasurement)findAncestorWithClass(this, ResultsMeasurement.class);
			if (!theResultsMeasurement.commitNeeded) {
				pageContext.getOut().print(theResultsMeasurement.getValue());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsMeasurement for value tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsMeasurement for value tag ");
		}
		return SKIP_BODY;
	}

	public String getValue() throws JspTagException {
		try {
			ResultsMeasurement theResultsMeasurement = (ResultsMeasurement)findAncestorWithClass(this, ResultsMeasurement.class);
			return theResultsMeasurement.getValue();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsMeasurement for value tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsMeasurement for value tag ");
		}
	}

	public void setValue(String value) throws JspTagException {
		try {
			ResultsMeasurement theResultsMeasurement = (ResultsMeasurement)findAncestorWithClass(this, ResultsMeasurement.class);
			theResultsMeasurement.setValue(value);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsMeasurement for value tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsMeasurement for value tag ");
		}
	}

}
