package edu.uiowa.slis.ClinicalTrialsTagLib.clinicalStudy;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.Date;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ClinicalStudyFirstreceivedDateToNow extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ClinicalStudyFirstreceivedDateToNow.class);


	public int doStartTag() throws JspException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			theClinicalStudy.setFirstreceivedDateToNow( );
		} catch (Exception e) {
			log.error(" Can't find enclosing ClinicalStudy for firstreceivedDate tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for firstreceivedDate tag ");
		}
		return SKIP_BODY;
	}

	public Date getFirstreceivedDate() throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			return theClinicalStudy.getFirstreceivedDate();
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for firstreceivedDate tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for firstreceivedDate tag ");
		}
	}

	public void setFirstreceivedDate( ) throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			theClinicalStudy.setFirstreceivedDateToNow( );
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for firstreceivedDate tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for firstreceivedDate tag ");
		}
	}

}
