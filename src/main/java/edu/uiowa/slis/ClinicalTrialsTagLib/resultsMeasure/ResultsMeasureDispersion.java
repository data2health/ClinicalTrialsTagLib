package edu.uiowa.slis.ClinicalTrialsTagLib.resultsMeasure;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsMeasureDispersion extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsMeasureDispersion.class);


	public int doStartTag() throws JspException {
		try {
			ResultsMeasure theResultsMeasure = (ResultsMeasure)findAncestorWithClass(this, ResultsMeasure.class);
			if (!theResultsMeasure.commitNeeded) {
				pageContext.getOut().print(theResultsMeasure.getDispersion());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsMeasure for dispersion tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsMeasure for dispersion tag ");
		}
		return SKIP_BODY;
	}

	public String getDispersion() throws JspTagException {
		try {
			ResultsMeasure theResultsMeasure = (ResultsMeasure)findAncestorWithClass(this, ResultsMeasure.class);
			return theResultsMeasure.getDispersion();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsMeasure for dispersion tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsMeasure for dispersion tag ");
		}
	}

	public void setDispersion(String dispersion) throws JspTagException {
		try {
			ResultsMeasure theResultsMeasure = (ResultsMeasure)findAncestorWithClass(this, ResultsMeasure.class);
			theResultsMeasure.setDispersion(dispersion);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsMeasure for dispersion tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsMeasure for dispersion tag ");
		}
	}

}
