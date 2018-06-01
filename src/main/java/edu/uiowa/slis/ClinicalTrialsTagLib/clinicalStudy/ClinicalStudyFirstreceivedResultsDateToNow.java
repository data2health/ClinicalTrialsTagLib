package edu.uiowa.slis.ClinicalTrialsTagLib.clinicalStudy;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.Date;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ClinicalStudyFirstreceivedResultsDateToNow extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ClinicalStudyFirstreceivedResultsDateToNow.class);


	public int doStartTag() throws JspException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			theClinicalStudy.setFirstreceivedResultsDateToNow( );
		} catch (Exception e) {
			log.error(" Can't find enclosing ClinicalStudy for firstreceivedResultsDate tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for firstreceivedResultsDate tag ");
		}
		return SKIP_BODY;
	}

	public Date getFirstreceivedResultsDate() throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			return theClinicalStudy.getFirstreceivedResultsDate();
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for firstreceivedResultsDate tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for firstreceivedResultsDate tag ");
		}
	}

	public void setFirstreceivedResultsDate( ) throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			theClinicalStudy.setFirstreceivedResultsDateToNow( );
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for firstreceivedResultsDate tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for firstreceivedResultsDate tag ");
		}
	}

}
