package edu.uiowa.slis.ClinicalTrialsTagLib.armGroup;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ArmGroupID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ArmGroupID.class);


	public int doStartTag() throws JspException {
		try {
			ArmGroup theArmGroup = (ArmGroup)findAncestorWithClass(this, ArmGroup.class);
			if (!theArmGroup.commitNeeded) {
				pageContext.getOut().print(theArmGroup.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ArmGroup for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ArmGroup for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			ArmGroup theArmGroup = (ArmGroup)findAncestorWithClass(this, ArmGroup.class);
			return theArmGroup.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing ArmGroup for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ArmGroup for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			ArmGroup theArmGroup = (ArmGroup)findAncestorWithClass(this, ArmGroup.class);
			theArmGroup.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing ArmGroup for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ArmGroup for ID tag ");
		}
	}

}
