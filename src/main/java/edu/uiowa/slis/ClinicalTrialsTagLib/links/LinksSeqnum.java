package edu.uiowa.slis.ClinicalTrialsTagLib.links;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class LinksSeqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(LinksSeqnum.class);


	public int doStartTag() throws JspException {
		try {
			Links theLinks = (Links)findAncestorWithClass(this, Links.class);
			if (!theLinks.commitNeeded) {
				pageContext.getOut().print(theLinks.getSeqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Links for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing Links for seqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getSeqnum() throws JspTagException {
		try {
			Links theLinks = (Links)findAncestorWithClass(this, Links.class);
			return theLinks.getSeqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing Links for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing Links for seqnum tag ");
		}
	}

	public void setSeqnum(int seqnum) throws JspTagException {
		try {
			Links theLinks = (Links)findAncestorWithClass(this, Links.class);
			theLinks.setSeqnum(seqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing Links for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing Links for seqnum tag ");
		}
	}

}
