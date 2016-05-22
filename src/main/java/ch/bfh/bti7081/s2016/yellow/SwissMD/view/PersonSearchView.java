package ch.bfh.bti7081.s2016.yellow.SwissMD.view;

import ch.bfh.bti7081.s2016.yellow.SwissMD.presenter.PersonSearchPresenter;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.BaseLayout;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.LayoutFactory;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.TileLayoutFactory;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.LayoutFactory.LayoutType;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

// Just do
@SuppressWarnings("serial")
public class PersonSearchView extends CustomComponent implements View {
	private PersonSearchPresenter personSearchPresenter = new PersonSearchPresenter(
			this);
	
	private BaseLayout layout;


	public PersonSearchView() {
		try {
			layout = LayoutFactory.getInstance(LayoutType.TILE_LAYOUT).createLayout(TileLayoutFactory.Arguments.ELEMENTS_PER_ROW.getName()+":3");
		} catch (Exception e1) {
			// TODO Go to error View
			e1.printStackTrace();
		}
		setCompositionRoot(layout);
	}

	@Override
	public void enter(ViewChangeEvent event) {

		// Wird jedes Mal aufgerufen, wenn hierhin navigiert wird. Hier könnte
		// man also den Parameter in der URL auslesen
		System.out.println(event.getParameters());
	}

	private Label headingLabel() {
		return new Label("PersonSearchView");
	}

}
