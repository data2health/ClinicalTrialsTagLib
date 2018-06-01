package edu.uiowa.slis.ClinicalTrialsTagLib.resultReference;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultReferencePmid extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultReferencePmid.class);


	public int doStartTag() throws JspException {
		try {
			ResultReference theResultReference = (ResultReference)findAncestorWithClass(this, ResultReference.class);
			if (!theResultReference.commitNeeded) {
				pageContext.getOut().print(theResultReference.getPmid());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultReference for pmid tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultReference for pmid tag ");
		}
		return SKIP_BODY;
	}

	public String getPmid() throws JspTagException {
		try {
			ResultReference theResultReference = (ResultReference)findAncestorWithClass(this, ResultReference.class);
			return theResultReference.getPmid();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultReference for pmid tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultReference for pmid tag ");
		}
	}

	public void setPmid(String pmid) throws JspTagException {
		try {
			ResultReference theResultReference = (ResultReference)findAncestorWithClass(this, ResultReference.class);
			theResultReference.setPmid(pmid);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultReference for pmid tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultReference for pmid tag ");
		}
	}

}
