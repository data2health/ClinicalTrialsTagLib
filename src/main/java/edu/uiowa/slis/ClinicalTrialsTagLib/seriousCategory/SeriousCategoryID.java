package edu.uiowa.slis.ClinicalTrialsTagLib.seriousCategory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class SeriousCategoryID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(SeriousCategoryID.class);


	public int doStartTag() throws JspException {
		try {
			SeriousCategory theSeriousCategory = (SeriousCategory)findAncestorWithClass(this, SeriousCategory.class);
			if (!theSeriousCategory.commitNeeded) {
				pageContext.getOut().print(theSeriousCategory.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing SeriousCategory for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing SeriousCategory for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			SeriousCategory theSeriousCategory = (SeriousCategory)findAncestorWithClass(this, SeriousCategory.class);
			return theSeriousCategory.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing SeriousCategory for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing SeriousCategory for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			SeriousCategory theSeriousCategory = (SeriousCategory)findAncestorWithClass(this, SeriousCategory.class);
			theSeriousCategory.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing SeriousCategory for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing SeriousCategory for ID tag ");
		}
	}

}
