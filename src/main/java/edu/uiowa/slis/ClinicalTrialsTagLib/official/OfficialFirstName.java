package edu.uiowa.slis.ClinicalTrialsTagLib.official;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class OfficialFirstName extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(OfficialFirstName.class);


	public int doStartTag() throws JspException {
		try {
			Official theOfficial = (Official)findAncestorWithClass(this, Official.class);
			if (!theOfficial.commitNeeded) {
				pageContext.getOut().print(theOfficial.getFirstName());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Official for firstName tag ", e);
			throw new JspTagException("Error: Can't find enclosing Official for firstName tag ");
		}
		return SKIP_BODY;
	}

	public String getFirstName() throws JspTagException {
		try {
			Official theOfficial = (Official)findAncestorWithClass(this, Official.class);
			return theOfficial.getFirstName();
		} catch (Exception e) {
			log.error(" Can't find enclosing Official for firstName tag ", e);
			throw new JspTagException("Error: Can't find enclosing Official for firstName tag ");
		}
	}

	public void setFirstName(String firstName) throws JspTagException {
		try {
			Official theOfficial = (Official)findAncestorWithClass(this, Official.class);
			theOfficial.setFirstName(firstName);
		} catch (Exception e) {
			log.error("Can't find enclosing Official for firstName tag ", e);
			throw new JspTagException("Error: Can't find enclosing Official for firstName tag ");
		}
	}

}
