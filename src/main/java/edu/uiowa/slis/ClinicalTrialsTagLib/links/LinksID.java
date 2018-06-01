package edu.uiowa.slis.ClinicalTrialsTagLib.links;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class LinksID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(LinksID.class);


	public int doStartTag() throws JspException {
		try {
			Links theLinks = (Links)findAncestorWithClass(this, Links.class);
			if (!theLinks.commitNeeded) {
				pageContext.getOut().print(theLinks.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Links for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing Links for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			Links theLinks = (Links)findAncestorWithClass(this, Links.class);
			return theLinks.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing Links for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing Links for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			Links theLinks = (Links)findAncestorWithClass(this, Links.class);
			theLinks.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing Links for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing Links for ID tag ");
		}
	}

}
