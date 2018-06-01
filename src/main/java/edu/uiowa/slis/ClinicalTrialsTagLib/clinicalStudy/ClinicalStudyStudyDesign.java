package edu.uiowa.slis.ClinicalTrialsTagLib.clinicalStudy;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ClinicalStudyStudyDesign extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ClinicalStudyStudyDesign.class);


	public int doStartTag() throws JspException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			if (!theClinicalStudy.commitNeeded) {
				pageContext.getOut().print(theClinicalStudy.getStudyDesign());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for studyDesign tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for studyDesign tag ");
		}
		return SKIP_BODY;
	}

	public String getStudyDesign() throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			return theClinicalStudy.getStudyDesign();
		} catch (Exception e) {
			log.error(" Can't find enclosing ClinicalStudy for studyDesign tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for studyDesign tag ");
		}
	}

	public void setStudyDesign(String studyDesign) throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			theClinicalStudy.setStudyDesign(studyDesign);
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for studyDesign tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for studyDesign tag ");
		}
	}

}
