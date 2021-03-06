package ch.bfh.bti7081.s2016.yellow.SwissMD.view;

import java.util.List;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PersonDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.presenter.PersonSearchPresenter;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.components.ErrorWindow;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.components.MultiplePersonTile;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.BaseLayout;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.LayoutFactory;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.LayoutFactory.LayoutType;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.Tile;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.TileLayoutFactory;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

/**
 * Hier werden Personen gesucht
 * 
 * @author SoullessStone
 *
 */
@SuppressWarnings("serial")
public class PersonSearchView extends CustomComponent implements View {
	protected static final String NO_SEARCH_CRITERIA = "Weder ein Name noch ein Geburtsdatum für die Suche ausgewählt";
	private static final String FOUND_COUNT_RESULTS = " Suchergebnisse wurden gefunden.";

	private PersonSearchPresenter personSearchPresenter = new PersonSearchPresenter();

	private TextField nameField = new TextField("Name");
	private DateField birthDateField = new DateField("Geburtsdatum");
	private MultiplePersonTile resultTile;

	private BaseLayout layout;

	public PersonSearchView() {
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

	// Wird jedes Mal aufgerufen, wenn hierhin navigiert wird.
	@Override
	public void enter(ViewChangeEvent event) {

		Tile tile = new Tile();
		Button searchButton = new Button("Suchen");
		searchButton.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				// Validate the input and search according to it
				if (nameField.isEmpty() && birthDateField.isEmpty()) {
					Notification.show(NO_SEARCH_CRITERIA,
							Type.HUMANIZED_MESSAGE);
				} else if (nameField.isEmpty()) {
					showSearchResults(personSearchPresenter
							.searchForBirthdate(birthDateField.getValue()));
				} else if (birthDateField.isEmpty()) {
					showSearchResults(personSearchPresenter
							.searchForLastName(nameField.getValue()));
				} else {
					showSearchResults(personSearchPresenter
							.searchForBirthdateAndLastname(
									birthDateField.getValue(),
									nameField.getValue()));
				}
			}
		});
		searchButton.setClickShortcut(KeyCode.ENTER);
		tile.addComponent(nameField);
		tile.addComponent(birthDateField);
		tile.addComponent(searchButton);
		layout.addComponent(tile);
		this.resultTile = new MultiplePersonTile(null);
		layout.addComponent(resultTile);
		layout.finishLayout();
	}

	private void showSearchResults(List<PersonDTO> results) {
		Notification.show(results.size() + FOUND_COUNT_RESULTS,
				Type.ASSISTIVE_NOTIFICATION);
		this.resultTile.setPersons(results);
	}

}
