package ch.bfh.bti7081.s2016.yellow.SwissMD.presenter;

import java.util.ArrayList;
import java.util.List;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.PersonDaoImpl;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PersonDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Person;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.CouldNotDeleteException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.PersonView;

public class PersonPresenter {
	private PersonDaoImpl personDao;

	public PersonPresenter(PersonView personView) {
		System.out.println("init PersonPresenter");
		this.personDao = new PersonDaoImpl();
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
			PersonDTO personDTO = new PersonDTO(person.getName(),
					person.getBirthdate(), person.getDtype());
			personDTO.setId(person.getId());

			return personDTO;
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
	public List<Person> getPatients() {
		List<Person> persons = personDao.readAll();
		List<Person> patients = new ArrayList<Person>();
		;
		for (Person person : persons) {
			if (person.getDtype().equals("Patient"))
				patients.add(person);
		}
		return patients;
	}
}
