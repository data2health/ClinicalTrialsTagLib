package edu.uiowa.slis.ClinicalTrialsTagLib.clinicalStudy;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ClinicalStudyEnrollment extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ClinicalStudyEnrollment.class);


	public int doStartTag() throws JspException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			if (!theClinicalStudy.commitNeeded) {
				pageContext.getOut().print(theClinicalStudy.getEnrollment());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for enrollment tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for enrollment tag ");
		}
		return SKIP_BODY;
	}

	public String getEnrollment() throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			return theClinicalStudy.getEnrollment();
		} catch (Exception e) {
			log.error(" Can't find enclosing ClinicalStudy for enrollment tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for enrollment tag ");
		}
	}

	public void setEnrollment(String enrollment) throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			theClinicalStudy.setEnrollment(enrollment);
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for enrollment tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for enrollment tag ");
		}
	}

}
