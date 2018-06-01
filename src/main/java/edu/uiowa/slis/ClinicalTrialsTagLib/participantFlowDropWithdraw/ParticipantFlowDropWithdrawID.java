package edu.uiowa.slis.ClinicalTrialsTagLib.participantFlowDropWithdraw;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ParticipantFlowDropWithdrawID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ParticipantFlowDropWithdrawID.class);


	public int doStartTag() throws JspException {
		try {
			ParticipantFlowDropWithdraw theParticipantFlowDropWithdraw = (ParticipantFlowDropWithdraw)findAncestorWithClass(this, ParticipantFlowDropWithdraw.class);
			if (!theParticipantFlowDropWithdraw.commitNeeded) {
				pageContext.getOut().print(theParticipantFlowDropWithdraw.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ParticipantFlowDropWithdraw for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowDropWithdraw for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			ParticipantFlowDropWithdraw theParticipantFlowDropWithdraw = (ParticipantFlowDropWithdraw)findAncestorWithClass(this, ParticipantFlowDropWithdraw.class);
			return theParticipantFlowDropWithdraw.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing ParticipantFlowDropWithdraw for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowDropWithdraw for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			ParticipantFlowDropWithdraw theParticipantFlowDropWithdraw = (ParticipantFlowDropWithdraw)findAncestorWithClass(this, ParticipantFlowDropWithdraw.class);
			theParticipantFlowDropWithdraw.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing ParticipantFlowDropWithdraw for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowDropWithdraw for ID tag ");
		}
	}

}
