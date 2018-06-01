package edu.uiowa.slis.ClinicalTrialsTagLib.clinicalStudy;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.Date;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ClinicalStudyLastchangedDateToNow extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ClinicalStudyLastchangedDateToNow.class);


	public int doStartTag() throws JspException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			theClinicalStudy.setLastchangedDateToNow( );
		} catch (Exception e) {
			log.error(" Can't find enclosing ClinicalStudy for lastchangedDate tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for lastchangedDate tag ");
		}
		return SKIP_BODY;
	}

	public Date getLastchangedDate() throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			return theClinicalStudy.getLastchangedDate();
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for lastchangedDate tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for lastchangedDate tag ");
		}
	}

	public void setLastchangedDate( ) throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			theClinicalStudy.setLastchangedDateToNow( );
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for lastchangedDate tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for lastchangedDate tag ");
		}
	}

}
