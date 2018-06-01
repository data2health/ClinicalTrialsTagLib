package edu.uiowa.slis.ClinicalTrialsTagLib.otherCategory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class OtherCategoryTitle extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(OtherCategoryTitle.class);


	public int doStartTag() throws JspException {
		try {
			OtherCategory theOtherCategory = (OtherCategory)findAncestorWithClass(this, OtherCategory.class);
			if (!theOtherCategory.commitNeeded) {
				pageContext.getOut().print(theOtherCategory.getTitle());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing OtherCategory for title tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherCategory for title tag ");
		}
		return SKIP_BODY;
	}

	public String getTitle() throws JspTagException {
		try {
			OtherCategory theOtherCategory = (OtherCategory)findAncestorWithClass(this, OtherCategory.class);
			return theOtherCategory.getTitle();
		} catch (Exception e) {
			log.error(" Can't find enclosing OtherCategory for title tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherCategory for title tag ");
		}
	}

	public void setTitle(String title) throws JspTagException {
		try {
			OtherCategory theOtherCategory = (OtherCategory)findAncestorWithClass(this, OtherCategory.class);
			theOtherCategory.setTitle(title);
		} catch (Exception e) {
			log.error("Can't find enclosing OtherCategory for title tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherCategory for title tag ");
		}
	}

}
