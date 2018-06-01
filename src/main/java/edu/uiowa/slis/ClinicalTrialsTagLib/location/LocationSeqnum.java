package edu.uiowa.slis.ClinicalTrialsTagLib.location;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class LocationSeqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(LocationSeqnum.class);


	public int doStartTag() throws JspException {
		try {
			Location theLocation = (Location)findAncestorWithClass(this, Location.class);
			if (!theLocation.commitNeeded) {
				pageContext.getOut().print(theLocation.getSeqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Location for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing Location for seqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getSeqnum() throws JspTagException {
		try {
			Location theLocation = (Location)findAncestorWithClass(this, Location.class);
			return theLocation.getSeqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing Location for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing Location for seqnum tag ");
		}
	}

	public void setSeqnum(int seqnum) throws JspTagException {
		try {
			Location theLocation = (Location)findAncestorWithClass(this, Location.class);
			theLocation.setSeqnum(seqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing Location for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing Location for seqnum tag ");
		}
	}

}
