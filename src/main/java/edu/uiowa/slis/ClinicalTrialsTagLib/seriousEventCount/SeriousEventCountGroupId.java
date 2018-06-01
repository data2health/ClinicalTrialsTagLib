package edu.uiowa.slis.ClinicalTrialsTagLib.seriousEventCount;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class SeriousEventCountGroupId extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(SeriousEventCountGroupId.class);


	public int doStartTag() throws JspException {
		try {
			SeriousEventCount theSeriousEventCount = (SeriousEventCount)findAncestorWithClass(this, SeriousEventCount.class);
			if (!theSeriousEventCount.commitNeeded) {
				pageContext.getOut().print(theSeriousEventCount.getGroupId());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing SeriousEventCount for groupId tag ", e);
			throw new JspTagException("Error: Can't find enclosing SeriousEventCount for groupId tag ");
		}
		return SKIP_BODY;
	}

	public String getGroupId() throws JspTagException {
		try {
			SeriousEventCount theSeriousEventCount = (SeriousEventCount)findAncestorWithClass(this, SeriousEventCount.class);
			return theSeriousEventCount.getGroupId();
		} catch (Exception e) {
			log.error(" Can't find enclosing SeriousEventCount for groupId tag ", e);
			throw new JspTagException("Error: Can't find enclosing SeriousEventCount for groupId tag ");
		}
	}

	public void setGroupId(String groupId) throws JspTagException {
		try {
			SeriousEventCount theSeriousEventCount = (SeriousEventCount)findAncestorWithClass(this, SeriousEventCount.class);
			theSeriousEventCount.setGroupId(groupId);
		} catch (Exception e) {
			log.error("Can't find enclosing SeriousEventCount for groupId tag ", e);
			throw new JspTagException("Error: Can't find enclosing SeriousEventCount for groupId tag ");
		}
	}

}
