package edu.uiowa.slis.ClinicalTrialsTagLib.responsibleParty;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResponsiblePartyInvestigatorAffiliation extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResponsiblePartyInvestigatorAffiliation.class);


	public int doStartTag() throws JspException {
		try {
			ResponsibleParty theResponsibleParty = (ResponsibleParty)findAncestorWithClass(this, ResponsibleParty.class);
			if (!theResponsibleParty.commitNeeded) {
				pageContext.getOut().print(theResponsibleParty.getInvestigatorAffiliation());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResponsibleParty for investigatorAffiliation tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResponsibleParty for investigatorAffiliation tag ");
		}
		return SKIP_BODY;
	}

	public String getInvestigatorAffiliation() throws JspTagException {
		try {
			ResponsibleParty theResponsibleParty = (ResponsibleParty)findAncestorWithClass(this, ResponsibleParty.class);
			return theResponsibleParty.getInvestigatorAffiliation();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResponsibleParty for investigatorAffiliation tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResponsibleParty for investigatorAffiliation tag ");
		}
	}

	public void setInvestigatorAffiliation(String investigatorAffiliation) throws JspTagException {
		try {
			ResponsibleParty theResponsibleParty = (ResponsibleParty)findAncestorWithClass(this, ResponsibleParty.class);
			theResponsibleParty.setInvestigatorAffiliation(investigatorAffiliation);
		} catch (Exception e) {
			log.error("Can't find enclosing ResponsibleParty for investigatorAffiliation tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResponsibleParty for investigatorAffiliation tag ");
		}
	}

}
