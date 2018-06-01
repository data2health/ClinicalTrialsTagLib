package edu.uiowa.slis.ClinicalTrialsTagLib.studyPop;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class StudyPopTextblock extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(StudyPopTextblock.class);


	public int doStartTag() throws JspException {
		try {
			StudyPop theStudyPop = (StudyPop)findAncestorWithClass(this, StudyPop.class);
			if (!theStudyPop.commitNeeded) {
				pageContext.getOut().print(theStudyPop.getTextblock());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing StudyPop for textblock tag ", e);
			throw new JspTagException("Error: Can't find enclosing StudyPop for textblock tag ");
		}
		return SKIP_BODY;
	}

	public String getTextblock() throws JspTagException {
		try {
			StudyPop theStudyPop = (StudyPop)findAncestorWithClass(this, StudyPop.class);
			return theStudyPop.getTextblock();
		} catch (Exception e) {
			log.error(" Can't find enclosing StudyPop for textblock tag ", e);
			throw new JspTagException("Error: Can't find enclosing StudyPop for textblock tag ");
		}
	}

	public void setTextblock(String textblock) throws JspTagException {
		try {
			StudyPop theStudyPop = (StudyPop)findAncestorWithClass(this, StudyPop.class);
			theStudyPop.setTextblock(textblock);
		} catch (Exception e) {
			log.error("Can't find enclosing StudyPop for textblock tag ", e);
			throw new JspTagException("Error: Can't find enclosing StudyPop for textblock tag ");
		}
	}

}
