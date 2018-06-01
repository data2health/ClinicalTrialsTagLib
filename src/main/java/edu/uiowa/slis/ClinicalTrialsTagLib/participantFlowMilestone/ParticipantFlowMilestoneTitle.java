package edu.uiowa.slis.ClinicalTrialsTagLib.participantFlowMilestone;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ParticipantFlowMilestoneTitle extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ParticipantFlowMilestoneTitle.class);


	public int doStartTag() throws JspException {
		try {
			ParticipantFlowMilestone theParticipantFlowMilestone = (ParticipantFlowMilestone)findAncestorWithClass(this, ParticipantFlowMilestone.class);
			if (!theParticipantFlowMilestone.commitNeeded) {
				pageContext.getOut().print(theParticipantFlowMilestone.getTitle());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ParticipantFlowMilestone for title tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowMilestone for title tag ");
		}
		return SKIP_BODY;
	}

	public String getTitle() throws JspTagException {
		try {
			ParticipantFlowMilestone theParticipantFlowMilestone = (ParticipantFlowMilestone)findAncestorWithClass(this, ParticipantFlowMilestone.class);
			return theParticipantFlowMilestone.getTitle();
		} catch (Exception e) {
			log.error(" Can't find enclosing ParticipantFlowMilestone for title tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowMilestone for title tag ");
		}
	}

	public void setTitle(String title) throws JspTagException {
		try {
			ParticipantFlowMilestone theParticipantFlowMilestone = (ParticipantFlowMilestone)findAncestorWithClass(this, ParticipantFlowMilestone.class);
			theParticipantFlowMilestone.setTitle(title);
		} catch (Exception e) {
			log.error("Can't find enclosing ParticipantFlowMilestone for title tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowMilestone for title tag ");
		}
	}

}
