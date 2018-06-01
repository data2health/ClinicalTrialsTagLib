package edu.uiowa.slis.ClinicalTrialsTagLib.clinicalResults;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ClinicalResultsContactOrganization extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ClinicalResultsContactOrganization.class);


	public int doStartTag() throws JspException {
		try {
			ClinicalResults theClinicalResults = (ClinicalResults)findAncestorWithClass(this, ClinicalResults.class);
			if (!theClinicalResults.commitNeeded) {
				pageContext.getOut().print(theClinicalResults.getContactOrganization());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalResults for contactOrganization tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalResults for contactOrganization tag ");
		}
		return SKIP_BODY;
	}

	public String getContactOrganization() throws JspTagException {
		try {
			ClinicalResults theClinicalResults = (ClinicalResults)findAncestorWithClass(this, ClinicalResults.class);
			return theClinicalResults.getContactOrganization();
		} catch (Exception e) {
			log.error(" Can't find enclosing ClinicalResults for contactOrganization tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalResults for contactOrganization tag ");
		}
	}

	public void setContactOrganization(String contactOrganization) throws JspTagException {
		try {
			ClinicalResults theClinicalResults = (ClinicalResults)findAncestorWithClass(this, ClinicalResults.class);
			theClinicalResults.setContactOrganization(contactOrganization);
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalResults for contactOrganization tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalResults for contactOrganization tag ");
		}
	}

}
