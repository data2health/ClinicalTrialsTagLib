package edu.uiowa.slis.ClinicalTrialsTagLib.clinicalStudy;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ClinicalStudyWhyStopped extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ClinicalStudyWhyStopped.class);


	public int doStartTag() throws JspException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			if (!theClinicalStudy.commitNeeded) {
				pageContext.getOut().print(theClinicalStudy.getWhyStopped());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for whyStopped tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for whyStopped tag ");
		}
		return SKIP_BODY;
	}

	public String getWhyStopped() throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			return theClinicalStudy.getWhyStopped();
		} catch (Exception e) {
			log.error(" Can't find enclosing ClinicalStudy for whyStopped tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for whyStopped tag ");
		}
	}

	public void setWhyStopped(String whyStopped) throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			theClinicalStudy.setWhyStopped(whyStopped);
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for whyStopped tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for whyStopped tag ");
		}
	}

}
