package edu.uiowa.slis.ClinicalTrialsTagLib.resultsMeasurement;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsMeasurementSeqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsMeasurementSeqnum.class);


	public int doStartTag() throws JspException {
		try {
			ResultsMeasurement theResultsMeasurement = (ResultsMeasurement)findAncestorWithClass(this, ResultsMeasurement.class);
			if (!theResultsMeasurement.commitNeeded) {
				pageContext.getOut().print(theResultsMeasurement.getSeqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsMeasurement for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsMeasurement for seqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getSeqnum() throws JspTagException {
		try {
			ResultsMeasurement theResultsMeasurement = (ResultsMeasurement)findAncestorWithClass(this, ResultsMeasurement.class);
			return theResultsMeasurement.getSeqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsMeasurement for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsMeasurement for seqnum tag ");
		}
	}

	public void setSeqnum(int seqnum) throws JspTagException {
		try {
			ResultsMeasurement theResultsMeasurement = (ResultsMeasurement)findAncestorWithClass(this, ResultsMeasurement.class);
			theResultsMeasurement.setSeqnum(seqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsMeasurement for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsMeasurement for seqnum tag ");
		}
	}

}
