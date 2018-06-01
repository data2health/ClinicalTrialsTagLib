package edu.uiowa.slis.ClinicalTrialsTagLib.resultsReference;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsReferenceCitation extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsReferenceCitation.class);


	public int doStartTag() throws JspException {
		try {
			ResultsReference theResultsReference = (ResultsReference)findAncestorWithClass(this, ResultsReference.class);
			if (!theResultsReference.commitNeeded) {
				pageContext.getOut().print(theResultsReference.getCitation());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsReference for citation tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsReference for citation tag ");
		}
		return SKIP_BODY;
	}

	public String getCitation() throws JspTagException {
		try {
			ResultsReference theResultsReference = (ResultsReference)findAncestorWithClass(this, ResultsReference.class);
			return theResultsReference.getCitation();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsReference for citation tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsReference for citation tag ");
		}
	}

	public void setCitation(String citation) throws JspTagException {
		try {
			ResultsReference theResultsReference = (ResultsReference)findAncestorWithClass(this, ResultsReference.class);
			theResultsReference.setCitation(citation);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsReference for citation tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsReference for citation tag ");
		}
	}

}
