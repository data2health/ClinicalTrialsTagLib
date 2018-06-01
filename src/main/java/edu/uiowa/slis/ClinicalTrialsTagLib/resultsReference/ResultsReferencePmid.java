package edu.uiowa.slis.ClinicalTrialsTagLib.resultsReference;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsReferencePmid extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsReferencePmid.class);


	public int doStartTag() throws JspException {
		try {
			ResultsReference theResultsReference = (ResultsReference)findAncestorWithClass(this, ResultsReference.class);
			if (!theResultsReference.commitNeeded) {
				pageContext.getOut().print(theResultsReference.getPmid());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsReference for pmid tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsReference for pmid tag ");
		}
		return SKIP_BODY;
	}

	public String getPmid() throws JspTagException {
		try {
			ResultsReference theResultsReference = (ResultsReference)findAncestorWithClass(this, ResultsReference.class);
			return theResultsReference.getPmid();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsReference for pmid tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsReference for pmid tag ");
		}
	}

	public void setPmid(String pmid) throws JspTagException {
		try {
			ResultsReference theResultsReference = (ResultsReference)findAncestorWithClass(this, ResultsReference.class);
			theResultsReference.setPmid(pmid);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsReference for pmid tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsReference for pmid tag ");
		}
	}

}
