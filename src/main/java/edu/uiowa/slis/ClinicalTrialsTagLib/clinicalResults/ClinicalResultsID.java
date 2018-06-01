package edu.uiowa.slis.ClinicalTrialsTagLib.clinicalResults;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ClinicalResultsID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ClinicalResultsID.class);


	public int doStartTag() throws JspException {
		try {
			ClinicalResults theClinicalResults = (ClinicalResults)findAncestorWithClass(this, ClinicalResults.class);
			if (!theClinicalResults.commitNeeded) {
				pageContext.getOut().print(theClinicalResults.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalResults for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalResults for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			ClinicalResults theClinicalResults = (ClinicalResults)findAncestorWithClass(this, ClinicalResults.class);
			return theClinicalResults.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing ClinicalResults for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalResults for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			ClinicalResults theClinicalResults = (ClinicalResults)findAncestorWithClass(this, ClinicalResults.class);
			theClinicalResults.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalResults for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalResults for ID tag ");
		}
	}

}
