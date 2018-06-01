package edu.uiowa.slis.ClinicalTrialsTagLib.reference;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ReferenceID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ReferenceID.class);


	public int doStartTag() throws JspException {
		try {
			Reference theReference = (Reference)findAncestorWithClass(this, Reference.class);
			if (!theReference.commitNeeded) {
				pageContext.getOut().print(theReference.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Reference for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing Reference for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			Reference theReference = (Reference)findAncestorWithClass(this, Reference.class);
			return theReference.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing Reference for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing Reference for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			Reference theReference = (Reference)findAncestorWithClass(this, Reference.class);
			theReference.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing Reference for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing Reference for ID tag ");
		}
	}

}
