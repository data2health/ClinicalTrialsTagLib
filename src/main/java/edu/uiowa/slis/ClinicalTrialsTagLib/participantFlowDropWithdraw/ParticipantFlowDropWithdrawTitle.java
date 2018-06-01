package edu.uiowa.slis.ClinicalTrialsTagLib.participantFlowDropWithdraw;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ParticipantFlowDropWithdrawTitle extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ParticipantFlowDropWithdrawTitle.class);


	public int doStartTag() throws JspException {
		try {
			ParticipantFlowDropWithdraw theParticipantFlowDropWithdraw = (ParticipantFlowDropWithdraw)findAncestorWithClass(this, ParticipantFlowDropWithdraw.class);
			if (!theParticipantFlowDropWithdraw.commitNeeded) {
				pageContext.getOut().print(theParticipantFlowDropWithdraw.getTitle());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ParticipantFlowDropWithdraw for title tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowDropWithdraw for title tag ");
		}
		return SKIP_BODY;
	}

	public String getTitle() throws JspTagException {
		try {
			ParticipantFlowDropWithdraw theParticipantFlowDropWithdraw = (ParticipantFlowDropWithdraw)findAncestorWithClass(this, ParticipantFlowDropWithdraw.class);
			return theParticipantFlowDropWithdraw.getTitle();
		} catch (Exception e) {
			log.error(" Can't find enclosing ParticipantFlowDropWithdraw for title tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowDropWithdraw for title tag ");
		}
	}

	public void setTitle(String title) throws JspTagException {
		try {
			ParticipantFlowDropWithdraw theParticipantFlowDropWithdraw = (ParticipantFlowDropWithdraw)findAncestorWithClass(this, ParticipantFlowDropWithdraw.class);
			theParticipantFlowDropWithdraw.setTitle(title);
		} catch (Exception e) {
			log.error("Can't find enclosing ParticipantFlowDropWithdraw for title tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowDropWithdraw for title tag ");
		}
	}

}
