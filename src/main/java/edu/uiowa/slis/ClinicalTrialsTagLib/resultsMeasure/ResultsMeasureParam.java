package edu.uiowa.slis.ClinicalTrialsTagLib.resultsMeasure;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsMeasureParam extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsMeasureParam.class);


	public int doStartTag() throws JspException {
		try {
			ResultsMeasure theResultsMeasure = (ResultsMeasure)findAncestorWithClass(this, ResultsMeasure.class);
			if (!theResultsMeasure.commitNeeded) {
				pageContext.getOut().print(theResultsMeasure.getParam());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsMeasure for param tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsMeasure for param tag ");
		}
		return SKIP_BODY;
	}

	public String getParam() throws JspTagException {
		try {
			ResultsMeasure theResultsMeasure = (ResultsMeasure)findAncestorWithClass(this, ResultsMeasure.class);
			return theResultsMeasure.getParam();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsMeasure for param tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsMeasure for param tag ");
		}
	}

	public void setParam(String param) throws JspTagException {
		try {
			ResultsMeasure theResultsMeasure = (ResultsMeasure)findAncestorWithClass(this, ResultsMeasure.class);
			theResultsMeasure.setParam(param);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsMeasure for param tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsMeasure for param tag ");
		}
	}

}
