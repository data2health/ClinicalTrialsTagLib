package edu.uiowa.slis.ClinicalTrialsTagLib.resultsAnalysisGroup;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsAnalysisGroupID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsAnalysisGroupID.class);


	public int doStartTag() throws JspException {
		try {
			ResultsAnalysisGroup theResultsAnalysisGroup = (ResultsAnalysisGroup)findAncestorWithClass(this, ResultsAnalysisGroup.class);
			if (!theResultsAnalysisGroup.commitNeeded) {
				pageContext.getOut().print(theResultsAnalysisGroup.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsAnalysisGroup for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysisGroup for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			ResultsAnalysisGroup theResultsAnalysisGroup = (ResultsAnalysisGroup)findAncestorWithClass(this, ResultsAnalysisGroup.class);
			return theResultsAnalysisGroup.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsAnalysisGroup for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysisGroup for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			ResultsAnalysisGroup theResultsAnalysisGroup = (ResultsAnalysisGroup)findAncestorWithClass(this, ResultsAnalysisGroup.class);
			theResultsAnalysisGroup.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsAnalysisGroup for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsAnalysisGroup for ID tag ");
		}
	}

}
