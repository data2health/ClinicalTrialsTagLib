package edu.uiowa.slis.ClinicalTrialsTagLib.link;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class LinkDescription extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(LinkDescription.class);


	public int doStartTag() throws JspException {
		try {
			Link theLink = (Link)findAncestorWithClass(this, Link.class);
			if (!theLink.commitNeeded) {
				pageContext.getOut().print(theLink.getDescription());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Link for description tag ", e);
			throw new JspTagException("Error: Can't find enclosing Link for description tag ");
		}
		return SKIP_BODY;
	}

	public String getDescription() throws JspTagException {
		try {
			Link theLink = (Link)findAncestorWithClass(this, Link.class);
			return theLink.getDescription();
		} catch (Exception e) {
			log.error(" Can't find enclosing Link for description tag ", e);
			throw new JspTagException("Error: Can't find enclosing Link for description tag ");
		}
	}

	public void setDescription(String description) throws JspTagException {
		try {
			Link theLink = (Link)findAncestorWithClass(this, Link.class);
			theLink.setDescription(description);
		} catch (Exception e) {
			log.error("Can't find enclosing Link for description tag ", e);
			throw new JspTagException("Error: Can't find enclosing Link for description tag ");
		}
	}

}
