package edu.uiowa.slis.ClinicalTrialsTagLib.resultsCategory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsCategoryCatseqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsCategoryCatseqnum.class);


	public int doStartTag() throws JspException {
		try {
			ResultsCategory theResultsCategory = (ResultsCategory)findAncestorWithClass(this, ResultsCategory.class);
			if (!theResultsCategory.commitNeeded) {
				pageContext.getOut().print(theResultsCategory.getCatseqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsCategory for catseqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsCategory for catseqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getCatseqnum() throws JspTagException {
		try {
			ResultsCategory theResultsCategory = (ResultsCategory)findAncestorWithClass(this, ResultsCategory.class);
			return theResultsCategory.getCatseqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsCategory for catseqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsCategory for catseqnum tag ");
		}
	}

	public void setCatseqnum(int catseqnum) throws JspTagException {
		try {
			ResultsCategory theResultsCategory = (ResultsCategory)findAncestorWithClass(this, ResultsCategory.class);
			theResultsCategory.setCatseqnum(catseqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsCategory for catseqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsCategory for catseqnum tag ");
		}
	}

}
