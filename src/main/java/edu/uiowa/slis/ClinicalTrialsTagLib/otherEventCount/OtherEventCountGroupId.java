package edu.uiowa.slis.ClinicalTrialsTagLib.otherEventCount;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class OtherEventCountGroupId extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(OtherEventCountGroupId.class);


	public int doStartTag() throws JspException {
		try {
			OtherEventCount theOtherEventCount = (OtherEventCount)findAncestorWithClass(this, OtherEventCount.class);
			if (!theOtherEventCount.commitNeeded) {
				pageContext.getOut().print(theOtherEventCount.getGroupId());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing OtherEventCount for groupId tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherEventCount for groupId tag ");
		}
		return SKIP_BODY;
	}

	public String getGroupId() throws JspTagException {
		try {
			OtherEventCount theOtherEventCount = (OtherEventCount)findAncestorWithClass(this, OtherEventCount.class);
			return theOtherEventCount.getGroupId();
		} catch (Exception e) {
			log.error(" Can't find enclosing OtherEventCount for groupId tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherEventCount for groupId tag ");
		}
	}

	public void setGroupId(String groupId) throws JspTagException {
		try {
			OtherEventCount theOtherEventCount = (OtherEventCount)findAncestorWithClass(this, OtherEventCount.class);
			theOtherEventCount.setGroupId(groupId);
		} catch (Exception e) {
			log.error("Can't find enclosing OtherEventCount for groupId tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherEventCount for groupId tag ");
		}
	}

}
