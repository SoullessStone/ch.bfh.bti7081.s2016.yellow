package ch.bfh.bti7081.s2016.yellow.SwissMD.view.components;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PrescriptionDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.Tile;

import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

/**
 * Stellt eine Liste von PrescriptionDTOs dar
 * 
 * @author SoullessStone
 *
 */
@SuppressWarnings("serial")
public class MultiplePrescriptionTile extends Tile {
	private List<PrescriptionDTO> prescriptions;
	private VerticalLayout layout;
	private final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

	public MultiplePrescriptionTile(List<PrescriptionDTO> prescriptions) {
		this.prescriptions = prescriptions;
		layout = new VerticalLayout();
		addComponent(layout);
		updateTileValue();
		System.out.println("MultiplePrescriptionTile created");
	}

	private void updateTileValue() {
		layout.removeAllComponents();
		if (prescriptions == null || prescriptions.isEmpty()) {
			layout.addComponent(new Label(
					"Keine Verschreibungen für diesen Patienten gefunden!"));
			return;
		}
		prescriptions.sort(new Comparator<PrescriptionDTO>() {

			@Override
			public int compare(PrescriptionDTO o1, PrescriptionDTO o2) {
				return o2.getValidity().getTo()
						.compareTo(o1.getValidity().getTo());
			}
		});

		Table table = new Table();
		table.addContainerProperty("Medikament", String.class, null);
		table.addContainerProperty("Dosis (mg)", String.class, null);
		table.addContainerProperty("Gültig von", String.class, null);
		table.addContainerProperty("Gültig bis", String.class, null);

		for (PrescriptionDTO prescription : prescriptions) {

			String drug = prescription.getDrug().getTradeName();
			String dosis = prescription.getDosisInMilligrams() + " mg";
			String validFrom = format.format(prescription.getValidity()
					.getFrom());
			String validTo = format.format(prescription.getValidity().getTo());
			table.addItem(new Object[] { drug, dosis, validFrom, validTo },
					prescriptions.indexOf(prescription) + 1);
		}

		table.setPageLength(table.size());
		this.layout.addComponent(table);
	}

	public void addPrescription(PrescriptionDTO prescription) {
		this.prescriptions.add(prescription);
		updateTileValue();
	}

	public void removePrescription(PrescriptionDTO prescription) {
		this.prescriptions.remove(prescription);
		updateTileValue();
	}

	public void setPrescriptions(List<PrescriptionDTO> prescriptions) {
		this.prescriptions = prescriptions;
		updateTileValue();
	}
}
