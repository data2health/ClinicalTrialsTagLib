package edu.uiowa.slis.ClinicalTrialsTagLib.resultsReference;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsReferenceID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsReferenceID.class);


	public int doStartTag() throws JspException {
		try {
			ResultsReference theResultsReference = (ResultsReference)findAncestorWithClass(this, ResultsReference.class);
			if (!theResultsReference.commitNeeded) {
				pageContext.getOut().print(theResultsReference.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsReference for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsReference for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			ResultsReference theResultsReference = (ResultsReference)findAncestorWithClass(this, ResultsReference.class);
			return theResultsReference.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsReference for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsReference for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			ResultsReference theResultsReference = (ResultsReference)findAncestorWithClass(this, ResultsReference.class);
			theResultsReference.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsReference for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsReference for ID tag ");
		}
	}

}
