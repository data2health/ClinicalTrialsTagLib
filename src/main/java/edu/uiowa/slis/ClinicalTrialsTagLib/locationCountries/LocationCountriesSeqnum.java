package edu.uiowa.slis.ClinicalTrialsTagLib.locationCountries;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class LocationCountriesSeqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(LocationCountriesSeqnum.class);


	public int doStartTag() throws JspException {
		try {
			LocationCountries theLocationCountries = (LocationCountries)findAncestorWithClass(this, LocationCountries.class);
			if (!theLocationCountries.commitNeeded) {
				pageContext.getOut().print(theLocationCountries.getSeqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing LocationCountries for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing LocationCountries for seqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getSeqnum() throws JspTagException {
		try {
			LocationCountries theLocationCountries = (LocationCountries)findAncestorWithClass(this, LocationCountries.class);
			return theLocationCountries.getSeqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing LocationCountries for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing LocationCountries for seqnum tag ");
		}
	}

	public void setSeqnum(int seqnum) throws JspTagException {
		try {
			LocationCountries theLocationCountries = (LocationCountries)findAncestorWithClass(this, LocationCountries.class);
			theLocationCountries.setSeqnum(seqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing LocationCountries for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing LocationCountries for seqnum tag ");
		}
	}

}
