package ch.bfh.bti7081.s2016.yellow.SwissMD.view;

import ch.bfh.bti7081.s2016.yellow.SwissMD.presenter.PersonPresenter;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.components.NavigationsMenu;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
// When just clicked: Shows the users information
// When navigated to from PersonSearch: Shows the chosen Person
// Can show Person, Doctor and Patient

@SuppressWarnings("serial")
public class PersonView extends VerticalLayout implements View {
	private PersonPresenter personPresenter = new PersonPresenter(this);

	public PersonView() {
		setSizeFull();
		setSpacing(true);
		addComponent(new NavigationsMenu());
		addComponent(headingLabel());
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// Wird jedes Mal aufgerufen, wenn hierhin navigiert wird. Hier könnte
		// man also den Parameter in der URL auslesen
		System.out.println(event.getParameters());
	}

	private Label headingLabel() {
		return new Label("PersonView");
	}

}
