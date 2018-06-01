package edu.uiowa.slis.ClinicalTrialsTagLib.interventionBrowse;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class InterventionBrowseID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(InterventionBrowseID.class);


	public int doStartTag() throws JspException {
		try {
			InterventionBrowse theInterventionBrowse = (InterventionBrowse)findAncestorWithClass(this, InterventionBrowse.class);
			if (!theInterventionBrowse.commitNeeded) {
				pageContext.getOut().print(theInterventionBrowse.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing InterventionBrowse for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing InterventionBrowse for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			InterventionBrowse theInterventionBrowse = (InterventionBrowse)findAncestorWithClass(this, InterventionBrowse.class);
			return theInterventionBrowse.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing InterventionBrowse for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing InterventionBrowse for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			InterventionBrowse theInterventionBrowse = (InterventionBrowse)findAncestorWithClass(this, InterventionBrowse.class);
			theInterventionBrowse.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing InterventionBrowse for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing InterventionBrowse for ID tag ");
		}
	}

}
