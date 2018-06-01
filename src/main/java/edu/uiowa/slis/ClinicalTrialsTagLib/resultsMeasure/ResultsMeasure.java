package edu.uiowa.slis.ClinicalTrialsTagLib.resultsMeasure;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import edu.uiowa.slis.ClinicalTrialsTagLib.resultsOutcome.ResultsOutcome;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;
import edu.uiowa.slis.ClinicalTrialsTagLib.Sequence;

@SuppressWarnings("serial")
public class ResultsMeasure extends ClinicalTrialsTagLibTagSupport {

	static ResultsMeasure currentInstance = null;
	boolean commitNeeded = false;
	boolean newRecord = false;

	private static final Log log = LogFactory.getLog(ResultsMeasure.class);

	Vector<ClinicalTrialsTagLibTagSupport> parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	int ID = 0;
	int seqnum = 0;
	int measeqnum = 0;
	String title = null;
	String description = null;
	String units = null;
	String param = null;
	String dispersion = null;

	public int doStartTag() throws JspException {
		currentInstance = this;
		try {
			ResultsOutcome theResultsOutcome = (ResultsOutcome)findAncestorWithClass(this, ResultsOutcome.class);
			if (theResultsOutcome!= null)
				parentEntities.addElement(theResultsOutcome);

			if (theResultsOutcome == null) {
			} else {
				ID = theResultsOutcome.getID();
				seqnum = theResultsOutcome.getSeqnum();
			}

			ResultsMeasureIterator theResultsMeasureIterator = (ResultsMeasureIterator)findAncestorWithClass(this, ResultsMeasureIterator.class);

			if (theResultsMeasureIterator != null) {
				ID = theResultsMeasureIterator.getID();
				seqnum = theResultsMeasureIterator.getSeqnum();
				measeqnum = theResultsMeasureIterator.getMeaseqnum();
			}

			if (theResultsMeasureIterator == null && theResultsOutcome == null && measeqnum == 0) {
				// no measeqnum was provided - the default is to assume that it is a new ResultsMeasure and to generate a new measeqnum
				measeqnum = Sequence.generateID();
				insertEntity();
			} else {
				// an iterator or measeqnum was provided as an attribute - we need to load a ResultsMeasure from the database
				boolean found = false;
				PreparedStatement stmt = getConnection().prepareStatement("select title,description,units,param,dispersion from clinical_trials.results_measure where id = ? and seqnum = ? and measeqnum = ?");
				stmt.setInt(1,ID);
				stmt.setInt(2,seqnum);
				stmt.setInt(3,measeqnum);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					if (title == null)
						title = rs.getString(1);
					if (description == null)
						description = rs.getString(2);
					if (units == null)
						units = rs.getString(3);
					if (param == null)
						param = rs.getString(4);
					if (dispersion == null)
						dispersion = rs.getString(5);
					found = true;
				}
				stmt.close();

				if (!found) {
					insertEntity();
				}
			}
		} catch (SQLException e) {
			log.error("JDBC error retrieving measeqnum " + measeqnum, e);
			throw new JspTagException("Error: JDBC error retrieving measeqnum " + measeqnum);
		} finally {
			freeConnection();
		}
		return EVAL_PAGE;
	}

	public int doEndTag() throws JspException {
		currentInstance = null;
		try {
			if (commitNeeded) {
				PreparedStatement stmt = getConnection().prepareStatement("update clinical_trials.results_measure set title = ?, description = ?, units = ?, param = ?, dispersion = ? where id = ? and seqnum = ? and measeqnum = ?");
				stmt.setString(1,title);
				stmt.setString(2,description);
				stmt.setString(3,units);
				stmt.setString(4,param);
				stmt.setString(5,dispersion);
				stmt.setInt(6,ID);
				stmt.setInt(7,seqnum);
				stmt.setInt(8,measeqnum);
				stmt.executeUpdate();
				stmt.close();
			}
		} catch (SQLException e) {
			log.error("Error: IOException while writing to the user", e);
			throw new JspTagException("Error: IOException while writing to the user");
		} finally {
			clearServiceState();
			freeConnection();
		}
		return super.doEndTag();
	}

	public void insertEntity() throws JspException {
		try {
			if (measeqnum == 0) {
				measeqnum = Sequence.generateID();
				log.debug("generating new ResultsMeasure " + measeqnum);
			}

			if (title == null)
				title = "";
			if (description == null)
				description = "";
			if (units == null)
				units = "";
			if (param == null)
				param = "";
			if (dispersion == null)
				dispersion = "";
			PreparedStatement stmt = getConnection().prepareStatement("insert into clinical_trials.results_measure(id,seqnum,measeqnum,title,description,units,param,dispersion) values (?,?,?,?,?,?,?,?)");
			stmt.setInt(1,ID);
			stmt.setInt(2,seqnum);
			stmt.setInt(3,measeqnum);
			stmt.setString(4,title);
			stmt.setString(5,description);
			stmt.setString(6,units);
			stmt.setString(7,param);
			stmt.setString(8,dispersion);
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			log.error("Error: IOException while writing to the user", e);
			throw new JspTagException("Error: IOException while writing to the user");
		} finally {
			freeConnection();
		}
	}

	public int getID () {
		return ID;
	}

	public void setID (int ID) {
		this.ID = ID;
	}

	public int getActualID () {
		return ID;
	}

	public int getSeqnum () {
		return seqnum;
	}

	public void setSeqnum (int seqnum) {
		this.seqnum = seqnum;
	}

	public int getActualSeqnum () {
		return seqnum;
	}

	public int getMeaseqnum () {
		return measeqnum;
	}

	public void setMeaseqnum (int measeqnum) {
		this.measeqnum = measeqnum;
	}

	public int getActualMeaseqnum () {
		return measeqnum;
	}

	public String getTitle () {
		if (commitNeeded)
			return "";
		else
			return title;
	}

	public void setTitle (String title) {
		this.title = title;
		commitNeeded = true;
	}

	public String getActualTitle () {
		return title;
	}

	public String getDescription () {
		if (commitNeeded)
			return "";
		else
			return description;
	}

	public void setDescription (String description) {
		this.description = description;
		commitNeeded = true;
	}

	public String getActualDescription () {
		return description;
	}

	public String getUnits () {
		if (commitNeeded)
			return "";
		else
			return units;
	}

	public void setUnits (String units) {
		this.units = units;
		commitNeeded = true;
	}

	public String getActualUnits () {
		return units;
	}

	public String getParam () {
		if (commitNeeded)
			return "";
		else
			return param;
	}

	public void setParam (String param) {
		this.param = param;
		commitNeeded = true;
	}

	public String getActualParam () {
		return param;
	}

	public String getDispersion () {
		if (commitNeeded)
			return "";
		else
			return dispersion;
	}

	public void setDispersion (String dispersion) {
		this.dispersion = dispersion;
		commitNeeded = true;
	}

	public String getActualDispersion () {
		return dispersion;
	}

	public static Integer IDValue() throws JspException {
		try {
			return currentInstance.getID();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function IDValue()");
		}
	}

	public static Integer seqnumValue() throws JspException {
		try {
			return currentInstance.getSeqnum();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function seqnumValue()");
		}
	}

	public static Integer measeqnumValue() throws JspException {
		try {
			return currentInstance.getMeaseqnum();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function measeqnumValue()");
		}
	}

	public static String titleValue() throws JspException {
		try {
			return currentInstance.getTitle();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function titleValue()");
		}
	}

	public static String descriptionValue() throws JspException {
		try {
			return currentInstance.getDescription();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function descriptionValue()");
		}
	}

	public static String unitsValue() throws JspException {
		try {
			return currentInstance.getUnits();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function unitsValue()");
		}
	}

	public static String paramValue() throws JspException {
		try {
			return currentInstance.getParam();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function paramValue()");
		}
	}

	public static String dispersionValue() throws JspException {
		try {
			return currentInstance.getDispersion();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function dispersionValue()");
		}
	}

	private void clearServiceState () {
		ID = 0;
		seqnum = 0;
		measeqnum = 0;
		title = null;
		description = null;
		units = null;
		param = null;
		dispersion = null;
		newRecord = false;
		commitNeeded = false;
		parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	}

}
