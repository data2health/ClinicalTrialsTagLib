package edu.uiowa.slis.ClinicalTrialsTagLib.baselineGroup;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class BaselineGroupID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(BaselineGroupID.class);


	public int doStartTag() throws JspException {
		try {
			BaselineGroup theBaselineGroup = (BaselineGroup)findAncestorWithClass(this, BaselineGroup.class);
			if (!theBaselineGroup.commitNeeded) {
				pageContext.getOut().print(theBaselineGroup.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing BaselineGroup for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineGroup for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			BaselineGroup theBaselineGroup = (BaselineGroup)findAncestorWithClass(this, BaselineGroup.class);
			return theBaselineGroup.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing BaselineGroup for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineGroup for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			BaselineGroup theBaselineGroup = (BaselineGroup)findAncestorWithClass(this, BaselineGroup.class);
			theBaselineGroup.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing BaselineGroup for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineGroup for ID tag ");
		}
	}

}
