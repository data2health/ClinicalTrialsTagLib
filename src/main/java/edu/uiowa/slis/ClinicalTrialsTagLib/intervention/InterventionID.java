package edu.uiowa.slis.ClinicalTrialsTagLib.intervention;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class InterventionID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(InterventionID.class);


	public int doStartTag() throws JspException {
		try {
			Intervention theIntervention = (Intervention)findAncestorWithClass(this, Intervention.class);
			if (!theIntervention.commitNeeded) {
				pageContext.getOut().print(theIntervention.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Intervention for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing Intervention for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			Intervention theIntervention = (Intervention)findAncestorWithClass(this, Intervention.class);
			return theIntervention.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing Intervention for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing Intervention for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			Intervention theIntervention = (Intervention)findAncestorWithClass(this, Intervention.class);
			theIntervention.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing Intervention for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing Intervention for ID tag ");
		}
	}

}
