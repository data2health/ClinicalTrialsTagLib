package edu.uiowa.slis.ClinicalTrialsTagLib.eligibility;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class EligibilityID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(EligibilityID.class);


	public int doStartTag() throws JspException {
		try {
			Eligibility theEligibility = (Eligibility)findAncestorWithClass(this, Eligibility.class);
			if (!theEligibility.commitNeeded) {
				pageContext.getOut().print(theEligibility.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Eligibility for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing Eligibility for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			Eligibility theEligibility = (Eligibility)findAncestorWithClass(this, Eligibility.class);
			return theEligibility.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing Eligibility for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing Eligibility for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			Eligibility theEligibility = (Eligibility)findAncestorWithClass(this, Eligibility.class);
			theEligibility.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing Eligibility for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing Eligibility for ID tag ");
		}
	}

}
