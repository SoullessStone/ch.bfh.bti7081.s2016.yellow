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
	private static final String MG = "mg";
	private static final String ICONS_EYEDROPPER = "img/icons/eyedropper_small.png";
	private static final String VALID_TO = "Gültig bis: ";
	private static final String VALID_FROM = "Gültig von: ";
	private static final String DOSIS = "Dosis (mg): ";
	
	private PrescriptionDTO prescription;
	private final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

	public PrescriptionTile(PrescriptionDTO PrescriptionDTO) {
		this.prescription = PrescriptionDTO;
		setTitleAndIcon(prescription.getDrug().getTradeName(),
				ICONS_EYEDROPPER);

		addComponent(new Label(DOSIS + prescription.getDosisInMilligrams()
				+ MG));

		addComponent(new Label(VALID_FROM
				+ format.format(prescription.getValidity().getFrom())));
		addComponent(new Label(VALID_TO
				+ format.format(prescription.getValidity().getTo())));

		contentLayout.setMargin(true);

	}

}
