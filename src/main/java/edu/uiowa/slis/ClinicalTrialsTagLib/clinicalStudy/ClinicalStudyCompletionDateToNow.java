package edu.uiowa.slis.ClinicalTrialsTagLib.clinicalStudy;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.Date;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ClinicalStudyCompletionDateToNow extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ClinicalStudyCompletionDateToNow.class);


	public int doStartTag() throws JspException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			theClinicalStudy.setCompletionDateToNow( );
		} catch (Exception e) {
			log.error(" Can't find enclosing ClinicalStudy for completionDate tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for completionDate tag ");
		}
		return SKIP_BODY;
	}

	public Date getCompletionDate() throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			return theClinicalStudy.getCompletionDate();
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for completionDate tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for completionDate tag ");
		}
	}

	public void setCompletionDate( ) throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			theClinicalStudy.setCompletionDateToNow( );
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for completionDate tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for completionDate tag ");
		}
	}

}
