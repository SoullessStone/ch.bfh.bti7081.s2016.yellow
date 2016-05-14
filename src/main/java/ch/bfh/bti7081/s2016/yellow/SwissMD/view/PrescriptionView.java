package ch.bfh.bti7081.s2016.yellow.SwissMD.view;

import ch.bfh.bti7081.s2016.yellow.SwissMD.presenter.PrescriptionPresenter;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.components.NavigationsMenu;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

// Can change Perscriptions
// Can create Perscriptions
// if just clicked (not navigated to from Patient) this view could show all the Perscriptions of the current doctor)
@SuppressWarnings("serial")
public class PrescriptionView extends VerticalLayout implements View {
	private PrescriptionPresenter prescriptionPresenter = new PrescriptionPresenter(
			this);

	public PrescriptionView() {
		setSizeFull();
		setSpacing(true);
		addComponent(new NavigationsMenu());
		addComponent(headingLabel());
	}

	@Override
	public void enter(ViewChangeEvent event) {
	}

	private Label headingLabel() {
		return new Label("PrescriptionView");
	}

}
