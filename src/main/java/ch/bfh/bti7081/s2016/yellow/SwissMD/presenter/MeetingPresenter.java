package ch.bfh.bti7081.s2016.yellow.SwissMD.presenter;

import java.util.ArrayList;
import java.util.List;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.DrugDao;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.DrugDaoImpl;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.MeetingDao;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.MeetingDaoImpl;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.PersonDao;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.PersonDaoImpl;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.WebEntityManagerProvider;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.DrugDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.MeetingDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PatientDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Doctor;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Drug;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Meeting;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Patient;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.CouldNotDeleteException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.CouldNotSaveException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.MeetingStateException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.MeetingView;

/**
 * Presenter für die MeetingView
 * 
 * @author Mutz
 *
 */
public class MeetingPresenter {
	private MeetingDao meetingDao;
	private DrugDao drugDao;
	private PersonDao personDao;

	public MeetingPresenter(MeetingView meetingView) {
		System.out.println("init MeetingPresenter");
		this.meetingDao = new MeetingDaoImpl(new WebEntityManagerProvider());
		this.drugDao = new DrugDaoImpl(new WebEntityManagerProvider());
		this.personDao = new PersonDaoImpl(new WebEntityManagerProvider());
	}

	public void update(MeetingDTO meetingDTO) throws CouldNotSaveException {
		Meeting existingMeeting = meetingDao.read(meetingDTO.getId());
		if (existingMeeting != null) {
			existingMeeting.setNotes(meetingDTO.getNotes());
			existingMeeting.setAppointmentTime(meetingDTO.getAppointmentTime());
			existingMeeting.setStateType(meetingDTO.getMeetingState());
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
			return new MeetingDTO(meeting);
		}
		return null;
	}

	public void delete(MeetingDTO meetingDTO) throws CouldNotDeleteException {
		Meeting existingMeeting = meetingDao.read(meetingDTO.getId());
		if (existingMeeting != null) {
			meetingDao.delete(existingMeeting);
		}
	}

	public void cancel(MeetingDTO meetingDTO) throws MeetingStateException {
		Meeting existingMeeting = meetingDao.read(meetingDTO.getId());
		if (existingMeeting != null) {
			meetingDTO.cancelMeeting();
			existingMeeting.setStateType(meetingDTO.getMeetingState());
			meetingDao.update(existingMeeting);
		}
	}

	public void perform(MeetingDTO meetingDTO) throws MeetingStateException {
		Meeting existingMeeting = meetingDao.read(meetingDTO.getId());
		if (existingMeeting != null) {
			meetingDTO.performMeeting();
			existingMeeting.setStateType(meetingDTO.getMeetingState());
			meetingDao.update(existingMeeting);
		}
	}

	public MeetingDTO create(MeetingDTO meetingDTO)
			throws MeetingStateException {
		Meeting meeting = new Meeting();
		meeting.setAppointmentTime(meetingDTO.getAppointmentTime());
		meeting.setDoctor((Doctor) personDao.read(6L));
		meeting.setNotes("Automatisch Generiert, weil session fehlt");
		Patient patient = (Patient) personDao.read(meetingDTO.getPatient()
				.getId());
		if (patient == null) {
			throw new IllegalArgumentException("Patient nicht valid");
		}
		meeting.setPatient(patient);

		Meeting createdMeeting = meetingDao.create(meeting);
		return new MeetingDTO(createdMeeting);
	}

	/**
	 * Returns a List of {@code Drug} or {@code null} if no drug could be found
	 * 
	 */
	public List<DrugDTO> getPossibleDrugs() {
		List<Drug> drugs = drugDao.readAll();
		List<DrugDTO> drugDTOs = new ArrayList<>();
		for (Drug drug : drugs) {
			drugDTOs.add(new DrugDTO(drug));
		}
		return drugDTOs;
	}

	/**
	 * Returns the newest meeting for a patient
	 * 
	 */
	public Long getNewestMeetingForPatient(Long id)
			throws MeetingStateException {
		Patient patient = (Patient) personDao.read(id);
		List<MeetingDTO> meetingList = new ArrayList<>();
		for (Meeting m : meetingDao.findMeetingForPerson(patient)) {
			meetingList.add(new MeetingDTO(m));
		}
		if (meetingList != null)
			return meetingList.get(meetingList.size() - 1).getId();
		else
			return null;
	}

	public PatientDTO getPatient(Long patientId) throws MeetingStateException {
		Patient p = (Patient) personDao.read(patientId);
		if (p == null) {
			throw new IllegalArgumentException("patientId not valid");
		}
		return new PatientDTO(p);
	}

}
