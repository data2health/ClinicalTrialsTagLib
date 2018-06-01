package edu.uiowa.slis.ClinicalTrialsTagLib.intervention;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class InterventionSeqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(InterventionSeqnum.class);


	public int doStartTag() throws JspException {
		try {
			Intervention theIntervention = (Intervention)findAncestorWithClass(this, Intervention.class);
			if (!theIntervention.commitNeeded) {
				pageContext.getOut().print(theIntervention.getSeqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Intervention for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing Intervention for seqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getSeqnum() throws JspTagException {
		try {
			Intervention theIntervention = (Intervention)findAncestorWithClass(this, Intervention.class);
			return theIntervention.getSeqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing Intervention for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing Intervention for seqnum tag ");
		}
	}

	public void setSeqnum(int seqnum) throws JspTagException {
		try {
			Intervention theIntervention = (Intervention)findAncestorWithClass(this, Intervention.class);
			theIntervention.setSeqnum(seqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing Intervention for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing Intervention for seqnum tag ");
		}
	}

}
