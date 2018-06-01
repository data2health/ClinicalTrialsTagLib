package edu.uiowa.slis.ClinicalTrialsTagLib.otherCategory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class OtherCategorySeqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(OtherCategorySeqnum.class);


	public int doStartTag() throws JspException {
		try {
			OtherCategory theOtherCategory = (OtherCategory)findAncestorWithClass(this, OtherCategory.class);
			if (!theOtherCategory.commitNeeded) {
				pageContext.getOut().print(theOtherCategory.getSeqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing OtherCategory for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherCategory for seqnum tag ");
		}
		return SKIP_BODY;
	}

	public String getSeqnum() throws JspTagException {
		try {
			OtherCategory theOtherCategory = (OtherCategory)findAncestorWithClass(this, OtherCategory.class);
			return theOtherCategory.getSeqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing OtherCategory for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherCategory for seqnum tag ");
		}
	}

	public void setSeqnum(String seqnum) throws JspTagException {
		try {
			OtherCategory theOtherCategory = (OtherCategory)findAncestorWithClass(this, OtherCategory.class);
			theOtherCategory.setSeqnum(seqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing OtherCategory for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherCategory for seqnum tag ");
		}
	}

}
