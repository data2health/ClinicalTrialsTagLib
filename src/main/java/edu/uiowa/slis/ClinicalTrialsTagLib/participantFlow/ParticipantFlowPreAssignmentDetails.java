package edu.uiowa.slis.ClinicalTrialsTagLib.participantFlow;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ParticipantFlowPreAssignmentDetails extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ParticipantFlowPreAssignmentDetails.class);


	public int doStartTag() throws JspException {
		try {
			ParticipantFlow theParticipantFlow = (ParticipantFlow)findAncestorWithClass(this, ParticipantFlow.class);
			if (!theParticipantFlow.commitNeeded) {
				pageContext.getOut().print(theParticipantFlow.getPreAssignmentDetails());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ParticipantFlow for preAssignmentDetails tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlow for preAssignmentDetails tag ");
		}
		return SKIP_BODY;
	}

	public String getPreAssignmentDetails() throws JspTagException {
		try {
			ParticipantFlow theParticipantFlow = (ParticipantFlow)findAncestorWithClass(this, ParticipantFlow.class);
			return theParticipantFlow.getPreAssignmentDetails();
		} catch (Exception e) {
			log.error(" Can't find enclosing ParticipantFlow for preAssignmentDetails tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlow for preAssignmentDetails tag ");
		}
	}

	public void setPreAssignmentDetails(String preAssignmentDetails) throws JspTagException {
		try {
			ParticipantFlow theParticipantFlow = (ParticipantFlow)findAncestorWithClass(this, ParticipantFlow.class);
			theParticipantFlow.setPreAssignmentDetails(preAssignmentDetails);
		} catch (Exception e) {
			log.error("Can't find enclosing ParticipantFlow for preAssignmentDetails tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlow for preAssignmentDetails tag ");
		}
	}

}
