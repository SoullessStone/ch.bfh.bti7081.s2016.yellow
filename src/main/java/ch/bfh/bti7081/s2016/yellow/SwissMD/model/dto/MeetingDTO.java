package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Meeting;

/**
 * Data transfer object for a {@code Meeting} entity. To use in the views.
 * 
 * @author K.Suter
 * 
 */
public class MeetingDTO extends GenericDTO {

	private PatientDTO patient;

	private DoctorDTO doctor;

	// TODO: Gemäss classmodel muss das eine Liste von Sitzungseinträgen werden.
	private String notes;

	private Date appointmentTime;

	// TODO: Braucht es noch eine Duration?

	public MeetingDTO(PatientDTO patient, DoctorDTO doctor, Date appointmentTime) {
		this.patient = patient;
		this.doctor = doctor;
		this.appointmentTime = appointmentTime;
	}

	public MeetingDTO() {
	}

	public MeetingDTO(Meeting createdMeeting) {
		this.appointmentTime = createdMeeting.getAppointmentTime();
		this.doctor = new DoctorDTO(createdMeeting.getDoctor());
		this.patient = new PatientDTO(createdMeeting.getPatient());
		this.notes = createdMeeting.getNotes();
		this.setId(createdMeeting.getId());
	}

	public PatientDTO getPatient() {
		return patient;
	}

	public void setPatient(PatientDTO patient) {
		this.patient = patient;
	}

	public DoctorDTO getDoctor() {
		return doctor;
	}

	public void setDoctor(DoctorDTO doctor) {
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

	public String getAppointmentTimeString() {
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		return df.format(appointmentTime);
	}

	public void setAppointmentTime(Date appointmentTime) {
		this.appointmentTime = appointmentTime;
	}
}
