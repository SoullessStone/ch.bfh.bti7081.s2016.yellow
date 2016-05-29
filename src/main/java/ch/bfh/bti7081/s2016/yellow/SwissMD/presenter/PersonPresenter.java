package ch.bfh.bti7081.s2016.yellow.SwissMD.presenter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.MeetingDaoImpl;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.PersonDaoImpl;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.DoctorDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.MeetingDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PatientDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PersonDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Meeting;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Patient;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Person;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.CouldNotDeleteException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.MeetingStateException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.PersonView;

public class PersonPresenter {
	private PersonDaoImpl personDao;
	private MeetingDaoImpl meetingDao;

	public PersonPresenter(PersonView personView) {
		System.out.println("init PersonPresenter");
		this.personDao = new PersonDaoImpl();
		this.meetingDao = new MeetingDaoImpl();
	}

	/**
	 * Returns a {@code PersonDTO} or {@code null} if no person with this id
	 * could be found
	 * 
	 * @param id
	 *            the technical key of the person in the database
	 * 
	 */
	public PersonDTO findPersonById(Long id) {
		Person person = personDao.read(id);
		if (person != null) {
			return new PersonDTO(person);
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

	/**
	 * Returns a List of {@code Patients} or {@code null} if no patients could
	 * be found
	 */
	public List<PersonDTO> getPatients() {
		List<Person> persons = personDao.readAll();
		List<PersonDTO> patients = new ArrayList<PersonDTO>(persons.size());
		for (Person person : persons) {
			if (person.getDtype().equals("Patient"))
				patients.add(new PersonDTO(person));
		}
		return patients;
	}

	public List<MeetingDTO> getMeetingsForPatient(Long id) throws MeetingStateException {
		Patient patient = (Patient) personDao.read(id);
		List<MeetingDTO> meetingList = new ArrayList<>();
		for (Meeting m : meetingDao.findMeetingForPerson(patient)) {
			meetingList.add(new MeetingDTO(m));
		}
		return meetingList;
	}
}
