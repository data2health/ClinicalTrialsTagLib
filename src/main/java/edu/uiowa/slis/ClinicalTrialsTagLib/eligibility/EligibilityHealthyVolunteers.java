package edu.uiowa.slis.ClinicalTrialsTagLib.eligibility;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class EligibilityHealthyVolunteers extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(EligibilityHealthyVolunteers.class);


	public int doStartTag() throws JspException {
		try {
			Eligibility theEligibility = (Eligibility)findAncestorWithClass(this, Eligibility.class);
			if (!theEligibility.commitNeeded) {
				pageContext.getOut().print(theEligibility.getHealthyVolunteers());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Eligibility for healthyVolunteers tag ", e);
			throw new JspTagException("Error: Can't find enclosing Eligibility for healthyVolunteers tag ");
		}
		return SKIP_BODY;
	}

	public String getHealthyVolunteers() throws JspTagException {
		try {
			Eligibility theEligibility = (Eligibility)findAncestorWithClass(this, Eligibility.class);
			return theEligibility.getHealthyVolunteers();
		} catch (Exception e) {
			log.error(" Can't find enclosing Eligibility for healthyVolunteers tag ", e);
			throw new JspTagException("Error: Can't find enclosing Eligibility for healthyVolunteers tag ");
		}
	}

	public void setHealthyVolunteers(String healthyVolunteers) throws JspTagException {
		try {
			Eligibility theEligibility = (Eligibility)findAncestorWithClass(this, Eligibility.class);
			theEligibility.setHealthyVolunteers(healthyVolunteers);
		} catch (Exception e) {
			log.error("Can't find enclosing Eligibility for healthyVolunteers tag ", e);
			throw new JspTagException("Error: Can't find enclosing Eligibility for healthyVolunteers tag ");
		}
	}

}
