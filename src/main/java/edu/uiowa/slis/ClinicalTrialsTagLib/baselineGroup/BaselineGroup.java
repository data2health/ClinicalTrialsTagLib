package edu.uiowa.slis.ClinicalTrialsTagLib.baselineGroup;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import edu.uiowa.slis.ClinicalTrialsTagLib.clinicalResults.ClinicalResults;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;
import edu.uiowa.slis.ClinicalTrialsTagLib.Sequence;

@SuppressWarnings("serial")
public class BaselineGroup extends ClinicalTrialsTagLibTagSupport {

	static BaselineGroup currentInstance = null;
	boolean commitNeeded = false;
	boolean newRecord = false;

	private static final Log log = LogFactory.getLog(BaselineGroup.class);

	Vector<ClinicalTrialsTagLibTagSupport> parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	int ID = 0;
	String groupId = null;
	String title = null;
	String description = null;

	public int doStartTag() throws JspException {
		currentInstance = this;
		try {
			ClinicalResults theClinicalResults = (ClinicalResults)findAncestorWithClass(this, ClinicalResults.class);
			if (theClinicalResults!= null)
				parentEntities.addElement(theClinicalResults);

			if (theClinicalResults == null) {
			} else {
				ID = theClinicalResults.getID();
			}

			BaselineGroupIterator theBaselineGroupIterator = (BaselineGroupIterator)findAncestorWithClass(this, BaselineGroupIterator.class);

			if (theBaselineGroupIterator != null) {
				ID = theBaselineGroupIterator.getID();
				groupId = theBaselineGroupIterator.getGroupId();
			}

			if (theBaselineGroupIterator == null && theClinicalResults == null && groupId == null) {
				// no groupId was provided - the default is to assume that it is a new BaselineGroup and to generate a new groupId
				groupId = null;
				insertEntity();
			} else {
				// an iterator or groupId was provided as an attribute - we need to load a BaselineGroup from the database
				boolean found = false;
				PreparedStatement stmt = getConnection().prepareStatement("select title,description from clinical_trials.baseline_group where id = ? and group_id = ?");
				stmt.setInt(1,ID);
				stmt.setString(2,groupId);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					if (title == null)
						title = rs.getString(1);
					if (description == null)
						description = rs.getString(2);
					found = true;
				}
				stmt.close();

				if (!found) {
					insertEntity();
				}
			}
		} catch (SQLException e) {
			log.error("JDBC error retrieving groupId " + groupId, e);
			throw new JspTagException("Error: JDBC error retrieving groupId " + groupId);
		} finally {
			freeConnection();
		}
		return EVAL_PAGE;
	}

	public int doEndTag() throws JspException {
		currentInstance = null;
		try {
			if (commitNeeded) {
				PreparedStatement stmt = getConnection().prepareStatement("update clinical_trials.baseline_group set title = ?, description = ? where id = ? and group_id = ?");
				stmt.setString(1,title);
				stmt.setString(2,description);
				stmt.setInt(3,ID);
				stmt.setString(4,groupId);
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
			if (title == null)
				title = "";
			if (description == null)
				description = "";
			PreparedStatement stmt = getConnection().prepareStatement("insert into clinical_trials.baseline_group(id,group_id,title,description) values (?,?,?,?)");
			stmt.setInt(1,ID);
			stmt.setString(2,groupId);
			stmt.setString(3,title);
			stmt.setString(4,description);
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

	public String getGroupId () {
		if (commitNeeded)
			return "";
		else
			return groupId;
	}

	public void setGroupId (String groupId) {
		this.groupId = groupId;
	}

	public String getActualGroupId () {
		return groupId;
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

	public static Integer IDValue() throws JspException {
		try {
			return currentInstance.getID();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function IDValue()");
		}
	}

	public static String groupIdValue() throws JspException {
		try {
			return currentInstance.getGroupId();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function groupIdValue()");
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

	private void clearServiceState () {
		ID = 0;
		groupId = null;
		title = null;
		description = null;
		newRecord = false;
		commitNeeded = false;
		parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	}

}
