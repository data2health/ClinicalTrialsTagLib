package edu.uiowa.slis.ClinicalTrialsTagLib.eligibility;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class EligibilityMinimumAge extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(EligibilityMinimumAge.class);


	public int doStartTag() throws JspException {
		try {
			Eligibility theEligibility = (Eligibility)findAncestorWithClass(this, Eligibility.class);
			if (!theEligibility.commitNeeded) {
				pageContext.getOut().print(theEligibility.getMinimumAge());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Eligibility for minimumAge tag ", e);
			throw new JspTagException("Error: Can't find enclosing Eligibility for minimumAge tag ");
		}
		return SKIP_BODY;
	}

	public String getMinimumAge() throws JspTagException {
		try {
			Eligibility theEligibility = (Eligibility)findAncestorWithClass(this, Eligibility.class);
			return theEligibility.getMinimumAge();
		} catch (Exception e) {
			log.error(" Can't find enclosing Eligibility for minimumAge tag ", e);
			throw new JspTagException("Error: Can't find enclosing Eligibility for minimumAge tag ");
		}
	}

	public void setMinimumAge(String minimumAge) throws JspTagException {
		try {
			Eligibility theEligibility = (Eligibility)findAncestorWithClass(this, Eligibility.class);
			theEligibility.setMinimumAge(minimumAge);
		} catch (Exception e) {
			log.error("Can't find enclosing Eligibility for minimumAge tag ", e);
			throw new JspTagException("Error: Can't find enclosing Eligibility for minimumAge tag ");
		}
	}

}
