package edu.uiowa.slis.ClinicalTrialsTagLib.resultsAnalysis;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsAnalysisID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsAnalysisID.class);


	public int doStartTag() throws JspException {
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			if (!theResultsAnalysis.commitNeeded) {
				pageContext.getOut().print(theResultsAnalysis.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsAnalysis for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysis for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			return theResultsAnalysis.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsAnalysis for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysis for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			theResultsAnalysis.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsAnalysis for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysis for ID tag ");
		}
	}

}
