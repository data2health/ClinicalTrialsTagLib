package edu.uiowa.slis.ClinicalTrialsTagLib.baselineMeasure;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class BaselineMeasureDispersion extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(BaselineMeasureDispersion.class);


	public int doStartTag() throws JspException {
		try {
			BaselineMeasure theBaselineMeasure = (BaselineMeasure)findAncestorWithClass(this, BaselineMeasure.class);
			if (!theBaselineMeasure.commitNeeded) {
				pageContext.getOut().print(theBaselineMeasure.getDispersion());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing BaselineMeasure for dispersion tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineMeasure for dispersion tag ");
		}
		return SKIP_BODY;
	}

	public String getDispersion() throws JspTagException {
		try {
			BaselineMeasure theBaselineMeasure = (BaselineMeasure)findAncestorWithClass(this, BaselineMeasure.class);
			return theBaselineMeasure.getDispersion();
		} catch (Exception e) {
			log.error(" Can't find enclosing BaselineMeasure for dispersion tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineMeasure for dispersion tag ");
		}
	}

	public void setDispersion(String dispersion) throws JspTagException {
		try {
			BaselineMeasure theBaselineMeasure = (BaselineMeasure)findAncestorWithClass(this, BaselineMeasure.class);
			theBaselineMeasure.setDispersion(dispersion);
		} catch (Exception e) {
			log.error("Can't find enclosing BaselineMeasure for dispersion tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineMeasure for dispersion tag ");
		}
	}

}
