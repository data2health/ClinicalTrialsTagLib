package edu.uiowa.slis.ClinicalTrialsTagLib.links;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class LinksDescription extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(LinksDescription.class);


	public int doStartTag() throws JspException {
		try {
			Links theLinks = (Links)findAncestorWithClass(this, Links.class);
			if (!theLinks.commitNeeded) {
				pageContext.getOut().print(theLinks.getDescription());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Links for description tag ", e);
			throw new JspTagException("Error: Can't find enclosing Links for description tag ");
		}
		return SKIP_BODY;
	}

	public String getDescription() throws JspTagException {
		try {
			Links theLinks = (Links)findAncestorWithClass(this, Links.class);
			return theLinks.getDescription();
		} catch (Exception e) {
			log.error(" Can't find enclosing Links for description tag ", e);
			throw new JspTagException("Error: Can't find enclosing Links for description tag ");
		}
	}

	public void setDescription(String description) throws JspTagException {
		try {
			Links theLinks = (Links)findAncestorWithClass(this, Links.class);
			theLinks.setDescription(description);
		} catch (Exception e) {
			log.error("Can't find enclosing Links for description tag ", e);
			throw new JspTagException("Error: Can't find enclosing Links for description tag ");
		}
	}

}
