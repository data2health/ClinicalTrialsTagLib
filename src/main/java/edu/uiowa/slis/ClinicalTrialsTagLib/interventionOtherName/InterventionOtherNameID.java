package edu.uiowa.slis.ClinicalTrialsTagLib.interventionOtherName;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class InterventionOtherNameID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(InterventionOtherNameID.class);


	public int doStartTag() throws JspException {
		try {
			InterventionOtherName theInterventionOtherName = (InterventionOtherName)findAncestorWithClass(this, InterventionOtherName.class);
			if (!theInterventionOtherName.commitNeeded) {
				pageContext.getOut().print(theInterventionOtherName.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing InterventionOtherName for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing InterventionOtherName for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			InterventionOtherName theInterventionOtherName = (InterventionOtherName)findAncestorWithClass(this, InterventionOtherName.class);
			return theInterventionOtherName.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing InterventionOtherName for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing InterventionOtherName for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			InterventionOtherName theInterventionOtherName = (InterventionOtherName)findAncestorWithClass(this, InterventionOtherName.class);
			theInterventionOtherName.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing InterventionOtherName for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing InterventionOtherName for ID tag ");
		}
	}

}
