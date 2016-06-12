package ch.bfh.bti7081.s2016.yellow.SwissMD.presenter;

import java.util.ArrayList;
import java.util.List;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.DiagnosisDao;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.DiagnosisDaoImpl;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.MeetingDao;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.MeetingDaoImpl;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.PersonDao;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.PersonDaoImpl;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.WebEntityManagerProvider;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.DiagnosisDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.MeetingDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PatientDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PersonDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Diagnosis;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Meeting;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Patient;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Person;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.CouldNotDeleteException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.DangerStateException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.MeetingStateException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.PersonView;

/**
 * Presenter für die Personview
 * 
 * @author Schaad
 *
 */
public class PersonPresenter {
	private PersonDao personDao;
	private MeetingDao meetingDao;
	private DiagnosisDao diagnosisDao;

	public PersonPresenter(PersonView personView) {
		System.out.println("init PersonPresenter");
		this.personDao = new PersonDaoImpl(new WebEntityManagerProvider());
		this.meetingDao = new MeetingDaoImpl(new WebEntityManagerProvider());
		this.diagnosisDao = new DiagnosisDaoImpl(new WebEntityManagerProvider());
	}

	/**
	 * Returns a {@code PersonDTO} or {@code null} if no person with this id
	 * could be found
	 * 
	 * @param id
	 *            the technical key of the person in the database
	 * @throws MeetingStateException
	 * 
	 */
	public PersonDTO findPersonById(Long id) throws MeetingStateException {
		Patient person = (Patient) personDao.read(id);
		if (person != null) {
			return new PatientDTO(person);
		}
		return null;
	}

	// Not yet used
	public void delete(PersonDTO personDTO) throws CouldNotDeleteException {
		Person existingPerson = personDao.read(personDTO.getId());
		if (existingPerson != null) {
			personDao.delete(existingPerson);
		}
	}

	/*
	 * public void update(PersonDTO personDTO) throws CouldNotSaveException {
	 * Meeting existingMeeting = meetingDao.read(meetingDTO.getId()); if
	 * (existingMeeting != null) {
	 * existingMeeting.setNotes(meetingDTO.getNotes());
	 * existingMeeting.setAppointmentTime(meetingDTO.getAppointmentTime());
	 * existingMeeting.setStateType(meetingDTO.getMeetingState());
	 * meetingDao.update(existingMeeting); } }
	 */

	/**
	 * Returns a List of {@code Patients} or {@code null} if no patients could
	 * be found
	 */
	public List<PatientDTO> getPatients() {
		List<Person> persons = personDao.readAll();
		List<PatientDTO> patients = new ArrayList<PatientDTO>(persons.size());
		for (Person person : persons) {
			if (person.getDtype().equals("Patient"))
				patients.add(new PatientDTO((Patient) person));
		}
		return patients;
	}

	public List<MeetingDTO> getMeetingsForPatient(Long id) {
		Patient patient = (Patient) personDao.read(id);
		List<MeetingDTO> meetingList = new ArrayList<>();
		for (Meeting m : meetingDao.findMeetingForPerson(patient)) {
			meetingList.add(new MeetingDTO(m));
		}
		return meetingList;
	}

	public List<DiagnosisDTO> getDiagnosisForPatient(Long id)
			throws DangerStateException {
		Patient patient = (Patient) personDao.read(id);
		List<DiagnosisDTO> diagnosisList = new ArrayList<>();
		for (Diagnosis d : diagnosisDao.findDiagnosisForPerson(patient)) {
			diagnosisList.add(new DiagnosisDTO(d));
		}
		return diagnosisList;
	}
}
