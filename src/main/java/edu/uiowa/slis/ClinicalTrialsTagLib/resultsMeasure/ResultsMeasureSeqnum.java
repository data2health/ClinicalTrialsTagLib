package edu.uiowa.slis.ClinicalTrialsTagLib.resultsMeasure;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsMeasureSeqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsMeasureSeqnum.class);


	public int doStartTag() throws JspException {
		try {
			ResultsMeasure theResultsMeasure = (ResultsMeasure)findAncestorWithClass(this, ResultsMeasure.class);
			if (!theResultsMeasure.commitNeeded) {
				pageContext.getOut().print(theResultsMeasure.getSeqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsMeasure for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsMeasure for seqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getSeqnum() throws JspTagException {
		try {
			ResultsMeasure theResultsMeasure = (ResultsMeasure)findAncestorWithClass(this, ResultsMeasure.class);
			return theResultsMeasure.getSeqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsMeasure for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsMeasure for seqnum tag ");
		}
	}

	public void setSeqnum(int seqnum) throws JspTagException {
		try {
			ResultsMeasure theResultsMeasure = (ResultsMeasure)findAncestorWithClass(this, ResultsMeasure.class);
			theResultsMeasure.setSeqnum(seqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsMeasure for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsMeasure for seqnum tag ");
		}
	}

}
