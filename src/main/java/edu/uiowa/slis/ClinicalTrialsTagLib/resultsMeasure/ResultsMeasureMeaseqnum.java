package edu.uiowa.slis.ClinicalTrialsTagLib.resultsMeasure;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsMeasureMeaseqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsMeasureMeaseqnum.class);


	public int doStartTag() throws JspException {
		try {
			ResultsMeasure theResultsMeasure = (ResultsMeasure)findAncestorWithClass(this, ResultsMeasure.class);
			if (!theResultsMeasure.commitNeeded) {
				pageContext.getOut().print(theResultsMeasure.getMeaseqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsMeasure for measeqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsMeasure for measeqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getMeaseqnum() throws JspTagException {
		try {
			ResultsMeasure theResultsMeasure = (ResultsMeasure)findAncestorWithClass(this, ResultsMeasure.class);
			return theResultsMeasure.getMeaseqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsMeasure for measeqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsMeasure for measeqnum tag ");
		}
	}

	public void setMeaseqnum(int measeqnum) throws JspTagException {
		try {
			ResultsMeasure theResultsMeasure = (ResultsMeasure)findAncestorWithClass(this, ResultsMeasure.class);
			theResultsMeasure.setMeaseqnum(measeqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsMeasure for measeqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsMeasure for measeqnum tag ");
		}
	}

}
