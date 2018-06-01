package edu.uiowa.slis.ClinicalTrialsTagLib.baselineGroup;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class BaselineGroupGroupId extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(BaselineGroupGroupId.class);


	public int doStartTag() throws JspException {
		try {
			BaselineGroup theBaselineGroup = (BaselineGroup)findAncestorWithClass(this, BaselineGroup.class);
			if (!theBaselineGroup.commitNeeded) {
				pageContext.getOut().print(theBaselineGroup.getGroupId());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing BaselineGroup for groupId tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineGroup for groupId tag ");
		}
		return SKIP_BODY;
	}

	public String getGroupId() throws JspTagException {
		try {
			BaselineGroup theBaselineGroup = (BaselineGroup)findAncestorWithClass(this, BaselineGroup.class);
			return theBaselineGroup.getGroupId();
		} catch (Exception e) {
			log.error(" Can't find enclosing BaselineGroup for groupId tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineGroup for groupId tag ");
		}
	}

	public void setGroupId(String groupId) throws JspTagException {
		try {
			BaselineGroup theBaselineGroup = (BaselineGroup)findAncestorWithClass(this, BaselineGroup.class);
			theBaselineGroup.setGroupId(groupId);
		} catch (Exception e) {
			log.error("Can't find enclosing BaselineGroup for groupId tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineGroup for groupId tag ");
		}
	}

}
