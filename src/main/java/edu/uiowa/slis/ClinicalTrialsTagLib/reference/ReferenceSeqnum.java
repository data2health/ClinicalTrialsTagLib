package edu.uiowa.slis.ClinicalTrialsTagLib.reference;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ReferenceSeqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ReferenceSeqnum.class);


	public int doStartTag() throws JspException {
		try {
			Reference theReference = (Reference)findAncestorWithClass(this, Reference.class);
			if (!theReference.commitNeeded) {
				pageContext.getOut().print(theReference.getSeqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Reference for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing Reference for seqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getSeqnum() throws JspTagException {
		try {
			Reference theReference = (Reference)findAncestorWithClass(this, Reference.class);
			return theReference.getSeqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing Reference for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing Reference for seqnum tag ");
		}
	}

	public void setSeqnum(int seqnum) throws JspTagException {
		try {
			Reference theReference = (Reference)findAncestorWithClass(this, Reference.class);
			theReference.setSeqnum(seqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing Reference for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing Reference for seqnum tag ");
		}
	}

}
