package edu.uiowa.slis.ClinicalTrialsTagLib.participantFlowMilestone;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ParticipantFlowMilestoneSeqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ParticipantFlowMilestoneSeqnum.class);


	public int doStartTag() throws JspException {
		try {
			ParticipantFlowMilestone theParticipantFlowMilestone = (ParticipantFlowMilestone)findAncestorWithClass(this, ParticipantFlowMilestone.class);
			if (!theParticipantFlowMilestone.commitNeeded) {
				pageContext.getOut().print(theParticipantFlowMilestone.getSeqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ParticipantFlowMilestone for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowMilestone for seqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getSeqnum() throws JspTagException {
		try {
			ParticipantFlowMilestone theParticipantFlowMilestone = (ParticipantFlowMilestone)findAncestorWithClass(this, ParticipantFlowMilestone.class);
			return theParticipantFlowMilestone.getSeqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing ParticipantFlowMilestone for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowMilestone for seqnum tag ");
		}
	}

	public void setSeqnum(int seqnum) throws JspTagException {
		try {
			ParticipantFlowMilestone theParticipantFlowMilestone = (ParticipantFlowMilestone)findAncestorWithClass(this, ParticipantFlowMilestone.class);
			theParticipantFlowMilestone.setSeqnum(seqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing ParticipantFlowMilestone for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowMilestone for seqnum tag ");
		}
	}

}
