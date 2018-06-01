package edu.uiowa.slis.ClinicalTrialsTagLib.participantFlowDropWithdraw;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ParticipantFlowDropWithdrawSeqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ParticipantFlowDropWithdrawSeqnum.class);


	public int doStartTag() throws JspException {
		try {
			ParticipantFlowDropWithdraw theParticipantFlowDropWithdraw = (ParticipantFlowDropWithdraw)findAncestorWithClass(this, ParticipantFlowDropWithdraw.class);
			if (!theParticipantFlowDropWithdraw.commitNeeded) {
				pageContext.getOut().print(theParticipantFlowDropWithdraw.getSeqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ParticipantFlowDropWithdraw for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowDropWithdraw for seqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getSeqnum() throws JspTagException {
		try {
			ParticipantFlowDropWithdraw theParticipantFlowDropWithdraw = (ParticipantFlowDropWithdraw)findAncestorWithClass(this, ParticipantFlowDropWithdraw.class);
			return theParticipantFlowDropWithdraw.getSeqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing ParticipantFlowDropWithdraw for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowDropWithdraw for seqnum tag ");
		}
	}

	public void setSeqnum(int seqnum) throws JspTagException {
		try {
			ParticipantFlowDropWithdraw theParticipantFlowDropWithdraw = (ParticipantFlowDropWithdraw)findAncestorWithClass(this, ParticipantFlowDropWithdraw.class);
			theParticipantFlowDropWithdraw.setSeqnum(seqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing ParticipantFlowDropWithdraw for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowDropWithdraw for seqnum tag ");
		}
	}

}
