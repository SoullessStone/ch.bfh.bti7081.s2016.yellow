package ch.bfh.bti7081.s2016.yellow.SwissMD.view;

import ch.bfh.bti7081.s2016.yellow.SwissMD.presenter.PersonSearchPresenter;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.navigation.NavigationsMenu;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

// Just do
@SuppressWarnings("serial")
public class PersonSearchView extends VerticalLayout implements View {
	private PersonSearchPresenter personSearchPresenter = new PersonSearchPresenter(
			this);

	public PersonSearchView() {
		setSizeFull();
		setSpacing(true);
		//addComponent(new NavigationsMenu());
		addComponent(headingLabel());
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// Wird jedes Mal aufgerufen, wenn hierhin navigiert wird. Hier könnte
		// man also den Parameter in der URL auslesen
		System.out.println(event.getParameters());
	}

	private Label headingLabel() {
		return new Label("PersonSearchView");
	}

}
