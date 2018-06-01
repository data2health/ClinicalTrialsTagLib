package edu.uiowa.slis.ClinicalTrialsTagLib.collaborator;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class CollaboratorAgency extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(CollaboratorAgency.class);


	public int doStartTag() throws JspException {
		try {
			Collaborator theCollaborator = (Collaborator)findAncestorWithClass(this, Collaborator.class);
			if (!theCollaborator.commitNeeded) {
				pageContext.getOut().print(theCollaborator.getAgency());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Collaborator for agency tag ", e);
			throw new JspTagException("Error: Can't find enclosing Collaborator for agency tag ");
		}
		return SKIP_BODY;
	}

	public String getAgency() throws JspTagException {
		try {
			Collaborator theCollaborator = (Collaborator)findAncestorWithClass(this, Collaborator.class);
			return theCollaborator.getAgency();
		} catch (Exception e) {
			log.error(" Can't find enclosing Collaborator for agency tag ", e);
			throw new JspTagException("Error: Can't find enclosing Collaborator for agency tag ");
		}
	}

	public void setAgency(String agency) throws JspTagException {
		try {
			Collaborator theCollaborator = (Collaborator)findAncestorWithClass(this, Collaborator.class);
			theCollaborator.setAgency(agency);
		} catch (Exception e) {
			log.error("Can't find enclosing Collaborator for agency tag ", e);
			throw new JspTagException("Error: Can't find enclosing Collaborator for agency tag ");
		}
	}

}
