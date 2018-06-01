package edu.uiowa.slis.ClinicalTrialsTagLib.participantFlowMilestone;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import edu.uiowa.slis.ClinicalTrialsTagLib.participantFlowPeriod.ParticipantFlowPeriod;

import edu.uiowa.slis.ClinicalTrialsTagLib.ClinicalTrialsTagLibTagSupport;
import edu.uiowa.slis.ClinicalTrialsTagLib.Sequence;

@SuppressWarnings("serial")
public class ParticipantFlowMilestone extends ClinicalTrialsTagLibTagSupport {

	static ParticipantFlowMilestone currentInstance = null;
	boolean commitNeeded = false;
	boolean newRecord = false;

	private static final Log log = LogFactory.getLog(ParticipantFlowMilestone.class);

	Vector<ClinicalTrialsTagLibTagSupport> parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	int ID = 0;
	int seqnum = 0;
	int mileseqnum = 0;
	String title = null;

	public int doStartTag() throws JspException {
		currentInstance = this;
		try {
			ParticipantFlowPeriod theParticipantFlowPeriod = (ParticipantFlowPeriod)findAncestorWithClass(this, ParticipantFlowPeriod.class);
			if (theParticipantFlowPeriod!= null)
				parentEntities.addElement(theParticipantFlowPeriod);

			if (theParticipantFlowPeriod == null) {
			} else {
				ID = theParticipantFlowPeriod.getID();
				seqnum = theParticipantFlowPeriod.getSeqnum();
			}

			ParticipantFlowMilestoneIterator theParticipantFlowMilestoneIterator = (ParticipantFlowMilestoneIterator)findAncestorWithClass(this, ParticipantFlowMilestoneIterator.class);

			if (theParticipantFlowMilestoneIterator != null) {
				ID = theParticipantFlowMilestoneIterator.getID();
				seqnum = theParticipantFlowMilestoneIterator.getSeqnum();
				mileseqnum = theParticipantFlowMilestoneIterator.getMileseqnum();
			}

			if (theParticipantFlowMilestoneIterator == null && theParticipantFlowPeriod == null && mileseqnum == 0) {
				// no mileseqnum was provided - the default is to assume that it is a new ParticipantFlowMilestone and to generate a new mileseqnum
				mileseqnum = Sequence.generateID();
				insertEntity();
			} else {
				// an iterator or mileseqnum was provided as an attribute - we need to load a ParticipantFlowMilestone from the database
				boolean found = false;
				PreparedStatement stmt = getConnection().prepareStatement("select title from clinical_trials.participant_flow_milestone where id = ? and seqnum = ? and mileseqnum = ?");
				stmt.setInt(1,ID);
				stmt.setInt(2,seqnum);
				stmt.setInt(3,mileseqnum);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					if (title == null)
						title = rs.getString(1);
					found = true;
				}
				stmt.close();

				if (!found) {
					insertEntity();
				}
			}
		} catch (SQLException e) {
			log.error("JDBC error retrieving mileseqnum " + mileseqnum, e);
			throw new JspTagException("Error: JDBC error retrieving mileseqnum " + mileseqnum);
		} finally {
			freeConnection();
		}
		return EVAL_PAGE;
	}

	public int doEndTag() throws JspException {
		currentInstance = null;
		try {
			if (commitNeeded) {
				PreparedStatement stmt = getConnection().prepareStatement("update clinical_trials.participant_flow_milestone set title = ? where id = ? and seqnum = ? and mileseqnum = ?");
				stmt.setString(1,title);
				stmt.setInt(2,ID);
				stmt.setInt(3,seqnum);
				stmt.setInt(4,mileseqnum);
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
			if (mileseqnum == 0) {
				mileseqnum = Sequence.generateID();
				log.debug("generating new ParticipantFlowMilestone " + mileseqnum);
			}

			if (title == null)
				title = "";
			PreparedStatement stmt = getConnection().prepareStatement("insert into clinical_trials.participant_flow_milestone(id,seqnum,mileseqnum,title) values (?,?,?,?)");
			stmt.setInt(1,ID);
			stmt.setInt(2,seqnum);
			stmt.setInt(3,mileseqnum);
			stmt.setString(4,title);
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

	public static String titleValue() throws JspException {
		try {
			return currentInstance.getTitle();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function titleValue()");
		}
	}

	private void clearServiceState () {
		ID = 0;
		seqnum = 0;
		mileseqnum = 0;
		title = null;
		newRecord = false;
		commitNeeded = false;
		parentEntities = new Vector<ClinicalTrialsTagLibTagSupport>();

	}

}
