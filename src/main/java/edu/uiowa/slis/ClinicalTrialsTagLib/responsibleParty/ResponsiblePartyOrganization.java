package edu.uiowa.slis.ClinicalTrialsTagLib.responsibleParty;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResponsiblePartyOrganization extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResponsiblePartyOrganization.class);


	public int doStartTag() throws JspException {
		try {
			ResponsibleParty theResponsibleParty = (ResponsibleParty)findAncestorWithClass(this, ResponsibleParty.class);
			if (!theResponsibleParty.commitNeeded) {
				pageContext.getOut().print(theResponsibleParty.getOrganization());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResponsibleParty for organization tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResponsibleParty for organization tag ");
		}
		return SKIP_BODY;
	}

	public String getOrganization() throws JspTagException {
		try {
			ResponsibleParty theResponsibleParty = (ResponsibleParty)findAncestorWithClass(this, ResponsibleParty.class);
			return theResponsibleParty.getOrganization();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResponsibleParty for organization tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResponsibleParty for organization tag ");
		}
	}

	public void setOrganization(String organization) throws JspTagException {
		try {
			ResponsibleParty theResponsibleParty = (ResponsibleParty)findAncestorWithClass(this, ResponsibleParty.class);
			theResponsibleParty.setOrganization(organization);
		} catch (Exception e) {
			log.error("Can't find enclosing ResponsibleParty for organization tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResponsibleParty for organization tag ");
		}
	}

}
