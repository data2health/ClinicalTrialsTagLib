package edu.uiowa.slis.ClinicalTrialsTagLib.link;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class LinkID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(LinkID.class);


	public int doStartTag() throws JspException {
		try {
			Link theLink = (Link)findAncestorWithClass(this, Link.class);
			if (!theLink.commitNeeded) {
				pageContext.getOut().print(theLink.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Link for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing Link for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			Link theLink = (Link)findAncestorWithClass(this, Link.class);
			return theLink.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing Link for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing Link for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			Link theLink = (Link)findAncestorWithClass(this, Link.class);
			theLink.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing Link for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing Link for ID tag ");
		}
	}

}
