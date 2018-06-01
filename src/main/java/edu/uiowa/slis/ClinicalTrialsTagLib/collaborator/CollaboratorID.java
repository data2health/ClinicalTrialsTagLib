package edu.uiowa.slis.ClinicalTrialsTagLib.collaborator;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class CollaboratorID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(CollaboratorID.class);


	public int doStartTag() throws JspException {
		try {
			Collaborator theCollaborator = (Collaborator)findAncestorWithClass(this, Collaborator.class);
			if (!theCollaborator.commitNeeded) {
				pageContext.getOut().print(theCollaborator.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Collaborator for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing Collaborator for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			Collaborator theCollaborator = (Collaborator)findAncestorWithClass(this, Collaborator.class);
			return theCollaborator.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing Collaborator for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing Collaborator for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			Collaborator theCollaborator = (Collaborator)findAncestorWithClass(this, Collaborator.class);
			theCollaborator.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing Collaborator for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing Collaborator for ID tag ");
		}
	}

}
