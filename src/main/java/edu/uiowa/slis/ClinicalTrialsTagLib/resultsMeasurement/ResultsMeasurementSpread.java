package edu.uiowa.slis.ClinicalTrialsTagLib.resultsMeasurement;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsMeasurementSpread extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsMeasurementSpread.class);


	public int doStartTag() throws JspException {
		try {
			ResultsMeasurement theResultsMeasurement = (ResultsMeasurement)findAncestorWithClass(this, ResultsMeasurement.class);
			if (!theResultsMeasurement.commitNeeded) {
				pageContext.getOut().print(theResultsMeasurement.getSpread());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsMeasurement for spread tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsMeasurement for spread tag ");
		}
		return SKIP_BODY;
	}

	public String getSpread() throws JspTagException {
		try {
			ResultsMeasurement theResultsMeasurement = (ResultsMeasurement)findAncestorWithClass(this, ResultsMeasurement.class);
			return theResultsMeasurement.getSpread();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsMeasurement for spread tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsMeasurement for spread tag ");
		}
	}

	public void setSpread(String spread) throws JspTagException {
		try {
			ResultsMeasurement theResultsMeasurement = (ResultsMeasurement)findAncestorWithClass(this, ResultsMeasurement.class);
			theResultsMeasurement.setSpread(spread);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsMeasurement for spread tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsMeasurement for spread tag ");
		}
	}

}
