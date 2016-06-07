package ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
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

	private String notes;

	private Date appointmentTime;

	private MeetingStateType state;

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
		return new Date(appointmentTime.getTime());
	}

	public void setAppointmentTime(Date appointmentTime) {
		this.appointmentTime = new Date(appointmentTime.getTime());
	}

	public MeetingStateType getStateType() {
		return state;
	}

	public void setStateType(MeetingStateType state) {
		this.state = state;
	}

}
