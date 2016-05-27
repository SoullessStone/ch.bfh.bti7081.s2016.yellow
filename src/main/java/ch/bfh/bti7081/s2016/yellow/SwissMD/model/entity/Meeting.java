package ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.MeetingState;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.util.MeetingStateType;

/**
 * Entity that represents a meeting. To use in the data access layer.
 * 
 * @author K.Suter
 * 
 */
@Entity
@Table
public class Meeting extends AbstractDatabaseObject {

	@ManyToOne
	private Patient patient;

	@ManyToOne
	private Doctor doctor;

	// TODO: Gemäss classmodel muss das eine Liste von Sitzungseinträgen werden.
	private String notes;

	private Date appointmentTime;

	private MeetingStateType state;

	// TODO: Braucht es noch eine Duration?

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Date getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(Date appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public MeetingStateType getStateType() {
		return state;
	}

	public void setStateType(MeetingStateType state) {
		this.state = state;
	}

}
