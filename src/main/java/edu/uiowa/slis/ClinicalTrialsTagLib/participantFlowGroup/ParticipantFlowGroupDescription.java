package edu.uiowa.slis.ClinicalTrialsTagLib.participantFlowGroup;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ParticipantFlowGroupDescription extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ParticipantFlowGroupDescription.class);


	public int doStartTag() throws JspException {
		try {
			ParticipantFlowGroup theParticipantFlowGroup = (ParticipantFlowGroup)findAncestorWithClass(this, ParticipantFlowGroup.class);
			if (!theParticipantFlowGroup.commitNeeded) {
				pageContext.getOut().print(theParticipantFlowGroup.getDescription());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ParticipantFlowGroup for description tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowGroup for description tag ");
		}
		return SKIP_BODY;
	}

	public String getDescription() throws JspTagException {
		try {
			ParticipantFlowGroup theParticipantFlowGroup = (ParticipantFlowGroup)findAncestorWithClass(this, ParticipantFlowGroup.class);
			return theParticipantFlowGroup.getDescription();
		} catch (Exception e) {
			log.error(" Can't find enclosing ParticipantFlowGroup for description tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowGroup for description tag ");
		}
	}

	public void setDescription(String description) throws JspTagException {
		try {
			ParticipantFlowGroup theParticipantFlowGroup = (ParticipantFlowGroup)findAncestorWithClass(this, ParticipantFlowGroup.class);
			theParticipantFlowGroup.setDescription(description);
		} catch (Exception e) {
			log.error("Can't find enclosing ParticipantFlowGroup for description tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowGroup for description tag ");
		}
	}

}
