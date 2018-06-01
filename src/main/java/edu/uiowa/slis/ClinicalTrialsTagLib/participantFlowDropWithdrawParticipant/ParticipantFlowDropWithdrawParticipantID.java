package edu.uiowa.slis.ClinicalTrialsTagLib.participantFlowDropWithdrawParticipant;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ParticipantFlowDropWithdrawParticipantID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ParticipantFlowDropWithdrawParticipantID.class);


	public int doStartTag() throws JspException {
		try {
			ParticipantFlowDropWithdrawParticipant theParticipantFlowDropWithdrawParticipant = (ParticipantFlowDropWithdrawParticipant)findAncestorWithClass(this, ParticipantFlowDropWithdrawParticipant.class);
			if (!theParticipantFlowDropWithdrawParticipant.commitNeeded) {
				pageContext.getOut().print(theParticipantFlowDropWithdrawParticipant.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ParticipantFlowDropWithdrawParticipant for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowDropWithdrawParticipant for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			ParticipantFlowDropWithdrawParticipant theParticipantFlowDropWithdrawParticipant = (ParticipantFlowDropWithdrawParticipant)findAncestorWithClass(this, ParticipantFlowDropWithdrawParticipant.class);
			return theParticipantFlowDropWithdrawParticipant.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing ParticipantFlowDropWithdrawParticipant for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowDropWithdrawParticipant for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			ParticipantFlowDropWithdrawParticipant theParticipantFlowDropWithdrawParticipant = (ParticipantFlowDropWithdrawParticipant)findAncestorWithClass(this, ParticipantFlowDropWithdrawParticipant.class);
			theParticipantFlowDropWithdrawParticipant.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing ParticipantFlowDropWithdrawParticipant for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowDropWithdrawParticipant for ID tag ");
		}
	}

}
