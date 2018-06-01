package edu.uiowa.slis.ClinicalTrialsTagLib.clinicalStudy;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ClinicalStudyPhase extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ClinicalStudyPhase.class);


	public int doStartTag() throws JspException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			if (!theClinicalStudy.commitNeeded) {
				pageContext.getOut().print(theClinicalStudy.getPhase());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for phase tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for phase tag ");
		}
		return SKIP_BODY;
	}

	public String getPhase() throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			return theClinicalStudy.getPhase();
		} catch (Exception e) {
			log.error(" Can't find enclosing ClinicalStudy for phase tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for phase tag ");
		}
	}

	public void setPhase(String phase) throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			theClinicalStudy.setPhase(phase);
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for phase tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for phase tag ");
		}
	}

}
