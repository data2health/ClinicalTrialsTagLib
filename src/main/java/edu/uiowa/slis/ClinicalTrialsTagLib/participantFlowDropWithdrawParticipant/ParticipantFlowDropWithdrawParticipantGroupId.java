package edu.uiowa.slis.ClinicalTrialsTagLib.participantFlowDropWithdrawParticipant;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ParticipantFlowDropWithdrawParticipantGroupId extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ParticipantFlowDropWithdrawParticipantGroupId.class);


	public int doStartTag() throws JspException {
		try {
			ParticipantFlowDropWithdrawParticipant theParticipantFlowDropWithdrawParticipant = (ParticipantFlowDropWithdrawParticipant)findAncestorWithClass(this, ParticipantFlowDropWithdrawParticipant.class);
			if (!theParticipantFlowDropWithdrawParticipant.commitNeeded) {
				pageContext.getOut().print(theParticipantFlowDropWithdrawParticipant.getGroupId());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ParticipantFlowDropWithdrawParticipant for groupId tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowDropWithdrawParticipant for groupId tag ");
		}
		return SKIP_BODY;
	}

	public String getGroupId() throws JspTagException {
		try {
			ParticipantFlowDropWithdrawParticipant theParticipantFlowDropWithdrawParticipant = (ParticipantFlowDropWithdrawParticipant)findAncestorWithClass(this, ParticipantFlowDropWithdrawParticipant.class);
			return theParticipantFlowDropWithdrawParticipant.getGroupId();
		} catch (Exception e) {
			log.error(" Can't find enclosing ParticipantFlowDropWithdrawParticipant for groupId tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowDropWithdrawParticipant for groupId tag ");
		}
	}

	public void setGroupId(String groupId) throws JspTagException {
		try {
			ParticipantFlowDropWithdrawParticipant theParticipantFlowDropWithdrawParticipant = (ParticipantFlowDropWithdrawParticipant)findAncestorWithClass(this, ParticipantFlowDropWithdrawParticipant.class);
			theParticipantFlowDropWithdrawParticipant.setGroupId(groupId);
		} catch (Exception e) {
			log.error("Can't find enclosing ParticipantFlowDropWithdrawParticipant for groupId tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowDropWithdrawParticipant for groupId tag ");
		}
	}

}
