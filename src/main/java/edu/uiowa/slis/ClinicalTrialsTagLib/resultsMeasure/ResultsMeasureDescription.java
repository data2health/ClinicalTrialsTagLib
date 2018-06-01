package edu.uiowa.slis.ClinicalTrialsTagLib.resultsMeasure;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsMeasureDescription extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsMeasureDescription.class);


	public int doStartTag() throws JspException {
		try {
			ResultsMeasure theResultsMeasure = (ResultsMeasure)findAncestorWithClass(this, ResultsMeasure.class);
			if (!theResultsMeasure.commitNeeded) {
				pageContext.getOut().print(theResultsMeasure.getDescription());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsMeasure for description tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsMeasure for description tag ");
		}
		return SKIP_BODY;
	}

	public String getDescription() throws JspTagException {
		try {
			ResultsMeasure theResultsMeasure = (ResultsMeasure)findAncestorWithClass(this, ResultsMeasure.class);
			return theResultsMeasure.getDescription();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsMeasure for description tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsMeasure for description tag ");
		}
	}

	public void setDescription(String description) throws JspTagException {
		try {
			ResultsMeasure theResultsMeasure = (ResultsMeasure)findAncestorWithClass(this, ResultsMeasure.class);
			theResultsMeasure.setDescription(description);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsMeasure for description tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsMeasure for description tag ");
		}
	}

}
