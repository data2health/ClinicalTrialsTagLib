package edu.uiowa.slis.ClinicalTrialsTagLib.clinicalStudy;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ClinicalStudyOfficialTitle extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ClinicalStudyOfficialTitle.class);


	public int doStartTag() throws JspException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			if (!theClinicalStudy.commitNeeded) {
				pageContext.getOut().print(theClinicalStudy.getOfficialTitle());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for officialTitle tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for officialTitle tag ");
		}
		return SKIP_BODY;
	}

	public String getOfficialTitle() throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			return theClinicalStudy.getOfficialTitle();
		} catch (Exception e) {
			log.error(" Can't find enclosing ClinicalStudy for officialTitle tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for officialTitle tag ");
		}
	}

	public void setOfficialTitle(String officialTitle) throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			theClinicalStudy.setOfficialTitle(officialTitle);
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for officialTitle tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for officialTitle tag ");
		}
	}

}
