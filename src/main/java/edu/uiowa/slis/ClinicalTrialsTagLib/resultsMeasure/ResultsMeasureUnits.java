package edu.uiowa.slis.ClinicalTrialsTagLib.resultsMeasure;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsMeasureUnits extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsMeasureUnits.class);


	public int doStartTag() throws JspException {
		try {
			ResultsMeasure theResultsMeasure = (ResultsMeasure)findAncestorWithClass(this, ResultsMeasure.class);
			if (!theResultsMeasure.commitNeeded) {
				pageContext.getOut().print(theResultsMeasure.getUnits());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsMeasure for units tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsMeasure for units tag ");
		}
		return SKIP_BODY;
	}

	public String getUnits() throws JspTagException {
		try {
			ResultsMeasure theResultsMeasure = (ResultsMeasure)findAncestorWithClass(this, ResultsMeasure.class);
			return theResultsMeasure.getUnits();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsMeasure for units tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsMeasure for units tag ");
		}
	}

	public void setUnits(String units) throws JspTagException {
		try {
			ResultsMeasure theResultsMeasure = (ResultsMeasure)findAncestorWithClass(this, ResultsMeasure.class);
			theResultsMeasure.setUnits(units);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsMeasure for units tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsMeasure for units tag ");
		}
	}

}
