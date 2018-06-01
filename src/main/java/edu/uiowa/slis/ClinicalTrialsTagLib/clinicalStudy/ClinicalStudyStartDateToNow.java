package edu.uiowa.slis.ClinicalTrialsTagLib.clinicalStudy;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.Date;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ClinicalStudyStartDateToNow extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ClinicalStudyStartDateToNow.class);


	public int doStartTag() throws JspException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			theClinicalStudy.setStartDateToNow( );
		} catch (Exception e) {
			log.error(" Can't find enclosing ClinicalStudy for startDate tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for startDate tag ");
		}
		return SKIP_BODY;
	}

	public Date getStartDate() throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			return theClinicalStudy.getStartDate();
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for startDate tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for startDate tag ");
		}
	}

	public void setStartDate( ) throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			theClinicalStudy.setStartDateToNow( );
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for startDate tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for startDate tag ");
		}
	}

}
