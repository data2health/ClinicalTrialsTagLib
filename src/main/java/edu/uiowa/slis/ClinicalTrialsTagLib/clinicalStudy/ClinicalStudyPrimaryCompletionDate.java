package edu.uiowa.slis.ClinicalTrialsTagLib.clinicalStudy;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;

@SuppressWarnings("serial")
public class ClinicalStudyPrimaryCompletionDate extends ClinicalTrialsTagLibTagSupport {
	String type = "DATE";
	String dateStyle = "DEFAULT";
	String timeStyle = "DEFAULT";
	String pattern = null;
	private static final Log log = LogFactory.getLog(ClinicalStudyPrimaryCompletionDate.class);


	public int doStartTag() throws JspException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			if (!theClinicalStudy.commitNeeded) {
				String resultString = null;
				if (theClinicalStudy.getPrimaryCompletionDate() == null) {
					resultString = "";
				} else {
					if (pattern != null) {
						resultString = (new SimpleDateFormat(pattern)).format(theClinicalStudy.getPrimaryCompletionDate());
					} else if (type.equals("BOTH")) {
						resultString = DateFormat.getDateTimeInstance(formatConvert(dateStyle),formatConvert(timeStyle)).format(theClinicalStudy.getPrimaryCompletionDate());
					} else if (type.equals("TIME")) {
						resultString = DateFormat.getTimeInstance(formatConvert(timeStyle)).format(theClinicalStudy.getPrimaryCompletionDate());
					} else { // date
						resultString = DateFormat.getDateInstance(formatConvert(dateStyle)).format(theClinicalStudy.getPrimaryCompletionDate());
					}
				}
				pageContext.getOut().print(resultString);
			}
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for primaryCompletionDate tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for primaryCompletionDate tag ");
		}
		return SKIP_BODY;
	}

	public Date getPrimaryCompletionDate() throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			return theClinicalStudy.getPrimaryCompletionDate();
		} catch (Exception e) {
			log.error(" Can't find enclosing ClinicalStudy for primaryCompletionDate tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for primaryCompletionDate tag ");
		}
	}

	public void setPrimaryCompletionDate(Date primaryCompletionDate) throws JspTagException {
		try {
			ClinicalStudy theClinicalStudy = (ClinicalStudy)findAncestorWithClass(this, ClinicalStudy.class);
			theClinicalStudy.setPrimaryCompletionDate(primaryCompletionDate);
		} catch (Exception e) {
			log.error("Can't find enclosing ClinicalStudy for primaryCompletionDate tag ", e);
			throw new JspTagException("Error: Can't find enclosing ClinicalStudy for primaryCompletionDate tag ");
		}
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type.toUpperCase();
	}

	public String getDateStyle() {
		return dateStyle;
	}

	public void setDateStyle(String dateStyle) {
		this.dateStyle = dateStyle.toUpperCase();
	}

	public String getTimeStyle() {
		return timeStyle;
	}

	public void setTimeStyle(String timeStyle) {
		this.timeStyle = timeStyle.toUpperCase();
	}

	public static int formatConvert(String stringValue) {
		if (stringValue.equals("SHORT"))
			return DateFormat.SHORT;
		if (stringValue.equals("MEDIUM"))
			return DateFormat.MEDIUM;
		if (stringValue.equals("LONG"))
			return DateFormat.LONG;
		if (stringValue.equals("FULL"))
			return DateFormat.FULL;
		return DateFormat.DEFAULT;
	}

}
