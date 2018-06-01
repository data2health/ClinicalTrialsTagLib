package edu.uiowa.slis.ClinicalTrialsTagLib.secondaryId;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class SecondaryIdSecondaryId extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(SecondaryIdSecondaryId.class);


	public int doStartTag() throws JspException {
		try {
			SecondaryId theSecondaryId = (SecondaryId)findAncestorWithClass(this, SecondaryId.class);
			if (!theSecondaryId.commitNeeded) {
				pageContext.getOut().print(theSecondaryId.getSecondaryId());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing SecondaryId for secondaryId tag ", e);
			throw new JspTagException("Error: Can't find enclosing SecondaryId for secondaryId tag ");
		}
		return SKIP_BODY;
	}

	public String getSecondaryId() throws JspTagException {
		try {
			SecondaryId theSecondaryId = (SecondaryId)findAncestorWithClass(this, SecondaryId.class);
			return theSecondaryId.getSecondaryId();
		} catch (Exception e) {
			log.error(" Can't find enclosing SecondaryId for secondaryId tag ", e);
			throw new JspTagException("Error: Can't find enclosing SecondaryId for secondaryId tag ");
		}
	}

	public void setSecondaryId(String secondaryId) throws JspTagException {
		try {
			SecondaryId theSecondaryId = (SecondaryId)findAncestorWithClass(this, SecondaryId.class);
			theSecondaryId.setSecondaryId(secondaryId);
		} catch (Exception e) {
			log.error("Can't find enclosing SecondaryId for secondaryId tag ", e);
			throw new JspTagException("Error: Can't find enclosing SecondaryId for secondaryId tag ");
		}
	}

}
