package edu.uiowa.slis.ClinicalTrialsTagLib.seriousEventCount;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class SeriousEventCountSubjectsAffected extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(SeriousEventCountSubjectsAffected.class);


	public int doStartTag() throws JspException {
		try {
			SeriousEventCount theSeriousEventCount = (SeriousEventCount)findAncestorWithClass(this, SeriousEventCount.class);
			if (!theSeriousEventCount.commitNeeded) {
				pageContext.getOut().print(theSeriousEventCount.getSubjectsAffected());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing SeriousEventCount for subjectsAffected tag ", e);
			throw new JspTagException("Error: Can't find enclosing SeriousEventCount for subjectsAffected tag ");
		}
		return SKIP_BODY;
	}

	public String getSubjectsAffected() throws JspTagException {
		try {
			SeriousEventCount theSeriousEventCount = (SeriousEventCount)findAncestorWithClass(this, SeriousEventCount.class);
			return theSeriousEventCount.getSubjectsAffected();
		} catch (Exception e) {
			log.error(" Can't find enclosing SeriousEventCount for subjectsAffected tag ", e);
			throw new JspTagException("Error: Can't find enclosing SeriousEventCount for subjectsAffected tag ");
		}
	}

	public void setSubjectsAffected(String subjectsAffected) throws JspTagException {
		try {
			SeriousEventCount theSeriousEventCount = (SeriousEventCount)findAncestorWithClass(this, SeriousEventCount.class);
			theSeriousEventCount.setSubjectsAffected(subjectsAffected);
		} catch (Exception e) {
			log.error("Can't find enclosing SeriousEventCount for subjectsAffected tag ", e);
			throw new JspTagException("Error: Can't find enclosing SeriousEventCount for subjectsAffected tag ");
		}
	}

}
