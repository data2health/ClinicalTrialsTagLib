package edu.uiowa.slis.ClinicalTrialsTagLib.clinicalStudy;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ClinicalStudyHasExpandedAccess extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ClinicalStudyHasExpandedAccess.class);


	public int doStartTag() throws JspException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			if (!theClinicalStudy.commitNeeded) {
				pageContext.getOut().print(theClinicalStudy.getHasExpandedAccess());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for hasExpandedAccess tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for hasExpandedAccess tag ");
		}
		return SKIP_BODY;
	}

	public String getHasExpandedAccess() throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			return theClinicalStudy.getHasExpandedAccess();
		} catch (Exception e) {
			log.error(" Can't find enclosing ClinicalStudy for hasExpandedAccess tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for hasExpandedAccess tag ");
		}
	}

	public void setHasExpandedAccess(String hasExpandedAccess) throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			theClinicalStudy.setHasExpandedAccess(hasExpandedAccess);
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for hasExpandedAccess tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for hasExpandedAccess tag ");
		}
	}

}
