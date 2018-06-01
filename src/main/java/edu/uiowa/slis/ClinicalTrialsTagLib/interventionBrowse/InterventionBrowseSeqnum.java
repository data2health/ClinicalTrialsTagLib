package edu.uiowa.slis.ClinicalTrialsTagLib.interventionBrowse;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class InterventionBrowseSeqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(InterventionBrowseSeqnum.class);


	public int doStartTag() throws JspException {
		try {
			InterventionBrowse theInterventionBrowse = (InterventionBrowse)findAncestorWithClass(this, InterventionBrowse.class);
			if (!theInterventionBrowse.commitNeeded) {
				pageContext.getOut().print(theInterventionBrowse.getSeqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing InterventionBrowse for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing InterventionBrowse for seqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getSeqnum() throws JspTagException {
		try {
			InterventionBrowse theInterventionBrowse = (InterventionBrowse)findAncestorWithClass(this, InterventionBrowse.class);
			return theInterventionBrowse.getSeqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing InterventionBrowse for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing InterventionBrowse for seqnum tag ");
		}
	}

	public void setSeqnum(int seqnum) throws JspTagException {
		try {
			InterventionBrowse theInterventionBrowse = (InterventionBrowse)findAncestorWithClass(this, InterventionBrowse.class);
			theInterventionBrowse.setSeqnum(seqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing InterventionBrowse for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing InterventionBrowse for seqnum tag ");
		}
	}

}
