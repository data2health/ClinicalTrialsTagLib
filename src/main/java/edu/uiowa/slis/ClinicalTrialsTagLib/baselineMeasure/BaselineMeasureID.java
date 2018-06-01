package edu.uiowa.slis.ClinicalTrialsTagLib.baselineMeasure;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class BaselineMeasureID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(BaselineMeasureID.class);


	public int doStartTag() throws JspException {
		try {
			BaselineMeasure theBaselineMeasure = (BaselineMeasure)findAncestorWithClass(this, BaselineMeasure.class);
			if (!theBaselineMeasure.commitNeeded) {
				pageContext.getOut().print(theBaselineMeasure.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing BaselineMeasure for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineMeasure for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			BaselineMeasure theBaselineMeasure = (BaselineMeasure)findAncestorWithClass(this, BaselineMeasure.class);
			return theBaselineMeasure.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing BaselineMeasure for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineMeasure for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			BaselineMeasure theBaselineMeasure = (BaselineMeasure)findAncestorWithClass(this, BaselineMeasure.class);
			theBaselineMeasure.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing BaselineMeasure for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing BaselineMeasure for ID tag ");
		}
	}

}
