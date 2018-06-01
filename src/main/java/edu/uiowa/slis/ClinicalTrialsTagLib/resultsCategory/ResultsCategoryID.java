package edu.uiowa.slis.ClinicalTrialsTagLib.resultsCategory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsCategoryID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsCategoryID.class);


	public int doStartTag() throws JspException {
		try {
			ResultsCategory theResultsCategory = (ResultsCategory)findAncestorWithClass(this, ResultsCategory.class);
			if (!theResultsCategory.commitNeeded) {
				pageContext.getOut().print(theResultsCategory.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsCategory for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsCategory for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			ResultsCategory theResultsCategory = (ResultsCategory)findAncestorWithClass(this, ResultsCategory.class);
			return theResultsCategory.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsCategory for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsCategory for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			ResultsCategory theResultsCategory = (ResultsCategory)findAncestorWithClass(this, ResultsCategory.class);
			theResultsCategory.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsCategory for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsCategory for ID tag ");
		}
	}

}
