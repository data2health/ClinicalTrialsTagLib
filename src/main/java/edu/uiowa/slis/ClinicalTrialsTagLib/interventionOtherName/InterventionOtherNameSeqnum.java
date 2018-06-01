package edu.uiowa.slis.ClinicalTrialsTagLib.interventionOtherName;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class InterventionOtherNameSeqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(InterventionOtherNameSeqnum.class);


	public int doStartTag() throws JspException {
		try {
			InterventionOtherName theInterventionOtherName = (InterventionOtherName)findAncestorWithClass(this, InterventionOtherName.class);
			if (!theInterventionOtherName.commitNeeded) {
				pageContext.getOut().print(theInterventionOtherName.getSeqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing InterventionOtherName for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing InterventionOtherName for seqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getSeqnum() throws JspTagException {
		try {
			InterventionOtherName theInterventionOtherName = (InterventionOtherName)findAncestorWithClass(this, InterventionOtherName.class);
			return theInterventionOtherName.getSeqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing InterventionOtherName for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing InterventionOtherName for seqnum tag ");
		}
	}

	public void setSeqnum(int seqnum) throws JspTagException {
		try {
			InterventionOtherName theInterventionOtherName = (InterventionOtherName)findAncestorWithClass(this, InterventionOtherName.class);
			theInterventionOtherName.setSeqnum(seqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing InterventionOtherName for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing InterventionOtherName for seqnum tag ");
		}
	}

}
