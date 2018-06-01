package edu.uiowa.slis.ClinicalTrialsTagLib.secondaryId;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class SecondaryIdID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(SecondaryIdID.class);


	public int doStartTag() throws JspException {
		try {
			SecondaryId theSecondaryId = (SecondaryId)findAncestorWithClass(this, SecondaryId.class);
			if (!theSecondaryId.commitNeeded) {
				pageContext.getOut().print(theSecondaryId.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing SecondaryId for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing SecondaryId for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			SecondaryId theSecondaryId = (SecondaryId)findAncestorWithClass(this, SecondaryId.class);
			return theSecondaryId.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing SecondaryId for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing SecondaryId for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			SecondaryId theSecondaryId = (SecondaryId)findAncestorWithClass(this, SecondaryId.class);
			theSecondaryId.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing SecondaryId for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing SecondaryId for ID tag ");
		}
	}

}
