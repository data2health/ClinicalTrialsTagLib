package edu.uiowa.slis.ClinicalTrialsTagLib.conditionBrowse;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ConditionBrowseID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ConditionBrowseID.class);


	public int doStartTag() throws JspException {
		try {
			ConditionBrowse theConditionBrowse = (ConditionBrowse)findAncestorWithClass(this, ConditionBrowse.class);
			if (!theConditionBrowse.commitNeeded) {
				pageContext.getOut().print(theConditionBrowse.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ConditionBrowse for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ConditionBrowse for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			ConditionBrowse theConditionBrowse = (ConditionBrowse)findAncestorWithClass(this, ConditionBrowse.class);
			return theConditionBrowse.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing ConditionBrowse for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ConditionBrowse for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			ConditionBrowse theConditionBrowse = (ConditionBrowse)findAncestorWithClass(this, ConditionBrowse.class);
			theConditionBrowse.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing ConditionBrowse for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ConditionBrowse for ID tag ");
		}
	}

}
