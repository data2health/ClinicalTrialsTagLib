package edu.uiowa.slis.ClinicalTrialsTagLib.otherEventCount;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class OtherEventCountSubjectsAtRisk extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(OtherEventCountSubjectsAtRisk.class);


	public int doStartTag() throws JspException {
		try {
			OtherEventCount theOtherEventCount = (OtherEventCount)findAncestorWithClass(this, OtherEventCount.class);
			if (!theOtherEventCount.commitNeeded) {
				pageContext.getOut().print(theOtherEventCount.getSubjectsAtRisk());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing OtherEventCount for subjectsAtRisk tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherEventCount for subjectsAtRisk tag ");
		}
		return SKIP_BODY;
	}

	public String getSubjectsAtRisk() throws JspTagException {
		try {
			OtherEventCount theOtherEventCount = (OtherEventCount)findAncestorWithClass(this, OtherEventCount.class);
			return theOtherEventCount.getSubjectsAtRisk();
		} catch (Exception e) {
			log.error(" Can't find enclosing OtherEventCount for subjectsAtRisk tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherEventCount for subjectsAtRisk tag ");
		}
	}

	public void setSubjectsAtRisk(String subjectsAtRisk) throws JspTagException {
		try {
			OtherEventCount theOtherEventCount = (OtherEventCount)findAncestorWithClass(this, OtherEventCount.class);
			theOtherEventCount.setSubjectsAtRisk(subjectsAtRisk);
		} catch (Exception e) {
			log.error("Can't find enclosing OtherEventCount for subjectsAtRisk tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherEventCount for subjectsAtRisk tag ");
		}
	}

}
