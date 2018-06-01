package edu.uiowa.slis.ClinicalTrialsTagLib.clinicalStudy;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ClinicalStudyBiospecRetention extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ClinicalStudyBiospecRetention.class);


	public int doStartTag() throws JspException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			if (!theClinicalStudy.commitNeeded) {
				pageContext.getOut().print(theClinicalStudy.getBiospecRetention());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for biospecRetention tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for biospecRetention tag ");
		}
		return SKIP_BODY;
	}

	public String getBiospecRetention() throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			return theClinicalStudy.getBiospecRetention();
		} catch (Exception e) {
			log.error(" Can't find enclosing ClinicalStudy for biospecRetention tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for biospecRetention tag ");
		}
	}

	public void setBiospecRetention(String biospecRetention) throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			theClinicalStudy.setBiospecRetention(biospecRetention);
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for biospecRetention tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for biospecRetention tag ");
		}
	}

}
