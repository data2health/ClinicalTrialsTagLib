package edu.uiowa.slis.ClinicalTrialsTagLib.interventionArmGroup;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class InterventionArmGroupArmGroupLabel extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(InterventionArmGroupArmGroupLabel.class);


	public int doStartTag() throws JspException {
		try {
			InterventionArmGroup theInterventionArmGroup = (InterventionArmGroup)findAncestorWithClass(this, InterventionArmGroup.class);
			if (!theInterventionArmGroup.commitNeeded) {
				pageContext.getOut().print(theInterventionArmGroup.getArmGroupLabel());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing InterventionArmGroup for armGroupLabel tag ", e);
			throw new JspTagException("Error: Can't find enclosing InterventionArmGroup for armGroupLabel tag ");
		}
		return SKIP_BODY;
	}

	public String getArmGroupLabel() throws JspTagException {
		try {
			InterventionArmGroup theInterventionArmGroup = (InterventionArmGroup)findAncestorWithClass(this, InterventionArmGroup.class);
			return theInterventionArmGroup.getArmGroupLabel();
		} catch (Exception e) {
			log.error(" Can't find enclosing InterventionArmGroup for armGroupLabel tag ", e);
			throw new JspTagException("Error: Can't find enclosing InterventionArmGroup for armGroupLabel tag ");
		}
	}

	public void setArmGroupLabel(String armGroupLabel) throws JspTagException {
		try {
			InterventionArmGroup theInterventionArmGroup = (InterventionArmGroup)findAncestorWithClass(this, InterventionArmGroup.class);
			theInterventionArmGroup.setArmGroupLabel(armGroupLabel);
		} catch (Exception e) {
			log.error("Can't find enclosing InterventionArmGroup for armGroupLabel tag ", e);
			throw new JspTagException("Error: Can't find enclosing InterventionArmGroup for armGroupLabel tag ");
		}
	}

}
