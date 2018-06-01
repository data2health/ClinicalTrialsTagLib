package edu.uiowa.slis.ClinicalTrialsTagLib.nctAlias;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class NctAliasSeqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(NctAliasSeqnum.class);


	public int doStartTag() throws JspException {
		try {
			NctAlias theNctAlias = (NctAlias)findAncestorWithClass(this, NctAlias.class);
			if (!theNctAlias.commitNeeded) {
				pageContext.getOut().print(theNctAlias.getSeqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing NctAlias for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing NctAlias for seqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getSeqnum() throws JspTagException {
		try {
			NctAlias theNctAlias = (NctAlias)findAncestorWithClass(this, NctAlias.class);
			return theNctAlias.getSeqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing NctAlias for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing NctAlias for seqnum tag ");
		}
	}

	public void setSeqnum(int seqnum) throws JspTagException {
		try {
			NctAlias theNctAlias = (NctAlias)findAncestorWithClass(this, NctAlias.class);
			theNctAlias.setSeqnum(seqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing NctAlias for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing NctAlias for seqnum tag ");
		}
	}

}
