package edu.uiowa.slis.ClinicalTrialsTagLib.intervention;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class InterventionDescription extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(InterventionDescription.class);


	public int doStartTag() throws JspException {
		try {
			Intervention theIntervention = (Intervention)findAncestorWithClass(this, Intervention.class);
			if (!theIntervention.commitNeeded) {
				pageContext.getOut().print(theIntervention.getDescription());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Intervention for description tag ", e);
			throw new JspTagException("Error: Can't find enclosing Intervention for description tag ");
		}
		return SKIP_BODY;
	}

	public String getDescription() throws JspTagException {
		try {
			Intervention theIntervention = (Intervention)findAncestorWithClass(this, Intervention.class);
			return theIntervention.getDescription();
		} catch (Exception e) {
			log.error(" Can't find enclosing Intervention for description tag ", e);
			throw new JspTagException("Error: Can't find enclosing Intervention for description tag ");
		}
	}

	public void setDescription(String description) throws JspTagException {
		try {
			Intervention theIntervention = (Intervention)findAncestorWithClass(this, Intervention.class);
			theIntervention.setDescription(description);
		} catch (Exception e) {
			log.error("Can't find enclosing Intervention for description tag ", e);
			throw new JspTagException("Error: Can't find enclosing Intervention for description tag ");
		}
	}

}
