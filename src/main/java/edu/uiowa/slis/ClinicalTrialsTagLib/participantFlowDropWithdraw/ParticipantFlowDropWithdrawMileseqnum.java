package edu.uiowa.slis.ClinicalTrialsTagLib.participantFlowDropWithdraw;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ParticipantFlowDropWithdrawMileseqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ParticipantFlowDropWithdrawMileseqnum.class);


	public int doStartTag() throws JspException {
		try {
			ParticipantFlowDropWithdraw theParticipantFlowDropWithdraw = (ParticipantFlowDropWithdraw)findAncestorWithClass(this, ParticipantFlowDropWithdraw.class);
			if (!theParticipantFlowDropWithdraw.commitNeeded) {
				pageContext.getOut().print(theParticipantFlowDropWithdraw.getMileseqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ParticipantFlowDropWithdraw for mileseqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowDropWithdraw for mileseqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getMileseqnum() throws JspTagException {
		try {
			ParticipantFlowDropWithdraw theParticipantFlowDropWithdraw = (ParticipantFlowDropWithdraw)findAncestorWithClass(this, ParticipantFlowDropWithdraw.class);
			return theParticipantFlowDropWithdraw.getMileseqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing ParticipantFlowDropWithdraw for mileseqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowDropWithdraw for mileseqnum tag ");
		}
	}

	public void setMileseqnum(int mileseqnum) throws JspTagException {
		try {
			ParticipantFlowDropWithdraw theParticipantFlowDropWithdraw = (ParticipantFlowDropWithdraw)findAncestorWithClass(this, ParticipantFlowDropWithdraw.class);
			theParticipantFlowDropWithdraw.setMileseqnum(mileseqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing ParticipantFlowDropWithdraw for mileseqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowDropWithdraw for mileseqnum tag ");
		}
	}

}
