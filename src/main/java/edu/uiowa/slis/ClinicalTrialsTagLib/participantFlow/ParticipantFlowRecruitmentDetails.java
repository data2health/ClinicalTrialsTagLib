package edu.uiowa.slis.ClinicalTrialsTagLib.participantFlow;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ParticipantFlowRecruitmentDetails extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ParticipantFlowRecruitmentDetails.class);


	public int doStartTag() throws JspException {
		try {
			ParticipantFlow theParticipantFlow = (ParticipantFlow)findAncestorWithClass(this, ParticipantFlow.class);
			if (!theParticipantFlow.commitNeeded) {
				pageContext.getOut().print(theParticipantFlow.getRecruitmentDetails());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ParticipantFlow for recruitmentDetails tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlow for recruitmentDetails tag ");
		}
		return SKIP_BODY;
	}

	public String getRecruitmentDetails() throws JspTagException {
		try {
			ParticipantFlow theParticipantFlow = (ParticipantFlow)findAncestorWithClass(this, ParticipantFlow.class);
			return theParticipantFlow.getRecruitmentDetails();
		} catch (Exception e) {
			log.error(" Can't find enclosing ParticipantFlow for recruitmentDetails tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlow for recruitmentDetails tag ");
		}
	}

	public void setRecruitmentDetails(String recruitmentDetails) throws JspTagException {
		try {
			ParticipantFlow theParticipantFlow = (ParticipantFlow)findAncestorWithClass(this, ParticipantFlow.class);
			theParticipantFlow.setRecruitmentDetails(recruitmentDetails);
		} catch (Exception e) {
			log.error("Can't find enclosing ParticipantFlow for recruitmentDetails tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlow for recruitmentDetails tag ");
		}
	}

}
