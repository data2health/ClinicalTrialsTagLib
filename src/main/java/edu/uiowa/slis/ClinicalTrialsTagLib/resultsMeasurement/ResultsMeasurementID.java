package edu.uiowa.slis.ClinicalTrialsTagLib.resultsMeasurement;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsMeasurementID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsMeasurementID.class);


	public int doStartTag() throws JspException {
		try {
			ResultsMeasurement theResultsMeasurement = (ResultsMeasurement)findAncestorWithClass(this, ResultsMeasurement.class);
			if (!theResultsMeasurement.commitNeeded) {
				pageContext.getOut().print(theResultsMeasurement.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsMeasurement for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsMeasurement for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			ResultsMeasurement theResultsMeasurement = (ResultsMeasurement)findAncestorWithClass(this, ResultsMeasurement.class);
			return theResultsMeasurement.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsMeasurement for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsMeasurement for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			ResultsMeasurement theResultsMeasurement = (ResultsMeasurement)findAncestorWithClass(this, ResultsMeasurement.class);
			theResultsMeasurement.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsMeasurement for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsMeasurement for ID tag ");
		}
	}

}
