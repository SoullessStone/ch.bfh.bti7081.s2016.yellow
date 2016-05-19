package ch.bfh.bti7081.s2016.yellow.SwissMD.view;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.DrugDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.CouldNotSaveException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.presenter.MeetingPresenter;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.components.DrugTile;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.components.PersonTile;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.components.PrescriptionTile;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.BaseLayout;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.Tile;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.TileLayout;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.TileLayoutFactory;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.navigation.NavigationsMenu;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class MeetingView extends CustomComponent implements View {
	private MeetingPresenter meetingPresenter = new MeetingPresenter(this);
	private BaseLayout layout;

	public MeetingView() {
		// Daten laden vom Presenter laden
		setSizeFull();
		
		layout = TileLayoutFactory.getInstance().createLayout(3);
		//layout.addComponent(headingLabel());

		Tile appointmentTile = new Tile();
		appointmentTile.addComponent(getAppointmentTimeDateField());
		layout.addComponent(appointmentTile);
		// TODO: Durch richtige Daten erweitern

		layout.addComponent(new PersonTile(meetingPresenter
				.getPatientForMeeting(4321), "Patient"));

		layout.addComponent(new PersonTile(meetingPresenter
				.getDoctorForMeeting(4321), "Arzt"));


		Tile meetingTile = new Tile();
		meetingTile.addComponent(getNoteArea());
		meetingTile.addComponent(getSaveButton());
		layout.addComponent(meetingTile);
		layout.createRowBrake();


		Tile prescriptionTile = new PrescriptionTile(meetingPresenter
				.getPerscriptionsForMeeting(4321).get(0));
		
		prescriptionTile.addComponent(getAddPrescriptionButton());
		layout.addComponent(prescriptionTile);
		
		setCompositionRoot(layout.toVaadinComponent());
	}

	private Button getAddPrescriptionButton() {
		Button b = new Button("Neues Medikament verschreiben");
		b.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				// TODO: DO IT!
				//Notification.show("TODO", Type.WARNING_MESSAGE);				
				Tile drugTile = new DrugTile(meetingPresenter
						.getDrugForMeeting(4321).get(0));
				drugTile.addComponent(getNewDrugButton());
				layout.addComponent(drugTile);
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
					Notification.show("Änderungen wurden gespeichert", Type.HUMANIZED_MESSAGE);
				} catch (CouldNotSaveException e) {
					Notification.show("Änderungen konnten nicht gespeichert werden", Type.ERROR_MESSAGE);
				}
			}
		});
		return b;
	}

	private Button getNewDrugButton() {
		Button saveNewDrug = new Button("Verordnen");
		saveNewDrug.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				// TODO: call Presenter mit richtigen Daten
				try {
					// TODO: DO IT!
					meetingPresenter.save();
					Notification.show("TODO", Type.HUMANIZED_MESSAGE);
				} catch (CouldNotSaveException e) {
					Notification.show("Änderungen konnten nicht gespeichert werden", Type.ERROR_MESSAGE);
				}
			}
		});
		return saveNewDrug;
	}
	
	private Component getNoteArea() {
		TextArea area = new TextArea("Sitzungsnotizen");
		area.setRows(15);
		//area.setWidth(100, Unit.PERCENTAGE);
		//area.setSizeFull();
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
		// Wird jedes Mal aufgerufen, wenn hierhin navigiert wird. Hier könnte
		// man also den Parameter in der URL auslesen
		System.out.println(event.getParameters());
	}

	private Label headingLabel() {
		return new Label("MeetingView");
	}

}
