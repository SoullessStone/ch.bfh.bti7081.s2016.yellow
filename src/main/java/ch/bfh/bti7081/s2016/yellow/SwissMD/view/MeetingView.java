package ch.bfh.bti7081.s2016.yellow.SwissMD.view;


import java.util.Date;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.Person;
import ch.bfh.bti7081.s2016.yellow.SwissMD.presenter.MeetingPresenter;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.components.NavigationsMenu;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.components.PersonenTile;

import com.vaadin.client.ui.calendar.schedule.DateCell;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class MeetingView extends VerticalLayout implements View {
	private MeetingPresenter meetingPresenter = new MeetingPresenter(this);

	public MeetingView() {
		setSizeFull();
		setSpacing(true);
		addComponent(new NavigationsMenu());
		addComponent(headingLabel());
		addComponent(getAppointmentTimeDateField());
		// TODO: Durch richtige Daten erweitern
		addComponent(new PersonenTile(new Person("Peter Test", new Date(
				768907564000L)), "Patient"));
		addComponent(new PersonenTile(new Person("Dieter Dümmlich", new Date(
				9997564000L)), "Arzt"));
		addComponent(getNoteArea());
	}

	private Component getNoteArea() {
		TextArea area = new TextArea();
		area.setRows(15);
		area.setWidth(400, Unit.PIXELS);
		return area;
	}

	private DateField getAppointmentTimeDateField() {
		DateField df = new DateField();
		df.setDateFormat("dd.MM.yyyy HH:mm");
		df.setResolution(Resolution.MINUTE);
		return df;
	}

	@Override
	public void enter(ViewChangeEvent event) {
	}

	private Label headingLabel() {
		return new Label("MeetingView");
	}

}
