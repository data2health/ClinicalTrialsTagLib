package edu.uiowa.slis.ClinicalTrialsTagLib.collaborator;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class CollaboratorCollseqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(CollaboratorCollseqnum.class);


	public int doStartTag() throws JspException {
		try {
			Collaborator theCollaborator = (Collaborator)findAncestorWithClass(this, Collaborator.class);
			if (!theCollaborator.commitNeeded) {
				pageContext.getOut().print(theCollaborator.getCollseqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Collaborator for collseqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing Collaborator for collseqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getCollseqnum() throws JspTagException {
		try {
			Collaborator theCollaborator = (Collaborator)findAncestorWithClass(this, Collaborator.class);
			return theCollaborator.getCollseqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing Collaborator for collseqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing Collaborator for collseqnum tag ");
		}
	}

	public void setCollseqnum(int collseqnum) throws JspTagException {
		try {
			Collaborator theCollaborator = (Collaborator)findAncestorWithClass(this, Collaborator.class);
			theCollaborator.setCollseqnum(collseqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing Collaborator for collseqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing Collaborator for collseqnum tag ");
		}
	}

}
