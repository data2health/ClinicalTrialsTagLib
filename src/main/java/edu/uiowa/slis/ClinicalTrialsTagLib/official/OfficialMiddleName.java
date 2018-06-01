package edu.uiowa.slis.ClinicalTrialsTagLib.official;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class OfficialMiddleName extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(OfficialMiddleName.class);


	public int doStartTag() throws JspException {
		try {
			Official theOfficial = (Official)findAncestorWithClass(this, Official.class);
			if (!theOfficial.commitNeeded) {
				pageContext.getOut().print(theOfficial.getMiddleName());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Official for middleName tag ", e);
			throw new JspTagException("Error: Can't find enclosing Official for middleName tag ");
		}
		return SKIP_BODY;
	}

	public String getMiddleName() throws JspTagException {
		try {
			Official theOfficial = (Official)findAncestorWithClass(this, Official.class);
			return theOfficial.getMiddleName();
		} catch (Exception e) {
			log.error(" Can't find enclosing Official for middleName tag ", e);
			throw new JspTagException("Error: Can't find enclosing Official for middleName tag ");
		}
	}

	public void setMiddleName(String middleName) throws JspTagException {
		try {
			Official theOfficial = (Official)findAncestorWithClass(this, Official.class);
			theOfficial.setMiddleName(middleName);
		} catch (Exception e) {
			log.error("Can't find enclosing Official for middleName tag ", e);
			throw new JspTagException("Error: Can't find enclosing Official for middleName tag ");
		}
	}

}
