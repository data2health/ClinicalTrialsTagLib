package edu.uiowa.slis.ClinicalTrialsTagLib.biospecDescr;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class BiospecDescrTextblock extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(BiospecDescrTextblock.class);


	public int doStartTag() throws JspException {
		try {
			BiospecDescr theBiospecDescr = (BiospecDescr)findAncestorWithClass(this, BiospecDescr.class);
			if (!theBiospecDescr.commitNeeded) {
				pageContext.getOut().print(theBiospecDescr.getTextblock());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing BiospecDescr for textblock tag ", e);
			throw new JspTagException("Error: Can't find enclosing BiospecDescr for textblock tag ");
		}
		return SKIP_BODY;
	}

	public String getTextblock() throws JspTagException {
		try {
			BiospecDescr theBiospecDescr = (BiospecDescr)findAncestorWithClass(this, BiospecDescr.class);
			return theBiospecDescr.getTextblock();
		} catch (Exception e) {
			log.error(" Can't find enclosing BiospecDescr for textblock tag ", e);
			throw new JspTagException("Error: Can't find enclosing BiospecDescr for textblock tag ");
		}
	}

	public void setTextblock(String textblock) throws JspTagException {
		try {
			BiospecDescr theBiospecDescr = (BiospecDescr)findAncestorWithClass(this, BiospecDescr.class);
			theBiospecDescr.setTextblock(textblock);
		} catch (Exception e) {
			log.error("Can't find enclosing BiospecDescr for textblock tag ", e);
			throw new JspTagException("Error: Can't find enclosing BiospecDescr for textblock tag ");
		}
	}

}
