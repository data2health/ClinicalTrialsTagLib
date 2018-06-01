package edu.uiowa.slis.ClinicalTrialsTagLib.resultsMeasurement;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsMeasurementGroupId extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsMeasurementGroupId.class);


	public int doStartTag() throws JspException {
		try {
			ResultsMeasurement theResultsMeasurement = (ResultsMeasurement)findAncestorWithClass(this, ResultsMeasurement.class);
			if (!theResultsMeasurement.commitNeeded) {
				pageContext.getOut().print(theResultsMeasurement.getGroupId());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsMeasurement for groupId tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsMeasurement for groupId tag ");
		}
		return SKIP_BODY;
	}

	public String getGroupId() throws JspTagException {
		try {
			ResultsMeasurement theResultsMeasurement = (ResultsMeasurement)findAncestorWithClass(this, ResultsMeasurement.class);
			return theResultsMeasurement.getGroupId();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsMeasurement for groupId tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsMeasurement for groupId tag ");
		}
	}

	public void setGroupId(String groupId) throws JspTagException {
		try {
			ResultsMeasurement theResultsMeasurement = (ResultsMeasurement)findAncestorWithClass(this, ResultsMeasurement.class);
			theResultsMeasurement.setGroupId(groupId);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsMeasurement for groupId tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsMeasurement for groupId tag ");
		}
	}

}
