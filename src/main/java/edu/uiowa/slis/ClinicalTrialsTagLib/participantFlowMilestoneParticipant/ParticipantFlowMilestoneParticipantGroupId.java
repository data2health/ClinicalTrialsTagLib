package edu.uiowa.slis.ClinicalTrialsTagLib.participantFlowMilestoneParticipant;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ParticipantFlowMilestoneParticipantGroupId extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ParticipantFlowMilestoneParticipantGroupId.class);


	public int doStartTag() throws JspException {
		try {
			ParticipantFlowMilestoneParticipant theParticipantFlowMilestoneParticipant = (ParticipantFlowMilestoneParticipant)findAncestorWithClass(this, ParticipantFlowMilestoneParticipant.class);
			if (!theParticipantFlowMilestoneParticipant.commitNeeded) {
				pageContext.getOut().print(theParticipantFlowMilestoneParticipant.getGroupId());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ParticipantFlowMilestoneParticipant for groupId tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowMilestoneParticipant for groupId tag ");
		}
		return SKIP_BODY;
	}

	public String getGroupId() throws JspTagException {
		try {
			ParticipantFlowMilestoneParticipant theParticipantFlowMilestoneParticipant = (ParticipantFlowMilestoneParticipant)findAncestorWithClass(this, ParticipantFlowMilestoneParticipant.class);
			return theParticipantFlowMilestoneParticipant.getGroupId();
		} catch (Exception e) {
			log.error(" Can't find enclosing ParticipantFlowMilestoneParticipant for groupId tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowMilestoneParticipant for groupId tag ");
		}
	}

	public void setGroupId(String groupId) throws JspTagException {
		try {
			ParticipantFlowMilestoneParticipant theParticipantFlowMilestoneParticipant = (ParticipantFlowMilestoneParticipant)findAncestorWithClass(this, ParticipantFlowMilestoneParticipant.class);
			theParticipantFlowMilestoneParticipant.setGroupId(groupId);
		} catch (Exception e) {
			log.error("Can't find enclosing ParticipantFlowMilestoneParticipant for groupId tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowMilestoneParticipant for groupId tag ");
		}
	}

}
