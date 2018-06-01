package edu.uiowa.slis.ClinicalTrialsTagLib.seriousEventCount;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class SeriousEventCountSubjectsAtRisk extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(SeriousEventCountSubjectsAtRisk.class);


	public int doStartTag() throws JspException {
		try {
			SeriousEventCount theSeriousEventCount = (SeriousEventCount)findAncestorWithClass(this, SeriousEventCount.class);
			if (!theSeriousEventCount.commitNeeded) {
				pageContext.getOut().print(theSeriousEventCount.getSubjectsAtRisk());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing SeriousEventCount for subjectsAtRisk tag ", e);
			throw new JspTagException("Error: Can't find enclosing SeriousEventCount for subjectsAtRisk tag ");
		}
		return SKIP_BODY;
	}

	public String getSubjectsAtRisk() throws JspTagException {
		try {
			SeriousEventCount theSeriousEventCount = (SeriousEventCount)findAncestorWithClass(this, SeriousEventCount.class);
			return theSeriousEventCount.getSubjectsAtRisk();
		} catch (Exception e) {
			log.error(" Can't find enclosing SeriousEventCount for subjectsAtRisk tag ", e);
			throw new JspTagException("Error: Can't find enclosing SeriousEventCount for subjectsAtRisk tag ");
		}
	}

	public void setSubjectsAtRisk(String subjectsAtRisk) throws JspTagException {
		try {
			SeriousEventCount theSeriousEventCount = (SeriousEventCount)findAncestorWithClass(this, SeriousEventCount.class);
			theSeriousEventCount.setSubjectsAtRisk(subjectsAtRisk);
		} catch (Exception e) {
			log.error("Can't find enclosing SeriousEventCount for subjectsAtRisk tag ", e);
			throw new JspTagException("Error: Can't find enclosing SeriousEventCount for subjectsAtRisk tag ");
		}
	}

}
