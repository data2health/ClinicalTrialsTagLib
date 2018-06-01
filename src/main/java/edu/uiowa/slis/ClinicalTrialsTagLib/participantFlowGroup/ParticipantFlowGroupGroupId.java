package edu.uiowa.slis.ClinicalTrialsTagLib.participantFlowGroup;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ParticipantFlowGroupGroupId extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ParticipantFlowGroupGroupId.class);


	public int doStartTag() throws JspException {
		try {
			ParticipantFlowGroup theParticipantFlowGroup = (ParticipantFlowGroup)findAncestorWithClass(this, ParticipantFlowGroup.class);
			if (!theParticipantFlowGroup.commitNeeded) {
				pageContext.getOut().print(theParticipantFlowGroup.getGroupId());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ParticipantFlowGroup for groupId tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowGroup for groupId tag ");
		}
		return SKIP_BODY;
	}

	public String getGroupId() throws JspTagException {
		try {
			ParticipantFlowGroup theParticipantFlowGroup = (ParticipantFlowGroup)findAncestorWithClass(this, ParticipantFlowGroup.class);
			return theParticipantFlowGroup.getGroupId();
		} catch (Exception e) {
			log.error(" Can't find enclosing ParticipantFlowGroup for groupId tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowGroup for groupId tag ");
		}
	}

	public void setGroupId(String groupId) throws JspTagException {
		try {
			ParticipantFlowGroup theParticipantFlowGroup = (ParticipantFlowGroup)findAncestorWithClass(this, ParticipantFlowGroup.class);
			theParticipantFlowGroup.setGroupId(groupId);
		} catch (Exception e) {
			log.error("Can't find enclosing ParticipantFlowGroup for groupId tag ", e);
			throw new JspTagException("Error: Can't find enclosing ParticipantFlowGroup for groupId tag ");
		}
	}

}
