package ch.bfh.bti7081.s2016.yellow.SwissMD.presenter;

import ch.bfh.bti7081.s2016.yellow.SwissMD.view.PersonSearchView;

public class PersonSearchPresenter {
	// TODO: Knows the model
	private PersonSearchView personSearchView;
	
	public PersonSearchPresenter(PersonSearchView personSearchView) {
		System.out.println("init PersonSearchPresenter");
		this.personSearchView = personSearchView;
	}
}
