package edu.uiowa.slis.ClinicalTrialsTagLib.clinicalStudy;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ClinicalStudyNumberOfArms extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ClinicalStudyNumberOfArms.class);


	public int doStartTag() throws JspException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			if (!theClinicalStudy.commitNeeded) {
				pageContext.getOut().print(theClinicalStudy.getNumberOfArms());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for numberOfArms tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for numberOfArms tag ");
		}
		return SKIP_BODY;
	}

	public String getNumberOfArms() throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			return theClinicalStudy.getNumberOfArms();
		} catch (Exception e) {
			log.error(" Can't find enclosing ClinicalStudy for numberOfArms tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for numberOfArms tag ");
		}
	}

	public void setNumberOfArms(String numberOfArms) throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			theClinicalStudy.setNumberOfArms(numberOfArms);
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for numberOfArms tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for numberOfArms tag ");
		}
	}

}
