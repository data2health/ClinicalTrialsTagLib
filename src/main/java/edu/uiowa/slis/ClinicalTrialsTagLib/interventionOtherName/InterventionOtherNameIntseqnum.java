package edu.uiowa.slis.ClinicalTrialsTagLib.interventionOtherName;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class InterventionOtherNameIntseqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(InterventionOtherNameIntseqnum.class);


	public int doStartTag() throws JspException {
		try {
			InterventionOtherName theInterventionOtherName = (InterventionOtherName)findAncestorWithClass(this, InterventionOtherName.class);
			if (!theInterventionOtherName.commitNeeded) {
				pageContext.getOut().print(theInterventionOtherName.getIntseqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing InterventionOtherName for intseqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing InterventionOtherName for intseqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getIntseqnum() throws JspTagException {
		try {
			InterventionOtherName theInterventionOtherName = (InterventionOtherName)findAncestorWithClass(this, InterventionOtherName.class);
			return theInterventionOtherName.getIntseqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing InterventionOtherName for intseqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing InterventionOtherName for intseqnum tag ");
		}
	}

	public void setIntseqnum(int intseqnum) throws JspTagException {
		try {
			InterventionOtherName theInterventionOtherName = (InterventionOtherName)findAncestorWithClass(this, InterventionOtherName.class);
			theInterventionOtherName.setIntseqnum(intseqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing InterventionOtherName for intseqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing InterventionOtherName for intseqnum tag ");
		}
	}

}
