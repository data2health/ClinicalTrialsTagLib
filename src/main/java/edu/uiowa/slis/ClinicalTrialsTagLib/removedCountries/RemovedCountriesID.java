package edu.uiowa.slis.ClinicalTrialsTagLib.removedCountries;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class RemovedCountriesID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(RemovedCountriesID.class);


	public int doStartTag() throws JspException {
		try {
			RemovedCountries theRemovedCountries = (RemovedCountries)findAncestorWithClass(this, RemovedCountries.class);
			if (!theRemovedCountries.commitNeeded) {
				pageContext.getOut().print(theRemovedCountries.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing RemovedCountries for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing RemovedCountries for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			RemovedCountries theRemovedCountries = (RemovedCountries)findAncestorWithClass(this, RemovedCountries.class);
			return theRemovedCountries.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing RemovedCountries for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing RemovedCountries for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			RemovedCountries theRemovedCountries = (RemovedCountries)findAncestorWithClass(this, RemovedCountries.class);
			theRemovedCountries.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing RemovedCountries for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing RemovedCountries for ID tag ");
		}
	}

}
