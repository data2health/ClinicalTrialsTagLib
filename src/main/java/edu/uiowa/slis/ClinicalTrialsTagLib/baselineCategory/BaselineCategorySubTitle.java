package edu.uiowa.slis.ClinicalTrialsTagLib.baselineCategory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class BaselineCategorySubTitle extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(BaselineCategorySubTitle.class);


	public int doStartTag() throws JspException {
		try {
			BaselineCategory theBaselineCategory = (BaselineCategory)findAncestorWithClass(this, BaselineCategory.class);
			if (!theBaselineCategory.commitNeeded) {
				pageContext.getOut().print(theBaselineCategory.getSubTitle());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing BaselineCategory for subTitle tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineCategory for subTitle tag ");
		}
		return SKIP_BODY;
	}

	public String getSubTitle() throws JspTagException {
		try {
			BaselineCategory theBaselineCategory = (BaselineCategory)findAncestorWithClass(this, BaselineCategory.class);
			return theBaselineCategory.getSubTitle();
		} catch (Exception e) {
			log.error(" Can't find enclosing BaselineCategory for subTitle tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineCategory for subTitle tag ");
		}
	}

	public void setSubTitle(String subTitle) throws JspTagException {
		try {
			BaselineCategory theBaselineCategory = (BaselineCategory)findAncestorWithClass(this, BaselineCategory.class);
			theBaselineCategory.setSubTitle(subTitle);
		} catch (Exception e) {
			log.error("Can't find enclosing BaselineCategory for subTitle tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineCategory for subTitle tag ");
		}
	}

}
