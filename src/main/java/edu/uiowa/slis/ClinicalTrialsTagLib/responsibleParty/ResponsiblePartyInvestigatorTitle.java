package edu.uiowa.slis.ClinicalTrialsTagLib.responsibleParty;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResponsiblePartyInvestigatorTitle extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResponsiblePartyInvestigatorTitle.class);


	public int doStartTag() throws JspException {
		try {
			ResponsibleParty theResponsibleParty = (ResponsibleParty)findAncestorWithClass(this, ResponsibleParty.class);
			if (!theResponsibleParty.commitNeeded) {
				pageContext.getOut().print(theResponsibleParty.getInvestigatorTitle());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResponsibleParty for investigatorTitle tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResponsibleParty for investigatorTitle tag ");
		}
		return SKIP_BODY;
	}

	public String getInvestigatorTitle() throws JspTagException {
		try {
			ResponsibleParty theResponsibleParty = (ResponsibleParty)findAncestorWithClass(this, ResponsibleParty.class);
			return theResponsibleParty.getInvestigatorTitle();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResponsibleParty for investigatorTitle tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResponsibleParty for investigatorTitle tag ");
		}
	}

	public void setInvestigatorTitle(String investigatorTitle) throws JspTagException {
		try {
			ResponsibleParty theResponsibleParty = (ResponsibleParty)findAncestorWithClass(this, ResponsibleParty.class);
			theResponsibleParty.setInvestigatorTitle(investigatorTitle);
		} catch (Exception e) {
			log.error("Can't find enclosing ResponsibleParty for investigatorTitle tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResponsibleParty for investigatorTitle tag ");
		}
	}

}
