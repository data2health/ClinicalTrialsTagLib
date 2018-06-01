package edu.uiowa.slis.ClinicalTrialsTagLib.seriousCategory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class SeriousCategoryTitle extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(SeriousCategoryTitle.class);


	public int doStartTag() throws JspException {
		try {
			SeriousCategory theSeriousCategory = (SeriousCategory)findAncestorWithClass(this, SeriousCategory.class);
			if (!theSeriousCategory.commitNeeded) {
				pageContext.getOut().print(theSeriousCategory.getTitle());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing SeriousCategory for title tag ", e);
			throw new JspTagException("Error: Can't find enclosing SeriousCategory for title tag ");
		}
		return SKIP_BODY;
	}

	public String getTitle() throws JspTagException {
		try {
			SeriousCategory theSeriousCategory = (SeriousCategory)findAncestorWithClass(this, SeriousCategory.class);
			return theSeriousCategory.getTitle();
		} catch (Exception e) {
			log.error(" Can't find enclosing SeriousCategory for title tag ", e);
			throw new JspTagException("Error: Can't find enclosing SeriousCategory for title tag ");
		}
	}

	public void setTitle(String title) throws JspTagException {
		try {
			SeriousCategory theSeriousCategory = (SeriousCategory)findAncestorWithClass(this, SeriousCategory.class);
			theSeriousCategory.setTitle(title);
		} catch (Exception e) {
			log.error("Can't find enclosing SeriousCategory for title tag ", e);
			throw new JspTagException("Error: Can't find enclosing SeriousCategory for title tag ");
		}
	}

}
