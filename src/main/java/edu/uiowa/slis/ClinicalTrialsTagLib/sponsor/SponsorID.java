package edu.uiowa.slis.ClinicalTrialsTagLib.sponsor;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class SponsorID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(SponsorID.class);


	public int doStartTag() throws JspException {
		try {
			Sponsor theSponsor = (Sponsor)findAncestorWithClass(this, Sponsor.class);
			if (!theSponsor.commitNeeded) {
				pageContext.getOut().print(theSponsor.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Sponsor for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing Sponsor for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			Sponsor theSponsor = (Sponsor)findAncestorWithClass(this, Sponsor.class);
			return theSponsor.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing Sponsor for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing Sponsor for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			Sponsor theSponsor = (Sponsor)findAncestorWithClass(this, Sponsor.class);
			theSponsor.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing Sponsor for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing Sponsor for ID tag ");
		}
	}

}
