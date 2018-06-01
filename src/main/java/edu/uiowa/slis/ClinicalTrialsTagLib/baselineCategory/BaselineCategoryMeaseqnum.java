package edu.uiowa.slis.ClinicalTrialsTagLib.baselineCategory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class BaselineCategoryMeaseqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(BaselineCategoryMeaseqnum.class);


	public int doStartTag() throws JspException {
		try {
			BaselineCategory theBaselineCategory = (BaselineCategory)findAncestorWithClass(this, BaselineCategory.class);
			if (!theBaselineCategory.commitNeeded) {
				pageContext.getOut().print(theBaselineCategory.getMeaseqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing BaselineCategory for measeqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineCategory for measeqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getMeaseqnum() throws JspTagException {
		try {
			BaselineCategory theBaselineCategory = (BaselineCategory)findAncestorWithClass(this, BaselineCategory.class);
			return theBaselineCategory.getMeaseqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing BaselineCategory for measeqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineCategory for measeqnum tag ");
		}
	}

	public void setMeaseqnum(int measeqnum) throws JspTagException {
		try {
			BaselineCategory theBaselineCategory = (BaselineCategory)findAncestorWithClass(this, BaselineCategory.class);
			theBaselineCategory.setMeaseqnum(measeqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing BaselineCategory for measeqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineCategory for measeqnum tag ");
		}
	}

}
