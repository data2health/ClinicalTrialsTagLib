package edu.uiowa.slis.ClinicalTrialsTagLib.otherEventCount;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class OtherEventCountSubjectsAffected extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(OtherEventCountSubjectsAffected.class);


	public int doStartTag() throws JspException {
		try {
			OtherEventCount theOtherEventCount = (OtherEventCount)findAncestorWithClass(this, OtherEventCount.class);
			if (!theOtherEventCount.commitNeeded) {
				pageContext.getOut().print(theOtherEventCount.getSubjectsAffected());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing OtherEventCount for subjectsAffected tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherEventCount for subjectsAffected tag ");
		}
		return SKIP_BODY;
	}

	public String getSubjectsAffected() throws JspTagException {
		try {
			OtherEventCount theOtherEventCount = (OtherEventCount)findAncestorWithClass(this, OtherEventCount.class);
			return theOtherEventCount.getSubjectsAffected();
		} catch (Exception e) {
			log.error(" Can't find enclosing OtherEventCount for subjectsAffected tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherEventCount for subjectsAffected tag ");
		}
	}

	public void setSubjectsAffected(String subjectsAffected) throws JspTagException {
		try {
			OtherEventCount theOtherEventCount = (OtherEventCount)findAncestorWithClass(this, OtherEventCount.class);
			theOtherEventCount.setSubjectsAffected(subjectsAffected);
		} catch (Exception e) {
			log.error("Can't find enclosing OtherEventCount for subjectsAffected tag ", e);
			throw new JspTagException("Error: Can't find enclosing OtherEventCount for subjectsAffected tag ");
		}
	}

}
