package edu.uiowa.slis.ClinicalTrialsTagLib.armGroup;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ArmGroupSeqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(ArmGroupSeqnum.class);


	public int doStartTag() throws JspException {
		try {
			ArmGroup theArmGroup = (ArmGroup)findAncestorWithClass(this, ArmGroup.class);
			if (!theArmGroup.commitNeeded) {
				pageContext.getOut().print(theArmGroup.getSeqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ArmGroup for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ArmGroup for seqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getSeqnum() throws JspTagException {
		try {
			ArmGroup theArmGroup = (ArmGroup)findAncestorWithClass(this, ArmGroup.class);
			return theArmGroup.getSeqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing ArmGroup for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ArmGroup for seqnum tag ");
		}
	}

	public void setSeqnum(int seqnum) throws JspTagException {
		try {
			ArmGroup theArmGroup = (ArmGroup)findAncestorWithClass(this, ArmGroup.class);
			theArmGroup.setSeqnum(seqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing ArmGroup for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing ArmGroup for seqnum tag ");
		}
	}

}
