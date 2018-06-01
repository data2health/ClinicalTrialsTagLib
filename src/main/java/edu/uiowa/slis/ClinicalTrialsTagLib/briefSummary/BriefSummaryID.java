package edu.uiowa.slis.ClinicalTrialsTagLib.briefSummary;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class BriefSummaryID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(BriefSummaryID.class);


	public int doStartTag() throws JspException {
		try {
			BriefSummary theBriefSummary = (BriefSummary)findAncestorWithClass(this, BriefSummary.class);
			if (!theBriefSummary.commitNeeded) {
				pageContext.getOut().print(theBriefSummary.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing BriefSummary for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing BriefSummary for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			BriefSummary theBriefSummary = (BriefSummary)findAncestorWithClass(this, BriefSummary.class);
			return theBriefSummary.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing BriefSummary for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing BriefSummary for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			BriefSummary theBriefSummary = (BriefSummary)findAncestorWithClass(this, BriefSummary.class);
			theBriefSummary.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing BriefSummary for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing BriefSummary for ID tag ");
		}
	}

}
