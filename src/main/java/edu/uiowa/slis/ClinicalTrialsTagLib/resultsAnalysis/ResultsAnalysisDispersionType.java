package edu.uiowa.slis.ClinicalTrialsTagLib.resultsAnalysis;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsAnalysisDispersionType extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsAnalysisDispersionType.class);


	public int doStartTag() throws JspException {
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			if (!theResultsAnalysis.commitNeeded) {
				pageContext.getOut().print(theResultsAnalysis.getDispersionType());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsAnalysis for dispersionType tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysis for dispersionType tag ");
		}
		return SKIP_BODY;
	}

	public String getDispersionType() throws JspTagException {
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			return theResultsAnalysis.getDispersionType();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsAnalysis for dispersionType tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysis for dispersionType tag ");
		}
	}

	public void setDispersionType(String dispersionType) throws JspTagException {
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			theResultsAnalysis.setDispersionType(dispersionType);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsAnalysis for dispersionType tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysis for dispersionType tag ");
		}
	}

}
