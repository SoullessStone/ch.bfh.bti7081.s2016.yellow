package ch.bfh.bti7081.s2016.yellow.SwissMD.view.components;

import java.text.SimpleDateFormat;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PrescriptionDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.Tile;

import com.vaadin.ui.Label;

/**
 * Stellt eine Verschreibung dar
 * 
 * @author SoullessStone
 *
 */
@SuppressWarnings("serial")
public class PrescriptionTile extends Tile {
	private PrescriptionDTO prescription;
	private final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

	public PrescriptionTile(PrescriptionDTO PrescriptionDTO) {
		this.prescription = PrescriptionDTO;
		setTitleAndIcon(prescription.getDrug().getTradeName(),
				"img/icons/eyedropper_small.png");

		addComponent(new Label("Dosis (mg): " + prescription.getDosisInMilligrams()
				+ "mg"));

		addComponent(new Label("Gültig von: "
				+ format.format(prescription.getValidity().getFrom())));
		addComponent(new Label("Gültig bis: "
				+ format.format(prescription.getValidity().getTo())));

		contentLayout.setMargin(true);

	}

}
