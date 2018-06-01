package edu.uiowa.slis.ClinicalTrialsTagLib.participantFlowPeriod;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ParticipantFlowPeriodTitle extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ParticipantFlowPeriodTitle.class);


	public int doStartTag() throws JspException {
		try {
			ParticipantFlowPeriod theParticipantFlowPeriod = (ParticipantFlowPeriod)findAncestorWithClass(this, ParticipantFlowPeriod.class);
			if (!theParticipantFlowPeriod.commitNeeded) {
				pageContext.getOut().print(theParticipantFlowPeriod.getTitle());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ParticipantFlowPeriod for title tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowPeriod for title tag ");
		}
		return SKIP_BODY;
	}

	public String getTitle() throws JspTagException {
		try {
			ParticipantFlowPeriod theParticipantFlowPeriod = (ParticipantFlowPeriod)findAncestorWithClass(this, ParticipantFlowPeriod.class);
			return theParticipantFlowPeriod.getTitle();
		} catch (Exception e) {
			log.error(" Can't find enclosing ParticipantFlowPeriod for title tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowPeriod for title tag ");
		}
	}

	public void setTitle(String title) throws JspTagException {
		try {
			ParticipantFlowPeriod theParticipantFlowPeriod = (ParticipantFlowPeriod)findAncestorWithClass(this, ParticipantFlowPeriod.class);
			theParticipantFlowPeriod.setTitle(title);
		} catch (Exception e) {
			log.error("Can't find enclosing ParticipantFlowPeriod for title tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowPeriod for title tag ");
		}
	}

}
