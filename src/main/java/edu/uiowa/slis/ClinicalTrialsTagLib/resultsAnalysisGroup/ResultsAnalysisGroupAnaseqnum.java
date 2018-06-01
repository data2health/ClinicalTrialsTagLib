package edu.uiowa.slis.ClinicalTrialsTagLib.resultsAnalysisGroup;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsAnalysisGroupAnaseqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsAnalysisGroupAnaseqnum.class);


	public int doStartTag() throws JspException {
		try {
			ResultsAnalysisGroup theResultsAnalysisGroup = (ResultsAnalysisGroup)findAncestorWithClass(this, ResultsAnalysisGroup.class);
			if (!theResultsAnalysisGroup.commitNeeded) {
				pageContext.getOut().print(theResultsAnalysisGroup.getAnaseqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsAnalysisGroup for anaseqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysisGroup for anaseqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getAnaseqnum() throws JspTagException {
		try {
			ResultsAnalysisGroup theResultsAnalysisGroup = (ResultsAnalysisGroup)findAncestorWithClass(this, ResultsAnalysisGroup.class);
			return theResultsAnalysisGroup.getAnaseqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsAnalysisGroup for anaseqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysisGroup for anaseqnum tag ");
		}
	}

	public void setAnaseqnum(int anaseqnum) throws JspTagException {
		try {
			ResultsAnalysisGroup theResultsAnalysisGroup = (ResultsAnalysisGroup)findAncestorWithClass(this, ResultsAnalysisGroup.class);
			theResultsAnalysisGroup.setAnaseqnum(anaseqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsAnalysisGroup for anaseqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysisGroup for anaseqnum tag ");
		}
	}

}
