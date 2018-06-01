package edu.uiowa.slis.ClinicalTrialsTagLib.criteria;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class CriteriaSeqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(CriteriaSeqnum.class);


	public int doStartTag() throws JspException {
		try {
			Criteria theCriteria = (Criteria)findAncestorWithClass(this, Criteria.class);
			if (!theCriteria.commitNeeded) {
				pageContext.getOut().print(theCriteria.getSeqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Criteria for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing Criteria for seqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getSeqnum() throws JspTagException {
		try {
			Criteria theCriteria = (Criteria)findAncestorWithClass(this, Criteria.class);
			return theCriteria.getSeqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing Criteria for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing Criteria for seqnum tag ");
		}
	}

	public void setSeqnum(int seqnum) throws JspTagException {
		try {
			Criteria theCriteria = (Criteria)findAncestorWithClass(this, Criteria.class);
			theCriteria.setSeqnum(seqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing Criteria for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing Criteria for seqnum tag ");
		}
	}

}
