package ch.bfh.bti7081.s2016.yellow.SwissMD.view.components;

import java.util.Date;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.Prescription;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PrescriptionDTO;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class PrescriptionTile extends CustomComponent {
	private PrescriptionDTO prescription;

	// TODO: Work with DTOs
	public PrescriptionTile(PrescriptionDTO PrescriptionDTO) {
		this.prescription = PrescriptionDTO;

		Panel tile = new Panel(prescription.getMedication());

		VerticalLayout contentLayout = new VerticalLayout();
		contentLayout.addComponent(new Label("Dosis: "
				+ prescription.getDosisInMilligrams() + "mg"));
		contentLayout.addComponent(new Label("Gültigkeit: "
				+ prescription.getValidity()));
		if (new Date().after(prescription.getValidity().getTo())){
			contentLayout.addComponent(new Label("Abgelaufen"));
		}
		contentLayout.setMargin(true);

		
		tile.setContent(contentLayout);
		tile.setWidth(300, Unit.PIXELS);
		setCompositionRoot(tile);
	}

}
