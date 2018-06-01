package edu.uiowa.slis.ClinicalTrialsTagLib.clinicalStudy;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ClinicalStudySource extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ClinicalStudySource.class);


	public int doStartTag() throws JspException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			if (!theClinicalStudy.commitNeeded) {
				pageContext.getOut().print(theClinicalStudy.getSource());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for source tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for source tag ");
		}
		return SKIP_BODY;
	}

	public String getSource() throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			return theClinicalStudy.getSource();
		} catch (Exception e) {
			log.error(" Can't find enclosing ClinicalStudy for source tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for source tag ");
		}
	}

	public void setSource(String source) throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			theClinicalStudy.setSource(source);
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for source tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for source tag ");
		}
	}

}
