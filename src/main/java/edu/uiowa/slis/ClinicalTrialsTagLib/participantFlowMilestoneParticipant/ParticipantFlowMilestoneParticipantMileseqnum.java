package edu.uiowa.slis.ClinicalTrialsTagLib.participantFlowMilestoneParticipant;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ParticipantFlowMilestoneParticipantMileseqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ParticipantFlowMilestoneParticipantMileseqnum.class);


	public int doStartTag() throws JspException {
		try {
			ParticipantFlowMilestoneParticipant theParticipantFlowMilestoneParticipant = (ParticipantFlowMilestoneParticipant)findAncestorWithClass(this, ParticipantFlowMilestoneParticipant.class);
			if (!theParticipantFlowMilestoneParticipant.commitNeeded) {
				pageContext.getOut().print(theParticipantFlowMilestoneParticipant.getMileseqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ParticipantFlowMilestoneParticipant for mileseqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowMilestoneParticipant for mileseqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getMileseqnum() throws JspTagException {
		try {
			ParticipantFlowMilestoneParticipant theParticipantFlowMilestoneParticipant = (ParticipantFlowMilestoneParticipant)findAncestorWithClass(this, ParticipantFlowMilestoneParticipant.class);
			return theParticipantFlowMilestoneParticipant.getMileseqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing ParticipantFlowMilestoneParticipant for mileseqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowMilestoneParticipant for mileseqnum tag ");
		}
	}

	public void setMileseqnum(int mileseqnum) throws JspTagException {
		try {
			ParticipantFlowMilestoneParticipant theParticipantFlowMilestoneParticipant = (ParticipantFlowMilestoneParticipant)findAncestorWithClass(this, ParticipantFlowMilestoneParticipant.class);
			theParticipantFlowMilestoneParticipant.setMileseqnum(mileseqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing ParticipantFlowMilestoneParticipant for mileseqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowMilestoneParticipant for mileseqnum tag ");
		}
	}

}
