package edu.uiowa.slis.ClinicalTrialsTagLib.resultsCategory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsCategoryMeaseqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsCategoryMeaseqnum.class);


	public int doStartTag() throws JspException {
		try {
			ResultsCategory theResultsCategory = (ResultsCategory)findAncestorWithClass(this, ResultsCategory.class);
			if (!theResultsCategory.commitNeeded) {
				pageContext.getOut().print(theResultsCategory.getMeaseqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsCategory for measeqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsCategory for measeqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getMeaseqnum() throws JspTagException {
		try {
			ResultsCategory theResultsCategory = (ResultsCategory)findAncestorWithClass(this, ResultsCategory.class);
			return theResultsCategory.getMeaseqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsCategory for measeqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsCategory for measeqnum tag ");
		}
	}

	public void setMeaseqnum(int measeqnum) throws JspTagException {
		try {
			ResultsCategory theResultsCategory = (ResultsCategory)findAncestorWithClass(this, ResultsCategory.class);
			theResultsCategory.setMeaseqnum(measeqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsCategory for measeqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsCategory for measeqnum tag ");
		}
	}

}
