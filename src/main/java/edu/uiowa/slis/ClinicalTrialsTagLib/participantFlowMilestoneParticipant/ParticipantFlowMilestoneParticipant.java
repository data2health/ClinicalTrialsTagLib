package edu.uiowa.slis.ClinicalTrialsTagLib.participantFlowMilestoneParticipant;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import edu.uiowa.slis.ClinicalTrialsTagLib.participantFlowMilestone.ParticipantFlowMilestone;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;
import edu.uiowa.slis.ClinicalTrialsTagLib.Sequence;

@SuppressWarnings("serial")
public class ParticipantFlowMilestoneParticipant extends ClinicalTrialsTagLibTagSupport {

	static ParticipantFlowMilestoneParticipant currentInstance = null;
	boolean commitNeeded = false;
	boolean newRecord = false;

	private static final Log log = LogFactory.getLog(ParticipantFlowMilestoneParticipant.class);

	Vector<ClinicalTrialsTagLibTagSupport> parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	int ID = 0;
	int seqnum = 0;
	int mileseqnum = 0;
	String groupId = null;
	String count = null;

	public int doStartTag() throws JspException {
		currentInstance = this;
		try {
			ParticipantFlowMilestone theParticipantFlowMilestone = (ParticipantFlowMilestone)findAncestorWithClass(this, ParticipantFlowMilestone.class);
			if (theParticipantFlowMilestone!= null)
				parentEntities.addElement(theParticipantFlowMilestone);

			if (theParticipantFlowMilestone == null) {
			} else {
				ID = theParticipantFlowMilestone.getID();
				seqnum = theParticipantFlowMilestone.getSeqnum();
				mileseqnum = theParticipantFlowMilestone.getMileseqnum();
			}

			ParticipantFlowMilestoneParticipantIterator theParticipantFlowMilestoneParticipantIterator = (ParticipantFlowMilestoneParticipantIterator)findAncestorWithClass(this, ParticipantFlowMilestoneParticipantIterator.class);

			if (theParticipantFlowMilestoneParticipantIterator != null) {
				ID = theParticipantFlowMilestoneParticipantIterator.getID();
				seqnum = theParticipantFlowMilestoneParticipantIterator.getSeqnum();
				mileseqnum = theParticipantFlowMilestoneParticipantIterator.getMileseqnum();
				groupId = theParticipantFlowMilestoneParticipantIterator.getGroupId();
			}

			if (theParticipantFlowMilestoneParticipantIterator == null && theParticipantFlowMilestone == null && groupId == null) {
				// no groupId was provided - the default is to assume that it is a new ParticipantFlowMilestoneParticipant and to generate a new groupId
				groupId = null;
				insertEntity();
			} else {
				// an iterator or groupId was provided as an attribute - we need to load a ParticipantFlowMilestoneParticipant from the database
				boolean found = false;
				PreparedStatement stmt = getConnection().prepareStatement("select count from clinical_trials.participant_flow_milestone_participant where id = ? and seqnum = ? and mileseqnum = ? and group_id = ?");
				stmt.setInt(1,ID);
				stmt.setInt(2,seqnum);
				stmt.setInt(3,mileseqnum);
				stmt.setString(4,groupId);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					if (count == null)
						count = rs.getString(1);
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
				PreparedStatement stmt = getConnection().prepareStatement("update clinical_trials.participant_flow_milestone_participant set count = ? where id = ? and seqnum = ? and mileseqnum = ? and group_id = ?");
				stmt.setString(1,count);
				stmt.setInt(2,ID);
				stmt.setInt(3,seqnum);
				stmt.setInt(4,mileseqnum);
				stmt.setString(5,groupId);
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
			if (count == null)
				count = "";
			PreparedStatement stmt = getConnection().prepareStatement("insert into clinical_trials.participant_flow_milestone_participant(id,seqnum,mileseqnum,group_id,count) values (?,?,?,?,?)");
			stmt.setInt(1,ID);
			stmt.setInt(2,seqnum);
			stmt.setInt(3,mileseqnum);
			stmt.setString(4,groupId);
			stmt.setString(5,count);
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

	public int getMileseqnum () {
		return mileseqnum;
	}

	public void setMileseqnum (int mileseqnum) {
		this.mileseqnum = mileseqnum;
	}

	public int getActualMileseqnum () {
		return mileseqnum;
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

	public String getCount () {
		if (commitNeeded)
			return "";
		else
			return count;
	}

	public void setCount (String count) {
		this.count = count;
		commitNeeded = true;
	}

	public String getActualCount () {
		return count;
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

	public static Integer mileseqnumValue() throws JspException {
		try {
			return currentInstance.getMileseqnum();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function mileseqnumValue()");
		}
	}

	public static String groupIdValue() throws JspException {
		try {
			return currentInstance.getGroupId();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function groupIdValue()");
		}
	}

	public static String countValue() throws JspException {
		try {
			return currentInstance.getCount();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function countValue()");
		}
	}

	private void clearServiceState () {
		ID = 0;
		seqnum = 0;
		mileseqnum = 0;
		groupId = null;
		count = null;
		newRecord = false;
		commitNeeded = false;
		parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	}

}
