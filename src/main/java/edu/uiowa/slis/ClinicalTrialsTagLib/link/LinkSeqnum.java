package edu.uiowa.slis.ClinicalTrialsTagLib.link;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class LinkSeqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(LinkSeqnum.class);


	public int doStartTag() throws JspException {
		try {
			Link theLink = (Link)findAncestorWithClass(this, Link.class);
			if (!theLink.commitNeeded) {
				pageContext.getOut().print(theLink.getSeqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Link for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing Link for seqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getSeqnum() throws JspTagException {
		try {
			Link theLink = (Link)findAncestorWithClass(this, Link.class);
			return theLink.getSeqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing Link for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing Link for seqnum tag ");
		}
	}

	public void setSeqnum(int seqnum) throws JspTagException {
		try {
			Link theLink = (Link)findAncestorWithClass(this, Link.class);
			theLink.setSeqnum(seqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing Link for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing Link for seqnum tag ");
		}
	}

}
