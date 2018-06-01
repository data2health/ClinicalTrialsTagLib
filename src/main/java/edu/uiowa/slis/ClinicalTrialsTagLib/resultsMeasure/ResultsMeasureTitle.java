package edu.uiowa.slis.ClinicalTrialsTagLib.resultsMeasure;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsMeasureTitle extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsMeasureTitle.class);


	public int doStartTag() throws JspException {
		try {
			ResultsMeasure theResultsMeasure = (ResultsMeasure)findAncestorWithClass(this, ResultsMeasure.class);
			if (!theResultsMeasure.commitNeeded) {
				pageContext.getOut().print(theResultsMeasure.getTitle());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsMeasure for title tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsMeasure for title tag ");
		}
		return SKIP_BODY;
	}

	public String getTitle() throws JspTagException {
		try {
			ResultsMeasure theResultsMeasure = (ResultsMeasure)findAncestorWithClass(this, ResultsMeasure.class);
			return theResultsMeasure.getTitle();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsMeasure for title tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsMeasure for title tag ");
		}
	}

	public void setTitle(String title) throws JspTagException {
		try {
			ResultsMeasure theResultsMeasure = (ResultsMeasure)findAncestorWithClass(this, ResultsMeasure.class);
			theResultsMeasure.setTitle(title);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsMeasure for title tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsMeasure for title tag ");
		}
	}

}
