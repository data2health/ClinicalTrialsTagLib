package edu.uiowa.slis.ClinicalTrialsTagLib.studyPop;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class StudyPopElseqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(StudyPopElseqnum.class);


	public int doStartTag() throws JspException {
		try {
			StudyPop theStudyPop = (StudyPop)findAncestorWithClass(this, StudyPop.class);
			if (!theStudyPop.commitNeeded) {
				pageContext.getOut().print(theStudyPop.getElseqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing StudyPop for elseqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing StudyPop for elseqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getElseqnum() throws JspTagException {
		try {
			StudyPop theStudyPop = (StudyPop)findAncestorWithClass(this, StudyPop.class);
			return theStudyPop.getElseqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing StudyPop for elseqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing StudyPop for elseqnum tag ");
		}
	}

	public void setElseqnum(int elseqnum) throws JspTagException {
		try {
			StudyPop theStudyPop = (StudyPop)findAncestorWithClass(this, StudyPop.class);
			theStudyPop.setElseqnum(elseqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing StudyPop for elseqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing StudyPop for elseqnum tag ");
		}
	}

}
