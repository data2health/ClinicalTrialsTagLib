package edu.uiowa.slis.ClinicalTrialsTagLib.responsibleParty;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResponsiblePartyID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResponsiblePartyID.class);


	public int doStartTag() throws JspException {
		try {
			ResponsibleParty theResponsibleParty = (ResponsibleParty)findAncestorWithClass(this, ResponsibleParty.class);
			if (!theResponsibleParty.commitNeeded) {
				pageContext.getOut().print(theResponsibleParty.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResponsibleParty for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResponsibleParty for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			ResponsibleParty theResponsibleParty = (ResponsibleParty)findAncestorWithClass(this, ResponsibleParty.class);
			return theResponsibleParty.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResponsibleParty for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResponsibleParty for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			ResponsibleParty theResponsibleParty = (ResponsibleParty)findAncestorWithClass(this, ResponsibleParty.class);
			theResponsibleParty.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing ResponsibleParty for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResponsibleParty for ID tag ");
		}
	}

}
