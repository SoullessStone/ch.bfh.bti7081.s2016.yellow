package ch.bfh.bti7081.s2016.yellow.SwissMD.presenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.time.DateUtils;

import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.PersonDaoImpl;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PersonDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Person;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.PersonSearchView;

public class PersonSearchPresenter {
	// TODO: Knows the model
	private PersonSearchView personSearchView;

	private PersonDaoImpl personDao;
	private List<PersonDTO> cachedPersons;

	public PersonSearchPresenter(PersonSearchView personSearchView) {
		System.out.println("init PersonSearchPresenter");
		this.personSearchView = personSearchView;
		this.personDao = new PersonDaoImpl();
	}

	public List<PersonDTO> searchForBirthdate(Date birthdate) {
		Notification.show("searchForBirthdate", Type.HUMANIZED_MESSAGE);
		List<PersonDTO> result = new ArrayList<>();
		for (PersonDTO p : getAllPersons()) {
			if (DateUtils.isSameDay(birthdate, p.getBirthdate())) {
				result.add(p);
			}
		}
		return result;
		/*
		 * Not working... return getAllPersons();.stream() .filter(p ->
		 * DateUtils.isSameDay(birthdate, p.getBirthdate()))
		 * .collect(Collectors.toList());
		 */
	}

	public List<PersonDTO> searchForLastName(String lastname) {
		Notification.show("searchForLastName", Type.HUMANIZED_MESSAGE);
		List<PersonDTO> result = new ArrayList<>();
		for (PersonDTO p : getAllPersons()) {
			if (p.getName().toLowerCase().contains(lastname.toLowerCase())) {
				result.add(p);
			}
		}
		return result;
		/*
		 * return getAllPersons().stream() .filter(p ->
		 * p.getName().equals(lastname)) .collect(Collectors.toList());
		 */
	}

	public List<PersonDTO> searchForBirthdateAndLastname(Date birthdate,
			String lastname) {
		Notification.show("searchForBirthdateAndLastname",
				Type.HUMANIZED_MESSAGE);
		List<PersonDTO> result = new ArrayList<>();
		for (PersonDTO p : getAllPersons()) {
			if (p.getName().toLowerCase().contains(lastname.toLowerCase())
					&& DateUtils.isSameDay(birthdate, p.getBirthdate())) {
				result.add(p);
			}
		}
		return result;
		/*
		 * .stream() .filter(p -> p.getName().equals(lastname) &&
		 * DateUtils.isSameDay(birthdate, p.getBirthdate()))
		 * .collect(Collectors.toList());
		 */
	}

	public List<PersonDTO> getAllPersons() {
		if (cachedPersons != null) {
			return cachedPersons;
		}
		List<Person> persons = personDao.readAll();
		List<PersonDTO> personDTOs = new ArrayList<>(persons.size());
		for (Person p : persons) {
			personDTOs.add(new PersonDTO(p));
		}
		return personDTOs;
	}
}
