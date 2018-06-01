package edu.uiowa.slis.ClinicalTrialsTagLib.participantFlow;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ParticipantFlowID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ParticipantFlowID.class);


	public int doStartTag() throws JspException {
		try {
			ParticipantFlow theParticipantFlow = (ParticipantFlow)findAncestorWithClass(this, ParticipantFlow.class);
			if (!theParticipantFlow.commitNeeded) {
				pageContext.getOut().print(theParticipantFlow.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ParticipantFlow for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlow for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			ParticipantFlow theParticipantFlow = (ParticipantFlow)findAncestorWithClass(this, ParticipantFlow.class);
			return theParticipantFlow.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing ParticipantFlow for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlow for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			ParticipantFlow theParticipantFlow = (ParticipantFlow)findAncestorWithClass(this, ParticipantFlow.class);
			theParticipantFlow.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing ParticipantFlow for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlow for ID tag ");
		}
	}

}
