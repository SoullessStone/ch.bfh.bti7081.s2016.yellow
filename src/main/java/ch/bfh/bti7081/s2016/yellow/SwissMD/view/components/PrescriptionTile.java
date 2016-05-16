package ch.bfh.bti7081.s2016.yellow.SwissMD.view.components;

import java.util.Date;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PrescriptionDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.Tile;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class PrescriptionTile extends Tile {
	private PrescriptionDTO prescription;

	public PrescriptionTile(PrescriptionDTO PrescriptionDTO) {
		this.prescription = PrescriptionDTO;
		setTitle(prescription.getMedication());
		
		addComponent(new Label("Dosis: "
				+ prescription.getDosisInMilligrams() + "mg"));
		addComponent(new Label("Gültigkeit: "
				+ prescription.getValidity()));
		
		if (new Date().after(prescription.getValidity().getTo())) {
			addComponent(new Label("Abgelaufen"));
		}
		contentLayout.setMargin(true);

	}

}
