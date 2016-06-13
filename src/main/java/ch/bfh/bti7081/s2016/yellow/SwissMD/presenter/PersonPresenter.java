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
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.DoctorDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.MeetingDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PatientDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PersonDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Diagnosis;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Doctor;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Meeting;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Patient;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Person;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.CouldNotDeleteException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.CouldNotSaveException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.DangerStateException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.MeetingStateException;

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

	public PersonPresenter() {
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
	 * @throws DangerStateException 
	 * 
	 */
	public PersonDTO findPersonById(Long id) throws MeetingStateException, DangerStateException {
		Person person = (Person) personDao.read(id);
		PersonDTO personDTO = null;
		if (person != null) {
			if ("Doctor".equals(person.getDtype())){
				personDTO = new DoctorDTO((Doctor) person);
			} else if ("Patient".equals(person.getDtype())){
				personDTO = new PatientDTO((Patient) person);
			} else {
				personDTO = new PersonDTO(person);
			}
		}
		return personDTO;
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
	 * @throws DangerStateException 
	 */
	public List<PatientDTO> getPatients() throws DangerStateException {
		List<Person> persons = personDao.readAll();
		List<PatientDTO> patients = new ArrayList<PatientDTO>(persons.size());
		for (Person person : persons) {
			if (person.getDtype().equals("Patient"))
				patients.add(new PatientDTO((Patient) person));
		}
		return patients;
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
	
	public void updateDangerState(PatientDTO patientDTO) throws CouldNotSaveException {
		Patient patient = (Patient) personDao.read(patientDTO.getId());
		personDao.updateDangerState(patient);
	}
}
