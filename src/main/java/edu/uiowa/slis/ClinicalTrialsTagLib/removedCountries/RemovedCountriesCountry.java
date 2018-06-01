package edu.uiowa.slis.ClinicalTrialsTagLib.removedCountries;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class RemovedCountriesCountry extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(RemovedCountriesCountry.class);


	public int doStartTag() throws JspException {
		try {
			RemovedCountries theRemovedCountries = (RemovedCountries)findAncestorWithClass(this, RemovedCountries.class);
			if (!theRemovedCountries.commitNeeded) {
				pageContext.getOut().print(theRemovedCountries.getCountry());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing RemovedCountries for country tag ", e);
			throw new JspTagException("Error: Can't find enclosing RemovedCountries for country tag ");
		}
		return SKIP_BODY;
	}

	public String getCountry() throws JspTagException {
		try {
			RemovedCountries theRemovedCountries = (RemovedCountries)findAncestorWithClass(this, RemovedCountries.class);
			return theRemovedCountries.getCountry();
		} catch (Exception e) {
			log.error(" Can't find enclosing RemovedCountries for country tag ", e);
			throw new JspTagException("Error: Can't find enclosing RemovedCountries for country tag ");
		}
	}

	public void setCountry(String country) throws JspTagException {
		try {
			RemovedCountries theRemovedCountries = (RemovedCountries)findAncestorWithClass(this, RemovedCountries.class);
			theRemovedCountries.setCountry(country);
		} catch (Exception e) {
			log.error("Can't find enclosing RemovedCountries for country tag ", e);
			throw new JspTagException("Error: Can't find enclosing RemovedCountries for country tag ");
		}
	}

}
