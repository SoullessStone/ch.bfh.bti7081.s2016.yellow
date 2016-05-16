package ch.bfh.bti7081.s2016.yellow.SwissMD.view;

import ch.bfh.bti7081.s2016.yellow.SwissMD.presenter.WikiPresenter;
import ch.bfh.bti7081.s2016.yellow.SwissMD.presenter.WikiPresenter.ArtikelId;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.navigation.NavigationsMenu;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

// Just do
@SuppressWarnings("serial")
public class WikiView extends VerticalLayout implements View {
	private WikiPresenter wikiPresenter = new WikiPresenter(this);
	private Label wikiText;

	public WikiView() {
		setSizeFull();
		setSpacing(true);
		//addComponent(new NavigationsMenu());
		addComponent(headingLabel());
		wikiText = new Label(
				wikiPresenter.getWikiText(ArtikelId.ANGSTZUSTAENDE),
				ContentMode.HTML);
		addComponent(wikiText);
	}

	public Label getWikiText() {
		return wikiText;
	}

	public void setWikiText(Label wikiText) {
		this.wikiText = wikiText;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// Wird jedes Mal aufgerufen, wenn hierhin navigiert wird. Hier könnte
		// man also den Parameter in der URL auslesen
		System.out.println(event.getParameters());
	}

	private Label headingLabel() {
		return new Label("WikiView");
	}

}
