package edu.uiowa.slis.ClinicalTrialsTagLib.clinicalStudy;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ClinicalStudyID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ClinicalStudyID.class);


	public int doStartTag() throws JspException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			if (!theClinicalStudy.commitNeeded) {
				pageContext.getOut().print(theClinicalStudy.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			return theClinicalStudy.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing ClinicalStudy for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			theClinicalStudy.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for ID tag ");
		}
	}

}
