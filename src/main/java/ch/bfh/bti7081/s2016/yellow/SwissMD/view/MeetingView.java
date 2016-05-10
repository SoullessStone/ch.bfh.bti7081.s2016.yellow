package ch.bfh.bti7081.s2016.yellow.SwissMD.view;


import ch.bfh.bti7081.s2016.yellow.SwissMD.presenter.MeetingPresenter;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.components.Menu;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

// Just do
@SuppressWarnings("serial")
public class MeetingView extends VerticalLayout implements View {
	private MeetingPresenter meetingPresenter = new MeetingPresenter(this);
	
	public MeetingView() {
		setSizeFull();
		setSpacing(true);
		addComponent(new Menu());
		addComponent(headingLabel());
	}

	@Override
	public void enter(ViewChangeEvent event) {
	}
	
	private Label headingLabel() {
		return new Label("MeetingView");
	}
	

}
