package ch.bfh.bti7081.s2016.yellow.SwissMD.view;

import java.util.List;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.DiagnosisDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.IllnessDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PatientDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.DangerStateException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.presenter.PersonPresenter;
import ch.bfh.bti7081.s2016.yellow.SwissMD.presenter.WikiPresenter;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.components.DiagnosisTile;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.components.ErrorWindow;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.components.MultipleIllnessTile;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.BaseLayout;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.LayoutFactory;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.LayoutFactory.LayoutType;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.Tile;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.TileLayoutFactory;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.navigation.NavigationIndex;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;

/**
 * Hier werden Krankheiten angezeigt und verschrieben
 * 
 * @author Zumstein, Suter
 *
 */
@SuppressWarnings("serial")
public class WikiView extends CustomComponent implements View {
	private static final String SEARCH = "Suchen";
	private static final String SEARCH_ILLNESS = "Krankheit suchen";
	private static final String DIAGNOSIS = "Diagnosen";
	private static final String NO_PATIENT_IN_SESSION = "Kein Patient ausgewählt";
	private static final String DANGER_STATE_ERROR = "Der Patient hat keinen Gefährdungsstatus gesetzt!";
	private static final String FOUND_COUNT_RESULTS = " Suchergebnisse wurden gefunden";
	private static final String FOUND_TOO_MANY_RESULTS = "Zu viele Treffer. Nur 100 werden angezeigt";
	private WikiPresenter wikiPresenter = new WikiPresenter(this);
	private BaseLayout layout;
	private TextField searchField;
	private MultipleIllnessTile resultTile;

	private PersonPresenter personPresenter = new PersonPresenter();

	public WikiView() {
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
		if (patientDTO == null) {
			getUI().getNavigator().navigateTo(
					NavigationIndex.PERSONSEARCHVIEW.getNavigationPath());
			Notification.show(NO_PATIENT_IN_SESSION, Type.HUMANIZED_MESSAGE);
		}
		Tile tile = new Tile();
		searchField = new TextField(SEARCH_ILLNESS);
		Button searchButton = new Button(SEARCH);
		searchButton.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if (!searchField.isEmpty()) {
					List<IllnessDTO> results = wikiPresenter
							.searchIllnesses(searchField.getValue());
					if (results != null && !results.isEmpty()) {
						showSearchResults(results);
					}
				}
			}
		});
		searchButton.setClickShortcut(KeyCode.ENTER);
		tile.addComponent(searchField);
		tile.addComponent(searchButton);
		layout.addComponent(tile);
		this.resultTile = new MultipleIllnessTile(null);
		layout.addComponent(resultTile);
		if (patientDTO != null) {
			createDiagnosisContainer(patientDTO);
		}
		layout.finishLayout();
	}

	private void showSearchResults(List<IllnessDTO> results) {
		if (results.size() <= 100) {
			Notification.show(results.size() + FOUND_COUNT_RESULTS,
					Type.HUMANIZED_MESSAGE);
		}
		Notification.show(FOUND_TOO_MANY_RESULTS, Type.HUMANIZED_MESSAGE);
		this.resultTile.setIllnesses(results);
	}

	private void createDiagnosisContainer(PatientDTO patientInSession) {
		try {

			Tile diagnosisContainer = new Tile(DIAGNOSIS);

			List<DiagnosisDTO> diagnosisForPatient = personPresenter
					.getDiagnosisForPatient(patientInSession.getId());

			for (DiagnosisDTO diagnosisDTO : diagnosisForPatient) {
				DiagnosisTile diagnosisTile = new DiagnosisTile(diagnosisDTO,
						diagnosisDTO.getIllness().toString());
				diagnosisTile.disableShadow();
				diagnosisContainer.addComponent(diagnosisTile);
			}

			if(!diagnosisForPatient.isEmpty()) {
				layout.createRowBrake();
				layout.addComponent(diagnosisContainer);
			}

		} catch (DangerStateException e1) {
			Notification.show(DANGER_STATE_ERROR, Type.ERROR_MESSAGE);
			e1.printStackTrace();
		}
	}

}
