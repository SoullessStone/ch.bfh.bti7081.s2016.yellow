package ch.bfh.bti7081.s2016.yellow.SwissMD.view.components;

import java.text.DateFormat;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PrescriptionDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.Tile;

import com.vaadin.ui.Label;

@SuppressWarnings("serial")
public class PrescriptionTile extends Tile {
	private PrescriptionDTO prescription;

	public PrescriptionTile(PrescriptionDTO PrescriptionDTO) {
		this.prescription = PrescriptionDTO;
		setTitleAndIcon(prescription.getDrug().getTradeName(), "img/icons/eyedropper_small.png");

		addComponent(new Label("Dosis: " + prescription.getDosisInMilligrams()
				+ "mg"));
		addComponent(new Label("Gültig von: " + prescription.getValidity().getFrom()));
		addComponent(new Label("Gültig bis: " + prescription.getValidity().getTo()));

		contentLayout.setMargin(true);

	}

}
