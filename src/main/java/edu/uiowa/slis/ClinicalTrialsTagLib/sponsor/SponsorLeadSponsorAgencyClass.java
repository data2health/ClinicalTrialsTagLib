package edu.uiowa.slis.ClinicalTrialsTagLib.sponsor;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class SponsorLeadSponsorAgencyClass extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(SponsorLeadSponsorAgencyClass.class);


	public int doStartTag() throws JspException {
		try {
			Sponsor theSponsor = (Sponsor)findAncestorWithClass(this, Sponsor.class);
			if (!theSponsor.commitNeeded) {
				pageContext.getOut().print(theSponsor.getLeadSponsorAgencyClass());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Sponsor for leadSponsorAgencyClass tag ", e);
			throw new JspTagException("Error: Can't find enclosing Sponsor for leadSponsorAgencyClass tag ");
		}
		return SKIP_BODY;
	}

	public String getLeadSponsorAgencyClass() throws JspTagException {
		try {
			Sponsor theSponsor = (Sponsor)findAncestorWithClass(this, Sponsor.class);
			return theSponsor.getLeadSponsorAgencyClass();
		} catch (Exception e) {
			log.error(" Can't find enclosing Sponsor for leadSponsorAgencyClass tag ", e);
			throw new JspTagException("Error: Can't find enclosing Sponsor for leadSponsorAgencyClass tag ");
		}
	}

	public void setLeadSponsorAgencyClass(String leadSponsorAgencyClass) throws JspTagException {
		try {
			Sponsor theSponsor = (Sponsor)findAncestorWithClass(this, Sponsor.class);
			theSponsor.setLeadSponsorAgencyClass(leadSponsorAgencyClass);
		} catch (Exception e) {
			log.error("Can't find enclosing Sponsor for leadSponsorAgencyClass tag ", e);
			throw new JspTagException("Error: Can't find enclosing Sponsor for leadSponsorAgencyClass tag ");
		}
	}

}
