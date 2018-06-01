package edu.uiowa.slis.ClinicalTrialsTagLib.participantFlowDropWithdrawParticipant;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ParticipantFlowDropWithdrawParticipantSeqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ParticipantFlowDropWithdrawParticipantSeqnum.class);


	public int doStartTag() throws JspException {
		try {
			ParticipantFlowDropWithdrawParticipant theParticipantFlowDropWithdrawParticipant = (ParticipantFlowDropWithdrawParticipant)findAncestorWithClass(this, ParticipantFlowDropWithdrawParticipant.class);
			if (!theParticipantFlowDropWithdrawParticipant.commitNeeded) {
				pageContext.getOut().print(theParticipantFlowDropWithdrawParticipant.getSeqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ParticipantFlowDropWithdrawParticipant for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowDropWithdrawParticipant for seqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getSeqnum() throws JspTagException {
		try {
			ParticipantFlowDropWithdrawParticipant theParticipantFlowDropWithdrawParticipant = (ParticipantFlowDropWithdrawParticipant)findAncestorWithClass(this, ParticipantFlowDropWithdrawParticipant.class);
			return theParticipantFlowDropWithdrawParticipant.getSeqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing ParticipantFlowDropWithdrawParticipant for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowDropWithdrawParticipant for seqnum tag ");
		}
	}

	public void setSeqnum(int seqnum) throws JspTagException {
		try {
			ParticipantFlowDropWithdrawParticipant theParticipantFlowDropWithdrawParticipant = (ParticipantFlowDropWithdrawParticipant)findAncestorWithClass(this, ParticipantFlowDropWithdrawParticipant.class);
			theParticipantFlowDropWithdrawParticipant.setSeqnum(seqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing ParticipantFlowDropWithdrawParticipant for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowDropWithdrawParticipant for seqnum tag ");
		}
	}

}
