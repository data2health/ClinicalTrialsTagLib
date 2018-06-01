package edu.uiowa.slis.ClinicalTrialsTagLib.official;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class OfficialAffiliation extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(OfficialAffiliation.class);


	public int doStartTag() throws JspException {
		try {
			Official theOfficial = (Official)findAncestorWithClass(this, Official.class);
			if (!theOfficial.commitNeeded) {
				pageContext.getOut().print(theOfficial.getAffiliation());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Official for affiliation tag ", e);
			throw new JspTagException("Error: Can't find enclosing Official for affiliation tag ");
		}
		return SKIP_BODY;
	}

	public String getAffiliation() throws JspTagException {
		try {
			Official theOfficial = (Official)findAncestorWithClass(this, Official.class);
			return theOfficial.getAffiliation();
		} catch (Exception e) {
			log.error(" Can't find enclosing Official for affiliation tag ", e);
			throw new JspTagException("Error: Can't find enclosing Official for affiliation tag ");
		}
	}

	public void setAffiliation(String affiliation) throws JspTagException {
		try {
			Official theOfficial = (Official)findAncestorWithClass(this, Official.class);
			theOfficial.setAffiliation(affiliation);
		} catch (Exception e) {
			log.error("Can't find enclosing Official for affiliation tag ", e);
			throw new JspTagException("Error: Can't find enclosing Official for affiliation tag ");
		}
	}

}
