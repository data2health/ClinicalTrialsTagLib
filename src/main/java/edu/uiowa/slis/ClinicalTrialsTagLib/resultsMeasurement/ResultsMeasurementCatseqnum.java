package edu.uiowa.slis.ClinicalTrialsTagLib.resultsMeasurement;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsMeasurementCatseqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsMeasurementCatseqnum.class);


	public int doStartTag() throws JspException {
		try {
			ResultsMeasurement theResultsMeasurement = (ResultsMeasurement)findAncestorWithClass(this, ResultsMeasurement.class);
			if (!theResultsMeasurement.commitNeeded) {
				pageContext.getOut().print(theResultsMeasurement.getCatseqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsMeasurement for catseqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsMeasurement for catseqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getCatseqnum() throws JspTagException {
		try {
			ResultsMeasurement theResultsMeasurement = (ResultsMeasurement)findAncestorWithClass(this, ResultsMeasurement.class);
			return theResultsMeasurement.getCatseqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsMeasurement for catseqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsMeasurement for catseqnum tag ");
		}
	}

	public void setCatseqnum(int catseqnum) throws JspTagException {
		try {
			ResultsMeasurement theResultsMeasurement = (ResultsMeasurement)findAncestorWithClass(this, ResultsMeasurement.class);
			theResultsMeasurement.setCatseqnum(catseqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsMeasurement for catseqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsMeasurement for catseqnum tag ");
		}
	}

}
