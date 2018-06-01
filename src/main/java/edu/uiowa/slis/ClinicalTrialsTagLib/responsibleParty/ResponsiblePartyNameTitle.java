package edu.uiowa.slis.ClinicalTrialsTagLib.responsibleParty;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResponsiblePartyNameTitle extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResponsiblePartyNameTitle.class);


	public int doStartTag() throws JspException {
		try {
			ResponsibleParty theResponsibleParty = (ResponsibleParty)findAncestorWithClass(this, ResponsibleParty.class);
			if (!theResponsibleParty.commitNeeded) {
				pageContext.getOut().print(theResponsibleParty.getNameTitle());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResponsibleParty for nameTitle tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResponsibleParty for nameTitle tag ");
		}
		return SKIP_BODY;
	}

	public String getNameTitle() throws JspTagException {
		try {
			ResponsibleParty theResponsibleParty = (ResponsibleParty)findAncestorWithClass(this, ResponsibleParty.class);
			return theResponsibleParty.getNameTitle();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResponsibleParty for nameTitle tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResponsibleParty for nameTitle tag ");
		}
	}

	public void setNameTitle(String nameTitle) throws JspTagException {
		try {
			ResponsibleParty theResponsibleParty = (ResponsibleParty)findAncestorWithClass(this, ResponsibleParty.class);
			theResponsibleParty.setNameTitle(nameTitle);
		} catch (Exception e) {
			log.error("Can't find enclosing ResponsibleParty for nameTitle tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResponsibleParty for nameTitle tag ");
		}
	}

}
