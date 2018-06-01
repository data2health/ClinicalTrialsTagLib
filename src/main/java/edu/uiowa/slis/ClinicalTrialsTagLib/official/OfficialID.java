package edu.uiowa.slis.ClinicalTrialsTagLib.official;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class OfficialID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(OfficialID.class);


	public int doStartTag() throws JspException {
		try {
			Official theOfficial = (Official)findAncestorWithClass(this, Official.class);
			if (!theOfficial.commitNeeded) {
				pageContext.getOut().print(theOfficial.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Official for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing Official for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			Official theOfficial = (Official)findAncestorWithClass(this, Official.class);
			return theOfficial.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing Official for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing Official for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			Official theOfficial = (Official)findAncestorWithClass(this, Official.class);
			theOfficial.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing Official for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing Official for ID tag ");
		}
	}

}
