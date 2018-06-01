package edu.uiowa.slis.ClinicalTrialsTagLib.resultsAnalysis;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsAnalysisCiNSides extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsAnalysisCiNSides.class);


	public int doStartTag() throws JspException {
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			if (!theResultsAnalysis.commitNeeded) {
				pageContext.getOut().print(theResultsAnalysis.getCiNSides());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsAnalysis for ciNSides tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysis for ciNSides tag ");
		}
		return SKIP_BODY;
	}

	public String getCiNSides() throws JspTagException {
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			return theResultsAnalysis.getCiNSides();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsAnalysis for ciNSides tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysis for ciNSides tag ");
		}
	}

	public void setCiNSides(String ciNSides) throws JspTagException {
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			theResultsAnalysis.setCiNSides(ciNSides);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsAnalysis for ciNSides tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysis for ciNSides tag ");
		}
	}

}
