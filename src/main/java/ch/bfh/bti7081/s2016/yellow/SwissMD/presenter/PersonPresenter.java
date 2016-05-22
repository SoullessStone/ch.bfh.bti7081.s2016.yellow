package ch.bfh.bti7081.s2016.yellow.SwissMD.presenter;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.PersonDaoImpl;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.PersonView;

public class PersonPresenter {
	private PersonView personView;
	private PersonDaoImpl personDao;

	public PersonPresenter(PersonView personView) {
		System.out.println("init PersonPresenter");
		this.personView = personView;
		personDao = new PersonDaoImpl();
	}
}
