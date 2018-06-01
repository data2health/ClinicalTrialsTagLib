package edu.uiowa.slis.ClinicalTrialsTagLib.clinicalResults;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ClinicalResultsRestrictiveAgreement extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ClinicalResultsRestrictiveAgreement.class);


	public int doStartTag() throws JspException {
		try {
			ClinicalResults theClinicalResults = (ClinicalResults)findAncestorWithClass(this, ClinicalResults.class);
			if (!theClinicalResults.commitNeeded) {
				pageContext.getOut().print(theClinicalResults.getRestrictiveAgreement());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalResults for restrictiveAgreement tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalResults for restrictiveAgreement tag ");
		}
		return SKIP_BODY;
	}

	public String getRestrictiveAgreement() throws JspTagException {
		try {
			ClinicalResults theClinicalResults = (ClinicalResults)findAncestorWithClass(this, ClinicalResults.class);
			return theClinicalResults.getRestrictiveAgreement();
		} catch (Exception e) {
			log.error(" Can't find enclosing ClinicalResults for restrictiveAgreement tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalResults for restrictiveAgreement tag ");
		}
	}

	public void setRestrictiveAgreement(String restrictiveAgreement) throws JspTagException {
		try {
			ClinicalResults theClinicalResults = (ClinicalResults)findAncestorWithClass(this, ClinicalResults.class);
			theClinicalResults.setRestrictiveAgreement(restrictiveAgreement);
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalResults for restrictiveAgreement tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalResults for restrictiveAgreement tag ");
		}
	}

}
