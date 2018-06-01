package edu.uiowa.slis.ClinicalTrialsTagLib.collaborator;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class CollaboratorSeqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(CollaboratorSeqnum.class);


	public int doStartTag() throws JspException {
		try {
			Collaborator theCollaborator = (Collaborator)findAncestorWithClass(this, Collaborator.class);
			if (!theCollaborator.commitNeeded) {
				pageContext.getOut().print(theCollaborator.getSeqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Collaborator for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing Collaborator for seqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getSeqnum() throws JspTagException {
		try {
			Collaborator theCollaborator = (Collaborator)findAncestorWithClass(this, Collaborator.class);
			return theCollaborator.getSeqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing Collaborator for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing Collaborator for seqnum tag ");
		}
	}

	public void setSeqnum(int seqnum) throws JspTagException {
		try {
			Collaborator theCollaborator = (Collaborator)findAncestorWithClass(this, Collaborator.class);
			theCollaborator.setSeqnum(seqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing Collaborator for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing Collaborator for seqnum tag ");
		}
	}

}
