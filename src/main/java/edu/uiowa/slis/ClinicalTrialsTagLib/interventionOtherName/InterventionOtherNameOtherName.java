package edu.uiowa.slis.ClinicalTrialsTagLib.interventionOtherName;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class InterventionOtherNameOtherName extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(InterventionOtherNameOtherName.class);


	public int doStartTag() throws JspException {
		try {
			InterventionOtherName theInterventionOtherName = (InterventionOtherName)findAncestorWithClass(this, InterventionOtherName.class);
			if (!theInterventionOtherName.commitNeeded) {
				pageContext.getOut().print(theInterventionOtherName.getOtherName());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing InterventionOtherName for otherName tag ", e);
			throw new JspTagException("Error: Can't find enclosing InterventionOtherName for otherName tag ");
		}
		return SKIP_BODY;
	}

	public String getOtherName() throws JspTagException {
		try {
			InterventionOtherName theInterventionOtherName = (InterventionOtherName)findAncestorWithClass(this, InterventionOtherName.class);
			return theInterventionOtherName.getOtherName();
		} catch (Exception e) {
			log.error(" Can't find enclosing InterventionOtherName for otherName tag ", e);
			throw new JspTagException("Error: Can't find enclosing InterventionOtherName for otherName tag ");
		}
	}

	public void setOtherName(String otherName) throws JspTagException {
		try {
			InterventionOtherName theInterventionOtherName = (InterventionOtherName)findAncestorWithClass(this, InterventionOtherName.class);
			theInterventionOtherName.setOtherName(otherName);
		} catch (Exception e) {
			log.error("Can't find enclosing InterventionOtherName for otherName tag ", e);
			throw new JspTagException("Error: Can't find enclosing InterventionOtherName for otherName tag ");
		}
	}

}
