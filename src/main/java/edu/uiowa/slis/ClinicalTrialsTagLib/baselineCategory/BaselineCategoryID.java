package edu.uiowa.slis.ClinicalTrialsTagLib.baselineCategory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class BaselineCategoryID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(BaselineCategoryID.class);


	public int doStartTag() throws JspException {
		try {
			BaselineCategory theBaselineCategory = (BaselineCategory)findAncestorWithClass(this, BaselineCategory.class);
			if (!theBaselineCategory.commitNeeded) {
				pageContext.getOut().print(theBaselineCategory.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing BaselineCategory for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineCategory for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			BaselineCategory theBaselineCategory = (BaselineCategory)findAncestorWithClass(this, BaselineCategory.class);
			return theBaselineCategory.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing BaselineCategory for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineCategory for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			BaselineCategory theBaselineCategory = (BaselineCategory)findAncestorWithClass(this, BaselineCategory.class);
			theBaselineCategory.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing BaselineCategory for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineCategory for ID tag ");
		}
	}

}
