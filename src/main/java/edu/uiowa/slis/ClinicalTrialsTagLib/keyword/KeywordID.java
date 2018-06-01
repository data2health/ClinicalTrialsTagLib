package edu.uiowa.slis.ClinicalTrialsTagLib.keyword;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class KeywordID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(KeywordID.class);


	public int doStartTag() throws JspException {
		try {
			Keyword theKeyword = (Keyword)findAncestorWithClass(this, Keyword.class);
			if (!theKeyword.commitNeeded) {
				pageContext.getOut().print(theKeyword.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Keyword for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing Keyword for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			Keyword theKeyword = (Keyword)findAncestorWithClass(this, Keyword.class);
			return theKeyword.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing Keyword for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing Keyword for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			Keyword theKeyword = (Keyword)findAncestorWithClass(this, Keyword.class);
			theKeyword.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing Keyword for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing Keyword for ID tag ");
		}
	}

}
