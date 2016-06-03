package ch.bfh.bti7081.s2016.yellow.SwissMD.view;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.IllnessDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.presenter.WikiPresenter;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.components.CreateDiagnosisTile;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.components.LoginTile;
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
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;

// Just do
@SuppressWarnings("serial")
public class WikiView extends CustomComponent implements View {
	private WikiPresenter wikiPresenter = new WikiPresenter(this);
	private IllnessDTO illnessToShow;
	private BaseLayout layout;

	public WikiView() {
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
		String param = event.getParameters();
		// param nicht leer
		if (param != null && !param.isEmpty()) {
			// Try to get an illnessId of param
			Long illnessId = null;
			try {
				illnessId = Long.valueOf(param);
			} catch (NumberFormatException e) {
				getUI().getNavigator().navigateTo(
						NavigationIndex.PERSONSEARCHVIEW.getNavigationPath());
			}

			if (illnessId != null) {
				// Lese IllnessDTO
				illnessToShow = wikiPresenter.findIllnessById(illnessId);

				if (illnessToShow != null) {
					Tile wikiTile = new Tile("WikiView", "img/icons/books_small.png");
					Button createDiagnose = new Button(
							"Für aktuellen Patienten diagnostizieren");
					createDiagnose.addClickListener(new ClickListener() {

						@SuppressWarnings("static-access")
						@Override
						public void buttonClick(ClickEvent event) {
							final Window window = new Window("Diagnose erstellen");
					        window.setWidth(300.0f, Unit.PIXELS);
					        window.center();
					        window.setModal(true);
					        window.setResizable(false);
					        window.setContent(new CreateDiagnosisTile(illnessToShow, window));
					        getUI().getCurrent().addWindow(window);
						}
					});
					wikiTile.addComponent(createDiagnose);
					layout.addComponent(wikiTile);
					// Zeige die Illness an
					showIllnessInView();
				} else {
				}
			} else {
				getUI().getNavigator().navigateTo(
						NavigationIndex.PERSONSEARCHVIEW.getNavigationPath());
			}
		}
	}

	private void showIllnessInView() {
		// TODO Michel Auto-generated method stub
		
	}

	private Label headingLabel() {
		return new Label("WikiView");
	}

}
