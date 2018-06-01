package edu.uiowa.slis.ClinicalTrialsTagLib.official;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class OfficialDegrees extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(OfficialDegrees.class);


	public int doStartTag() throws JspException {
		try {
			Official theOfficial = (Official)findAncestorWithClass(this, Official.class);
			if (!theOfficial.commitNeeded) {
				pageContext.getOut().print(theOfficial.getDegrees());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Official for degrees tag ", e);
			throw new JspTagException("Error: Can't find enclosing Official for degrees tag ");
		}
		return SKIP_BODY;
	}

	public String getDegrees() throws JspTagException {
		try {
			Official theOfficial = (Official)findAncestorWithClass(this, Official.class);
			return theOfficial.getDegrees();
		} catch (Exception e) {
			log.error(" Can't find enclosing Official for degrees tag ", e);
			throw new JspTagException("Error: Can't find enclosing Official for degrees tag ");
		}
	}

	public void setDegrees(String degrees) throws JspTagException {
		try {
			Official theOfficial = (Official)findAncestorWithClass(this, Official.class);
			theOfficial.setDegrees(degrees);
		} catch (Exception e) {
			log.error("Can't find enclosing Official for degrees tag ", e);
			throw new JspTagException("Error: Can't find enclosing Official for degrees tag ");
		}
	}

}
