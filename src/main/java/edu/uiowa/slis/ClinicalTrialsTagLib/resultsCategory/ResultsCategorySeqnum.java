package edu.uiowa.slis.ClinicalTrialsTagLib.resultsCategory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsCategorySeqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsCategorySeqnum.class);


	public int doStartTag() throws JspException {
		try {
			ResultsCategory theResultsCategory = (ResultsCategory)findAncestorWithClass(this, ResultsCategory.class);
			if (!theResultsCategory.commitNeeded) {
				pageContext.getOut().print(theResultsCategory.getSeqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsCategory for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsCategory for seqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getSeqnum() throws JspTagException {
		try {
			ResultsCategory theResultsCategory = (ResultsCategory)findAncestorWithClass(this, ResultsCategory.class);
			return theResultsCategory.getSeqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsCategory for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsCategory for seqnum tag ");
		}
	}

	public void setSeqnum(int seqnum) throws JspTagException {
		try {
			ResultsCategory theResultsCategory = (ResultsCategory)findAncestorWithClass(this, ResultsCategory.class);
			theResultsCategory.setSeqnum(seqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsCategory for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsCategory for seqnum tag ");
		}
	}

}
