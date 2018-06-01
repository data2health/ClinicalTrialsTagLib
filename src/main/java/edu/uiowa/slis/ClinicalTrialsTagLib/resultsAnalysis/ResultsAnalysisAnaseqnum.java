package edu.uiowa.slis.ClinicalTrialsTagLib.resultsAnalysis;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsAnalysisAnaseqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsAnalysisAnaseqnum.class);


	public int doStartTag() throws JspException {
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			if (!theResultsAnalysis.commitNeeded) {
				pageContext.getOut().print(theResultsAnalysis.getAnaseqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsAnalysis for anaseqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysis for anaseqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getAnaseqnum() throws JspTagException {
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			return theResultsAnalysis.getAnaseqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsAnalysis for anaseqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysis for anaseqnum tag ");
		}
	}

	public void setAnaseqnum(int anaseqnum) throws JspTagException {
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			theResultsAnalysis.setAnaseqnum(anaseqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsAnalysis for anaseqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysis for anaseqnum tag ");
		}
	}

}
