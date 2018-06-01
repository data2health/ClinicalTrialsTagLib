package edu.uiowa.slis.ClinicalTrialsTagLib.official;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class OfficialLastName extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(OfficialLastName.class);


	public int doStartTag() throws JspException {
		try {
			Official theOfficial = (Official)findAncestorWithClass(this, Official.class);
			if (!theOfficial.commitNeeded) {
				pageContext.getOut().print(theOfficial.getLastName());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Official for lastName tag ", e);
			throw new JspTagException("Error: Can't find enclosing Official for lastName tag ");
		}
		return SKIP_BODY;
	}

	public String getLastName() throws JspTagException {
		try {
			Official theOfficial = (Official)findAncestorWithClass(this, Official.class);
			return theOfficial.getLastName();
		} catch (Exception e) {
			log.error(" Can't find enclosing Official for lastName tag ", e);
			throw new JspTagException("Error: Can't find enclosing Official for lastName tag ");
		}
	}

	public void setLastName(String lastName) throws JspTagException {
		try {
			Official theOfficial = (Official)findAncestorWithClass(this, Official.class);
			theOfficial.setLastName(lastName);
		} catch (Exception e) {
			log.error("Can't find enclosing Official for lastName tag ", e);
			throw new JspTagException("Error: Can't find enclosing Official for lastName tag ");
		}
	}

}
