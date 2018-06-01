package edu.uiowa.slis.ClinicalTrialsTagLib.resultReference;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultReferenceCitation extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultReferenceCitation.class);


	public int doStartTag() throws JspException {
		try {
			ResultReference theResultReference = (ResultReference)findAncestorWithClass(this, ResultReference.class);
			if (!theResultReference.commitNeeded) {
				pageContext.getOut().print(theResultReference.getCitation());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultReference for citation tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultReference for citation tag ");
		}
		return SKIP_BODY;
	}

	public String getCitation() throws JspTagException {
		try {
			ResultReference theResultReference = (ResultReference)findAncestorWithClass(this, ResultReference.class);
			return theResultReference.getCitation();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultReference for citation tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultReference for citation tag ");
		}
	}

	public void setCitation(String citation) throws JspTagException {
		try {
			ResultReference theResultReference = (ResultReference)findAncestorWithClass(this, ResultReference.class);
			theResultReference.setCitation(citation);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultReference for citation tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultReference for citation tag ");
		}
	}

}
