package edu.uiowa.slis.ClinicalTrialsTagLib.armGroup;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ArmGroupArmGroupLabel extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ArmGroupArmGroupLabel.class);


	public int doStartTag() throws JspException {
		try {
			ArmGroup theArmGroup = (ArmGroup)findAncestorWithClass(this, ArmGroup.class);
			if (!theArmGroup.commitNeeded) {
				pageContext.getOut().print(theArmGroup.getArmGroupLabel());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ArmGroup for armGroupLabel tag ", e);
			throw new JspTagException("Error: Can't find enclosing ArmGroup for armGroupLabel tag ");
		}
		return SKIP_BODY;
	}

	public String getArmGroupLabel() throws JspTagException {
		try {
			ArmGroup theArmGroup = (ArmGroup)findAncestorWithClass(this, ArmGroup.class);
			return theArmGroup.getArmGroupLabel();
		} catch (Exception e) {
			log.error(" Can't find enclosing ArmGroup for armGroupLabel tag ", e);
			throw new JspTagException("Error: Can't find enclosing ArmGroup for armGroupLabel tag ");
		}
	}

	public void setArmGroupLabel(String armGroupLabel) throws JspTagException {
		try {
			ArmGroup theArmGroup = (ArmGroup)findAncestorWithClass(this, ArmGroup.class);
			theArmGroup.setArmGroupLabel(armGroupLabel);
		} catch (Exception e) {
			log.error("Can't find enclosing ArmGroup for armGroupLabel tag ", e);
			throw new JspTagException("Error: Can't find enclosing ArmGroup for armGroupLabel tag ");
		}
	}

}
