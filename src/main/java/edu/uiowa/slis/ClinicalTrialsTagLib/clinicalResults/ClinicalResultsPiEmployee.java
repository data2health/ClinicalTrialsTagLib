package edu.uiowa.slis.ClinicalTrialsTagLib.clinicalResults;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ClinicalResultsPiEmployee extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ClinicalResultsPiEmployee.class);


	public int doStartTag() throws JspException {
		try {
			ClinicalResults theClinicalResults = (ClinicalResults)findAncestorWithClass(this, ClinicalResults.class);
			if (!theClinicalResults.commitNeeded) {
				pageContext.getOut().print(theClinicalResults.getPiEmployee());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalResults for piEmployee tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalResults for piEmployee tag ");
		}
		return SKIP_BODY;
	}

	public String getPiEmployee() throws JspTagException {
		try {
			ClinicalResults theClinicalResults = (ClinicalResults)findAncestorWithClass(this, ClinicalResults.class);
			return theClinicalResults.getPiEmployee();
		} catch (Exception e) {
			log.error(" Can't find enclosing ClinicalResults for piEmployee tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalResults for piEmployee tag ");
		}
	}

	public void setPiEmployee(String piEmployee) throws JspTagException {
		try {
			ClinicalResults theClinicalResults = (ClinicalResults)findAncestorWithClass(this, ClinicalResults.class);
			theClinicalResults.setPiEmployee(piEmployee);
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalResults for piEmployee tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalResults for piEmployee tag ");
		}
	}

}
