package edu.uiowa.slis.ClinicalTrialsTagLib.otherCategory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class OtherCategoryID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(OtherCategoryID.class);


	public int doStartTag() throws JspException {
		try {
			OtherCategory theOtherCategory = (OtherCategory)findAncestorWithClass(this, OtherCategory.class);
			if (!theOtherCategory.commitNeeded) {
				pageContext.getOut().print(theOtherCategory.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing OtherCategory for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherCategory for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			OtherCategory theOtherCategory = (OtherCategory)findAncestorWithClass(this, OtherCategory.class);
			return theOtherCategory.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing OtherCategory for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherCategory for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			OtherCategory theOtherCategory = (OtherCategory)findAncestorWithClass(this, OtherCategory.class);
			theOtherCategory.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing OtherCategory for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherCategory for ID tag ");
		}
	}

}
