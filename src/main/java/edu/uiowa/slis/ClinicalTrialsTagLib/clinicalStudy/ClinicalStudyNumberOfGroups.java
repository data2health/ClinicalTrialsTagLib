package edu.uiowa.slis.ClinicalTrialsTagLib.clinicalStudy;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ClinicalStudyNumberOfGroups extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ClinicalStudyNumberOfGroups.class);


	public int doStartTag() throws JspException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			if (!theClinicalStudy.commitNeeded) {
				pageContext.getOut().print(theClinicalStudy.getNumberOfGroups());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for numberOfGroups tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for numberOfGroups tag ");
		}
		return SKIP_BODY;
	}

	public String getNumberOfGroups() throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			return theClinicalStudy.getNumberOfGroups();
		} catch (Exception e) {
			log.error(" Can't find enclosing ClinicalStudy for numberOfGroups tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for numberOfGroups tag ");
		}
	}

	public void setNumberOfGroups(String numberOfGroups) throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			theClinicalStudy.setNumberOfGroups(numberOfGroups);
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for numberOfGroups tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for numberOfGroups tag ");
		}
	}

}
