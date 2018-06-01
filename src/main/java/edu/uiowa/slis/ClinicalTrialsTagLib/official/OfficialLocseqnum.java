package edu.uiowa.slis.ClinicalTrialsTagLib.official;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class OfficialLocseqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(OfficialLocseqnum.class);


	public int doStartTag() throws JspException {
		try {
			Official theOfficial = (Official)findAncestorWithClass(this, Official.class);
			if (!theOfficial.commitNeeded) {
				pageContext.getOut().print(theOfficial.getLocseqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Official for locseqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing Official for locseqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getLocseqnum() throws JspTagException {
		try {
			Official theOfficial = (Official)findAncestorWithClass(this, Official.class);
			return theOfficial.getLocseqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing Official for locseqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing Official for locseqnum tag ");
		}
	}

	public void setLocseqnum(int locseqnum) throws JspTagException {
		try {
			Official theOfficial = (Official)findAncestorWithClass(this, Official.class);
			theOfficial.setLocseqnum(locseqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing Official for locseqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing Official for locseqnum tag ");
		}
	}

}
