package edu.uiowa.slis.ClinicalTrialsTagLib.links;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class LinksUrl extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(LinksUrl.class);


	public int doStartTag() throws JspException {
		try {
			Links theLinks = (Links)findAncestorWithClass(this, Links.class);
			if (!theLinks.commitNeeded) {
				pageContext.getOut().print(theLinks.getUrl());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Links for url tag ", e);
			throw new JspTagException("Error: Can't find enclosing Links for url tag ");
		}
		return SKIP_BODY;
	}

	public String getUrl() throws JspTagException {
		try {
			Links theLinks = (Links)findAncestorWithClass(this, Links.class);
			return theLinks.getUrl();
		} catch (Exception e) {
			log.error(" Can't find enclosing Links for url tag ", e);
			throw new JspTagException("Error: Can't find enclosing Links for url tag ");
		}
	}

	public void setUrl(String url) throws JspTagException {
		try {
			Links theLinks = (Links)findAncestorWithClass(this, Links.class);
			theLinks.setUrl(url);
		} catch (Exception e) {
			log.error("Can't find enclosing Links for url tag ", e);
			throw new JspTagException("Error: Can't find enclosing Links for url tag ");
		}
	}

}
