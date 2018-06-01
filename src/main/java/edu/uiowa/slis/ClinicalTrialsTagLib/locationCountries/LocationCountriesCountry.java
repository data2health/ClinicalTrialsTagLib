package edu.uiowa.slis.ClinicalTrialsTagLib.locationCountries;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class LocationCountriesCountry extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(LocationCountriesCountry.class);


	public int doStartTag() throws JspException {
		try {
			LocationCountries theLocationCountries = (LocationCountries)findAncestorWithClass(this, LocationCountries.class);
			if (!theLocationCountries.commitNeeded) {
				pageContext.getOut().print(theLocationCountries.getCountry());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing LocationCountries for country tag ", e);
			throw new JspTagException("Error: Can't find enclosing LocationCountries for country tag ");
		}
		return SKIP_BODY;
	}

	public String getCountry() throws JspTagException {
		try {
			LocationCountries theLocationCountries = (LocationCountries)findAncestorWithClass(this, LocationCountries.class);
			return theLocationCountries.getCountry();
		} catch (Exception e) {
			log.error(" Can't find enclosing LocationCountries for country tag ", e);
			throw new JspTagException("Error: Can't find enclosing LocationCountries for country tag ");
		}
	}

	public void setCountry(String country) throws JspTagException {
		try {
			LocationCountries theLocationCountries = (LocationCountries)findAncestorWithClass(this, LocationCountries.class);
			theLocationCountries.setCountry(country);
		} catch (Exception e) {
			log.error("Can't find enclosing LocationCountries for country tag ", e);
			throw new JspTagException("Error: Can't find enclosing LocationCountries for country tag ");
		}
	}

}
