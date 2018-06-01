package edu.uiowa.slis.ClinicalTrialsTagLib.resultsMeasure;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsMeasureID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsMeasureID.class);


	public int doStartTag() throws JspException {
		try {
			ResultsMeasure theResultsMeasure = (ResultsMeasure)findAncestorWithClass(this, ResultsMeasure.class);
			if (!theResultsMeasure.commitNeeded) {
				pageContext.getOut().print(theResultsMeasure.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsMeasure for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsMeasure for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			ResultsMeasure theResultsMeasure = (ResultsMeasure)findAncestorWithClass(this, ResultsMeasure.class);
			return theResultsMeasure.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsMeasure for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsMeasure for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			ResultsMeasure theResultsMeasure = (ResultsMeasure)findAncestorWithClass(this, ResultsMeasure.class);
			theResultsMeasure.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsMeasure for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsMeasure for ID tag ");
		}
	}

}
