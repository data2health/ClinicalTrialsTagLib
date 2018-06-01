package edu.uiowa.slis.ClinicalTrialsTagLib.responsibleParty;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResponsiblePartyInvestigatorFullName extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResponsiblePartyInvestigatorFullName.class);


	public int doStartTag() throws JspException {
		try {
			ResponsibleParty theResponsibleParty = (ResponsibleParty)findAncestorWithClass(this, ResponsibleParty.class);
			if (!theResponsibleParty.commitNeeded) {
				pageContext.getOut().print(theResponsibleParty.getInvestigatorFullName());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResponsibleParty for investigatorFullName tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResponsibleParty for investigatorFullName tag ");
		}
		return SKIP_BODY;
	}

	public String getInvestigatorFullName() throws JspTagException {
		try {
			ResponsibleParty theResponsibleParty = (ResponsibleParty)findAncestorWithClass(this, ResponsibleParty.class);
			return theResponsibleParty.getInvestigatorFullName();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResponsibleParty for investigatorFullName tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResponsibleParty for investigatorFullName tag ");
		}
	}

	public void setInvestigatorFullName(String investigatorFullName) throws JspTagException {
		try {
			ResponsibleParty theResponsibleParty = (ResponsibleParty)findAncestorWithClass(this, ResponsibleParty.class);
			theResponsibleParty.setInvestigatorFullName(investigatorFullName);
		} catch (Exception e) {
			log.error("Can't find enclosing ResponsibleParty for investigatorFullName tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResponsibleParty for investigatorFullName tag ");
		}
	}

}
