package edu.uiowa.slis.ClinicalTrialsTagLib.seriousCategory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class SeriousCategorySeqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(SeriousCategorySeqnum.class);


	public int doStartTag() throws JspException {
		try {
			SeriousCategory theSeriousCategory = (SeriousCategory)findAncestorWithClass(this, SeriousCategory.class);
			if (!theSeriousCategory.commitNeeded) {
				pageContext.getOut().print(theSeriousCategory.getSeqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing SeriousCategory for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing SeriousCategory for seqnum tag ");
		}
		return SKIP_BODY;
	}

	public String getSeqnum() throws JspTagException {
		try {
			SeriousCategory theSeriousCategory = (SeriousCategory)findAncestorWithClass(this, SeriousCategory.class);
			return theSeriousCategory.getSeqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing SeriousCategory for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing SeriousCategory for seqnum tag ");
		}
	}

	public void setSeqnum(String seqnum) throws JspTagException {
		try {
			SeriousCategory theSeriousCategory = (SeriousCategory)findAncestorWithClass(this, SeriousCategory.class);
			theSeriousCategory.setSeqnum(seqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing SeriousCategory for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing SeriousCategory for seqnum tag ");
		}
	}

}
