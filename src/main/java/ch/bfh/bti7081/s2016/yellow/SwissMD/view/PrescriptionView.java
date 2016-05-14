package ch.bfh.bti7081.s2016.yellow.SwissMD.view;


import java.util.Date;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.Person;
import ch.bfh.bti7081.s2016.yellow.SwissMD.presenter.PrescriptionPresenter;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.components.NavigationsMenu;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.components.PersonenTile;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.components.PrescriptionTile;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

// Can change Perscriptions
// Can create Perscriptions
// if just clicked (not navigated to from Patient) this view could show all the Perscriptions of the current doctor)
@SuppressWarnings("serial")
public class PrescriptionView extends VerticalLayout implements View {
	private PrescriptionPresenter prescriptionPresenter = new PrescriptionPresenter(this);
	
	public PrescriptionView() {
		setSizeFull();
		setSpacing(true);
		addComponent(new NavigationsMenu());
		addComponent(headingLabel());
		// addComponent(new PrescriptionTile(new Person("Paul Medici", new Date(768907564000L))));
	}

	@Override
	public void enter(ViewChangeEvent event) {
	}
	
	private Label headingLabel() {
		return new Label("PrescriptionView");
	}
	

}
