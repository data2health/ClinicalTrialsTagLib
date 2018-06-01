package edu.uiowa.slis.ClinicalTrialsTagLib.overallOfficial;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class OverallOfficialRole extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(OverallOfficialRole.class);


	public int doStartTag() throws JspException {
		try {
			OverallOfficial theOverallOfficial = (OverallOfficial)findAncestorWithClass(this, OverallOfficial.class);
			if (!theOverallOfficial.commitNeeded) {
				pageContext.getOut().print(theOverallOfficial.getRole());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing OverallOfficial for role tag ", e);
			throw new JspTagException("Error: Can't find enclosing OverallOfficial for role tag ");
		}
		return SKIP_BODY;
	}

	public String getRole() throws JspTagException {
		try {
			OverallOfficial theOverallOfficial = (OverallOfficial)findAncestorWithClass(this, OverallOfficial.class);
			return theOverallOfficial.getRole();
		} catch (Exception e) {
			log.error(" Can't find enclosing OverallOfficial for role tag ", e);
			throw new JspTagException("Error: Can't find enclosing OverallOfficial for role tag ");
		}
	}

	public void setRole(String role) throws JspTagException {
		try {
			OverallOfficial theOverallOfficial = (OverallOfficial)findAncestorWithClass(this, OverallOfficial.class);
			theOverallOfficial.setRole(role);
		} catch (Exception e) {
			log.error("Can't find enclosing OverallOfficial for role tag ", e);
			throw new JspTagException("Error: Can't find enclosing OverallOfficial for role tag ");
		}
	}

}
