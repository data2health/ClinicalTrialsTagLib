package edu.uiowa.slis.ClinicalTrialsTagLib.interventionArmGroup;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class InterventionArmGroupID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(InterventionArmGroupID.class);


	public int doStartTag() throws JspException {
		try {
			InterventionArmGroup theInterventionArmGroup = (InterventionArmGroup)findAncestorWithClass(this, InterventionArmGroup.class);
			if (!theInterventionArmGroup.commitNeeded) {
				pageContext.getOut().print(theInterventionArmGroup.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing InterventionArmGroup for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing InterventionArmGroup for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			InterventionArmGroup theInterventionArmGroup = (InterventionArmGroup)findAncestorWithClass(this, InterventionArmGroup.class);
			return theInterventionArmGroup.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing InterventionArmGroup for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing InterventionArmGroup for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			InterventionArmGroup theInterventionArmGroup = (InterventionArmGroup)findAncestorWithClass(this, InterventionArmGroup.class);
			theInterventionArmGroup.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing InterventionArmGroup for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing InterventionArmGroup for ID tag ");
		}
	}

}
