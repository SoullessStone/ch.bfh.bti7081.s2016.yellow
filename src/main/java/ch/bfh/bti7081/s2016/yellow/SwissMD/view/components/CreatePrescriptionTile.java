package ch.bfh.bti7081.s2016.yellow.SwissMD.view.components;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.DrugDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PrescriptionDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Drug;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.util.DateRange;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.Tile;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;

/**
 * Tile for creating a new prescription
 * 
 * @author D. Halter
 * 
 */

public class CreatePrescriptionTile extends Tile {
	private static final String EMPTY_DOSIS = "Konnte die Verschreibung nicht verordnen, da die Dosis leer ist.";
	private static final String DOSIS_MUST_BE_INT = "Konnte die Verschreibung nicht verordnen, da die Dosis keine Zahl ist.";
	private static final String EMPTY_DRUG = "Konnte die Verschreibung nicht verordnen, da kein Medikament gewählt wurde.";

	List<CreationPrescriptiontileObserver> observer = new ArrayList<>();

	public CreatePrescriptionTile(List<DrugDTO> list) {
		setTitle("Neues Medikament verordnen");
		// Combobox mit allen möglichen Medikamenten
		ComboBox selectDrug = new ComboBox("Medikamente");
		for (DrugDTO drug : list) {
			selectDrug.addItem(drug);
		}
		addComponent(selectDrug);
		// Textfeld für die Dosis
		TextField dosis = new TextField("Dosis");
		addComponent(dosis);
		// Button, welcher die Verordnung handlet
		Button saveNewDrug = new Button("Verordnen");
		saveNewDrug.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				// Validierung
				if (dosis.getValue() == null || dosis.getValue().isEmpty()) {
					Notification.show(EMPTY_DOSIS, Type.HUMANIZED_MESSAGE);
					return;
				}
				if (selectDrug.getValue() == null) {
					Notification.show(EMPTY_DRUG, Type.HUMANIZED_MESSAGE);
					return;
				}
				DrugDTO selectedDrug = (DrugDTO) selectDrug.getValue();
				Integer selectedDosis = 0;
				try {
					selectedDosis = Integer.valueOf(dosis.getValue());
				} catch (NumberFormatException e) {
					dosis.setValue("0");
					Notification
							.show(DOSIS_MUST_BE_INT, Type.HUMANIZED_MESSAGE);
					return;
				}
				// Zusammenstellen der gewünschten PrescriptionDTO
				// TODO Exceptions müssen weiter geworfen werden.
				PrescriptionDTO prescriptionDTO = new PrescriptionDTO(
						selectedDrug, selectedDosis, new DateRange(new Date(1L), new Date(10000L)));
				// Allen Observer Bescheid geben, dass eine Prescription
				// erstellt wurde
				for (CreationPrescriptiontileObserver observer : observer) {
					observer.perscriptionCreated(prescriptionDTO);
				}
				// Die CreatePrescriptionTile gleich durch eine PrescriptionTile
				// ersetzen.
				Layout parent = (Layout) CreatePrescriptionTile.this
						.getParent();
				parent.replaceComponent(CreatePrescriptionTile.this,
						new PrescriptionTile(prescriptionDTO));
			}
		});
		addComponent(saveNewDrug);
		contentLayout.setMargin(true);
		System.out.println("drug tile created");
	}

	public void addObserver(CreationPrescriptiontileObserver observer) {
		this.observer.add(observer);
	}

}
