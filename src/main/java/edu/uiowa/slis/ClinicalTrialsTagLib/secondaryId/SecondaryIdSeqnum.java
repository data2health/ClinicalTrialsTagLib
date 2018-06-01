package edu.uiowa.slis.ClinicalTrialsTagLib.secondaryId;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class SecondaryIdSeqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(SecondaryIdSeqnum.class);


	public int doStartTag() throws JspException {
		try {
			SecondaryId theSecondaryId = (SecondaryId)findAncestorWithClass(this, SecondaryId.class);
			if (!theSecondaryId.commitNeeded) {
				pageContext.getOut().print(theSecondaryId.getSeqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing SecondaryId for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing SecondaryId for seqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getSeqnum() throws JspTagException {
		try {
			SecondaryId theSecondaryId = (SecondaryId)findAncestorWithClass(this, SecondaryId.class);
			return theSecondaryId.getSeqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing SecondaryId for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing SecondaryId for seqnum tag ");
		}
	}

	public void setSeqnum(int seqnum) throws JspTagException {
		try {
			SecondaryId theSecondaryId = (SecondaryId)findAncestorWithClass(this, SecondaryId.class);
			theSecondaryId.setSeqnum(seqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing SecondaryId for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing SecondaryId for seqnum tag ");
		}
	}

}
