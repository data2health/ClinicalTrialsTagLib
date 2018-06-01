package edu.uiowa.slis.ClinicalTrialsTagLib.clinicalStudy;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ClinicalStudyAcronym extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ClinicalStudyAcronym.class);


	public int doStartTag() throws JspException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			if (!theClinicalStudy.commitNeeded) {
				pageContext.getOut().print(theClinicalStudy.getAcronym());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for acronym tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for acronym tag ");
		}
		return SKIP_BODY;
	}

	public String getAcronym() throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			return theClinicalStudy.getAcronym();
		} catch (Exception e) {
			log.error(" Can't find enclosing ClinicalStudy for acronym tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for acronym tag ");
		}
	}

	public void setAcronym(String acronym) throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			theClinicalStudy.setAcronym(acronym);
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for acronym tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for acronym tag ");
		}
	}

}
