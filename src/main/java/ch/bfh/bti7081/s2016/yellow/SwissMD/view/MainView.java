package ch.bfh.bti7081.s2016.yellow.SwissMD.view;

import ch.bfh.bti7081.s2016.yellow.SwissMD.view.components.Menu;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class MainView extends VerticalLayout implements View {

	public MainView() {
		setSizeFull();
		setSpacing(true);
		addComponent(new Menu());
		addComponent(headingLabel());
//		addComponent(new TableExample());
	}

	@Override
	public void enter(ViewChangeEvent event) {
		Notification.show("Showing view: Main!");
	}
	
	private Label headingLabel() {
		return new Label("Main");
	}
	

}