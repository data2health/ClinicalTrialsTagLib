package edu.uiowa.slis.ClinicalTrialsTagLib.nctAlias;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class NctAliasNctAlias extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(NctAliasNctAlias.class);


	public int doStartTag() throws JspException {
		try {
			NctAlias theNctAlias = (NctAlias)findAncestorWithClass(this, NctAlias.class);
			if (!theNctAlias.commitNeeded) {
				pageContext.getOut().print(theNctAlias.getNctAlias());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing NctAlias for nctAlias tag ", e);
			throw new JspTagException("Error: Can't find enclosing NctAlias for nctAlias tag ");
		}
		return SKIP_BODY;
	}

	public String getNctAlias() throws JspTagException {
		try {
			NctAlias theNctAlias = (NctAlias)findAncestorWithClass(this, NctAlias.class);
			return theNctAlias.getNctAlias();
		} catch (Exception e) {
			log.error(" Can't find enclosing NctAlias for nctAlias tag ", e);
			throw new JspTagException("Error: Can't find enclosing NctAlias for nctAlias tag ");
		}
	}

	public void setNctAlias(String nctAlias) throws JspTagException {
		try {
			NctAlias theNctAlias = (NctAlias)findAncestorWithClass(this, NctAlias.class);
			theNctAlias.setNctAlias(nctAlias);
		} catch (Exception e) {
			log.error("Can't find enclosing NctAlias for nctAlias tag ", e);
			throw new JspTagException("Error: Can't find enclosing NctAlias for nctAlias tag ");
		}
	}

}
