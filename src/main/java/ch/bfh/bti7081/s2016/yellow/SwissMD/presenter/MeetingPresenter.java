package ch.bfh.bti7081.s2016.yellow.SwissMD.presenter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.mapping.Array;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.DrugDaoImpl;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.MeetingDaoImpl;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.PersonDaoImpl;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.DoctorDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.DrugDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.MeetingDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PatientDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Doctor;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Drug;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Meeting;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Patient;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.CouldNotDeleteException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.CouldNotSaveException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.MeetingView;

public class MeetingPresenter {
	private MeetingDaoImpl meetingDao;
	private DrugDaoImpl drugDao;
	private PersonDaoImpl personDao;

	public MeetingPresenter(MeetingView meetingView) {
		System.out.println("init MeetingPresenter");
		this.meetingDao = new MeetingDaoImpl();
		this.drugDao = new DrugDaoImpl();
		this.personDao = new PersonDaoImpl();
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
			meetingDTO.setDoctor(new DoctorDTO(meeting.getDoctor()));
			meetingDTO.setPatient(new PatientDTO(meeting.getPatient()));
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

	public MeetingDTO create(MeetingDTO meetingDTO) {
		Meeting meeting = new Meeting();
		meeting.setAppointmentTime(meetingDTO.getAppointmentTime());
		// TODO: Doctor aus Session
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
			drugDTOs.add(new DrugDTO(drug.getTradeName(), drug.getSubstance(),
					drug.getSubstanceQuantitiy(), drug.getMaxDose()));
		}
		return drugDTOs;
	}

	public PatientDTO getPatient(Long patientId) {
		Patient p = (Patient) personDao.read(patientId);
		if (p == null) {
			throw new IllegalArgumentException("patientId not valid");
		}
		return new PatientDTO(p);
	}

}
