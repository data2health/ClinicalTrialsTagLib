package edu.uiowa.slis.ClinicalTrialsTagLib.collaborator;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class CollaboratorAgencyClass extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(CollaboratorAgencyClass.class);


	public int doStartTag() throws JspException {
		try {
			Collaborator theCollaborator = (Collaborator)findAncestorWithClass(this, Collaborator.class);
			if (!theCollaborator.commitNeeded) {
				pageContext.getOut().print(theCollaborator.getAgencyClass());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Collaborator for agencyClass tag ", e);
			throw new JspTagException("Error: Can't find enclosing Collaborator for agencyClass tag ");
		}
		return SKIP_BODY;
	}

	public String getAgencyClass() throws JspTagException {
		try {
			Collaborator theCollaborator = (Collaborator)findAncestorWithClass(this, Collaborator.class);
			return theCollaborator.getAgencyClass();
		} catch (Exception e) {
			log.error(" Can't find enclosing Collaborator for agencyClass tag ", e);
			throw new JspTagException("Error: Can't find enclosing Collaborator for agencyClass tag ");
		}
	}

	public void setAgencyClass(String agencyClass) throws JspTagException {
		try {
			Collaborator theCollaborator = (Collaborator)findAncestorWithClass(this, Collaborator.class);
			theCollaborator.setAgencyClass(agencyClass);
		} catch (Exception e) {
			log.error("Can't find enclosing Collaborator for agencyClass tag ", e);
			throw new JspTagException("Error: Can't find enclosing Collaborator for agencyClass tag ");
		}
	}

}
