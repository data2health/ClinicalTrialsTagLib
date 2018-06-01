package edu.uiowa.slis.ClinicalTrialsTagLib.clinicalStudy;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ClinicalStudyBriefTitle extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ClinicalStudyBriefTitle.class);


	public int doStartTag() throws JspException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			if (!theClinicalStudy.commitNeeded) {
				pageContext.getOut().print(theClinicalStudy.getBriefTitle());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for briefTitle tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for briefTitle tag ");
		}
		return SKIP_BODY;
	}

	public String getBriefTitle() throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			return theClinicalStudy.getBriefTitle();
		} catch (Exception e) {
			log.error(" Can't find enclosing ClinicalStudy for briefTitle tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for briefTitle tag ");
		}
	}

	public void setBriefTitle(String briefTitle) throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			theClinicalStudy.setBriefTitle(briefTitle);
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for briefTitle tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for briefTitle tag ");
		}
	}

}
