package edu.uiowa.slis.ClinicalTrialsTagLib.clinicalResults;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ClinicalResultsContactNameOrTitle extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ClinicalResultsContactNameOrTitle.class);


	public int doStartTag() throws JspException {
		try {
			ClinicalResults theClinicalResults = (ClinicalResults)findAncestorWithClass(this, ClinicalResults.class);
			if (!theClinicalResults.commitNeeded) {
				pageContext.getOut().print(theClinicalResults.getContactNameOrTitle());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalResults for contactNameOrTitle tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalResults for contactNameOrTitle tag ");
		}
		return SKIP_BODY;
	}

	public String getContactNameOrTitle() throws JspTagException {
		try {
			ClinicalResults theClinicalResults = (ClinicalResults)findAncestorWithClass(this, ClinicalResults.class);
			return theClinicalResults.getContactNameOrTitle();
		} catch (Exception e) {
			log.error(" Can't find enclosing ClinicalResults for contactNameOrTitle tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalResults for contactNameOrTitle tag ");
		}
	}

	public void setContactNameOrTitle(String contactNameOrTitle) throws JspTagException {
		try {
			ClinicalResults theClinicalResults = (ClinicalResults)findAncestorWithClass(this, ClinicalResults.class);
			theClinicalResults.setContactNameOrTitle(contactNameOrTitle);
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalResults for contactNameOrTitle tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalResults for contactNameOrTitle tag ");
		}
	}

}
