package edu.uiowa.slis.ClinicalTrialsTagLib.baselineMeasure;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class BaselineMeasureDescription extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(BaselineMeasureDescription.class);


	public int doStartTag() throws JspException {
		try {
			BaselineMeasure theBaselineMeasure = (BaselineMeasure)findAncestorWithClass(this, BaselineMeasure.class);
			if (!theBaselineMeasure.commitNeeded) {
				pageContext.getOut().print(theBaselineMeasure.getDescription());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing BaselineMeasure for description tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineMeasure for description tag ");
		}
		return SKIP_BODY;
	}

	public String getDescription() throws JspTagException {
		try {
			BaselineMeasure theBaselineMeasure = (BaselineMeasure)findAncestorWithClass(this, BaselineMeasure.class);
			return theBaselineMeasure.getDescription();
		} catch (Exception e) {
			log.error(" Can't find enclosing BaselineMeasure for description tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineMeasure for description tag ");
		}
	}

	public void setDescription(String description) throws JspTagException {
		try {
			BaselineMeasure theBaselineMeasure = (BaselineMeasure)findAncestorWithClass(this, BaselineMeasure.class);
			theBaselineMeasure.setDescription(description);
		} catch (Exception e) {
			log.error("Can't find enclosing BaselineMeasure for description tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineMeasure for description tag ");
		}
	}

}
