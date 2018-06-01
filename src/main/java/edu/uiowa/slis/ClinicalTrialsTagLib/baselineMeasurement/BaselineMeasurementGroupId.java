package edu.uiowa.slis.ClinicalTrialsTagLib.baselineMeasurement;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class BaselineMeasurementGroupId extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(BaselineMeasurementGroupId.class);


	public int doStartTag() throws JspException {
		try {
			BaselineMeasurement theBaselineMeasurement = (BaselineMeasurement)findAncestorWithClass(this, BaselineMeasurement.class);
			if (!theBaselineMeasurement.commitNeeded) {
				pageContext.getOut().print(theBaselineMeasurement.getGroupId());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing BaselineMeasurement for groupId tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineMeasurement for groupId tag ");
		}
		return SKIP_BODY;
	}

	public String getGroupId() throws JspTagException {
		try {
			BaselineMeasurement theBaselineMeasurement = (BaselineMeasurement)findAncestorWithClass(this, BaselineMeasurement.class);
			return theBaselineMeasurement.getGroupId();
		} catch (Exception e) {
			log.error(" Can't find enclosing BaselineMeasurement for groupId tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineMeasurement for groupId tag ");
		}
	}

	public void setGroupId(String groupId) throws JspTagException {
		try {
			BaselineMeasurement theBaselineMeasurement = (BaselineMeasurement)findAncestorWithClass(this, BaselineMeasurement.class);
			theBaselineMeasurement.setGroupId(groupId);
		} catch (Exception e) {
			log.error("Can't find enclosing BaselineMeasurement for groupId tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineMeasurement for groupId tag ");
		}
	}

}
