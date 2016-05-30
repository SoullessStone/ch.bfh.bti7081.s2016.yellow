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

	// TODO: Gemäss classmodel muss das eine Liste von Sitzungseinträgen werden.
	private String notes;

	private Date appointmentTime;

	// TODO: Braucht es noch eine Duration?

	public MeetingDTO(PatientDTO patient, DoctorDTO doctor, Date appointmentTime)
			throws MeetingStateException {
		this.patient = patient;
		this.doctor = doctor;
		this.appointmentTime = appointmentTime;
		this.state = new MeetingStateNew();
		this.state.planMeeting(this, appointmentTime);
		this.appointmentTime = appointmentTime;
	}

	public MeetingDTO() {
	}

	public MeetingDTO(Meeting createdMeeting) throws MeetingStateException {
		Date appointmentTime = createdMeeting.getAppointmentTime();
		
		MeetingStateType meetingState = createdMeeting.getStateType();
		if (meetingState !=null) {
			this.state = meetingState.getMeetingState();
		} else {
			this.state = new MeetingStateNew();
			this.state.planMeeting(this, appointmentTime);
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

	public MeetingStateType getMeetingState() {
		return this.state.getState();
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

	@Override
	public int compareTo(MeetingDTO o) {
		return o.getAppointmentTime().compareTo(getAppointmentTime());
	}
}
