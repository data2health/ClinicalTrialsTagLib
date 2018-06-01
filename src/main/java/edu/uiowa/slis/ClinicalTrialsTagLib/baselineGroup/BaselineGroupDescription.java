package edu.uiowa.slis.ClinicalTrialsTagLib.baselineGroup;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class BaselineGroupDescription extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(BaselineGroupDescription.class);


	public int doStartTag() throws JspException {
		try {
			BaselineGroup theBaselineGroup = (BaselineGroup)findAncestorWithClass(this, BaselineGroup.class);
			if (!theBaselineGroup.commitNeeded) {
				pageContext.getOut().print(theBaselineGroup.getDescription());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing BaselineGroup for description tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineGroup for description tag ");
		}
		return SKIP_BODY;
	}

	public String getDescription() throws JspTagException {
		try {
			BaselineGroup theBaselineGroup = (BaselineGroup)findAncestorWithClass(this, BaselineGroup.class);
			return theBaselineGroup.getDescription();
		} catch (Exception e) {
			log.error(" Can't find enclosing BaselineGroup for description tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineGroup for description tag ");
		}
	}

	public void setDescription(String description) throws JspTagException {
		try {
			BaselineGroup theBaselineGroup = (BaselineGroup)findAncestorWithClass(this, BaselineGroup.class);
			theBaselineGroup.setDescription(description);
		} catch (Exception e) {
			log.error("Can't find enclosing BaselineGroup for description tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineGroup for description tag ");
		}
	}

}
