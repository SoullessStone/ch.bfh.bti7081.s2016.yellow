package ch.bfh.bti7081.s2016.yellow.SwissMD.presenter;

import ch.bfh.bti7081.s2016.yellow.SwissMD.view.PersonView;

public class PersonPresenter {
	// TODO: Knows the model
	private PersonView personView;

	public PersonPresenter(PersonView personView) {
		System.out.println("init PersonPresenter");
		this.personView = personView;
	}
}
