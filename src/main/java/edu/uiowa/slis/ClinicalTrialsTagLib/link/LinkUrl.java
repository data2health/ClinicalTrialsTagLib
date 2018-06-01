package edu.uiowa.slis.ClinicalTrialsTagLib.link;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class LinkUrl extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(LinkUrl.class);


	public int doStartTag() throws JspException {
		try {
			Link theLink = (Link)findAncestorWithClass(this, Link.class);
			if (!theLink.commitNeeded) {
				pageContext.getOut().print(theLink.getUrl());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Link for url tag ", e);
			throw new JspTagException("Error: Can't find enclosing Link for url tag ");
		}
		return SKIP_BODY;
	}

	public String getUrl() throws JspTagException {
		try {
			Link theLink = (Link)findAncestorWithClass(this, Link.class);
			return theLink.getUrl();
		} catch (Exception e) {
			log.error(" Can't find enclosing Link for url tag ", e);
			throw new JspTagException("Error: Can't find enclosing Link for url tag ");
		}
	}

	public void setUrl(String url) throws JspTagException {
		try {
			Link theLink = (Link)findAncestorWithClass(this, Link.class);
			theLink.setUrl(url);
		} catch (Exception e) {
			log.error("Can't find enclosing Link for url tag ", e);
			throw new JspTagException("Error: Can't find enclosing Link for url tag ");
		}
	}

}
