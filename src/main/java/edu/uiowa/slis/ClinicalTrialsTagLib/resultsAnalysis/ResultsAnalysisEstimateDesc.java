package edu.uiowa.slis.ClinicalTrialsTagLib.resultsAnalysis;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsAnalysisEstimateDesc extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsAnalysisEstimateDesc.class);


	public int doStartTag() throws JspException {
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			if (!theResultsAnalysis.commitNeeded) {
				pageContext.getOut().print(theResultsAnalysis.getEstimateDesc());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsAnalysis for estimateDesc tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysis for estimateDesc tag ");
		}
		return SKIP_BODY;
	}

	public String getEstimateDesc() throws JspTagException {
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			return theResultsAnalysis.getEstimateDesc();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsAnalysis for estimateDesc tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysis for estimateDesc tag ");
		}
	}

	public void setEstimateDesc(String estimateDesc) throws JspTagException {
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			theResultsAnalysis.setEstimateDesc(estimateDesc);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsAnalysis for estimateDesc tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysis for estimateDesc tag ");
		}
	}

}
