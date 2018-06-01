package edu.uiowa.slis.ClinicalTrialsTagLib.baselineMeasure;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class BaselineMeasureUnits extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(BaselineMeasureUnits.class);


	public int doStartTag() throws JspException {
		try {
			BaselineMeasure theBaselineMeasure = (BaselineMeasure)findAncestorWithClass(this, BaselineMeasure.class);
			if (!theBaselineMeasure.commitNeeded) {
				pageContext.getOut().print(theBaselineMeasure.getUnits());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing BaselineMeasure for units tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineMeasure for units tag ");
		}
		return SKIP_BODY;
	}

	public String getUnits() throws JspTagException {
		try {
			BaselineMeasure theBaselineMeasure = (BaselineMeasure)findAncestorWithClass(this, BaselineMeasure.class);
			return theBaselineMeasure.getUnits();
		} catch (Exception e) {
			log.error(" Can't find enclosing BaselineMeasure for units tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineMeasure for units tag ");
		}
	}

	public void setUnits(String units) throws JspTagException {
		try {
			BaselineMeasure theBaselineMeasure = (BaselineMeasure)findAncestorWithClass(this, BaselineMeasure.class);
			theBaselineMeasure.setUnits(units);
		} catch (Exception e) {
			log.error("Can't find enclosing BaselineMeasure for units tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineMeasure for units tag ");
		}
	}

}
