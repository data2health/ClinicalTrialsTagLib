package edu.uiowa.slis.ClinicalTrialsTagLib.clinicalStudy;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ClinicalStudyOverallStatus extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ClinicalStudyOverallStatus.class);


	public int doStartTag() throws JspException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			if (!theClinicalStudy.commitNeeded) {
				pageContext.getOut().print(theClinicalStudy.getOverallStatus());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for overallStatus tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for overallStatus tag ");
		}
		return SKIP_BODY;
	}

	public String getOverallStatus() throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			return theClinicalStudy.getOverallStatus();
		} catch (Exception e) {
			log.error(" Can't find enclosing ClinicalStudy for overallStatus tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for overallStatus tag ");
		}
	}

	public void setOverallStatus(String overallStatus) throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			theClinicalStudy.setOverallStatus(overallStatus);
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for overallStatus tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for overallStatus tag ");
		}
	}

}
