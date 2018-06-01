package edu.uiowa.slis.ClinicalTrialsTagLib.overallOfficial;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class OverallOfficialDegrees extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(OverallOfficialDegrees.class);


	public int doStartTag() throws JspException {
		try {
			OverallOfficial theOverallOfficial = (OverallOfficial)findAncestorWithClass(this, OverallOfficial.class);
			if (!theOverallOfficial.commitNeeded) {
				pageContext.getOut().print(theOverallOfficial.getDegrees());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing OverallOfficial for degrees tag ", e);
			throw new JspTagException("Error: Can't find enclosing OverallOfficial for degrees tag ");
		}
		return SKIP_BODY;
	}

	public String getDegrees() throws JspTagException {
		try {
			OverallOfficial theOverallOfficial = (OverallOfficial)findAncestorWithClass(this, OverallOfficial.class);
			return theOverallOfficial.getDegrees();
		} catch (Exception e) {
			log.error(" Can't find enclosing OverallOfficial for degrees tag ", e);
			throw new JspTagException("Error: Can't find enclosing OverallOfficial for degrees tag ");
		}
	}

	public void setDegrees(String degrees) throws JspTagException {
		try {
			OverallOfficial theOverallOfficial = (OverallOfficial)findAncestorWithClass(this, OverallOfficial.class);
			theOverallOfficial.setDegrees(degrees);
		} catch (Exception e) {
			log.error("Can't find enclosing OverallOfficial for degrees tag ", e);
			throw new JspTagException("Error: Can't find enclosing OverallOfficial for degrees tag ");
		}
	}

}
