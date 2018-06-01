package edu.uiowa.slis.ClinicalTrialsTagLib.clinicalStudy;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ClinicalStudyNctId extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ClinicalStudyNctId.class);


	public int doStartTag() throws JspException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			if (!theClinicalStudy.commitNeeded) {
				pageContext.getOut().print(theClinicalStudy.getNctId());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for nctId tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for nctId tag ");
		}
		return SKIP_BODY;
	}

	public String getNctId() throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			return theClinicalStudy.getNctId();
		} catch (Exception e) {
			log.error(" Can't find enclosing ClinicalStudy for nctId tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for nctId tag ");
		}
	}

	public void setNctId(String nctId) throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			theClinicalStudy.setNctId(nctId);
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for nctId tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for nctId tag ");
		}
	}

}
