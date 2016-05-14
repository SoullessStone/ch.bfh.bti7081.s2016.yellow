package ch.bfh.bti7081.s2016.yellow.SwissMD.view.components;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.Person;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.Prescription;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class PrescriptionTile extends CustomComponent {
	private Prescription prescription;

	public PrescriptionTile(Prescription prescription) {
		this.prescription = prescription;

		Panel tile = new Panel();

		VerticalLayout contentLayout = new VerticalLayout();
		contentLayout.addComponent(new Label("Medikament: "
				+ prescription.getMedication()));
		contentLayout.setMargin(true);

		tile.setContent(contentLayout);
		tile.setWidth(200, Unit.PIXELS);
		setCompositionRoot(tile);
	}

}
