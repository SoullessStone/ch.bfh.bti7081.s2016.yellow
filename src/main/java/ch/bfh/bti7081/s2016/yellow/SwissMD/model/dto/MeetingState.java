package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto;

import java.util.Date;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.MeetingStateException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.util.MeetingStateType;

/**
 * 
 * Saves the state of a meeting
 * 
 * @author K.Suter
 * 
 */
public abstract class MeetingState {
	abstract void cancelMeeting(MeetingDTO meeting) throws MeetingStateException;
	protected void planMeeting(MeetingDTO meeting, Date appointmentTime) throws MeetingStateException {
		if (appointmentTime.after(new Date())) {
			meeting.changeMeetingState(new MeetingStatePlanned());
		} else {
			meeting.changeMeetingState(new MeetingStateOverdue());
		}
		meeting.setAppointmentTime(appointmentTime);
	};
	abstract void performMeeting(MeetingDTO meeting) throws MeetingStateException;
	abstract MeetingStateType getState();
}
