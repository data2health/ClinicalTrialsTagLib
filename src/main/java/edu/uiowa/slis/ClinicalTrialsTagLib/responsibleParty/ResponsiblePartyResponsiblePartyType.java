package edu.uiowa.slis.ClinicalTrialsTagLib.responsibleParty;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResponsiblePartyResponsiblePartyType extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResponsiblePartyResponsiblePartyType.class);


	public int doStartTag() throws JspException {
		try {
			ResponsibleParty theResponsibleParty = (ResponsibleParty)findAncestorWithClass(this, ResponsibleParty.class);
			if (!theResponsibleParty.commitNeeded) {
				pageContext.getOut().print(theResponsibleParty.getResponsiblePartyType());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResponsibleParty for responsiblePartyType tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResponsibleParty for responsiblePartyType tag ");
		}
		return SKIP_BODY;
	}

	public String getResponsiblePartyType() throws JspTagException {
		try {
			ResponsibleParty theResponsibleParty = (ResponsibleParty)findAncestorWithClass(this, ResponsibleParty.class);
			return theResponsibleParty.getResponsiblePartyType();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResponsibleParty for responsiblePartyType tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResponsibleParty for responsiblePartyType tag ");
		}
	}

	public void setResponsiblePartyType(String responsiblePartyType) throws JspTagException {
		try {
			ResponsibleParty theResponsibleParty = (ResponsibleParty)findAncestorWithClass(this, ResponsibleParty.class);
			theResponsibleParty.setResponsiblePartyType(responsiblePartyType);
		} catch (Exception e) {
			log.error("Can't find enclosing ResponsibleParty for responsiblePartyType tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResponsibleParty for responsiblePartyType tag ");
		}
	}

}
