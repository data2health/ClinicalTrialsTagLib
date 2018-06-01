package edu.uiowa.slis.ClinicalTrialsTagLib.official;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class OfficialSeqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(OfficialSeqnum.class);


	public int doStartTag() throws JspException {
		try {
			Official theOfficial = (Official)findAncestorWithClass(this, Official.class);
			if (!theOfficial.commitNeeded) {
				pageContext.getOut().print(theOfficial.getSeqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Official for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing Official for seqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getSeqnum() throws JspTagException {
		try {
			Official theOfficial = (Official)findAncestorWithClass(this, Official.class);
			return theOfficial.getSeqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing Official for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing Official for seqnum tag ");
		}
	}

	public void setSeqnum(int seqnum) throws JspTagException {
		try {
			Official theOfficial = (Official)findAncestorWithClass(this, Official.class);
			theOfficial.setSeqnum(seqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing Official for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing Official for seqnum tag ");
		}
	}

}
