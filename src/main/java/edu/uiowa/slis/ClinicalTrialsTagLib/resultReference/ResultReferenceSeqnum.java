package edu.uiowa.slis.ClinicalTrialsTagLib.resultReference;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultReferenceSeqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultReferenceSeqnum.class);


	public int doStartTag() throws JspException {
		try {
			ResultReference theResultReference = (ResultReference)findAncestorWithClass(this, ResultReference.class);
			if (!theResultReference.commitNeeded) {
				pageContext.getOut().print(theResultReference.getSeqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultReference for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultReference for seqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getSeqnum() throws JspTagException {
		try {
			ResultReference theResultReference = (ResultReference)findAncestorWithClass(this, ResultReference.class);
			return theResultReference.getSeqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultReference for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultReference for seqnum tag ");
		}
	}

	public void setSeqnum(int seqnum) throws JspTagException {
		try {
			ResultReference theResultReference = (ResultReference)findAncestorWithClass(this, ResultReference.class);
			theResultReference.setSeqnum(seqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultReference for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultReference for seqnum tag ");
		}
	}

}
