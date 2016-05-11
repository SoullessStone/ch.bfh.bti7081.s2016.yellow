package ch.bfh.bti7081.s2016.yellow.SwissMD.view;


import ch.bfh.bti7081.s2016.yellow.SwissMD.presenter.WikiPresenter;
import ch.bfh.bti7081.s2016.yellow.SwissMD.presenter.WikiPresenter.ArtikelId;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.components.Menu;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

// Just do
@SuppressWarnings("serial")
public class WikiView extends VerticalLayout implements View {
	private WikiPresenter wikiPresenter = new WikiPresenter(this);
	private Label wikiText;
	private ArtikelId artikelToShow;

	
	public WikiView(ArtikelId artikelToShow) {
		this.artikelToShow = artikelToShow;
		setSizeFull();
		setSpacing(true);
		addComponent(new Menu());
		addComponent(headingLabel());
		wikiText = new Label(wikiPresenter.getWikiText(artikelToShow),
			    ContentMode.HTML);
		addComponent(wikiText);
	}

	public Label getWikiText() {
		return wikiText;
	}

	public void setWikiText(Label wikiText) {
		this.wikiText = wikiText;
	}

	public ArtikelId getArtikelToShow() {
		return artikelToShow;
	}

	public void setArtikelToShow(ArtikelId artikelToShow) {
		this.artikelToShow = artikelToShow;
	}

	@Override
	public void enter(ViewChangeEvent event) {
	}
	
	private Label headingLabel() {
		return new Label("WikiView");
	}
	

}
