package edu.uiowa.slis.ClinicalTrialsTagLib.baselineGroup;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class BaselineGroupTitle extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(BaselineGroupTitle.class);


	public int doStartTag() throws JspException {
		try {
			BaselineGroup theBaselineGroup = (BaselineGroup)findAncestorWithClass(this, BaselineGroup.class);
			if (!theBaselineGroup.commitNeeded) {
				pageContext.getOut().print(theBaselineGroup.getTitle());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing BaselineGroup for title tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineGroup for title tag ");
		}
		return SKIP_BODY;
	}

	public String getTitle() throws JspTagException {
		try {
			BaselineGroup theBaselineGroup = (BaselineGroup)findAncestorWithClass(this, BaselineGroup.class);
			return theBaselineGroup.getTitle();
		} catch (Exception e) {
			log.error(" Can't find enclosing BaselineGroup for title tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineGroup for title tag ");
		}
	}

	public void setTitle(String title) throws JspTagException {
		try {
			BaselineGroup theBaselineGroup = (BaselineGroup)findAncestorWithClass(this, BaselineGroup.class);
			theBaselineGroup.setTitle(title);
		} catch (Exception e) {
			log.error("Can't find enclosing BaselineGroup for title tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineGroup for title tag ");
		}
	}

}
