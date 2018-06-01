package edu.uiowa.slis.ClinicalTrialsTagLib.biospecDescr;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class BiospecDescrID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(BiospecDescrID.class);


	public int doStartTag() throws JspException {
		try {
			BiospecDescr theBiospecDescr = (BiospecDescr)findAncestorWithClass(this, BiospecDescr.class);
			if (!theBiospecDescr.commitNeeded) {
				pageContext.getOut().print(theBiospecDescr.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing BiospecDescr for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing BiospecDescr for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			BiospecDescr theBiospecDescr = (BiospecDescr)findAncestorWithClass(this, BiospecDescr.class);
			return theBiospecDescr.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing BiospecDescr for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing BiospecDescr for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			BiospecDescr theBiospecDescr = (BiospecDescr)findAncestorWithClass(this, BiospecDescr.class);
			theBiospecDescr.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing BiospecDescr for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing BiospecDescr for ID tag ");
		}
	}

}
