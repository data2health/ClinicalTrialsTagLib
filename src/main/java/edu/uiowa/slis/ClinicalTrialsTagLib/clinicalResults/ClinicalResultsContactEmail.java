package edu.uiowa.slis.ClinicalTrialsTagLib.clinicalResults;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ClinicalResultsContactEmail extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ClinicalResultsContactEmail.class);


	public int doStartTag() throws JspException {
		try {
			ClinicalResults theClinicalResults = (ClinicalResults)findAncestorWithClass(this, ClinicalResults.class);
			if (!theClinicalResults.commitNeeded) {
				pageContext.getOut().print(theClinicalResults.getContactEmail());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalResults for contactEmail tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalResults for contactEmail tag ");
		}
		return SKIP_BODY;
	}

	public String getContactEmail() throws JspTagException {
		try {
			ClinicalResults theClinicalResults = (ClinicalResults)findAncestorWithClass(this, ClinicalResults.class);
			return theClinicalResults.getContactEmail();
		} catch (Exception e) {
			log.error(" Can't find enclosing ClinicalResults for contactEmail tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalResults for contactEmail tag ");
		}
	}

	public void setContactEmail(String contactEmail) throws JspTagException {
		try {
			ClinicalResults theClinicalResults = (ClinicalResults)findAncestorWithClass(this, ClinicalResults.class);
			theClinicalResults.setContactEmail(contactEmail);
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalResults for contactEmail tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalResults for contactEmail tag ");
		}
	}

}
