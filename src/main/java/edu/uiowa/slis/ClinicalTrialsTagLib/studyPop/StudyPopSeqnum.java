package edu.uiowa.slis.ClinicalTrialsTagLib.studyPop;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class StudyPopSeqnum extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(StudyPopSeqnum.class);


	public int doStartTag() throws JspException {
		try {
			StudyPop theStudyPop = (StudyPop)findAncestorWithClass(this, StudyPop.class);
			if (!theStudyPop.commitNeeded) {
				pageContext.getOut().print(theStudyPop.getSeqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing StudyPop for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing StudyPop for seqnum tag ");
		}
		return SKIP_BODY;
	}

	public int getSeqnum() throws JspTagException {
		try {
			StudyPop theStudyPop = (StudyPop)findAncestorWithClass(this, StudyPop.class);
			return theStudyPop.getSeqnum();
		} catch (Exception e) {
			log.error(" Can't find enclosing StudyPop for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing StudyPop for seqnum tag ");
		}
	}

	public void setSeqnum(int seqnum) throws JspTagException {
		try {
			StudyPop theStudyPop = (StudyPop)findAncestorWithClass(this, StudyPop.class);
			theStudyPop.setSeqnum(seqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing StudyPop for seqnum tag ", e);
			throw new JspTagException("Error: Can't find enclosing StudyPop for seqnum tag ");
		}
	}

}
