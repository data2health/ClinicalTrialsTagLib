package edu.uiowa.slis.ClinicalTrialsTagLib.intervention;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class InterventionInterventionName extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(InterventionInterventionName.class);


	public int doStartTag() throws JspException {
		try {
			Intervention theIntervention = (Intervention)findAncestorWithClass(this, Intervention.class);
			if (!theIntervention.commitNeeded) {
				pageContext.getOut().print(theIntervention.getInterventionName());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Intervention for interventionName tag ", e);
			throw new JspTagException("Error: Can't find enclosing Intervention for interventionName tag ");
		}
		return SKIP_BODY;
	}

	public String getInterventionName() throws JspTagException {
		try {
			Intervention theIntervention = (Intervention)findAncestorWithClass(this, Intervention.class);
			return theIntervention.getInterventionName();
		} catch (Exception e) {
			log.error(" Can't find enclosing Intervention for interventionName tag ", e);
			throw new JspTagException("Error: Can't find enclosing Intervention for interventionName tag ");
		}
	}

	public void setInterventionName(String interventionName) throws JspTagException {
		try {
			Intervention theIntervention = (Intervention)findAncestorWithClass(this, Intervention.class);
			theIntervention.setInterventionName(interventionName);
		} catch (Exception e) {
			log.error("Can't find enclosing Intervention for interventionName tag ", e);
			throw new JspTagException("Error: Can't find enclosing Intervention for interventionName tag ");
		}
	}

}
