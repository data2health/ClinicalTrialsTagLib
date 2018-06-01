package edu.uiowa.slis.ClinicalTrialsTagLib.reference;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ReferenceCitation extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ReferenceCitation.class);


	public int doStartTag() throws JspException {
		try {
			Reference theReference = (Reference)findAncestorWithClass(this, Reference.class);
			if (!theReference.commitNeeded) {
				pageContext.getOut().print(theReference.getCitation());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Reference for citation tag ", e);
			throw new JspTagException("Error: Can't find enclosing Reference for citation tag ");
		}
		return SKIP_BODY;
	}

	public String getCitation() throws JspTagException {
		try {
			Reference theReference = (Reference)findAncestorWithClass(this, Reference.class);
			return theReference.getCitation();
		} catch (Exception e) {
			log.error(" Can't find enclosing Reference for citation tag ", e);
			throw new JspTagException("Error: Can't find enclosing Reference for citation tag ");
		}
	}

	public void setCitation(String citation) throws JspTagException {
		try {
			Reference theReference = (Reference)findAncestorWithClass(this, Reference.class);
			theReference.setCitation(citation);
		} catch (Exception e) {
			log.error("Can't find enclosing Reference for citation tag ", e);
			throw new JspTagException("Error: Can't find enclosing Reference for citation tag ");
		}
	}

}
