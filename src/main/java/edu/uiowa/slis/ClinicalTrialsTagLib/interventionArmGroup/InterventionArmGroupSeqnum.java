package edu.uiowa.slis.ClinicalTrialsTagLib.interventionArmGroup;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class InterventionArmGroupSeqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(InterventionArmGroupSeqnum.class);


	public int doStartTag() throws JspException {
		try {
			InterventionArmGroup theInterventionArmGroup = (InterventionArmGroup)findAncestorWithClass(this, InterventionArmGroup.class);
			if (!theInterventionArmGroup.commitNeeded) {
				pageContext.getOut().print(theInterventionArmGroup.getSeqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing InterventionArmGroup for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing InterventionArmGroup for seqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getSeqnum() throws JspTagException {
		try {
			InterventionArmGroup theInterventionArmGroup = (InterventionArmGroup)findAncestorWithClass(this, InterventionArmGroup.class);
			return theInterventionArmGroup.getSeqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing InterventionArmGroup for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing InterventionArmGroup for seqnum tag ");
		}
	}

	public void setSeqnum(int seqnum) throws JspTagException {
		try {
			InterventionArmGroup theInterventionArmGroup = (InterventionArmGroup)findAncestorWithClass(this, InterventionArmGroup.class);
			theInterventionArmGroup.setSeqnum(seqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing InterventionArmGroup for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing InterventionArmGroup for seqnum tag ");
		}
	}

}
