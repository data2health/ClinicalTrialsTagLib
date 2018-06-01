package edu.uiowa.slis.ClinicalTrialsTagLib.baselineMeasure;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class BaselineMeasureTitle extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(BaselineMeasureTitle.class);


	public int doStartTag() throws JspException {
		try {
			BaselineMeasure theBaselineMeasure = (BaselineMeasure)findAncestorWithClass(this, BaselineMeasure.class);
			if (!theBaselineMeasure.commitNeeded) {
				pageContext.getOut().print(theBaselineMeasure.getTitle());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing BaselineMeasure for title tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineMeasure for title tag ");
		}
		return SKIP_BODY;
	}

	public String getTitle() throws JspTagException {
		try {
			BaselineMeasure theBaselineMeasure = (BaselineMeasure)findAncestorWithClass(this, BaselineMeasure.class);
			return theBaselineMeasure.getTitle();
		} catch (Exception e) {
			log.error(" Can't find enclosing BaselineMeasure for title tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineMeasure for title tag ");
		}
	}

	public void setTitle(String title) throws JspTagException {
		try {
			BaselineMeasure theBaselineMeasure = (BaselineMeasure)findAncestorWithClass(this, BaselineMeasure.class);
			theBaselineMeasure.setTitle(title);
		} catch (Exception e) {
			log.error("Can't find enclosing BaselineMeasure for title tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineMeasure for title tag ");
		}
	}

}
