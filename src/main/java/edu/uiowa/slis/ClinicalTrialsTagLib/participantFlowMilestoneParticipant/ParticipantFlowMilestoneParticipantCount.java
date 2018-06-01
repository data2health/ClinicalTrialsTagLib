package edu.uiowa.slis.ClinicalTrialsTagLib.participantFlowMilestoneParticipant;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ParticipantFlowMilestoneParticipantCount extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ParticipantFlowMilestoneParticipantCount.class);


	public int doStartTag() throws JspException {
		try {
			ParticipantFlowMilestoneParticipant theParticipantFlowMilestoneParticipant = (ParticipantFlowMilestoneParticipant)findAncestorWithClass(this, ParticipantFlowMilestoneParticipant.class);
			if (!theParticipantFlowMilestoneParticipant.commitNeeded) {
				pageContext.getOut().print(theParticipantFlowMilestoneParticipant.getCount());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ParticipantFlowMilestoneParticipant for count tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowMilestoneParticipant for count tag ");
		}
		return SKIP_BODY;
	}

	public String getCount() throws JspTagException {
		try {
			ParticipantFlowMilestoneParticipant theParticipantFlowMilestoneParticipant = (ParticipantFlowMilestoneParticipant)findAncestorWithClass(this, ParticipantFlowMilestoneParticipant.class);
			return theParticipantFlowMilestoneParticipant.getCount();
		} catch (Exception e) {
			log.error(" Can't find enclosing ParticipantFlowMilestoneParticipant for count tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowMilestoneParticipant for count tag ");
		}
	}

	public void setCount(String count) throws JspTagException {
		try {
			ParticipantFlowMilestoneParticipant theParticipantFlowMilestoneParticipant = (ParticipantFlowMilestoneParticipant)findAncestorWithClass(this, ParticipantFlowMilestoneParticipant.class);
			theParticipantFlowMilestoneParticipant.setCount(count);
		} catch (Exception e) {
			log.error("Can't find enclosing ParticipantFlowMilestoneParticipant for count tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowMilestoneParticipant for count tag ");
		}
	}

}
