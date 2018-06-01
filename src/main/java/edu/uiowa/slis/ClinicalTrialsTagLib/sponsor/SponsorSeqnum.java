package edu.uiowa.slis.ClinicalTrialsTagLib.sponsor;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class SponsorSeqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(SponsorSeqnum.class);


	public int doStartTag() throws JspException {
		try {
			Sponsor theSponsor = (Sponsor)findAncestorWithClass(this, Sponsor.class);
			if (!theSponsor.commitNeeded) {
				pageContext.getOut().print(theSponsor.getSeqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Sponsor for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing Sponsor for seqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getSeqnum() throws JspTagException {
		try {
			Sponsor theSponsor = (Sponsor)findAncestorWithClass(this, Sponsor.class);
			return theSponsor.getSeqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing Sponsor for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing Sponsor for seqnum tag ");
		}
	}

	public void setSeqnum(int seqnum) throws JspTagException {
		try {
			Sponsor theSponsor = (Sponsor)findAncestorWithClass(this, Sponsor.class);
			theSponsor.setSeqnum(seqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing Sponsor for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing Sponsor for seqnum tag ");
		}
	}

}
