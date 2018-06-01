package edu.uiowa.slis.ClinicalTrialsTagLib.briefSummary;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class BriefSummarySeqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(BriefSummarySeqnum.class);


	public int doStartTag() throws JspException {
		try {
			BriefSummary theBriefSummary = (BriefSummary)findAncestorWithClass(this, BriefSummary.class);
			if (!theBriefSummary.commitNeeded) {
				pageContext.getOut().print(theBriefSummary.getSeqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing BriefSummary for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing BriefSummary for seqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getSeqnum() throws JspTagException {
		try {
			BriefSummary theBriefSummary = (BriefSummary)findAncestorWithClass(this, BriefSummary.class);
			return theBriefSummary.getSeqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing BriefSummary for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing BriefSummary for seqnum tag ");
		}
	}

	public void setSeqnum(int seqnum) throws JspTagException {
		try {
			BriefSummary theBriefSummary = (BriefSummary)findAncestorWithClass(this, BriefSummary.class);
			theBriefSummary.setSeqnum(seqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing BriefSummary for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing BriefSummary for seqnum tag ");
		}
	}

}
