package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Meeting;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.MeetingStateException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.util.MeetingStateType;

/**
 * Data transfer object for a {@link Meeting} entity. To use in the views.
 * 
 * @author K.Suter
 * 
 */
public class MeetingDTO extends GenericDTO implements Comparable<MeetingDTO> {

	private PatientDTO patient;

	private DoctorDTO doctor;

	private MeetingState state;

	private String notes;

	private Date appointmentTime;

	public MeetingDTO(PatientDTO patient, DoctorDTO doctor, Date appointmentTime)
			throws MeetingStateException {
		this.patient = patient;
		this.doctor = doctor;
		this.appointmentTime = new Date(appointmentTime.getTime());
		this.state = new MeetingStateNew();
		this.state.planMeeting(this, appointmentTime);
		this.appointmentTime = new Date(appointmentTime.getTime());
	}

	public MeetingDTO() {
	}

	public MeetingDTO(Meeting createdMeeting) {
		Date appointmentTime = createdMeeting.getAppointmentTime();

		MeetingStateType meetingState = createdMeeting.getStateType();
		if (meetingState != null) {
			this.state = meetingState.getMeetingState();
		} else {
			this.state = new MeetingStateNew();
			try {
				this.state.planMeeting(this, appointmentTime);
			} catch (MeetingStateException e) {
				e.printStackTrace();
			}
		}
		this.appointmentTime = appointmentTime;
		this.doctor = new DoctorDTO(createdMeeting.getDoctor());
		this.patient = new PatientDTO(createdMeeting.getPatient());
		this.notes = createdMeeting.getNotes();
		this.setId(createdMeeting.getId());
	}

	/**
	 * This method must remain protected! It is not allowed to change the
	 * meeting state from outside this package.
	 **/
	protected void changeMeetingState(MeetingState newMeetingState) {
		this.state = newMeetingState;
	}

	public void cancelMeeting() throws MeetingStateException {
		this.state.cancelMeeting(this);
	}

	public void postponeMeeting(Date newAppointmentTime)
			throws MeetingStateException {
		this.state.planMeeting(this, newAppointmentTime);
	}

	public void performMeeting() throws MeetingStateException {
		this.state.performMeeting(this);
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

	public MeetingStateType getMeetingState() {
		return this.state.getState();
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Date getAppointmentTime() {
		return new Date(appointmentTime.getTime());
	}

	public String getAppointmentTimeString() {
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		return df.format(appointmentTime);
	}

	public void setAppointmentTime(Date appointmentTime) {
		this.appointmentTime = new Date(appointmentTime.getTime());
	}

	@Override
	public int compareTo(MeetingDTO o) {
		return o.getAppointmentTime().compareTo(getAppointmentTime());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((appointmentTime == null) ? 0 : appointmentTime.hashCode());
		result = prime * result + ((doctor == null) ? 0 : doctor.hashCode());
		result = prime * result + ((notes == null) ? 0 : notes.hashCode());
		result = prime * result + ((patient == null) ? 0 : patient.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MeetingDTO other = (MeetingDTO) obj;
		if (appointmentTime == null) {
			if (other.appointmentTime != null)
				return false;
		} else if (!appointmentTime.equals(other.appointmentTime))
			return false;
		if (doctor == null) {
			if (other.doctor != null)
				return false;
		} else if (!doctor.equals(other.doctor))
			return false;
		if (notes == null) {
			if (other.notes != null)
				return false;
		} else if (!notes.equals(other.notes))
			return false;
		if (patient == null) {
			if (other.patient != null)
				return false;
		} else if (!patient.equals(other.patient))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}

}
