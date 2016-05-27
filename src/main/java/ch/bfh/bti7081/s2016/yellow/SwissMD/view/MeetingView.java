package ch.bfh.bti7081.s2016.yellow.SwissMD.view;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.DoctorDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.MeetingDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PatientDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PrescriptionDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.CouldNotDeleteException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.CouldNotSaveException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.MeetingStateException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.presenter.MeetingPresenter;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.components.CreatePrescriptionTile;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.components.CreationPrescriptiontileObserver;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.components.PersonTile;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.components.PrescriptionTile;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.BaseLayout;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.LayoutFactory;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.LayoutFactory.LayoutType;
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
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextArea;

@SuppressWarnings("serial")
public class MeetingView extends CustomComponent implements View,
		CreationPrescriptiontileObserver {
	private static final String REASON = " Grund: ";
	private static final String NEW_MEETING_KEYWORD = "new";
	private static final String CHANGES_SAVED_SUCCESSFULLY = "Änderungen wurden gespeichert";
	private static final String CHANGES_NOT_SAVED = "Änderungen konnten nicht gespeichert werden";
	private static final String MEETING_DELETED_SUCCESSFULLY = "Das Meeting wurde gelöscht";
	private static final String MEETING_NOT_DELETED = "Das Meeting konnte nicht gelöscht werden";
	private static final String MEETING_WITH_ID_NOT_EXIST = "Meeting mit der Id {0} existiert nicht!";
	private static final String MEETING_ID_NOT_A_NUMBER = "Übergebener Meeting-Parameter ist keine Zahl";
	private static final String PATIENT_ID_NOT_A_NUMBER = "Übergebener Patient-Parameter ist keine Zahl";
	private static final String MEETING_NOT_CREATED = "Meeting konnte nicht erstellt werden" ;
	private MeetingPresenter meetingPresenter = new MeetingPresenter(this);
	private BaseLayout layout;

	private MeetingDTO meetingDTO;

	private TextArea noteArea;
	private DateField dateField;

	// private CreatePrescriptionTile createPrescriptionTile;

	public MeetingView() {
		try {
			layout = LayoutFactory.getInstance(LayoutType.TILE_LAYOUT)
					.createLayout(
							TileLayoutFactory.Arguments.ELEMENTS_PER_ROW
									.getName() + ":3");
		} catch (Exception e1) {
			// TODO Go to error View
			e1.printStackTrace();
		}

		setCompositionRoot(layout);
	}

	private Button getSaveButton() {
		Button b = new Button("Speichern");
		b.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				try {
					// TODO: Subscriptions auch updaten!
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

	private TextArea getNoteArea() {
		return noteArea;
	}

	private DateField getAppointmentTimeDateField() {
		return dateField;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		String param = event.getParameters();
		// param nicht leer
		if (param != null && !param.isEmpty()) {
			// wenn ein neues Meeting erstellt werden soll, kommt das if zum Zug
			if (param.contains(NEW_MEETING_KEYWORD)) {
				String[] splitted = param.split("=");
				if (splitted.length == 2) {
					try {
						// Versuchen, PatientenId zu lesen
						Long patientId = Long.valueOf(splitted[1]);
						PatientDTO patient = meetingPresenter
								.getPatient(patientId);
						// Meeting mit Patient erstellen
						try {
							MeetingDTO meetingDTO = new MeetingDTO(patient,
									new DoctorDTO("bla", new Date()), new Date());
							MeetingDTO m;
							
							m = meetingPresenter.create(meetingDTO);
							
							if (m != null) {
								getUI().getNavigator().navigateTo(
										NavigationIndex.MEETINGVIEW + "/"
												+ m.getId());
							} else {
								getUI().getNavigator().navigateTo(
										NavigationIndex.PERSONSEARCHVIEW
												.getNavigationPath());
							}
						} catch (MeetingStateException e) {
							Notification.show(MEETING_NOT_CREATED + REASON + e.getMessage() , Type.HUMANIZED_MESSAGE);
							e.printStackTrace();
						}
						
					} catch (NumberFormatException e) {
						Notification.show(PATIENT_ID_NOT_A_NUMBER,
								Type.HUMANIZED_MESSAGE);
					}

				}
			} else {
				// Meeting soll angezeigt werden
				Long meetingId = null;
				try {
					meetingId = Long.valueOf(param);
				} catch (NumberFormatException e) {
					Notification.show(MEETING_ID_NOT_A_NUMBER,
							Type.HUMANIZED_MESSAGE);
					getUI().getNavigator().navigateTo(
							NavigationIndex.PERSONSEARCHVIEW
									.getNavigationPath());
				}

				if (meetingId != null) {
					// Lese MeetingDTO
					meetingDTO = meetingPresenter.findMeetingById(meetingId);

					if (meetingDTO != null) {
						showMeetingInView();
					} else {
						Notification.show(MessageFormat.format(
								MEETING_WITH_ID_NOT_EXIST, param),
								Type.HUMANIZED_MESSAGE);
					}
				} else {
					getUI().getNavigator().navigateTo(
							NavigationIndex.PERSONSEARCHVIEW
									.getNavigationPath());
				}
			}
		} else {
			getUI().getNavigator().navigateTo(
					NavigationIndex.PERSONSEARCHVIEW.getNavigationPath());
		}
		layout.finishLayout();
	}

	private void showMeetingInView() {
		// MeetingDTO angemessen abbilden
		Tile appointmentTile = new Tile("Sitzung");

		DateField df = new DateField("Termin");
		df.setWidth(200, Unit.PIXELS);
		df.setDateFormat("dd.MM.yyyy HH:mm");
		df.setResolution(Resolution.MINUTE);
		this.dateField = df;
		dateField.setValue(meetingDTO.getAppointmentTime());

		appointmentTile.addComponent(dateField);
		layout.addComponent(appointmentTile);

		layout.addComponent(new PersonTile(meetingDTO.getPatient(), "Patient"));

		layout.addComponent(new PersonTile(meetingDTO.getDoctor(), "Arzt"));

		Tile meetingTile = new Tile("Sitzungsnotizen");
		meetingTile.setStdWidth(3);
		TextArea area = new TextArea();
		area.setRows(15);
		area.setWidth(100, Unit.PERCENTAGE);
		area.setValue(meetingDTO.getNotes());
		this.noteArea = area;

		meetingTile.addComponent(this.noteArea);
		HorizontalLayout buttonArea = new HorizontalLayout();
		buttonArea.addComponent(getSaveButton());
		buttonArea.addComponent(getDeleteButton());
		buttonArea.setSpacing(true);
		meetingTile.addComponent(buttonArea);
		layout.addComponent(meetingTile);
		layout.createRowBrake();

		List<PrescriptionDTO> prescriptions = meetingDTO.getPatient()
				.getPrescriptions();
		if (prescriptions.isEmpty()) {

			// Tile prescriptionTile = new Tile("Verschreibungen");
			// prescriptionTile
			// .addComponent(getAddPrescriptionButton());
			// layout.addComponent(prescriptionTile);
		}
		for (PrescriptionDTO prescriptionDTO : prescriptions) {
			Tile prescriptionTile = new PrescriptionTile(prescriptionDTO);
			// prescriptionTile.addComponent(getAddPrescriptionButton());
			layout.addComponent(prescriptionTile);
			layout.createRowBrake();
		}

		createPrescriptionTile();
	}

	private Label headingLabel() {
		return new Label("MeetingView");
	}

	private void createPrescriptionTile() {
		CreatePrescriptionTile createPrescriptionTile = new CreatePrescriptionTile(
				meetingPresenter.getPossibleDrugs());
		createPrescriptionTile.addObserver(MeetingView.this);
		layout.addComponent(createPrescriptionTile);
		layout.createRowBrake();
	}

	@Override
	public void perscriptionCreated(PrescriptionDTO prescriptionDTO) {
		this.meetingDTO.getPatient().addPrescription(prescriptionDTO);
		createPrescriptionTile();
		System.out.println("Prescription created");
	}

}
