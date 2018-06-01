package edu.uiowa.slis.ClinicalTrialsTagLib.participantFlowMilestoneParticipant;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ParticipantFlowMilestoneParticipantID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ParticipantFlowMilestoneParticipantID.class);


	public int doStartTag() throws JspException {
		try {
			ParticipantFlowMilestoneParticipant theParticipantFlowMilestoneParticipant = (ParticipantFlowMilestoneParticipant)findAncestorWithClass(this, ParticipantFlowMilestoneParticipant.class);
			if (!theParticipantFlowMilestoneParticipant.commitNeeded) {
				pageContext.getOut().print(theParticipantFlowMilestoneParticipant.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ParticipantFlowMilestoneParticipant for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowMilestoneParticipant for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			ParticipantFlowMilestoneParticipant theParticipantFlowMilestoneParticipant = (ParticipantFlowMilestoneParticipant)findAncestorWithClass(this, ParticipantFlowMilestoneParticipant.class);
			return theParticipantFlowMilestoneParticipant.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing ParticipantFlowMilestoneParticipant for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowMilestoneParticipant for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			ParticipantFlowMilestoneParticipant theParticipantFlowMilestoneParticipant = (ParticipantFlowMilestoneParticipant)findAncestorWithClass(this, ParticipantFlowMilestoneParticipant.class);
			theParticipantFlowMilestoneParticipant.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing ParticipantFlowMilestoneParticipant for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowMilestoneParticipant for ID tag ");
		}
	}

}
