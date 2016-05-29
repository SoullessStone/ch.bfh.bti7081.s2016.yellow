package ch.bfh.bti7081.s2016.yellow.SwissMD.view.components;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PrescriptionDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.Tile;

import com.vaadin.ui.Label;

public class PrescriptionTile extends Tile {
	private PrescriptionDTO prescription;

	public PrescriptionTile(PrescriptionDTO PrescriptionDTO) {
		this.prescription = PrescriptionDTO;
		setTitleAndIcon(prescription.getMedication().getTradeName(), "img/icons/eyedropper_small.png");

		addComponent(new Label("Dosis: " + prescription.getDosisInMilligrams()
				+ "mg"));
		// addComponent(new Label("Gültigkeit: "
		// + prescription.getValidity()));

		// if (new Date().after(prescription.getValidity().getTo())) {
		// addComponent(new Label("Abgelaufen"));
		// }
		contentLayout.setMargin(true);

	}

}
