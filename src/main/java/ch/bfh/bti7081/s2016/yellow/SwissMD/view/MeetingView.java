package ch.bfh.bti7081.s2016.yellow.SwissMD.view;

import java.util.List;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.MeetingDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PrescriptionDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.CouldNotDeleteException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.CouldNotSaveException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.presenter.MeetingPresenter;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.components.PersonTile;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.components.PrescriptionTile;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.BaseLayout;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.Tile;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.TileLayoutFactory;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.navigation.NavigationIndex;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextArea;

import java.text.MessageFormat;

@SuppressWarnings("serial")
public class MeetingView extends CustomComponent implements View {
	private static final String COULD_NOT_READ_MEETING_ID = "Meeting-Id konnte nicht gelesen werden";
	private static final String CHANGES_SAVED_SUCCESSFULLY = "Änderungen wurden gespeichert";
	private static final String CHANGES_NOT_SAVED = "Änderungen konnten nicht gespeichert werden";
	private static final String MEETING_DELETED_SUCCESSFULLY = "Das Meeting wurde gelöscht";
	private static final String MEETING_NOT_DELETED = "Das Meeting konnte nicht gelöscht werden";
	private static final String NO_MEETING_ID_PROVIDED = "Keine Meeting-Id zum Laden eines Meetings angegeben!";
	private static final String MEETING_WITH_ID_NOT_EXIST = "Meeting mit der Id {0} existiert nicht!";
	private static final String MEETING_ID_NOT_A_NUMBER = "Übergebener Parameter ist keine Zahl";
	private MeetingPresenter meetingPresenter = new MeetingPresenter(this);
	private BaseLayout layout;

	private MeetingDTO meetingDTO;

	private TextArea noteArea;
	private DateField dateField;

	public MeetingView() {
		System.out.println("INIT");
	}

	private Button getAddPrescriptionButton() {
		Button b = new Button("Neues Medikament verschreiben");
		b.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				// TODO: DO IT!
				// Notification.show("TODO", Type.WARNING_MESSAGE);
				// Tile drugTile = new DrugTile(meetingPresenter
				// .getDrugForMeeting(4321).get(0));
				// drugTile.addComponent(getNewDrugButton());
				// layout.addComponent(drugTile);
			}
		});
		return b;
	}

	private Button getUpdateButton() {
		Button b = new Button("Speichern");
		b.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				try {
					meetingDTO.setNotes(getNoteArea().getValue());
					meetingDTO.setAppointmentTime(getAppointmentTimeDateField()
							.getValue());
					meetingPresenter.update(meetingDTO);
					Notification.show(CHANGES_SAVED_SUCCESSFULLY,
							Type.HUMANIZED_MESSAGE);
				} catch (CouldNotSaveException e) {
					Notification.show(CHANGES_NOT_SAVED, Type.ERROR_MESSAGE);
				}
			}
		});
		return b;
	}

	private Button getDeleteButton() {
		Button b = new Button("Löschen");
		b.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				try {
					meetingPresenter.delete(meetingDTO);
					Notification.show(MEETING_DELETED_SUCCESSFULLY,
							Type.HUMANIZED_MESSAGE);
					getUI().getNavigator().navigateTo(
							NavigationIndex.PERSONVIEW + "/"
									+ meetingDTO.getPatient().getId());
				} catch (CouldNotDeleteException e) {
					Notification.show(MEETING_NOT_DELETED, Type.ERROR_MESSAGE);
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
				// TODO: DO IT!
				Notification.show("TODO", Type.HUMANIZED_MESSAGE);
			}
		});
		return saveNewDrug;
	}

	private TextArea getNoteArea() {
		return noteArea;
	}

	private DateField getAppointmentTimeDateField() {
		return dateField;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		System.out.println("ENTER ");

		setSizeFull();

		layout = TileLayoutFactory.getInstance().createLayout(3);
		// layout.addComponent(headingLabel());

		System.out.println(event.getParameters());
		String param = event.getParameters();

		if (param != null && !param.isEmpty()) {
			Long meetingId = null;
			try {
				meetingId = Long.valueOf(param);
			} catch (Exception e) {
				Notification.show(MEETING_ID_NOT_A_NUMBER,
						Type.HUMANIZED_MESSAGE);
			}

			if (meetingId != null) {

				meetingDTO = meetingPresenter.findMeetingById(meetingId);

				if (meetingDTO != null) {

					Tile appointmentTile = new Tile();
					DateField df = new DateField("Termin");
					df.setWidth(200, Unit.PIXELS);
					df.setDateFormat("dd.MM.yyyy HH:mm");
					df.setResolution(Resolution.MINUTE);
					this.dateField = df;
					dateField.setValue(meetingDTO.getAppointmentTime());
					appointmentTile.addComponent(dateField);
					layout.addComponent(appointmentTile);

					Tile meetingTile = new Tile();

					TextArea area = new TextArea("Sitzungsnotizen");
					area.setRows(15);
					// area.setWidth(100, Unit.PERCENTAGE);
					// area.setSizeFull();
					area.setWidth(400, Unit.PIXELS);
					area.setValue(meetingDTO.getNotes());
					this.noteArea = area;

					meetingTile.addComponent(this.noteArea);
					meetingTile.addComponent(getUpdateButton());
					meetingTile.addComponent(getDeleteButton());
					layout.addComponent(meetingTile);
					layout.createRowBrake();

					List<PrescriptionDTO> prescriptions = meetingDTO
							.getPatient().getPrescriptions();
					for (PrescriptionDTO prescriptionDTO : prescriptions) {
						Tile prescriptionTile = new PrescriptionTile(
								prescriptionDTO);
						prescriptionTile
								.addComponent(getAddPrescriptionButton());
						layout.addComponent(prescriptionTile);
					}

					layout.addComponent(new PersonTile(meetingDTO.getPatient(),
							"Patient"));

					layout.addComponent(new PersonTile(meetingDTO.getDoctor(),
							"Arzt"));

				} else {
					Notification.show(MessageFormat.format(
							MEETING_WITH_ID_NOT_EXIST, param),
							Type.HUMANIZED_MESSAGE);
				}
			}
		}
		setCompositionRoot(layout.toVaadinComponent());
	}

	private Label headingLabel() {
		return new Label("MeetingView");
	}

}
