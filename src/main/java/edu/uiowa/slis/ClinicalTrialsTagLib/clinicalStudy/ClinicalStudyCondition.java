package edu.uiowa.slis.ClinicalTrialsTagLib.clinicalStudy;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ClinicalStudyCondition extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ClinicalStudyCondition.class);


	public int doStartTag() throws JspException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			if (!theClinicalStudy.commitNeeded) {
				pageContext.getOut().print(theClinicalStudy.getCondition());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for condition tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for condition tag ");
		}
		return SKIP_BODY;
	}

	public String getCondition() throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			return theClinicalStudy.getCondition();
		} catch (Exception e) {
			log.error(" Can't find enclosing ClinicalStudy for condition tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for condition tag ");
		}
	}

	public void setCondition(String condition) throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			theClinicalStudy.setCondition(condition);
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for condition tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for condition tag ");
		}
	}

}
