package edu.uiowa.slis.ClinicalTrialsTagLib.overallOfficial;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class OverallOfficialAffiliation extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(OverallOfficialAffiliation.class);


	public int doStartTag() throws JspException {
		try {
			OverallOfficial theOverallOfficial = (OverallOfficial)findAncestorWithClass(this, OverallOfficial.class);
			if (!theOverallOfficial.commitNeeded) {
				pageContext.getOut().print(theOverallOfficial.getAffiliation());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing OverallOfficial for affiliation tag ", e);
			throw new JspTagException("Error: Can't find enclosing OverallOfficial for affiliation tag ");
		}
		return SKIP_BODY;
	}

	public String getAffiliation() throws JspTagException {
		try {
			OverallOfficial theOverallOfficial = (OverallOfficial)findAncestorWithClass(this, OverallOfficial.class);
			return theOverallOfficial.getAffiliation();
		} catch (Exception e) {
			log.error(" Can't find enclosing OverallOfficial for affiliation tag ", e);
			throw new JspTagException("Error: Can't find enclosing OverallOfficial for affiliation tag ");
		}
	}

	public void setAffiliation(String affiliation) throws JspTagException {
		try {
			OverallOfficial theOverallOfficial = (OverallOfficial)findAncestorWithClass(this, OverallOfficial.class);
			theOverallOfficial.setAffiliation(affiliation);
		} catch (Exception e) {
			log.error("Can't find enclosing OverallOfficial for affiliation tag ", e);
			throw new JspTagException("Error: Can't find enclosing OverallOfficial for affiliation tag ");
		}
	}

}
