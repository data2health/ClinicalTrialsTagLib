package edu.uiowa.slis.ClinicalTrialsTagLib.resultsGroup;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsGroupID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsGroupID.class);


	public int doStartTag() throws JspException {
		try {
			ResultsGroup theResultsGroup = (ResultsGroup)findAncestorWithClass(this, ResultsGroup.class);
			if (!theResultsGroup.commitNeeded) {
				pageContext.getOut().print(theResultsGroup.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsGroup for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsGroup for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			ResultsGroup theResultsGroup = (ResultsGroup)findAncestorWithClass(this, ResultsGroup.class);
			return theResultsGroup.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsGroup for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsGroup for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			ResultsGroup theResultsGroup = (ResultsGroup)findAncestorWithClass(this, ResultsGroup.class);
			theResultsGroup.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsGroup for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsGroup for ID tag ");
		}
	}

}
