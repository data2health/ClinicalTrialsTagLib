package edu.uiowa.slis.ClinicalTrialsTagLib.baselineCategory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class BaselineCategorySeqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(BaselineCategorySeqnum.class);


	public int doStartTag() throws JspException {
		try {
			BaselineCategory theBaselineCategory = (BaselineCategory)findAncestorWithClass(this, BaselineCategory.class);
			if (!theBaselineCategory.commitNeeded) {
				pageContext.getOut().print(theBaselineCategory.getSeqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing BaselineCategory for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineCategory for seqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getSeqnum() throws JspTagException {
		try {
			BaselineCategory theBaselineCategory = (BaselineCategory)findAncestorWithClass(this, BaselineCategory.class);
			return theBaselineCategory.getSeqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing BaselineCategory for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineCategory for seqnum tag ");
		}
	}

	public void setSeqnum(int seqnum) throws JspTagException {
		try {
			BaselineCategory theBaselineCategory = (BaselineCategory)findAncestorWithClass(this, BaselineCategory.class);
			theBaselineCategory.setSeqnum(seqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing BaselineCategory for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineCategory for seqnum tag ");
		}
	}

}
