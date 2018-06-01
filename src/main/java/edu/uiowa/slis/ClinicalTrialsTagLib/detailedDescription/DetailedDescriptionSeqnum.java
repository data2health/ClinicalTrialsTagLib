package edu.uiowa.slis.ClinicalTrialsTagLib.detailedDescription;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class DetailedDescriptionSeqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(DetailedDescriptionSeqnum.class);


	public int doStartTag() throws JspException {
		try {
			DetailedDescription theDetailedDescription = (DetailedDescription)findAncestorWithClass(this, DetailedDescription.class);
			if (!theDetailedDescription.commitNeeded) {
				pageContext.getOut().print(theDetailedDescription.getSeqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing DetailedDescription for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing DetailedDescription for seqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getSeqnum() throws JspTagException {
		try {
			DetailedDescription theDetailedDescription = (DetailedDescription)findAncestorWithClass(this, DetailedDescription.class);
			return theDetailedDescription.getSeqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing DetailedDescription for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing DetailedDescription for seqnum tag ");
		}
	}

	public void setSeqnum(int seqnum) throws JspTagException {
		try {
			DetailedDescription theDetailedDescription = (DetailedDescription)findAncestorWithClass(this, DetailedDescription.class);
			theDetailedDescription.setSeqnum(seqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing DetailedDescription for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing DetailedDescription for seqnum tag ");
		}
	}

}
