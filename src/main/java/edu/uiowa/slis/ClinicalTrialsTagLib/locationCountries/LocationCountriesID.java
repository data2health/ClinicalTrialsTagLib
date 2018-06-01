package edu.uiowa.slis.ClinicalTrialsTagLib.locationCountries;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class LocationCountriesID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(LocationCountriesID.class);


	public int doStartTag() throws JspException {
		try {
			LocationCountries theLocationCountries = (LocationCountries)findAncestorWithClass(this, LocationCountries.class);
			if (!theLocationCountries.commitNeeded) {
				pageContext.getOut().print(theLocationCountries.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing LocationCountries for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing LocationCountries for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			LocationCountries theLocationCountries = (LocationCountries)findAncestorWithClass(this, LocationCountries.class);
			return theLocationCountries.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing LocationCountries for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing LocationCountries for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			LocationCountries theLocationCountries = (LocationCountries)findAncestorWithClass(this, LocationCountries.class);
			theLocationCountries.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing LocationCountries for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing LocationCountries for ID tag ");
		}
	}

}
