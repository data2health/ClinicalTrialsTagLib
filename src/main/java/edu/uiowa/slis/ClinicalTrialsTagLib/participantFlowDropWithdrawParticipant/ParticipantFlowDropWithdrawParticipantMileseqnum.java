package edu.uiowa.slis.ClinicalTrialsTagLib.participantFlowDropWithdrawParticipant;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ParticipantFlowDropWithdrawParticipantMileseqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ParticipantFlowDropWithdrawParticipantMileseqnum.class);


	public int doStartTag() throws JspException {
		try {
			ParticipantFlowDropWithdrawParticipant theParticipantFlowDropWithdrawParticipant = (ParticipantFlowDropWithdrawParticipant)findAncestorWithClass(this, ParticipantFlowDropWithdrawParticipant.class);
			if (!theParticipantFlowDropWithdrawParticipant.commitNeeded) {
				pageContext.getOut().print(theParticipantFlowDropWithdrawParticipant.getMileseqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ParticipantFlowDropWithdrawParticipant for mileseqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowDropWithdrawParticipant for mileseqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getMileseqnum() throws JspTagException {
		try {
			ParticipantFlowDropWithdrawParticipant theParticipantFlowDropWithdrawParticipant = (ParticipantFlowDropWithdrawParticipant)findAncestorWithClass(this, ParticipantFlowDropWithdrawParticipant.class);
			return theParticipantFlowDropWithdrawParticipant.getMileseqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing ParticipantFlowDropWithdrawParticipant for mileseqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowDropWithdrawParticipant for mileseqnum tag ");
		}
	}

	public void setMileseqnum(int mileseqnum) throws JspTagException {
		try {
			ParticipantFlowDropWithdrawParticipant theParticipantFlowDropWithdrawParticipant = (ParticipantFlowDropWithdrawParticipant)findAncestorWithClass(this, ParticipantFlowDropWithdrawParticipant.class);
			theParticipantFlowDropWithdrawParticipant.setMileseqnum(mileseqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing ParticipantFlowDropWithdrawParticipant for mileseqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowDropWithdrawParticipant for mileseqnum tag ");
		}
	}

}
