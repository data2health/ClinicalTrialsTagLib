package edu.uiowa.slis.ClinicalTrialsTagLib.conditionBrowse;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ConditionBrowseMesh extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ConditionBrowseMesh.class);


	public int doStartTag() throws JspException {
		try {
			ConditionBrowse theConditionBrowse = (ConditionBrowse)findAncestorWithClass(this, ConditionBrowse.class);
			if (!theConditionBrowse.commitNeeded) {
				pageContext.getOut().print(theConditionBrowse.getMesh());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ConditionBrowse for mesh tag ", e);
			throw new JspTagException("Error: Can't find enclosing ConditionBrowse for mesh tag ");
		}
		return SKIP_BODY;
	}

	public String getMesh() throws JspTagException {
		try {
			ConditionBrowse theConditionBrowse = (ConditionBrowse)findAncestorWithClass(this, ConditionBrowse.class);
			return theConditionBrowse.getMesh();
		} catch (Exception e) {
			log.error(" Can't find enclosing ConditionBrowse for mesh tag ", e);
			throw new JspTagException("Error: Can't find enclosing ConditionBrowse for mesh tag ");
		}
	}

	public void setMesh(String mesh) throws JspTagException {
		try {
			ConditionBrowse theConditionBrowse = (ConditionBrowse)findAncestorWithClass(this, ConditionBrowse.class);
			theConditionBrowse.setMesh(mesh);
		} catch (Exception e) {
			log.error("Can't find enclosing ConditionBrowse for mesh tag ", e);
			throw new JspTagException("Error: Can't find enclosing ConditionBrowse for mesh tag ");
		}
	}

}
