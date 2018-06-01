package edu.uiowa.slis.ClinicalTrialsTagLib.sponsor;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class SponsorLeadSponsorAgency extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(SponsorLeadSponsorAgency.class);


	public int doStartTag() throws JspException {
		try {
			Sponsor theSponsor = (Sponsor)findAncestorWithClass(this, Sponsor.class);
			if (!theSponsor.commitNeeded) {
				pageContext.getOut().print(theSponsor.getLeadSponsorAgency());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Sponsor for leadSponsorAgency tag ", e);
			throw new JspTagException("Error: Can't find enclosing Sponsor for leadSponsorAgency tag ");
		}
		return SKIP_BODY;
	}

	public String getLeadSponsorAgency() throws JspTagException {
		try {
			Sponsor theSponsor = (Sponsor)findAncestorWithClass(this, Sponsor.class);
			return theSponsor.getLeadSponsorAgency();
		} catch (Exception e) {
			log.error(" Can't find enclosing Sponsor for leadSponsorAgency tag ", e);
			throw new JspTagException("Error: Can't find enclosing Sponsor for leadSponsorAgency tag ");
		}
	}

	public void setLeadSponsorAgency(String leadSponsorAgency) throws JspTagException {
		try {
			Sponsor theSponsor = (Sponsor)findAncestorWithClass(this, Sponsor.class);
			theSponsor.setLeadSponsorAgency(leadSponsorAgency);
		} catch (Exception e) {
			log.error("Can't find enclosing Sponsor for leadSponsorAgency tag ", e);
			throw new JspTagException("Error: Can't find enclosing Sponsor for leadSponsorAgency tag ");
		}
	}

}
