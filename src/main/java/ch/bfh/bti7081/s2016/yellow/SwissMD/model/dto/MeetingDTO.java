package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.Doctor;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.Patient;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.Person;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.Prescription;

/**
 * Data transfer object for a  {@code Meeting} entity. To use in the views.
 * 
 * @author K.Suter
 * 
 * */
public class MeetingDTO {

	private Long id;

	// TODO: PatientDTO
	private Patient patient;

	// TODO: DoctorDTO
	private Doctor doctor;

	// TODO: Gemäss classmodel muss das eine Liste von Sitzungseinträgen werden.
	private String notes;

	private List<PrescriptionDTO> prescriptions;

	private Date appointmentTime;

	// TODO: Braucht es noch eine Duration?

	public MeetingDTO(Patient patient, Doctor doctor, Date appointmentTime) {
		this.patient = patient;
		this.doctor = doctor;
		this.appointmentTime = appointmentTime;
	}

	public MeetingDTO(Patient patient, Doctor doctor, Date appointmentTime, List<PrescriptionDTO> prescriptions) {
		this(patient, doctor, appointmentTime);
		this.prescriptions = prescriptions;
	}
	
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

	public List<PrescriptionDTO> getPrescriptions() {
		// TODO: Copyof zurückgeben?
		return prescriptions;
	}

	public void addPrescription(PrescriptionDTO prescription) {
		this.prescriptions.add(prescription);
	}

	public void removePrescription(Prescription prescription) {
		this.prescriptions.remove(prescription);
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
