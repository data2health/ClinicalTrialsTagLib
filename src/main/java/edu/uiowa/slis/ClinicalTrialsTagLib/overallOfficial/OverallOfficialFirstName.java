package edu.uiowa.slis.ClinicalTrialsTagLib.overallOfficial;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class OverallOfficialFirstName extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(OverallOfficialFirstName.class);


	public int doStartTag() throws JspException {
		try {
			OverallOfficial theOverallOfficial = (OverallOfficial)findAncestorWithClass(this, OverallOfficial.class);
			if (!theOverallOfficial.commitNeeded) {
				pageContext.getOut().print(theOverallOfficial.getFirstName());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing OverallOfficial for firstName tag ", e);
			throw new JspTagException("Error: Can't find enclosing OverallOfficial for firstName tag ");
		}
		return SKIP_BODY;
	}

	public String getFirstName() throws JspTagException {
		try {
			OverallOfficial theOverallOfficial = (OverallOfficial)findAncestorWithClass(this, OverallOfficial.class);
			return theOverallOfficial.getFirstName();
		} catch (Exception e) {
			log.error(" Can't find enclosing OverallOfficial for firstName tag ", e);
			throw new JspTagException("Error: Can't find enclosing OverallOfficial for firstName tag ");
		}
	}

	public void setFirstName(String firstName) throws JspTagException {
		try {
			OverallOfficial theOverallOfficial = (OverallOfficial)findAncestorWithClass(this, OverallOfficial.class);
			theOverallOfficial.setFirstName(firstName);
		} catch (Exception e) {
			log.error("Can't find enclosing OverallOfficial for firstName tag ", e);
			throw new JspTagException("Error: Can't find enclosing OverallOfficial for firstName tag ");
		}
	}

}
