package edu.uiowa.slis.ClinicalTrialsTagLib.baselineMeasure;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class BaselineMeasureSeqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(BaselineMeasureSeqnum.class);


	public int doStartTag() throws JspException {
		try {
			BaselineMeasure theBaselineMeasure = (BaselineMeasure)findAncestorWithClass(this, BaselineMeasure.class);
			if (!theBaselineMeasure.commitNeeded) {
				pageContext.getOut().print(theBaselineMeasure.getSeqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing BaselineMeasure for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineMeasure for seqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getSeqnum() throws JspTagException {
		try {
			BaselineMeasure theBaselineMeasure = (BaselineMeasure)findAncestorWithClass(this, BaselineMeasure.class);
			return theBaselineMeasure.getSeqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing BaselineMeasure for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineMeasure for seqnum tag ");
		}
	}

	public void setSeqnum(int seqnum) throws JspTagException {
		try {
			BaselineMeasure theBaselineMeasure = (BaselineMeasure)findAncestorWithClass(this, BaselineMeasure.class);
			theBaselineMeasure.setSeqnum(seqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing BaselineMeasure for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineMeasure for seqnum tag ");
		}
	}

}
