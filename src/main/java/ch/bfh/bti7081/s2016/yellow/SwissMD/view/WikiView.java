package ch.bfh.bti7081.s2016.yellow.SwissMD.view;

import ch.bfh.bti7081.s2016.yellow.SwissMD.presenter.WikiPresenter;
import ch.bfh.bti7081.s2016.yellow.SwissMD.presenter.WikiPresenter.ArtikelId;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.BaseLayout;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.LayoutFactory;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.LayoutFactory.LayoutType;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.Tile;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.TileLayoutFactory;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;

// Just do
@SuppressWarnings("serial")
public class WikiView extends CustomComponent implements View {
	private WikiPresenter wikiPresenter = new WikiPresenter(this);
	private Label wikiText;

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

	public Label getWikiText() {
		return wikiText;
	}

	public void setWikiText(Label wikiText) {
		this.wikiText = wikiText;
	}

	@Override
	public void enter(ViewChangeEvent event) {

		Tile wikiTile = new Tile("WikiView");
		wikiText = new Label(
				wikiPresenter.getWikiText(ArtikelId.ANGSTZUSTAENDE),
				ContentMode.HTML);
		wikiTile.addComponent(wikiText);
		layout.addComponent(wikiTile);

		// Wird jedes Mal aufgerufen, wenn hierhin navigiert wird. Hier könnte
		// man also den Parameter in der URL auslesen
		System.out.println(event.getParameters());
	}

	private Label headingLabel() {
		return new Label("WikiView");
	}

}
