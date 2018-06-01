package edu.uiowa.slis.ClinicalTrialsTagLib.clinicalStudy;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.Date;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ClinicalStudyVerificationDateToNow extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ClinicalStudyVerificationDateToNow.class);


	public int doStartTag() throws JspException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			theClinicalStudy.setVerificationDateToNow( );
		} catch (Exception e) {
			log.error(" Can't find enclosing ClinicalStudy for verificationDate tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for verificationDate tag ");
		}
		return SKIP_BODY;
	}

	public Date getVerificationDate() throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			return theClinicalStudy.getVerificationDate();
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for verificationDate tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for verificationDate tag ");
		}
	}

	public void setVerificationDate( ) throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			theClinicalStudy.setVerificationDateToNow( );
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for verificationDate tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for verificationDate tag ");
		}
	}

}
