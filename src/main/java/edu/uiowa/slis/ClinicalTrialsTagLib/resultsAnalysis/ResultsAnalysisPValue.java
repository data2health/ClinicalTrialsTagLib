package edu.uiowa.slis.ClinicalTrialsTagLib.resultsAnalysis;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsAnalysisPValue extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsAnalysisPValue.class);


	public int doStartTag() throws JspException {
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			if (!theResultsAnalysis.commitNeeded) {
				pageContext.getOut().print(theResultsAnalysis.getPValue());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsAnalysis for pValue tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysis for pValue tag ");
		}
		return SKIP_BODY;
	}

	public String getPValue() throws JspTagException {
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			return theResultsAnalysis.getPValue();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsAnalysis for pValue tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysis for pValue tag ");
		}
	}

	public void setPValue(String pValue) throws JspTagException {
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			theResultsAnalysis.setPValue(pValue);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsAnalysis for pValue tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysis for pValue tag ");
		}
	}

}
