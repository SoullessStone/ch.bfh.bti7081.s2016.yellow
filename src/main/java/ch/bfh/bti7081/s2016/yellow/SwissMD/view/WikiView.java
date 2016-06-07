package ch.bfh.bti7081.s2016.yellow.SwissMD.view;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.IllnessDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PersonDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.presenter.WikiPresenter;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.components.CreateDiagnosisTile;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.components.LoginTile;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.components.MultipleIllnessTile;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.components.MultiplePersonTile;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.BaseLayout;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.LayoutFactory;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.LayoutFactory.LayoutType;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.Tile;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.TileLayoutFactory;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.navigation.NavigationIndex;

import java.util.List;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.BrowserFrame;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Window;

// Just do
@SuppressWarnings("serial")
public class WikiView extends CustomComponent implements View {
	private static final String FOUND_COUNT_RESULTS = "Suchergebnisse wurden gefunden";
	private WikiPresenter wikiPresenter = new WikiPresenter(this);
	private BaseLayout layout;
	private TextField searchField;
	private MultipleIllnessTile resultTile;

	public WikiView() {
		try {
			layout = LayoutFactory.getInstance(LayoutType.TILE_LAYOUT)
					.createLayout(TileLayoutFactory.Arguments.ELEMENTS_PER_ROW.getName() + ":3");
		} catch (Exception e1) {
			// TODO Go to error View
			e1.printStackTrace();
		}
		setCompositionRoot(layout);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		Tile tile = new Tile();
		searchField = new TextField("Suchbegriff");
		Button searchButton = new Button("Suchen");
		searchButton.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if (!searchField.isEmpty()) {
					List<IllnessDTO> results = wikiPresenter.searchIllnesses(searchField.getValue());
					if(results != null && !results.isEmpty()) {
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
		layout.finishLayout();
	}

	private void showSearchResults(List<IllnessDTO> results) {
		Notification.show(results.size() + FOUND_COUNT_RESULTS, Type.ASSISTIVE_NOTIFICATION);
		this.resultTile.setIllnesses(results);
	}

	private Label headingLabel() {
		return new Label("WikiSearchView");
	}

}
