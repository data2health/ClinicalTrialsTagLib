package edu.uiowa.slis.ClinicalTrialsTagLib.conditionBrowse;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ConditionBrowseSeqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ConditionBrowseSeqnum.class);


	public int doStartTag() throws JspException {
		try {
			ConditionBrowse theConditionBrowse = (ConditionBrowse)findAncestorWithClass(this, ConditionBrowse.class);
			if (!theConditionBrowse.commitNeeded) {
				pageContext.getOut().print(theConditionBrowse.getSeqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ConditionBrowse for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ConditionBrowse for seqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getSeqnum() throws JspTagException {
		try {
			ConditionBrowse theConditionBrowse = (ConditionBrowse)findAncestorWithClass(this, ConditionBrowse.class);
			return theConditionBrowse.getSeqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing ConditionBrowse for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ConditionBrowse for seqnum tag ");
		}
	}

	public void setSeqnum(int seqnum) throws JspTagException {
		try {
			ConditionBrowse theConditionBrowse = (ConditionBrowse)findAncestorWithClass(this, ConditionBrowse.class);
			theConditionBrowse.setSeqnum(seqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing ConditionBrowse for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ConditionBrowse for seqnum tag ");
		}
	}

}
