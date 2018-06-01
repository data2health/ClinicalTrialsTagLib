package edu.uiowa.slis.ClinicalTrialsTagLib.overallOfficial;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class OverallOfficialID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(OverallOfficialID.class);


	public int doStartTag() throws JspException {
		try {
			OverallOfficial theOverallOfficial = (OverallOfficial)findAncestorWithClass(this, OverallOfficial.class);
			if (!theOverallOfficial.commitNeeded) {
				pageContext.getOut().print(theOverallOfficial.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing OverallOfficial for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing OverallOfficial for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			OverallOfficial theOverallOfficial = (OverallOfficial)findAncestorWithClass(this, OverallOfficial.class);
			return theOverallOfficial.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing OverallOfficial for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing OverallOfficial for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			OverallOfficial theOverallOfficial = (OverallOfficial)findAncestorWithClass(this, OverallOfficial.class);
			theOverallOfficial.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing OverallOfficial for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing OverallOfficial for ID tag ");
		}
	}

}
