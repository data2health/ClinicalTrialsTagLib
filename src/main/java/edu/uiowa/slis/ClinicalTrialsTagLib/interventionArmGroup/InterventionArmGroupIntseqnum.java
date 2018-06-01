package edu.uiowa.slis.ClinicalTrialsTagLib.interventionArmGroup;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class InterventionArmGroupIntseqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(InterventionArmGroupIntseqnum.class);


	public int doStartTag() throws JspException {
		try {
			InterventionArmGroup theInterventionArmGroup = (InterventionArmGroup)findAncestorWithClass(this, InterventionArmGroup.class);
			if (!theInterventionArmGroup.commitNeeded) {
				pageContext.getOut().print(theInterventionArmGroup.getIntseqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing InterventionArmGroup for intseqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing InterventionArmGroup for intseqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getIntseqnum() throws JspTagException {
		try {
			InterventionArmGroup theInterventionArmGroup = (InterventionArmGroup)findAncestorWithClass(this, InterventionArmGroup.class);
			return theInterventionArmGroup.getIntseqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing InterventionArmGroup for intseqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing InterventionArmGroup for intseqnum tag ");
		}
	}

	public void setIntseqnum(int intseqnum) throws JspTagException {
		try {
			InterventionArmGroup theInterventionArmGroup = (InterventionArmGroup)findAncestorWithClass(this, InterventionArmGroup.class);
			theInterventionArmGroup.setIntseqnum(intseqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing InterventionArmGroup for intseqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing InterventionArmGroup for intseqnum tag ");
		}
	}

}
