package edu.uiowa.slis.ClinicalTrialsTagLib.briefSummary;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class BriefSummaryTextblock extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(BriefSummaryTextblock.class);


	public int doStartTag() throws JspException {
		try {
			BriefSummary theBriefSummary = (BriefSummary)findAncestorWithClass(this, BriefSummary.class);
			if (!theBriefSummary.commitNeeded) {
				pageContext.getOut().print(theBriefSummary.getTextblock());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing BriefSummary for textblock tag ", e);
			throw new JspTagException("Error: Can't find enclosing BriefSummary for textblock tag ");
		}
		return SKIP_BODY;
	}

	public String getTextblock() throws JspTagException {
		try {
			BriefSummary theBriefSummary = (BriefSummary)findAncestorWithClass(this, BriefSummary.class);
			return theBriefSummary.getTextblock();
		} catch (Exception e) {
			log.error(" Can't find enclosing BriefSummary for textblock tag ", e);
			throw new JspTagException("Error: Can't find enclosing BriefSummary for textblock tag ");
		}
	}

	public void setTextblock(String textblock) throws JspTagException {
		try {
			BriefSummary theBriefSummary = (BriefSummary)findAncestorWithClass(this, BriefSummary.class);
			theBriefSummary.setTextblock(textblock);
		} catch (Exception e) {
			log.error("Can't find enclosing BriefSummary for textblock tag ", e);
			throw new JspTagException("Error: Can't find enclosing BriefSummary for textblock tag ");
		}
	}

}
