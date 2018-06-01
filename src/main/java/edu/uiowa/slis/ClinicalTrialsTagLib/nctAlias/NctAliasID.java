package edu.uiowa.slis.ClinicalTrialsTagLib.nctAlias;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class NctAliasID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(NctAliasID.class);


	public int doStartTag() throws JspException {
		try {
			NctAlias theNctAlias = (NctAlias)findAncestorWithClass(this, NctAlias.class);
			if (!theNctAlias.commitNeeded) {
				pageContext.getOut().print(theNctAlias.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing NctAlias for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing NctAlias for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			NctAlias theNctAlias = (NctAlias)findAncestorWithClass(this, NctAlias.class);
			return theNctAlias.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing NctAlias for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing NctAlias for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			NctAlias theNctAlias = (NctAlias)findAncestorWithClass(this, NctAlias.class);
			theNctAlias.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing NctAlias for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing NctAlias for ID tag ");
		}
	}

}
