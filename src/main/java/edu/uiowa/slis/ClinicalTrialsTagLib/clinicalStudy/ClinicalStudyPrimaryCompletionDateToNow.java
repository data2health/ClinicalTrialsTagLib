package edu.uiowa.slis.ClinicalTrialsTagLib.clinicalStudy;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.Date;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ClinicalStudyPrimaryCompletionDateToNow extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ClinicalStudyPrimaryCompletionDateToNow.class);


	public int doStartTag() throws JspException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			theClinicalStudy.setPrimaryCompletionDateToNow( );
		} catch (Exception e) {
			log.error(" Can't find enclosing ClinicalStudy for primaryCompletionDate tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for primaryCompletionDate tag ");
		}
		return SKIP_BODY;
	}

	public Date getPrimaryCompletionDate() throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			return theClinicalStudy.getPrimaryCompletionDate();
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for primaryCompletionDate tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for primaryCompletionDate tag ");
		}
	}

	public void setPrimaryCompletionDate( ) throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			theClinicalStudy.setPrimaryCompletionDateToNow( );
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for primaryCompletionDate tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for primaryCompletionDate tag ");
		}
	}

}
