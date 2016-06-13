package ch.bfh.bti7081.s2016.yellow.SwissMD.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PatientDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PersonDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.presenter.PersonPresenter;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.components.ErrorWindow;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.components.GridTile;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.components.PersonTile;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.BaseLayout;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.LayoutFactory;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.LayoutFactory.LayoutType;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.Tile;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.TileLayoutFactory;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.navigation.NavigationIndex;

/**
 * Hier werden Personen dargestellt
 * 
 * @author Schaad
 *
 */
@SuppressWarnings("serial")
public class PersonView extends CustomComponent implements View {
	private static final String TELEPHONE = "Festnetz";
	private static final String MOBILE = "Mobile";
	private static final String ZIP_PLACE = "PLZ / Ort";
	private static final String ADDRESS = "Adresse";
	private static final String NAME = "Name";
	private static final String ALLGEMEINE_GRUNDDATEN = "Allgemeine Grunddaten";
	private static final String NO_PERSON_IN_SESSION = "Keine Person ausgewählt";

	private PersonPresenter personPresenter = new PersonPresenter();
	private BaseLayout layout;

	public PersonView() {
		try {
			layout = LayoutFactory.getInstance(LayoutType.TILE_LAYOUT)
					.createLayout(
							TileLayoutFactory.Arguments.ELEMENTS_PER_ROW
									.getName() + ":3");
		} catch (Exception e1) {
			Window window = new ErrorWindow(e1);
			UI.getCurrent().addWindow(window);
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

}
