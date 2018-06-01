package edu.uiowa.slis.ClinicalTrialsTagLib.studyPop;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class StudyPopID extends ClinicalTrialsTagLibTagSupport {
	private static final Log log = LogFactory.getLog(StudyPopID.class);


	public int doStartTag() throws JspException {
		try {
			StudyPop theStudyPop = (StudyPop)findAncestorWithClass(this, StudyPop.class);
			if (!theStudyPop.commitNeeded) {
				pageContext.getOut().print(theStudyPop.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing StudyPop for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing StudyPop for ID tag ");
		}
		return SKIP_BODY;
	}

	public int getID() throws JspTagException {
		try {
			StudyPop theStudyPop = (StudyPop)findAncestorWithClass(this, StudyPop.class);
			return theStudyPop.getID();
		} catch (Exception e) {
			log.error(" Can't find enclosing StudyPop for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing StudyPop for ID tag ");
		}
	}

	public void setID(int ID) throws JspTagException {
		try {
			StudyPop theStudyPop = (StudyPop)findAncestorWithClass(this, StudyPop.class);
			theStudyPop.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing StudyPop for ID tag ", e);
			throw new JspTagException("Error: Can't find enclosing StudyPop for ID tag ");
		}
	}

}
