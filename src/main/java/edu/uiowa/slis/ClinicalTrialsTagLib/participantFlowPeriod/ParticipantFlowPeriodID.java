package edu.uiowa.slis.ClinicalTrialsTagLib.participantFlowPeriod;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ParticipantFlowPeriodID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ParticipantFlowPeriodID.class);


	public int doStartTag() throws JspException {
		try {
			ParticipantFlowPeriod theParticipantFlowPeriod = (ParticipantFlowPeriod)findAncestorWithClass(this, ParticipantFlowPeriod.class);
			if (!theParticipantFlowPeriod.commitNeeded) {
				pageContext.getOut().print(theParticipantFlowPeriod.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ParticipantFlowPeriod for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowPeriod for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			ParticipantFlowPeriod theParticipantFlowPeriod = (ParticipantFlowPeriod)findAncestorWithClass(this, ParticipantFlowPeriod.class);
			return theParticipantFlowPeriod.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing ParticipantFlowPeriod for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowPeriod for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			ParticipantFlowPeriod theParticipantFlowPeriod = (ParticipantFlowPeriod)findAncestorWithClass(this, ParticipantFlowPeriod.class);
			theParticipantFlowPeriod.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing ParticipantFlowPeriod for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowPeriod for ID tag ");
		}
	}

}
