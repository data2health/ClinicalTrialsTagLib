package edu.uiowa.slis.ClinicalTrialsTagLib.eligibility;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class EligibilityMaximumAge extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(EligibilityMaximumAge.class);


	public int doStartTag() throws JspException {
		try {
			Eligibility theEligibility = (Eligibility)findAncestorWithClass(this, Eligibility.class);
			if (!theEligibility.commitNeeded) {
				pageContext.getOut().print(theEligibility.getMaximumAge());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Eligibility for maximumAge tag ", e);
			throw new JspTagException("Error: Can't find enclosing Eligibility for maximumAge tag ");
		}
		return SKIP_BODY;
	}

	public String getMaximumAge() throws JspTagException {
		try {
			Eligibility theEligibility = (Eligibility)findAncestorWithClass(this, Eligibility.class);
			return theEligibility.getMaximumAge();
		} catch (Exception e) {
			log.error(" Can't find enclosing Eligibility for maximumAge tag ", e);
			throw new JspTagException("Error: Can't find enclosing Eligibility for maximumAge tag ");
		}
	}

	public void setMaximumAge(String maximumAge) throws JspTagException {
		try {
			Eligibility theEligibility = (Eligibility)findAncestorWithClass(this, Eligibility.class);
			theEligibility.setMaximumAge(maximumAge);
		} catch (Exception e) {
			log.error("Can't find enclosing Eligibility for maximumAge tag ", e);
			throw new JspTagException("Error: Can't find enclosing Eligibility for maximumAge tag ");
		}
	}

}
