package ch.bfh.bti7081.s2016.yellow.SwissMD.view;


import ch.bfh.bti7081.s2016.yellow.SwissMD.view.components.Menu;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
// When just clicked: Shows the users information
// When navigated to from PersonSearch: Shows the chosen Person
// Can show Person, Doctor and Patient
@SuppressWarnings("serial")
public class PersonView extends VerticalLayout implements View {

	public PersonView() {
		setSizeFull();
		setSpacing(true);
		addComponent(new Menu());
		addComponent(headingLabel());
	}

	@Override
	public void enter(ViewChangeEvent event) {
	}
	
	private Label headingLabel() {
		return new Label("PersonView");
	}
	

}
