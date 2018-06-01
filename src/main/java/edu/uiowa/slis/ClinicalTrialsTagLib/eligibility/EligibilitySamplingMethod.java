package edu.uiowa.slis.ClinicalTrialsTagLib.eligibility;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class EligibilitySamplingMethod extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(EligibilitySamplingMethod.class);


	public int doStartTag() throws JspException {
		try {
			Eligibility theEligibility = (Eligibility)findAncestorWithClass(this, Eligibility.class);
			if (!theEligibility.commitNeeded) {
				pageContext.getOut().print(theEligibility.getSamplingMethod());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Eligibility for samplingMethod tag ", e);
			throw new JspTagException("Error: Can't find enclosing Eligibility for samplingMethod tag ");
		}
		return SKIP_BODY;
	}

	public String getSamplingMethod() throws JspTagException {
		try {
			Eligibility theEligibility = (Eligibility)findAncestorWithClass(this, Eligibility.class);
			return theEligibility.getSamplingMethod();
		} catch (Exception e) {
			log.error(" Can't find enclosing Eligibility for samplingMethod tag ", e);
			throw new JspTagException("Error: Can't find enclosing Eligibility for samplingMethod tag ");
		}
	}

	public void setSamplingMethod(String samplingMethod) throws JspTagException {
		try {
			Eligibility theEligibility = (Eligibility)findAncestorWithClass(this, Eligibility.class);
			theEligibility.setSamplingMethod(samplingMethod);
		} catch (Exception e) {
			log.error("Can't find enclosing Eligibility for samplingMethod tag ", e);
			throw new JspTagException("Error: Can't find enclosing Eligibility for samplingMethod tag ");
		}
	}

}
