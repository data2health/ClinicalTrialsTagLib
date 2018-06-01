package edu.uiowa.slis.ClinicalTrialsTagLib.participantFlowGroup;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ParticipantFlowGroupTitle extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ParticipantFlowGroupTitle.class);


	public int doStartTag() throws JspException {
		try {
			ParticipantFlowGroup theParticipantFlowGroup = (ParticipantFlowGroup)findAncestorWithClass(this, ParticipantFlowGroup.class);
			if (!theParticipantFlowGroup.commitNeeded) {
				pageContext.getOut().print(theParticipantFlowGroup.getTitle());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ParticipantFlowGroup for title tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowGroup for title tag ");
		}
		return SKIP_BODY;
	}

	public String getTitle() throws JspTagException {
		try {
			ParticipantFlowGroup theParticipantFlowGroup = (ParticipantFlowGroup)findAncestorWithClass(this, ParticipantFlowGroup.class);
			return theParticipantFlowGroup.getTitle();
		} catch (Exception e) {
			log.error(" Can't find enclosing ParticipantFlowGroup for title tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowGroup for title tag ");
		}
	}

	public void setTitle(String title) throws JspTagException {
		try {
			ParticipantFlowGroup theParticipantFlowGroup = (ParticipantFlowGroup)findAncestorWithClass(this, ParticipantFlowGroup.class);
			theParticipantFlowGroup.setTitle(title);
		} catch (Exception e) {
			log.error("Can't find enclosing ParticipantFlowGroup for title tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowGroup for title tag ");
		}
	}

}
