package edu.uiowa.slis.ClinicalTrialsTagLib.baselineMeasurement;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class BaselineMeasurementSeqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(BaselineMeasurementSeqnum.class);


	public int doStartTag() throws JspException {
		try {
			BaselineMeasurement theBaselineMeasurement = (BaselineMeasurement)findAncestorWithClass(this, BaselineMeasurement.class);
			if (!theBaselineMeasurement.commitNeeded) {
				pageContext.getOut().print(theBaselineMeasurement.getSeqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing BaselineMeasurement for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineMeasurement for seqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getSeqnum() throws JspTagException {
		try {
			BaselineMeasurement theBaselineMeasurement = (BaselineMeasurement)findAncestorWithClass(this, BaselineMeasurement.class);
			return theBaselineMeasurement.getSeqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing BaselineMeasurement for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineMeasurement for seqnum tag ");
		}
	}

	public void setSeqnum(int seqnum) throws JspTagException {
		try {
			BaselineMeasurement theBaselineMeasurement = (BaselineMeasurement)findAncestorWithClass(this, BaselineMeasurement.class);
			theBaselineMeasurement.setSeqnum(seqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing BaselineMeasurement for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineMeasurement for seqnum tag ");
		}
	}

}
