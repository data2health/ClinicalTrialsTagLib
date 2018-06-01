package edu.uiowa.slis.ClinicalTrialsTagLib.criteria;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class CriteriaElseqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(CriteriaElseqnum.class);


	public int doStartTag() throws JspException {
		try {
			Criteria theCriteria = (Criteria)findAncestorWithClass(this, Criteria.class);
			if (!theCriteria.commitNeeded) {
				pageContext.getOut().print(theCriteria.getElseqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Criteria for elseqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing Criteria for elseqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getElseqnum() throws JspTagException {
		try {
			Criteria theCriteria = (Criteria)findAncestorWithClass(this, Criteria.class);
			return theCriteria.getElseqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing Criteria for elseqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing Criteria for elseqnum tag ");
		}
	}

	public void setElseqnum(int elseqnum) throws JspTagException {
		try {
			Criteria theCriteria = (Criteria)findAncestorWithClass(this, Criteria.class);
			theCriteria.setElseqnum(elseqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing Criteria for elseqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing Criteria for elseqnum tag ");
		}
	}

}
