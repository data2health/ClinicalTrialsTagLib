package edu.uiowa.slis.ClinicalTrialsTagLib.resultsGroup;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsGroupDescription extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsGroupDescription.class);


	public int doStartTag() throws JspException {
		try {
			ResultsGroup theResultsGroup = (ResultsGroup)findAncestorWithClass(this, ResultsGroup.class);
			if (!theResultsGroup.commitNeeded) {
				pageContext.getOut().print(theResultsGroup.getDescription());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsGroup for description tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsGroup for description tag ");
		}
		return SKIP_BODY;
	}

	public String getDescription() throws JspTagException {
		try {
			ResultsGroup theResultsGroup = (ResultsGroup)findAncestorWithClass(this, ResultsGroup.class);
			return theResultsGroup.getDescription();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsGroup for description tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsGroup for description tag ");
		}
	}

	public void setDescription(String description) throws JspTagException {
		try {
			ResultsGroup theResultsGroup = (ResultsGroup)findAncestorWithClass(this, ResultsGroup.class);
			theResultsGroup.setDescription(description);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsGroup for description tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsGroup for description tag ");
		}
	}

}
