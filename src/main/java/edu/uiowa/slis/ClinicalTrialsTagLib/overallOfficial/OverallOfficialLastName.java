package edu.uiowa.slis.ClinicalTrialsTagLib.overallOfficial;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class OverallOfficialLastName extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(OverallOfficialLastName.class);


	public int doStartTag() throws JspException {
		try {
			OverallOfficial theOverallOfficial = (OverallOfficial)findAncestorWithClass(this, OverallOfficial.class);
			if (!theOverallOfficial.commitNeeded) {
				pageContext.getOut().print(theOverallOfficial.getLastName());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing OverallOfficial for lastName tag ", e);
			throw new JspTagException("Error: Can't find enclosing OverallOfficial for lastName tag ");
		}
		return SKIP_BODY;
	}

	public String getLastName() throws JspTagException {
		try {
			OverallOfficial theOverallOfficial = (OverallOfficial)findAncestorWithClass(this, OverallOfficial.class);
			return theOverallOfficial.getLastName();
		} catch (Exception e) {
			log.error(" Can't find enclosing OverallOfficial for lastName tag ", e);
			throw new JspTagException("Error: Can't find enclosing OverallOfficial for lastName tag ");
		}
	}

	public void setLastName(String lastName) throws JspTagException {
		try {
			OverallOfficial theOverallOfficial = (OverallOfficial)findAncestorWithClass(this, OverallOfficial.class);
			theOverallOfficial.setLastName(lastName);
		} catch (Exception e) {
			log.error("Can't find enclosing OverallOfficial for lastName tag ", e);
			throw new JspTagException("Error: Can't find enclosing OverallOfficial for lastName tag ");
		}
	}

}
