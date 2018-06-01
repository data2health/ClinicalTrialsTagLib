package edu.uiowa.slis.ClinicalTrialsTagLib.seriousEventCount;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import edu.uiowa.slis.ClinicalTrialsTagLib.seriousEvent.SeriousEvent;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;
import edu.uiowa.slis.ClinicalTrialsTagLib.Sequence;

@SuppressWarnings("serial")
public class SeriousEventCount extends ClinicalTrialsTagLibTagSupport {

	static SeriousEventCount currentInstance = null;
	boolean commitNeeded = false;
	boolean newRecord = false;

	private static final Log log = LogFactory.getLog(SeriousEventCount.class);

	Vector<ClinicalTrialsTagLibTagSupport> parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	int ID = 0;
	String seqnum = null;
	int eveseqnum = 0;
	String groupId = null;
	String subjectsAffected = null;
	String subjectsAtRisk = null;
	String events = null;

	public int doStartTag() throws JspException {
		currentInstance = this;
		try {
			SeriousEvent theSeriousEvent = (SeriousEvent)findAncestorWithClass(this, SeriousEvent.class);
			if (theSeriousEvent!= null)
				parentEntities.addElement(theSeriousEvent);

			if (theSeriousEvent == null) {
			} else {
				ID = theSeriousEvent.getID();
				seqnum = theSeriousEvent.getSeqnum();
				eveseqnum = theSeriousEvent.getEveseqnum();
			}

			SeriousEventCountIterator theSeriousEventCountIterator = (SeriousEventCountIterator)findAncestorWithClass(this, SeriousEventCountIterator.class);

			if (theSeriousEventCountIterator != null) {
				ID = theSeriousEventCountIterator.getID();
				seqnum = theSeriousEventCountIterator.getSeqnum();
				eveseqnum = theSeriousEventCountIterator.getEveseqnum();
				groupId = theSeriousEventCountIterator.getGroupId();
			}

			if (theSeriousEventCountIterator == null && theSeriousEvent == null && groupId == null) {
				// no groupId was provided - the default is to assume that it is a new SeriousEventCount and to generate a new groupId
				groupId = null;
				insertEntity();
			} else {
				// an iterator or groupId was provided as an attribute - we need to load a SeriousEventCount from the database
				boolean found = false;
				PreparedStatement stmt = getConnection().prepareStatement("select subjects_affected,subjects_at_risk,events from clinical_trials.serious_event_count where id = ? and seqnum = ? and eveseqnum = ? and group_id = ?");
				stmt.setInt(1,ID);
				stmt.setString(2,seqnum);
				stmt.setInt(3,eveseqnum);
				stmt.setString(4,groupId);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					if (subjectsAffected == null)
						subjectsAffected = rs.getString(1);
					if (subjectsAtRisk == null)
						subjectsAtRisk = rs.getString(2);
					if (events == null)
						events = rs.getString(3);
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
				PreparedStatement stmt = getConnection().prepareStatement("update clinical_trials.serious_event_count set subjects_affected = ?, subjects_at_risk = ?, events = ? where id = ? and seqnum = ? and eveseqnum = ? and group_id = ?");
				stmt.setString(1,subjectsAffected);
				stmt.setString(2,subjectsAtRisk);
				stmt.setString(3,events);
				stmt.setInt(4,ID);
				stmt.setString(5,seqnum);
				stmt.setInt(6,eveseqnum);
				stmt.setString(7,groupId);
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
			if (subjectsAffected == null)
				subjectsAffected = "";
			if (subjectsAtRisk == null)
				subjectsAtRisk = "";
			if (events == null)
				events = "";
			PreparedStatement stmt = getConnection().prepareStatement("insert into clinical_trials.serious_event_count(id,seqnum,eveseqnum,group_id,subjects_affected,subjects_at_risk,events) values (?,?,?,?,?,?,?)");
			stmt.setInt(1,ID);
			stmt.setString(2,seqnum);
			stmt.setInt(3,eveseqnum);
			stmt.setString(4,groupId);
			stmt.setString(5,subjectsAffected);
			stmt.setString(6,subjectsAtRisk);
			stmt.setString(7,events);
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

	public String getSeqnum () {
		if (commitNeeded)
			return "";
		else
			return seqnum;
	}

	public void setSeqnum (String seqnum) {
		this.seqnum = seqnum;
	}

	public String getActualSeqnum () {
		return seqnum;
	}

	public int getEveseqnum () {
		return eveseqnum;
	}

	public void setEveseqnum (int eveseqnum) {
		this.eveseqnum = eveseqnum;
	}

	public int getActualEveseqnum () {
		return eveseqnum;
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

	public String getSubjectsAffected () {
		if (commitNeeded)
			return "";
		else
			return subjectsAffected;
	}

	public void setSubjectsAffected (String subjectsAffected) {
		this.subjectsAffected = subjectsAffected;
		commitNeeded = true;
	}

	public String getActualSubjectsAffected () {
		return subjectsAffected;
	}

	public String getSubjectsAtRisk () {
		if (commitNeeded)
			return "";
		else
			return subjectsAtRisk;
	}

	public void setSubjectsAtRisk (String subjectsAtRisk) {
		this.subjectsAtRisk = subjectsAtRisk;
		commitNeeded = true;
	}

	public String getActualSubjectsAtRisk () {
		return subjectsAtRisk;
	}

	public String getEvents () {
		if (commitNeeded)
			return "";
		else
			return events;
	}

	public void setEvents (String events) {
		this.events = events;
		commitNeeded = true;
	}

	public String getActualEvents () {
		return events;
	}

	public static Integer IDValue() throws JspException {
		try {
			return currentInstance.getID();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function IDValue()");
		}
	}

	public static String seqnumValue() throws JspException {
		try {
			return currentInstance.getSeqnum();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function seqnumValue()");
		}
	}

	public static Integer eveseqnumValue() throws JspException {
		try {
			return currentInstance.getEveseqnum();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function eveseqnumValue()");
		}
	}

	public static String groupIdValue() throws JspException {
		try {
			return currentInstance.getGroupId();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function groupIdValue()");
		}
	}

	public static String subjectsAffectedValue() throws JspException {
		try {
			return currentInstance.getSubjectsAffected();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function subjectsAffectedValue()");
		}
	}

	public static String subjectsAtRiskValue() throws JspException {
		try {
			return currentInstance.getSubjectsAtRisk();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function subjectsAtRiskValue()");
		}
	}

	public static String eventsValue() throws JspException {
		try {
			return currentInstance.getEvents();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function eventsValue()");
		}
	}

	private void clearServiceState () {
		ID = 0;
		seqnum = null;
		eveseqnum = 0;
		groupId = null;
		subjectsAffected = null;
		subjectsAtRisk = null;
		events = null;
		newRecord = false;
		commitNeeded = false;
		parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	}

}
