package edu.uiowa.slis.ClinicalTrialsTagLib.baselineMeasure;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class BaselineMeasureParam extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(BaselineMeasureParam.class);


	public int doStartTag() throws JspException {
		try {
			BaselineMeasure theBaselineMeasure = (BaselineMeasure)findAncestorWithClass(this, BaselineMeasure.class);
			if (!theBaselineMeasure.commitNeeded) {
				pageContext.getOut().print(theBaselineMeasure.getParam());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing BaselineMeasure for param tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineMeasure for param tag ");
		}
		return SKIP_BODY;
	}

	public String getParam() throws JspTagException {
		try {
			BaselineMeasure theBaselineMeasure = (BaselineMeasure)findAncestorWithClass(this, BaselineMeasure.class);
			return theBaselineMeasure.getParam();
		} catch (Exception e) {
			log.error(" Can't find enclosing BaselineMeasure for param tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineMeasure for param tag ");
		}
	}

	public void setParam(String param) throws JspTagException {
		try {
			BaselineMeasure theBaselineMeasure = (BaselineMeasure)findAncestorWithClass(this, BaselineMeasure.class);
			theBaselineMeasure.setParam(param);
		} catch (Exception e) {
			log.error("Can't find enclosing BaselineMeasure for param tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineMeasure for param tag ");
		}
	}

}
