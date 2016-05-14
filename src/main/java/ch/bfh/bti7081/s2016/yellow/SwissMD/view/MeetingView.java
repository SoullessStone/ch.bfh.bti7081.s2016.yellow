package ch.bfh.bti7081.s2016.yellow.SwissMD.view;

import java.util.Date;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.Person;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.Prescription;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.CouldNotSaveException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.util.DateRange;
import ch.bfh.bti7081.s2016.yellow.SwissMD.presenter.MeetingPresenter;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.components.NavigationsMenu;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.components.PersonTile;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.components.PrescriptionTile;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

@SuppressWarnings("serial")
public class MeetingView extends VerticalLayout implements View {
	private MeetingPresenter meetingPresenter = new MeetingPresenter(this);

	public MeetingView() {
		// Daten laden vom Presenter laden
		this.addComponent(new NavigationsMenu());
		this.addComponent(headingLabel());
		this.addComponent(getSaveButton());
		this.setSizeFull();
		this.setSpacing(true);

		GridLayout grid = new GridLayout(3, 1);
		grid.setSpacing(true);

		VerticalLayout verticalLayoutLeft = new VerticalLayout();
		verticalLayoutLeft.setSpacing(true);
		verticalLayoutLeft.addComponent(getAppointmentTimeDateField());
		// TODO: Durch richtige Daten erweitern
		verticalLayoutLeft.addComponent(new PersonTile(meetingPresenter
				.getPatientForMeeting(4321), "Patient"));

		verticalLayoutLeft.addComponent(new PersonTile(meetingPresenter
				.getDoctorForMeeting(4321), "Arzt"));
		grid.addComponent(verticalLayoutLeft, 0, 0);

		grid.addComponent(getNoteArea(), 1, 0);

		VerticalLayout verticalLayoutRight = new VerticalLayout();
		verticalLayoutRight.setSpacing(true);
		verticalLayoutRight.addComponent(getAddPrescriptionButton());
		verticalLayoutRight.addComponent(new PrescriptionTile(meetingPresenter.getPerscriptionsForMeeting(4321).get(0)));
		grid.addComponent(verticalLayoutRight, 2, 0);

		this.addComponent(grid);
	}

	private Button getAddPrescriptionButton() {
		Button b = new Button("Neues Medikament verschreiben");
		b.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO: DO IT!
				Notification.show("TODO",
						Type.WARNING_MESSAGE);
			}
		});
		return b;
	}

	private Button getSaveButton() {
		Button b = new Button("Speichern");
		b.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				// TODO: call Presenter mit richtigen Daten
				try {
					meetingPresenter.save();
					Notification.show("Änderungen wurden gespeichert",
							Type.HUMANIZED_MESSAGE);
				} catch (CouldNotSaveException e) {
					Notification.show(
							"Änderungen konnten nicht gespeichert werden",
							Type.ERROR_MESSAGE);
				}
			}
		});
		return b;
	}

	private Component getNoteArea() {
		TextArea area = new TextArea("Sitzungsnotizen");
		area.setRows(15);
		area.setWidth(400, Unit.PIXELS);
		return area;
	}

	private DateField getAppointmentTimeDateField() {
		DateField df = new DateField("Termin");
		df.setWidth(200, Unit.PIXELS);
		df.setDateFormat("dd.MM.yyyy HH:mm");
		df.setResolution(Resolution.MINUTE);
		return df;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		System.out.println(event.getParameters());
	}

	private Label headingLabel() {
		return new Label("MeetingView");
	}

}
