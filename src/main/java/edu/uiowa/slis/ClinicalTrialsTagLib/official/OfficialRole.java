package edu.uiowa.slis.ClinicalTrialsTagLib.official;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class OfficialRole extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(OfficialRole.class);


	public int doStartTag() throws JspException {
		try {
			Official theOfficial = (Official)findAncestorWithClass(this, Official.class);
			if (!theOfficial.commitNeeded) {
				pageContext.getOut().print(theOfficial.getRole());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Official for role tag ", e);
			throw new JspTagException("Error: Can't find enclosing Official for role tag ");
		}
		return SKIP_BODY;
	}

	public String getRole() throws JspTagException {
		try {
			Official theOfficial = (Official)findAncestorWithClass(this, Official.class);
			return theOfficial.getRole();
		} catch (Exception e) {
			log.error(" Can't find enclosing Official for role tag ", e);
			throw new JspTagException("Error: Can't find enclosing Official for role tag ");
		}
	}

	public void setRole(String role) throws JspTagException {
		try {
			Official theOfficial = (Official)findAncestorWithClass(this, Official.class);
			theOfficial.setRole(role);
		} catch (Exception e) {
			log.error("Can't find enclosing Official for role tag ", e);
			throw new JspTagException("Error: Can't find enclosing Official for role tag ");
		}
	}

}
