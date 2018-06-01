package edu.uiowa.slis.ClinicalTrialsTagLib.participantFlowDropWithdrawParticipant;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ParticipantFlowDropWithdrawParticipantCount extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ParticipantFlowDropWithdrawParticipantCount.class);


	public int doStartTag() throws JspException {
		try {
			ParticipantFlowDropWithdrawParticipant theParticipantFlowDropWithdrawParticipant = (ParticipantFlowDropWithdrawParticipant)findAncestorWithClass(this, ParticipantFlowDropWithdrawParticipant.class);
			if (!theParticipantFlowDropWithdrawParticipant.commitNeeded) {
				pageContext.getOut().print(theParticipantFlowDropWithdrawParticipant.getCount());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ParticipantFlowDropWithdrawParticipant for count tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowDropWithdrawParticipant for count tag ");
		}
		return SKIP_BODY;
	}

	public String getCount() throws JspTagException {
		try {
			ParticipantFlowDropWithdrawParticipant theParticipantFlowDropWithdrawParticipant = (ParticipantFlowDropWithdrawParticipant)findAncestorWithClass(this, ParticipantFlowDropWithdrawParticipant.class);
			return theParticipantFlowDropWithdrawParticipant.getCount();
		} catch (Exception e) {
			log.error(" Can't find enclosing ParticipantFlowDropWithdrawParticipant for count tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowDropWithdrawParticipant for count tag ");
		}
	}

	public void setCount(String count) throws JspTagException {
		try {
			ParticipantFlowDropWithdrawParticipant theParticipantFlowDropWithdrawParticipant = (ParticipantFlowDropWithdrawParticipant)findAncestorWithClass(this, ParticipantFlowDropWithdrawParticipant.class);
			theParticipantFlowDropWithdrawParticipant.setCount(count);
		} catch (Exception e) {
			log.error("Can't find enclosing ParticipantFlowDropWithdrawParticipant for count tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowDropWithdrawParticipant for count tag ");
		}
	}

}
