package edu.uiowa.slis.ClinicalTrialsTagLib.participantFlowMilestone;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ParticipantFlowMilestoneID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ParticipantFlowMilestoneID.class);


	public int doStartTag() throws JspException {
		try {
			ParticipantFlowMilestone theParticipantFlowMilestone = (ParticipantFlowMilestone)findAncestorWithClass(this, ParticipantFlowMilestone.class);
			if (!theParticipantFlowMilestone.commitNeeded) {
				pageContext.getOut().print(theParticipantFlowMilestone.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ParticipantFlowMilestone for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowMilestone for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			ParticipantFlowMilestone theParticipantFlowMilestone = (ParticipantFlowMilestone)findAncestorWithClass(this, ParticipantFlowMilestone.class);
			return theParticipantFlowMilestone.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing ParticipantFlowMilestone for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowMilestone for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			ParticipantFlowMilestone theParticipantFlowMilestone = (ParticipantFlowMilestone)findAncestorWithClass(this, ParticipantFlowMilestone.class);
			theParticipantFlowMilestone.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing ParticipantFlowMilestone for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowMilestone for ID tag ");
		}
	}

}
