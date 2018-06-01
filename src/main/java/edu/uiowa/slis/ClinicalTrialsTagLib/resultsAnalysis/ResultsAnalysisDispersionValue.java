package edu.uiowa.slis.ClinicalTrialsTagLib.resultsAnalysis;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsAnalysisDispersionValue extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsAnalysisDispersionValue.class);


	public int doStartTag() throws JspException {
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			if (!theResultsAnalysis.commitNeeded) {
				pageContext.getOut().print(theResultsAnalysis.getDispersionValue());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsAnalysis for dispersionValue tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysis for dispersionValue tag ");
		}
		return SKIP_BODY;
	}

	public String getDispersionValue() throws JspTagException {
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			return theResultsAnalysis.getDispersionValue();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsAnalysis for dispersionValue tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysis for dispersionValue tag ");
		}
	}

	public void setDispersionValue(String dispersionValue) throws JspTagException {
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			theResultsAnalysis.setDispersionValue(dispersionValue);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsAnalysis for dispersionValue tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysis for dispersionValue tag ");
		}
	}

}
