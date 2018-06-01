package edu.uiowa.slis.ClinicalTrialsTagLib.intervention;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class InterventionInterventionType extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(InterventionInterventionType.class);


	public int doStartTag() throws JspException {
		try {
			Intervention theIntervention = (Intervention)findAncestorWithClass(this, Intervention.class);
			if (!theIntervention.commitNeeded) {
				pageContext.getOut().print(theIntervention.getInterventionType());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Intervention for interventionType tag ", e);
			throw new JspTagException("Error: Can't find enclosing Intervention for interventionType tag ");
		}
		return SKIP_BODY;
	}

	public String getInterventionType() throws JspTagException {
		try {
			Intervention theIntervention = (Intervention)findAncestorWithClass(this, Intervention.class);
			return theIntervention.getInterventionType();
		} catch (Exception e) {
			log.error(" Can't find enclosing Intervention for interventionType tag ", e);
			throw new JspTagException("Error: Can't find enclosing Intervention for interventionType tag ");
		}
	}

	public void setInterventionType(String interventionType) throws JspTagException {
		try {
			Intervention theIntervention = (Intervention)findAncestorWithClass(this, Intervention.class);
			theIntervention.setInterventionType(interventionType);
		} catch (Exception e) {
			log.error("Can't find enclosing Intervention for interventionType tag ", e);
			throw new JspTagException("Error: Can't find enclosing Intervention for interventionType tag ");
		}
	}

}
