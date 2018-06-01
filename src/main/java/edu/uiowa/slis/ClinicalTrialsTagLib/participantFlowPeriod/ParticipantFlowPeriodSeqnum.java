package edu.uiowa.slis.ClinicalTrialsTagLib.participantFlowPeriod;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ParticipantFlowPeriodSeqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ParticipantFlowPeriodSeqnum.class);


	public int doStartTag() throws JspException {
		try {
			ParticipantFlowPeriod theParticipantFlowPeriod = (ParticipantFlowPeriod)findAncestorWithClass(this, ParticipantFlowPeriod.class);
			if (!theParticipantFlowPeriod.commitNeeded) {
				pageContext.getOut().print(theParticipantFlowPeriod.getSeqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ParticipantFlowPeriod for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowPeriod for seqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getSeqnum() throws JspTagException {
		try {
			ParticipantFlowPeriod theParticipantFlowPeriod = (ParticipantFlowPeriod)findAncestorWithClass(this, ParticipantFlowPeriod.class);
			return theParticipantFlowPeriod.getSeqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing ParticipantFlowPeriod for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowPeriod for seqnum tag ");
		}
	}

	public void setSeqnum(int seqnum) throws JspTagException {
		try {
			ParticipantFlowPeriod theParticipantFlowPeriod = (ParticipantFlowPeriod)findAncestorWithClass(this, ParticipantFlowPeriod.class);
			theParticipantFlowPeriod.setSeqnum(seqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing ParticipantFlowPeriod for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowPeriod for seqnum tag ");
		}
	}

}
