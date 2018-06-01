package edu.uiowa.slis.ClinicalTrialsTagLib.resultsGroup;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultsGroupGroupId extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultsGroupGroupId.class);


	public int doStartTag() throws JspException {
		try {
			ResultsGroup theResultsGroup = (ResultsGroup)findAncestorWithClass(this, ResultsGroup.class);
			if (!theResultsGroup.commitNeeded) {
				pageContext.getOut().print(theResultsGroup.getGroupId());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsGroup for groupId tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsGroup for groupId tag ");
		}
		return SKIP_BODY;
	}

	public String getGroupId() throws JspTagException {
		try {
			ResultsGroup theResultsGroup = (ResultsGroup)findAncestorWithClass(this, ResultsGroup.class);
			return theResultsGroup.getGroupId();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultsGroup for groupId tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsGroup for groupId tag ");
		}
	}

	public void setGroupId(String groupId) throws JspTagException {
		try {
			ResultsGroup theResultsGroup = (ResultsGroup)findAncestorWithClass(this, ResultsGroup.class);
			theResultsGroup.setGroupId(groupId);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultsGroup for groupId tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultsGroup for groupId tag ");
		}
	}

}
