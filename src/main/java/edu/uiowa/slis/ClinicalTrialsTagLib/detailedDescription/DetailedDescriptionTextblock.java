package edu.uiowa.slis.ClinicalTrialsTagLib.detailedDescription;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class DetailedDescriptionTextblock extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(DetailedDescriptionTextblock.class);


	public int doStartTag() throws JspException {
		try {
			DetailedDescription theDetailedDescription = (DetailedDescription)findAncestorWithClass(this, DetailedDescription.class);
			if (!theDetailedDescription.commitNeeded) {
				pageContext.getOut().print(theDetailedDescription.getTextblock());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing DetailedDescription for textblock tag ", e);
			throw new JspTagException("Error: Can't find enclosing DetailedDescription for textblock tag ");
		}
		return SKIP_BODY;
	}

	public String getTextblock() throws JspTagException {
		try {
			DetailedDescription theDetailedDescription = (DetailedDescription)findAncestorWithClass(this, DetailedDescription.class);
			return theDetailedDescription.getTextblock();
		} catch (Exception e) {
			log.error(" Can't find enclosing DetailedDescription for textblock tag ", e);
			throw new JspTagException("Error: Can't find enclosing DetailedDescription for textblock tag ");
		}
	}

	public void setTextblock(String textblock) throws JspTagException {
		try {
			DetailedDescription theDetailedDescription = (DetailedDescription)findAncestorWithClass(this, DetailedDescription.class);
			theDetailedDescription.setTextblock(textblock);
		} catch (Exception e) {
			log.error("Can't find enclosing DetailedDescription for textblock tag ", e);
			throw new JspTagException("Error: Can't find enclosing DetailedDescription for textblock tag ");
		}
	}

}
