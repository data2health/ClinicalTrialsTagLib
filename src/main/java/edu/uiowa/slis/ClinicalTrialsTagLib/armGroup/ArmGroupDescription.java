package edu.uiowa.slis.ClinicalTrialsTagLib.armGroup;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ArmGroupDescription extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ArmGroupDescription.class);


	public int doStartTag() throws JspException {
		try {
			ArmGroup theArmGroup = (ArmGroup)findAncestorWithClass(this, ArmGroup.class);
			if (!theArmGroup.commitNeeded) {
				pageContext.getOut().print(theArmGroup.getDescription());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ArmGroup for description tag ", e);
			throw new JspTagException("Error: Can't find enclosing ArmGroup for description tag ");
		}
		return SKIP_BODY;
	}

	public String getDescription() throws JspTagException {
		try {
			ArmGroup theArmGroup = (ArmGroup)findAncestorWithClass(this, ArmGroup.class);
			return theArmGroup.getDescription();
		} catch (Exception e) {
			log.error(" Can't find enclosing ArmGroup for description tag ", e);
			throw new JspTagException("Error: Can't find enclosing ArmGroup for description tag ");
		}
	}

	public void setDescription(String description) throws JspTagException {
		try {
			ArmGroup theArmGroup = (ArmGroup)findAncestorWithClass(this, ArmGroup.class);
			theArmGroup.setDescription(description);
		} catch (Exception e) {
			log.error("Can't find enclosing ArmGroup for description tag ", e);
			throw new JspTagException("Error: Can't find enclosing ArmGroup for description tag ");
		}
	}

}
