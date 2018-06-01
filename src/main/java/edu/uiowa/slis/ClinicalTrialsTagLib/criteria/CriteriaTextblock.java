package edu.uiowa.slis.ClinicalTrialsTagLib.criteria;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class CriteriaTextblock extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(CriteriaTextblock.class);


	public int doStartTag() throws JspException {
		try {
			Criteria theCriteria = (Criteria)findAncestorWithClass(this, Criteria.class);
			if (!theCriteria.commitNeeded) {
				pageContext.getOut().print(theCriteria.getTextblock());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Criteria for textblock tag ", e);
			throw new JspTagException("Error: Can't find enclosing Criteria for textblock tag ");
		}
		return SKIP_BODY;
	}

	public String getTextblock() throws JspTagException {
		try {
			Criteria theCriteria = (Criteria)findAncestorWithClass(this, Criteria.class);
			return theCriteria.getTextblock();
		} catch (Exception e) {
			log.error(" Can't find enclosing Criteria for textblock tag ", e);
			throw new JspTagException("Error: Can't find enclosing Criteria for textblock tag ");
		}
	}

	public void setTextblock(String textblock) throws JspTagException {
		try {
			Criteria theCriteria = (Criteria)findAncestorWithClass(this, Criteria.class);
			theCriteria.setTextblock(textblock);
		} catch (Exception e) {
			log.error("Can't find enclosing Criteria for textblock tag ", e);
			throw new JspTagException("Error: Can't find enclosing Criteria for textblock tag ");
		}
	}

}
