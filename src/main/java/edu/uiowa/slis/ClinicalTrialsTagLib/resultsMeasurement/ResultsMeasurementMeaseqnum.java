package edu.uiowa.slis.ClinicalTrialsTagLib.resultsMeasurement;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsMeasurementMeaseqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsMeasurementMeaseqnum.class);


	public int doStartTag() throws JspException {
		try {
			ResultsMeasurement theResultsMeasurement = (ResultsMeasurement)findAncestorWithClass(this, ResultsMeasurement.class);
			if (!theResultsMeasurement.commitNeeded) {
				pageContext.getOut().print(theResultsMeasurement.getMeaseqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsMeasurement for measeqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsMeasurement for measeqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getMeaseqnum() throws JspTagException {
		try {
			ResultsMeasurement theResultsMeasurement = (ResultsMeasurement)findAncestorWithClass(this, ResultsMeasurement.class);
			return theResultsMeasurement.getMeaseqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsMeasurement for measeqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsMeasurement for measeqnum tag ");
		}
	}

	public void setMeaseqnum(int measeqnum) throws JspTagException {
		try {
			ResultsMeasurement theResultsMeasurement = (ResultsMeasurement)findAncestorWithClass(this, ResultsMeasurement.class);
			theResultsMeasurement.setMeaseqnum(measeqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsMeasurement for measeqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsMeasurement for measeqnum tag ");
		}
	}

}
