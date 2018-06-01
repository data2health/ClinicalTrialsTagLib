package edu.uiowa.slis.ClinicalTrialsTagLib.clinicalStudy;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ClinicalStudyOrgStudyId extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ClinicalStudyOrgStudyId.class);


	public int doStartTag() throws JspException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			if (!theClinicalStudy.commitNeeded) {
				pageContext.getOut().print(theClinicalStudy.getOrgStudyId());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for orgStudyId tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for orgStudyId tag ");
		}
		return SKIP_BODY;
	}

	public String getOrgStudyId() throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			return theClinicalStudy.getOrgStudyId();
		} catch (Exception e) {
			log.error(" Can't find enclosing ClinicalStudy for orgStudyId tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for orgStudyId tag ");
		}
	}

	public void setOrgStudyId(String orgStudyId) throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			theClinicalStudy.setOrgStudyId(orgStudyId);
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for orgStudyId tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for orgStudyId tag ");
		}
	}

}
