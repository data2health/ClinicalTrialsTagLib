package edu.uiowa.slis.ClinicalTrialsTagLib.resultsAnalysis;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsAnalysisPValueDesc extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsAnalysisPValueDesc.class);


	public int doStartTag() throws JspException {
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			if (!theResultsAnalysis.commitNeeded) {
				pageContext.getOut().print(theResultsAnalysis.getPValueDesc());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsAnalysis for pValueDesc tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysis for pValueDesc tag ");
		}
		return SKIP_BODY;
	}

	public String getPValueDesc() throws JspTagException {
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			return theResultsAnalysis.getPValueDesc();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsAnalysis for pValueDesc tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysis for pValueDesc tag ");
		}
	}

	public void setPValueDesc(String pValueDesc) throws JspTagException {
		try {
			ResultsAnalysis theResultsAnalysis = (ResultsAnalysis)findAncestorWithClass(this, ResultsAnalysis.class);
			theResultsAnalysis.setPValueDesc(pValueDesc);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsAnalysis for pValueDesc tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysis for pValueDesc tag ");
		}
	}

}
