package edu.uiowa.slis.ClinicalTrialsTagLib.biospecDescr;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class BiospecDescrSeqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(BiospecDescrSeqnum.class);


	public int doStartTag() throws JspException {
		try {
			BiospecDescr theBiospecDescr = (BiospecDescr)findAncestorWithClass(this, BiospecDescr.class);
			if (!theBiospecDescr.commitNeeded) {
				pageContext.getOut().print(theBiospecDescr.getSeqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing BiospecDescr for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing BiospecDescr for seqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getSeqnum() throws JspTagException {
		try {
			BiospecDescr theBiospecDescr = (BiospecDescr)findAncestorWithClass(this, BiospecDescr.class);
			return theBiospecDescr.getSeqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing BiospecDescr for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing BiospecDescr for seqnum tag ");
		}
	}

	public void setSeqnum(int seqnum) throws JspTagException {
		try {
			BiospecDescr theBiospecDescr = (BiospecDescr)findAncestorWithClass(this, BiospecDescr.class);
			theBiospecDescr.setSeqnum(seqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing BiospecDescr for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing BiospecDescr for seqnum tag ");
		}
	}

}
