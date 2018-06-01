package edu.uiowa.slis.ClinicalTrialsTagLib.clinicalStudy;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.Date;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ClinicalStudyEndDateToNow extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ClinicalStudyEndDateToNow.class);


	public int doStartTag() throws JspException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			theClinicalStudy.setEndDateToNow( );
		} catch (Exception e) {
			log.error(" Can't find enclosing ClinicalStudy for endDate tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for endDate tag ");
		}
		return SKIP_BODY;
	}

	public Date getEndDate() throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			return theClinicalStudy.getEndDate();
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for endDate tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for endDate tag ");
		}
	}

	public void setEndDate( ) throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			theClinicalStudy.setEndDateToNow( );
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for endDate tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for endDate tag ");
		}
	}

}
