package edu.uiowa.slis.ClinicalTrialsTagLib.interventionBrowse;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class InterventionBrowseMesh extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(InterventionBrowseMesh.class);


	public int doStartTag() throws JspException {
		try {
			InterventionBrowse theInterventionBrowse = (InterventionBrowse)findAncestorWithClass(this, InterventionBrowse.class);
			if (!theInterventionBrowse.commitNeeded) {
				pageContext.getOut().print(theInterventionBrowse.getMesh());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing InterventionBrowse for mesh tag ", e);
			throw new JspTagException("Error: Can't find enclosing InterventionBrowse for mesh tag ");
		}
		return SKIP_BODY;
	}

	public String getMesh() throws JspTagException {
		try {
			InterventionBrowse theInterventionBrowse = (InterventionBrowse)findAncestorWithClass(this, InterventionBrowse.class);
			return theInterventionBrowse.getMesh();
		} catch (Exception e) {
			log.error(" Can't find enclosing InterventionBrowse for mesh tag ", e);
			throw new JspTagException("Error: Can't find enclosing InterventionBrowse for mesh tag ");
		}
	}

	public void setMesh(String mesh) throws JspTagException {
		try {
			InterventionBrowse theInterventionBrowse = (InterventionBrowse)findAncestorWithClass(this, InterventionBrowse.class);
			theInterventionBrowse.setMesh(mesh);
		} catch (Exception e) {
			log.error("Can't find enclosing InterventionBrowse for mesh tag ", e);
			throw new JspTagException("Error: Can't find enclosing InterventionBrowse for mesh tag ");
		}
	}

}
