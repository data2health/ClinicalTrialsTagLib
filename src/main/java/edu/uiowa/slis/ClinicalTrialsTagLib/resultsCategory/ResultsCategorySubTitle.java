package edu.uiowa.slis.ClinicalTrialsTagLib.resultsCategory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsCategorySubTitle extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsCategorySubTitle.class);


	public int doStartTag() throws JspException {
		try {
			ResultsCategory theResultsCategory = (ResultsCategory)findAncestorWithClass(this, ResultsCategory.class);
			if (!theResultsCategory.commitNeeded) {
				pageContext.getOut().print(theResultsCategory.getSubTitle());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsCategory for subTitle tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsCategory for subTitle tag ");
		}
		return SKIP_BODY;
	}

	public String getSubTitle() throws JspTagException {
		try {
			ResultsCategory theResultsCategory = (ResultsCategory)findAncestorWithClass(this, ResultsCategory.class);
			return theResultsCategory.getSubTitle();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsCategory for subTitle tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsCategory for subTitle tag ");
		}
	}

	public void setSubTitle(String subTitle) throws JspTagException {
		try {
			ResultsCategory theResultsCategory = (ResultsCategory)findAncestorWithClass(this, ResultsCategory.class);
			theResultsCategory.setSubTitle(subTitle);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsCategory for subTitle tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsCategory for subTitle tag ");
		}
	}

}
