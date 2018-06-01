package edu.uiowa.slis.ClinicalTrialsTagLib.responsibleParty;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResponsiblePartySeqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResponsiblePartySeqnum.class);


	public int doStartTag() throws JspException {
		try {
			ResponsibleParty theResponsibleParty = (ResponsibleParty)findAncestorWithClass(this, ResponsibleParty.class);
			if (!theResponsibleParty.commitNeeded) {
				pageContext.getOut().print(theResponsibleParty.getSeqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResponsibleParty for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResponsibleParty for seqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getSeqnum() throws JspTagException {
		try {
			ResponsibleParty theResponsibleParty = (ResponsibleParty)findAncestorWithClass(this, ResponsibleParty.class);
			return theResponsibleParty.getSeqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResponsibleParty for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResponsibleParty for seqnum tag ");
		}
	}

	public void setSeqnum(int seqnum) throws JspTagException {
		try {
			ResponsibleParty theResponsibleParty = (ResponsibleParty)findAncestorWithClass(this, ResponsibleParty.class);
			theResponsibleParty.setSeqnum(seqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing ResponsibleParty for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResponsibleParty for seqnum tag ");
		}
	}

}
