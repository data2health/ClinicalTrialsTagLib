package edu.uiowa.slis.ClinicalTrialsTagLib.criteria;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class CriteriaID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(CriteriaID.class);


	public int doStartTag() throws JspException {
		try {
			Criteria theCriteria = (Criteria)findAncestorWithClass(this, Criteria.class);
			if (!theCriteria.commitNeeded) {
				pageContext.getOut().print(theCriteria.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Criteria for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing Criteria for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			Criteria theCriteria = (Criteria)findAncestorWithClass(this, Criteria.class);
			return theCriteria.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing Criteria for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing Criteria for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			Criteria theCriteria = (Criteria)findAncestorWithClass(this, Criteria.class);
			theCriteria.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing Criteria for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing Criteria for ID tag ");
		}
	}

}
