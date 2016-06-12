package ch.bfh.bti7081.s2016.yellow.SwissMD.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.DiagnosisDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.MeetingDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PatientDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PersonDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Person;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.DangerStateException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.MeetingStateException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.presenter.PersonPresenter;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.components.DiagnosisTile;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.components.GridTile;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.components.MeetingTile;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.components.PersonTile;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.BaseLayout;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.LayoutFactory;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.LayoutFactory.LayoutType;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.Tile;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.TileLayoutFactory;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.navigation.NavigationIndex;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class PersonView extends CustomComponent implements View {
	private final String COULD_NOT_READ_PERSON_ID = "Ungültige Id angegeben!";
	private final String ID_NOT_A_NUMBER = "Übergebener Parameter ist keine Zahl";
	private final String ID_NOT_A_PATIENT = "Die gesuchte Person ist kein Patient";
	private final String PERSON_NOT_FOUND = "Die gewünschte Person konnte nicht gefunden werden";
	private final String NO_PERSON_IN_SESSION = "Keine Person ausgewählt";

	private PersonPresenter personPresenter = new PersonPresenter(this);
	private BaseLayout layout;

	public PersonView() {
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

	@Override
	public void enter(ViewChangeEvent event) {
		PatientDTO patientDTO = (PatientDTO) getUI().getSession().getAttribute(
				"currentPatient");
		PersonDTO sessionPersonDTO = (PersonDTO) getUI().getSession()
				.getAttribute("currentPerson");

		// Combobox mit allen möglichen Patienten
		// Tile selectPatientTile = new
		// Tile("Patient auswählen","img/icons/users_small.png");
		// HorizontalLayout selectPatientsArea = new HorizontalLayout();
		// List<PatientDTO> list = personPresenter.getPatients();
		// ComboBox selectPatientCBox = new ComboBox();
		// for (PatientDTO patient : list) {
		// selectPatientCBox.addItem(patient);
		// }
		// selectPatientsArea.addComponent(selectPatientCBox);
		// selectPatientsArea
		// .addComponent(getSelectPatientButton(selectPatientCBox));
		// selectPatientsArea.setSpacing(true);
		// selectPatientTile.addComponent(selectPatientsArea);
		// layout.addComponent(selectPatientTile);
		// layout.createRowBrake();

		// if person is a not a patient
		if (sessionPersonDTO != null) {
			layout.addComponent(new PersonTile(sessionPersonDTO,
					sessionPersonDTO.getDtype()));
		}
		// patientDTO is in session
		else if (patientDTO != null) {

			// 1. tile: base data
			Tile baseDataTile = new Tile("Allgemeine Grunddaten "
					+ patientDTO.getName());
			baseDataTile.setStdWidth(3);
			GridLayout grid = new GridLayout(2, 4);
			grid.setSizeFull();
			grid.addComponent(new Label("Name: " + patientDTO.getName()));
			grid.addComponent(new Label("Adresse: "
					+ (patientDTO.getAddress() != null ? patientDTO
							.getAddress() : "---")));
			grid.addComponent(new Label("PLZ / Ort: "
					+ (patientDTO.getZip() != null ? patientDTO.getZip() + " "
							: "")
					+ (patientDTO.getCity() != null ? patientDTO.getCity()
							: "---")));
			grid.addComponent(new Label("Mobile: "
					+ (patientDTO.getMobile() != null ? patientDTO.getMobile()
							: "---")));
			grid.addComponent(new Label("Festnetz: "
					+ (patientDTO.getLandline() != null ? patientDTO
							.getLandline() : "---")));

			baseDataTile.addComponent(grid);
			layout.addComponent(baseDataTile);

			// 2. tile: medical base data (data protection critical stuff)
			GridTile medicalDataTile = new GridTile(patientDTO);
			layout.addComponent(medicalDataTile);

			// 3. tile: diagnosis of patient
			PatientDTO patientInSession = (PatientDTO) getUI().getSession()
					.getAttribute("currentPatient");
			if (patientInSession == null) {
				getUI().getNavigator().navigateTo(
						NavigationIndex.PERSONSEARCHVIEW.getNavigationPath());
			}
			try {
				Tile diagnosisContainer = new Tile("Diagnosen");
				for (DiagnosisDTO diagnosisDTO : personPresenter
						.getDiagnosisForPatient(patientInSession.getId())) {
					diagnosisContainer.addComponent(new DiagnosisTile(diagnosisDTO,
							diagnosisDTO.getIllness().toString()));
				}
				layout.addComponent(diagnosisContainer);
			} catch (DangerStateException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// 4. tile: action-tile
			Button createMeetingButton = new Button(
					"Neues Meeting mit diesem Patient");
			createMeetingButton.addClickListener(new ClickListener() {

				@Override
				public void buttonClick(ClickEvent event) {
					getUI().getNavigator().navigateTo(
							NavigationIndex.MEETINGVIEW.getNavigationPath()
									+ "/new=" + patientDTO.getId());
				}
			});
			Tile actionsTile = new Tile("Aktionen");
			actionsTile.addComponent(createMeetingButton);
			layout.createRowBrake();
			layout.addComponent(actionsTile);

			Tile historyTile = new Tile("Patientenhistory");
			try {
				List<MeetingDTO> meetingDTOs = new ArrayList<MeetingDTO>();
				meetingDTOs = personPresenter.getMeetingsForPatient(patientDTO
						.getId());
				Collections.sort(meetingDTOs);
				VerticalLayout verticalLayout = new VerticalLayout();
				verticalLayout.setSpacing(true);
				for (MeetingDTO m : meetingDTOs) {
					verticalLayout.addComponent(new MeetingTile(m));
				}
				historyTile.addComponent(verticalLayout);
				layout.createRowBrake();
				layout.addComponent(historyTile);
			} catch (MeetingStateException e) {
				// TODO ? Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			getUI().getNavigator().navigateTo(
					NavigationIndex.PERSONSEARCHVIEW.getNavigationPath());
			Notification.show(NO_PERSON_IN_SESSION, Type.HUMANIZED_MESSAGE);
		}
	}

	// private Button getSelectPatientButton(ComboBox patientDTO) {
	// Button b = new Button("Patient auswählen");
	// b.addClickListener(new ClickListener() {
	//
	// @Override
	// public void buttonClick(ClickEvent event) {
	// PatientDTO patient = (PatientDTO) patientDTO.getValue();
	// getUI().getSession().setAttribute("currentPatient", patient);
	// try {
	// getUI().getNavigator().navigateTo(
	// NavigationIndex.PERSONVIEW.getNavigationPath());
	// } catch (Exception e) {
	// e.printStackTrace();
	// Notification.show(PERSON_NOT_FOUND, Type.ERROR_MESSAGE);
	// }
	// }
	// });
	// return b;
	// }

}
