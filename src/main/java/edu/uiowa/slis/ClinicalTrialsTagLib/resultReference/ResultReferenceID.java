package edu.uiowa.slis.ClinicalTrialsTagLib.resultReference;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ResultReferenceID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ResultReferenceID.class);


	public int doStartTag() throws JspException {
		try {
			ResultReference theResultReference = (ResultReference)findAncestorWithClass(this, ResultReference.class);
			if (!theResultReference.commitNeeded) {
				pageContext.getOut().print(theResultReference.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ResultReference for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultReference for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			ResultReference theResultReference = (ResultReference)findAncestorWithClass(this, ResultReference.class);
			return theResultReference.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing ResultReference for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultReference for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			ResultReference theResultReference = (ResultReference)findAncestorWithClass(this, ResultReference.class);
			theResultReference.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing ResultReference for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing ResultReference for ID tag ");
		}
	}

}
