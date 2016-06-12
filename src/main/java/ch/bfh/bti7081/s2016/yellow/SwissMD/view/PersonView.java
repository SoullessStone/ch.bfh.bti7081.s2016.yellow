package ch.bfh.bti7081.s2016.yellow.SwissMD.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.DiagnosisDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.MeetingDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PatientDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PersonDTO;
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
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.VerticalLayout;

/**
 * Hier werden Personen dargestellt
 * 
 * @author Schaad
 *
 */
@SuppressWarnings("serial")
public class PersonView extends CustomComponent implements View {
	private static final String DIAGNOSIS = "Diagnosen";
	private static final String ACTIONS = "Aktionen";
	private static final String NEW_MEETING_WITH_PATIENT = "Neues Meeting mit diesem Patient";
	private static final String PATIENT_HISTORY = "Patientenhistory";
	private static final String TELEPHONE = "Festnetz";
	private static final String MOBILE = "Mobile";
	private static final String ZIP_PLACE = "PLZ / Ort";
	private static final String ADDRESS = "Adresse";
	private static final String NAME = "Name";
	private static final String ALLGEMEINE_GRUNDDATEN = "Allgemeine Grunddaten";
	private static final String COULD_NOT_READ_PERSON_ID = "Ungültige Id angegeben!";
	private static final String ID_NOT_A_NUMBER = "Übergebener Parameter ist keine Zahl";
	private static final String ID_NOT_A_PATIENT = "Die gesuchte Person ist kein Patient";
	private static final String PERSON_NOT_FOUND = "Die gewünschte Person konnte nicht gefunden werden";
	private static final String NO_PERSON_IN_SESSION = "Keine Person ausgewählt";
	private static final String DANGER_STATE_ERROR = "Der Patient hat keinen Gefährdungsstatus gesetzt!";

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

		// if person is a not a patient
		if (sessionPersonDTO != null) {
			layout.addComponent(new PersonTile(sessionPersonDTO,
					sessionPersonDTO.getDtype()));
		}
		// patientDTO is in session
		else if (patientDTO != null) {

			createBaseDataTile(patientDTO);

			GridTile medicalDataTile = new GridTile(patientDTO);
			layout.addComponent(medicalDataTile);

			createDiagnosisContainer(patientDTO);

			createActionTile(patientDTO);

			try {
				createHistoryTile(patientDTO);
			} catch (DangerStateException e) {
				e.printStackTrace();
			}
		} else {
			getUI().getNavigator().navigateTo(
					NavigationIndex.PERSONSEARCHVIEW.getNavigationPath());
			Notification.show(NO_PERSON_IN_SESSION, Type.HUMANIZED_MESSAGE);
		}
	}

	private void createBaseDataTile(PatientDTO patientDTO) {
		Tile baseDataTile = new Tile(ALLGEMEINE_GRUNDDATEN + " "
				+ patientDTO.getName());
		baseDataTile.setStdWidth(3);
		GridLayout grid = new GridLayout(2, 4);
		grid.setSizeFull();
		grid.addComponent(new Label(NAME + ": " + patientDTO.getName()));
		grid.addComponent(new Label(ADDRESS
				+ ": "
				+ (patientDTO.getAddress() != null ? patientDTO.getAddress()
						: "---")));
		grid.addComponent(new Label(
				ZIP_PLACE
						+ ": "
						+ (patientDTO.getZip() != null ? patientDTO.getZip()
								+ " " : "")
						+ (patientDTO.getCity() != null ? patientDTO.getCity()
								: "---")));
		grid.addComponent(new Label(MOBILE
				+ ": "
				+ (patientDTO.getMobile() != null ? patientDTO.getMobile()
						: "---")));
		grid.addComponent(new Label(TELEPHONE
				+ ": "
				+ (patientDTO.getLandline() != null ? patientDTO.getLandline()
						: "---")));

		baseDataTile.addComponent(grid);
		layout.addComponent(baseDataTile);
	}

	private void createHistoryTile(PatientDTO patientDTO) throws DangerStateException {
		Tile historyTile = new Tile(PATIENT_HISTORY);
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
	}

	private void createActionTile(PatientDTO patientDTO) {
		Button createMeetingButton = new Button(NEW_MEETING_WITH_PATIENT);
		createMeetingButton.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				getUI().getNavigator().navigateTo(
						NavigationIndex.MEETINGVIEW.getNavigationPath()
								+ "/new=" + patientDTO.getId());
			}
		});
		Tile actionsTile = new Tile(ACTIONS);
		actionsTile.addComponent(createMeetingButton);
		layout.createRowBrake();
		layout.addComponent(actionsTile);
	}

	private void createDiagnosisContainer(PatientDTO patientInSession) {
		try {
			Tile diagnosisContainer = new Tile(DIAGNOSIS);
			for (DiagnosisDTO diagnosisDTO : personPresenter
					.getDiagnosisForPatient(patientInSession.getId())) {
				diagnosisContainer.addComponent(new DiagnosisTile(diagnosisDTO,
						diagnosisDTO.getIllness().toString()));
			}
			layout.addComponent(diagnosisContainer);
		} catch (DangerStateException e1) {
			Notification.show(DANGER_STATE_ERROR, Type.ERROR_MESSAGE);
			e1.printStackTrace();
		}
	}

}
