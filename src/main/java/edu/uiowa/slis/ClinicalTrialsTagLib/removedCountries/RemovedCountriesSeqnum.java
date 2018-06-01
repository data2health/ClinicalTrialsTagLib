package edu.uiowa.slis.ClinicalTrialsTagLib.removedCountries;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class RemovedCountriesSeqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(RemovedCountriesSeqnum.class);


	public int doStartTag() throws JspException {
		try {
			RemovedCountries theRemovedCountries = (RemovedCountries)findAncestorWithClass(this, RemovedCountries.class);
			if (!theRemovedCountries.commitNeeded) {
				pageContext.getOut().print(theRemovedCountries.getSeqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing RemovedCountries for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing RemovedCountries for seqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getSeqnum() throws JspTagException {
		try {
			RemovedCountries theRemovedCountries = (RemovedCountries)findAncestorWithClass(this, RemovedCountries.class);
			return theRemovedCountries.getSeqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing RemovedCountries for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing RemovedCountries for seqnum tag ");
		}
	}

	public void setSeqnum(int seqnum) throws JspTagException {
		try {
			RemovedCountries theRemovedCountries = (RemovedCountries)findAncestorWithClass(this, RemovedCountries.class);
			theRemovedCountries.setSeqnum(seqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing RemovedCountries for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing RemovedCountries for seqnum tag ");
		}
	}

}
