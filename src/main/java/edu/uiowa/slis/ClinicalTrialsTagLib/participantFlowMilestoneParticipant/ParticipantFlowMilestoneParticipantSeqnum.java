package edu.uiowa.slis.ClinicalTrialsTagLib.participantFlowMilestoneParticipant;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ParticipantFlowMilestoneParticipantSeqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ParticipantFlowMilestoneParticipantSeqnum.class);


	public int doStartTag() throws JspException {
		try {
			ParticipantFlowMilestoneParticipant theParticipantFlowMilestoneParticipant = (ParticipantFlowMilestoneParticipant)findAncestorWithClass(this, ParticipantFlowMilestoneParticipant.class);
			if (!theParticipantFlowMilestoneParticipant.commitNeeded) {
				pageContext.getOut().print(theParticipantFlowMilestoneParticipant.getSeqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ParticipantFlowMilestoneParticipant for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowMilestoneParticipant for seqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getSeqnum() throws JspTagException {
		try {
			ParticipantFlowMilestoneParticipant theParticipantFlowMilestoneParticipant = (ParticipantFlowMilestoneParticipant)findAncestorWithClass(this, ParticipantFlowMilestoneParticipant.class);
			return theParticipantFlowMilestoneParticipant.getSeqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing ParticipantFlowMilestoneParticipant for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowMilestoneParticipant for seqnum tag ");
		}
	}

	public void setSeqnum(int seqnum) throws JspTagException {
		try {
			ParticipantFlowMilestoneParticipant theParticipantFlowMilestoneParticipant = (ParticipantFlowMilestoneParticipant)findAncestorWithClass(this, ParticipantFlowMilestoneParticipant.class);
			theParticipantFlowMilestoneParticipant.setSeqnum(seqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing ParticipantFlowMilestoneParticipant for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowMilestoneParticipant for seqnum tag ");
		}
	}

}
