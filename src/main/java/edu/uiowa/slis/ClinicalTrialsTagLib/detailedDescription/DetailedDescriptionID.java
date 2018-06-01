package edu.uiowa.slis.ClinicalTrialsTagLib.detailedDescription;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class DetailedDescriptionID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(DetailedDescriptionID.class);


	public int doStartTag() throws JspException {
		try {
			DetailedDescription theDetailedDescription = (DetailedDescription)findAncestorWithClass(this, DetailedDescription.class);
			if (!theDetailedDescription.commitNeeded) {
				pageContext.getOut().print(theDetailedDescription.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing DetailedDescription for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing DetailedDescription for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			DetailedDescription theDetailedDescription = (DetailedDescription)findAncestorWithClass(this, DetailedDescription.class);
			return theDetailedDescription.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing DetailedDescription for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing DetailedDescription for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			DetailedDescription theDetailedDescription = (DetailedDescription)findAncestorWithClass(this, DetailedDescription.class);
			theDetailedDescription.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing DetailedDescription for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing DetailedDescription for ID tag ");
		}
	}

}
