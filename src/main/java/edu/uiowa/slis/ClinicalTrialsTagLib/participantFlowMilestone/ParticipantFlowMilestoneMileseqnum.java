package edu.uiowa.slis.ClinicalTrialsTagLib.participantFlowMilestone;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ParticipantFlowMilestoneMileseqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ParticipantFlowMilestoneMileseqnum.class);


	public int doStartTag() throws JspException {
		try {
			ParticipantFlowMilestone theParticipantFlowMilestone = (ParticipantFlowMilestone)findAncestorWithClass(this, ParticipantFlowMilestone.class);
			if (!theParticipantFlowMilestone.commitNeeded) {
				pageContext.getOut().print(theParticipantFlowMilestone.getMileseqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ParticipantFlowMilestone for mileseqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowMilestone for mileseqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getMileseqnum() throws JspTagException {
		try {
			ParticipantFlowMilestone theParticipantFlowMilestone = (ParticipantFlowMilestone)findAncestorWithClass(this, ParticipantFlowMilestone.class);
			return theParticipantFlowMilestone.getMileseqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing ParticipantFlowMilestone for mileseqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowMilestone for mileseqnum tag ");
		}
	}

	public void setMileseqnum(int mileseqnum) throws JspTagException {
		try {
			ParticipantFlowMilestone theParticipantFlowMilestone = (ParticipantFlowMilestone)findAncestorWithClass(this, ParticipantFlowMilestone.class);
			theParticipantFlowMilestone.setMileseqnum(mileseqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing ParticipantFlowMilestone for mileseqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowMilestone for mileseqnum tag ");
		}
	}

}
