package edu.uiowa.slis.ClinicalTrialsTagLib.clinicalStudy;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ClinicalStudyStudyType extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ClinicalStudyStudyType.class);


	public int doStartTag() throws JspException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			if (!theClinicalStudy.commitNeeded) {
				pageContext.getOut().print(theClinicalStudy.getStudyType());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for studyType tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for studyType tag ");
		}
		return SKIP_BODY;
	}

	public String getStudyType() throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			return theClinicalStudy.getStudyType();
		} catch (Exception e) {
			log.error(" Can't find enclosing ClinicalStudy for studyType tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for studyType tag ");
		}
	}

	public void setStudyType(String studyType) throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			theClinicalStudy.setStudyType(studyType);
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for studyType tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for studyType tag ");
		}
	}

}
