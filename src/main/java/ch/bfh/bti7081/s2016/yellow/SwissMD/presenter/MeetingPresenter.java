package ch.bfh.bti7081.s2016.yellow.SwissMD.presenter;

import java.util.ArrayList;
import java.util.List;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.DrugDaoImpl;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.MeetingDaoImpl;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.DoctorDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.DrugDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.MeetingDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PatientDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Drug;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Meeting;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.CouldNotDeleteException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.CouldNotSaveException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.MeetingView;

public class MeetingPresenter {
	private MeetingDaoImpl meetingDao;
	private DrugDaoImpl drugDao;

	public MeetingPresenter(MeetingView meetingView) {
		System.out.println("init MeetingPresenter");
		this.meetingDao = new MeetingDaoImpl();
		this.drugDao = new DrugDaoImpl();
	}

	public void update(MeetingDTO meetingDTO) throws CouldNotSaveException {
		Meeting existingMeeting = meetingDao.read(meetingDTO.getId());
		if (existingMeeting != null) {
			existingMeeting.setNotes(meetingDTO.getNotes());
			existingMeeting.setAppointmentTime(meetingDTO.getAppointmentTime());
			meetingDao.update(existingMeeting);
		}
	}

	/**
	 * Returns a {@code MeetingDTO} or {@code null} if no meeting with this id
	 * could be found
	 * 
	 * @param id
	 *            the technical key of the meeting in the database
	 * 
	 */
	public MeetingDTO findMeetingById(Long id) {
		Meeting meeting = meetingDao.read(id);
		if (meeting != null) {
			MeetingDTO meetingDTO = new MeetingDTO();
			meetingDTO.setId(meeting.getId());
			meetingDTO.setDoctor(new DoctorDTO(meeting.getDoctor().getName(),
					meeting.getDoctor().getBirthdate()));
			meetingDTO.setPatient(new PatientDTO(
					meeting.getPatient().getName(), meeting.getPatient()
							.getBirthdate()));
			meetingDTO.setAppointmentTime(meeting.getAppointmentTime());
			meetingDTO.setNotes(meeting.getNotes());
			return meetingDTO;
		}
		return null;
	}

	public void delete(MeetingDTO meetingDTO) throws CouldNotDeleteException {
		Meeting existingMeeting = meetingDao.read(meetingDTO.getId());
		if (existingMeeting != null) {
			meetingDao.delete(existingMeeting);
		}
	}
	
	/**
	 * Returns a List of {@code Drug} or {@code null} if no drug could be found
	 * 
	 */
	public List<Drug> getPossibleDrugs() {
		List<Drug> drugs = drugDao.readAll();
		return drugs;
	}
	

}
