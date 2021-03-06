package edu.uiowa.slis.ClinicalTrialsTagLib.clinicalStudy;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ClinicalStudyIsFdaRegulated extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ClinicalStudyIsFdaRegulated.class);


	public int doStartTag() throws JspException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			if (!theClinicalStudy.commitNeeded) {
				pageContext.getOut().print(theClinicalStudy.getIsFdaRegulated());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for isFdaRegulated tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for isFdaRegulated tag ");
		}
		return SKIP_BODY;
	}

	public String getIsFdaRegulated() throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			return theClinicalStudy.getIsFdaRegulated();
		} catch (Exception e) {
			log.error(" Can't find enclosing ClinicalStudy for isFdaRegulated tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for isFdaRegulated tag ");
		}
	}

	public void setIsFdaRegulated(String isFdaRegulated) throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			theClinicalStudy.setIsFdaRegulated(isFdaRegulated);
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for isFdaRegulated tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for isFdaRegulated tag ");
		}
	}

}
