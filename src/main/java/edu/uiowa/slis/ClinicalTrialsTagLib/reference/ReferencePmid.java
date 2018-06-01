package edu.uiowa.slis.ClinicalTrialsTagLib.reference;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ReferencePmid extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ReferencePmid.class);


	public int doStartTag() throws JspException {
		try {
			Reference theReference = (Reference)findAncestorWithClass(this, Reference.class);
			if (!theReference.commitNeeded) {
				pageContext.getOut().print(theReference.getPmid());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Reference for pmid tag ", e);
			throw new JspTagException("Error: Can't find enclosing Reference for pmid tag ");
		}
		return SKIP_BODY;
	}

	public String getPmid() throws JspTagException {
		try {
			Reference theReference = (Reference)findAncestorWithClass(this, Reference.class);
			return theReference.getPmid();
		} catch (Exception e) {
			log.error(" Can't find enclosing Reference for pmid tag ", e);
			throw new JspTagException("Error: Can't find enclosing Reference for pmid tag ");
		}
	}

	public void setPmid(String pmid) throws JspTagException {
		try {
			Reference theReference = (Reference)findAncestorWithClass(this, Reference.class);
			theReference.setPmid(pmid);
		} catch (Exception e) {
			log.error("Can't find enclosing Reference for pmid tag ", e);
			throw new JspTagException("Error: Can't find enclosing Reference for pmid tag ");
		}
	}

}
