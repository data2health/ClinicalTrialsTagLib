package edu.uiowa.slis.ClinicalTrialsTagLib.clinicalResults;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ClinicalResultsContactPhone extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ClinicalResultsContactPhone.class);


	public int doStartTag() throws JspException {
		try {
			ClinicalResults theClinicalResults = (ClinicalResults)findAncestorWithClass(this, ClinicalResults.class);
			if (!theClinicalResults.commitNeeded) {
				pageContext.getOut().print(theClinicalResults.getContactPhone());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalResults for contactPhone tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalResults for contactPhone tag ");
		}
		return SKIP_BODY;
	}

	public String getContactPhone() throws JspTagException {
		try {
			ClinicalResults theClinicalResults = (ClinicalResults)findAncestorWithClass(this, ClinicalResults.class);
			return theClinicalResults.getContactPhone();
		} catch (Exception e) {
			log.error(" Can't find enclosing ClinicalResults for contactPhone tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalResults for contactPhone tag ");
		}
	}

	public void setContactPhone(String contactPhone) throws JspTagException {
		try {
			ClinicalResults theClinicalResults = (ClinicalResults)findAncestorWithClass(this, ClinicalResults.class);
			theClinicalResults.setContactPhone(contactPhone);
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalResults for contactPhone tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalResults for contactPhone tag ");
		}
	}

}
