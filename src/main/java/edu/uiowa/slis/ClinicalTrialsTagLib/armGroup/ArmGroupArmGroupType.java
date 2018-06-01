package edu.uiowa.slis.ClinicalTrialsTagLib.armGroup;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ArmGroupArmGroupType extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ArmGroupArmGroupType.class);


	public int doStartTag() throws JspException {
		try {
			ArmGroup theArmGroup = (ArmGroup)findAncestorWithClass(this, ArmGroup.class);
			if (!theArmGroup.commitNeeded) {
				pageContext.getOut().print(theArmGroup.getArmGroupType());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ArmGroup for armGroupType tag ", e);
			throw new JspTagException("Error: Can't find enclosing ArmGroup for armGroupType tag ");
		}
		return SKIP_BODY;
	}

	public String getArmGroupType() throws JspTagException {
		try {
			ArmGroup theArmGroup = (ArmGroup)findAncestorWithClass(this, ArmGroup.class);
			return theArmGroup.getArmGroupType();
		} catch (Exception e) {
			log.error(" Can't find enclosing ArmGroup for armGroupType tag ", e);
			throw new JspTagException("Error: Can't find enclosing ArmGroup for armGroupType tag ");
		}
	}

	public void setArmGroupType(String armGroupType) throws JspTagException {
		try {
			ArmGroup theArmGroup = (ArmGroup)findAncestorWithClass(this, ArmGroup.class);
			theArmGroup.setArmGroupType(armGroupType);
		} catch (Exception e) {
			log.error("Can't find enclosing ArmGroup for armGroupType tag ", e);
			throw new JspTagException("Error: Can't find enclosing ArmGroup for armGroupType tag ");
		}
	}

}
