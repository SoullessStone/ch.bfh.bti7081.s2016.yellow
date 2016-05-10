package ch.bfh.bti7081.s2016.yellow.SwissMD.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class HelpView extends VerticalLayout implements View {

	public HelpView() {
		setSizeFull();
		setSpacing(true);
//		addComponent(new Menu());
		addComponent(headingLabel());
		addComponent(someText());
	}

	@Override
	public void enter(ViewChangeEvent event) {
		Notification.show("Showing view: Help!");
	}
	
	private Label headingLabel() {
		return new Label("Help");
	}
	
	private Label someText() {
		Label label = new Label("gaga");
		label.setContentMode(ContentMode.HTML);
		return label;
	}
	
}