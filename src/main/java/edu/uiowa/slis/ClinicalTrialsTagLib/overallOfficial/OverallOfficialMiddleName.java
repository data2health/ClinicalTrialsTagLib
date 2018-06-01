package edu.uiowa.slis.ClinicalTrialsTagLib.overallOfficial;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class OverallOfficialMiddleName extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(OverallOfficialMiddleName.class);


	public int doStartTag() throws JspException {
		try {
			OverallOfficial theOverallOfficial = (OverallOfficial)findAncestorWithClass(this, OverallOfficial.class);
			if (!theOverallOfficial.commitNeeded) {
				pageContext.getOut().print(theOverallOfficial.getMiddleName());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing OverallOfficial for middleName tag ", e);
			throw new JspTagException("Error: Can't find enclosing OverallOfficial for middleName tag ");
		}
		return SKIP_BODY;
	}

	public String getMiddleName() throws JspTagException {
		try {
			OverallOfficial theOverallOfficial = (OverallOfficial)findAncestorWithClass(this, OverallOfficial.class);
			return theOverallOfficial.getMiddleName();
		} catch (Exception e) {
			log.error(" Can't find enclosing OverallOfficial for middleName tag ", e);
			throw new JspTagException("Error: Can't find enclosing OverallOfficial for middleName tag ");
		}
	}

	public void setMiddleName(String middleName) throws JspTagException {
		try {
			OverallOfficial theOverallOfficial = (OverallOfficial)findAncestorWithClass(this, OverallOfficial.class);
			theOverallOfficial.setMiddleName(middleName);
		} catch (Exception e) {
			log.error("Can't find enclosing OverallOfficial for middleName tag ", e);
			throw new JspTagException("Error: Can't find enclosing OverallOfficial for middleName tag ");
		}
	}

}
