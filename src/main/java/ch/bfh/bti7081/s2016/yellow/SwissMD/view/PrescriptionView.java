package ch.bfh.bti7081.s2016.yellow.SwissMD.view;

import ch.bfh.bti7081.s2016.yellow.SwissMD.presenter.PrescriptionPresenter;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.BaseLayout;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.LayoutFactory;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.LayoutFactory.LayoutType;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.TileLayoutFactory;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;

// Can change Perscriptions
// Can create Perscriptions
// if just clicked (not navigated to from Patient) this view could show all the Perscriptions of the current doctor)
@SuppressWarnings("serial")
public class PrescriptionView extends CustomComponent implements View {
	private PrescriptionPresenter prescriptionPresenter = new PrescriptionPresenter(
			this);

	private BaseLayout layout;

	public PrescriptionView() {
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
		// Wird jedes Mal aufgerufen, wenn hierhin navigiert wird. Hier könnte
		// man also den Parameter in der URL auslesen
		System.out.println(event.getParameters());
	}

	private Label headingLabel() {
		return new Label("PrescriptionView");
	}

}
