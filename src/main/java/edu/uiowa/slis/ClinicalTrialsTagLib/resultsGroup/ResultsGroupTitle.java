package edu.uiowa.slis.ClinicalTrialsTagLib.resultsGroup;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsGroupTitle extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsGroupTitle.class);


	public int doStartTag() throws JspException {
		try {
			ResultsGroup theResultsGroup = (ResultsGroup)findAncestorWithClass(this, ResultsGroup.class);
			if (!theResultsGroup.commitNeeded) {
				pageContext.getOut().print(theResultsGroup.getTitle());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsGroup for title tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsGroup for title tag ");
		}
		return SKIP_BODY;
	}

	public String getTitle() throws JspTagException {
		try {
			ResultsGroup theResultsGroup = (ResultsGroup)findAncestorWithClass(this, ResultsGroup.class);
			return theResultsGroup.getTitle();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsGroup for title tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsGroup for title tag ");
		}
	}

	public void setTitle(String title) throws JspTagException {
		try {
			ResultsGroup theResultsGroup = (ResultsGroup)findAncestorWithClass(this, ResultsGroup.class);
			theResultsGroup.setTitle(title);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsGroup for title tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsGroup for title tag ");
		}
	}

}
