package edu.uiowa.slis.ClinicalTrialsTagLib.clinicalStudy;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ClinicalStudyIsSection801 extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ClinicalStudyIsSection801.class);


	public int doStartTag() throws JspException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			if (!theClinicalStudy.commitNeeded) {
				pageContext.getOut().print(theClinicalStudy.getIsSection801());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for isSection801 tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for isSection801 tag ");
		}
		return SKIP_BODY;
	}

	public String getIsSection801() throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			return theClinicalStudy.getIsSection801();
		} catch (Exception e) {
			log.error(" Can't find enclosing ClinicalStudy for isSection801 tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for isSection801 tag ");
		}
	}

	public void setIsSection801(String isSection801) throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			theClinicalStudy.setIsSection801(isSection801);
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for isSection801 tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for isSection801 tag ");
		}
	}

}
