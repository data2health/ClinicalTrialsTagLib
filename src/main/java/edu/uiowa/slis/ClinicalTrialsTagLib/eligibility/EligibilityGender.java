package edu.uiowa.slis.ClinicalTrialsTagLib.eligibility;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class EligibilityGender extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(EligibilityGender.class);


	public int doStartTag() throws JspException {
		try {
			Eligibility theEligibility = (Eligibility)findAncestorWithClass(this, Eligibility.class);
			if (!theEligibility.commitNeeded) {
				pageContext.getOut().print(theEligibility.getGender());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Eligibility for gender tag ", e);
			throw new JspTagException("Error: Can't find enclosing Eligibility for gender tag ");
		}
		return SKIP_BODY;
	}

	public String getGender() throws JspTagException {
		try {
			Eligibility theEligibility = (Eligibility)findAncestorWithClass(this, Eligibility.class);
			return theEligibility.getGender();
		} catch (Exception e) {
			log.error(" Can't find enclosing Eligibility for gender tag ", e);
			throw new JspTagException("Error: Can't find enclosing Eligibility for gender tag ");
		}
	}

	public void setGender(String gender) throws JspTagException {
		try {
			Eligibility theEligibility = (Eligibility)findAncestorWithClass(this, Eligibility.class);
			theEligibility.setGender(gender);
		} catch (Exception e) {
			log.error("Can't find enclosing Eligibility for gender tag ", e);
			throw new JspTagException("Error: Can't find enclosing Eligibility for gender tag ");
		}
	}

}
