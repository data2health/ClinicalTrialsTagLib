package edu.uiowa.slis.ClinicalTrialsTagLib.overallOfficial;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class OverallOfficialSeqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(OverallOfficialSeqnum.class);


	public int doStartTag() throws JspException {
		try {
			OverallOfficial theOverallOfficial = (OverallOfficial)findAncestorWithClass(this, OverallOfficial.class);
			if (!theOverallOfficial.commitNeeded) {
				pageContext.getOut().print(theOverallOfficial.getSeqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing OverallOfficial for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing OverallOfficial for seqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getSeqnum() throws JspTagException {
		try {
			OverallOfficial theOverallOfficial = (OverallOfficial)findAncestorWithClass(this, OverallOfficial.class);
			return theOverallOfficial.getSeqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing OverallOfficial for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing OverallOfficial for seqnum tag ");
		}
	}

	public void setSeqnum(int seqnum) throws JspTagException {
		try {
			OverallOfficial theOverallOfficial = (OverallOfficial)findAncestorWithClass(this, OverallOfficial.class);
			theOverallOfficial.setSeqnum(seqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing OverallOfficial for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing OverallOfficial for seqnum tag ");
		}
	}

}
