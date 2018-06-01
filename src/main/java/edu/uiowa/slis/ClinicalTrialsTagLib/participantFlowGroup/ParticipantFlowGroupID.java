package edu.uiowa.slis.ClinicalTrialsTagLib.participantFlowGroup;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ParticipantFlowGroupID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ParticipantFlowGroupID.class);


	public int doStartTag() throws JspException {
		try {
			ParticipantFlowGroup theParticipantFlowGroup = (ParticipantFlowGroup)findAncestorWithClass(this, ParticipantFlowGroup.class);
			if (!theParticipantFlowGroup.commitNeeded) {
				pageContext.getOut().print(theParticipantFlowGroup.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ParticipantFlowGroup for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowGroup for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			ParticipantFlowGroup theParticipantFlowGroup = (ParticipantFlowGroup)findAncestorWithClass(this, ParticipantFlowGroup.class);
			return theParticipantFlowGroup.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing ParticipantFlowGroup for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowGroup for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			ParticipantFlowGroup theParticipantFlowGroup = (ParticipantFlowGroup)findAncestorWithClass(this, ParticipantFlowGroup.class);
			theParticipantFlowGroup.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing ParticipantFlowGroup for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowGroup for ID tag ");
		}
	}

}
