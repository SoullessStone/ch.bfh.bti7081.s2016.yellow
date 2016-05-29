package ch.bfh.bti7081.s2016.yellow.SwissMD.view.components;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.DrugDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PrescriptionDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.IllegalDateRangeException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.util.DateRange;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.Tile;

import com.vaadin.server.Sizeable.Unit;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
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
	private static final String ILLEGAL_DATE_RANGE = "Gültig von Datum ist nach gültig bis Datum.";
	private static final String EMPTY_DOSIS = "Konnte die Verschreibung nicht verordnen, da die Dosis leer ist.";
	private static final String DOSIS_MUST_BE_INT = "Konnte die Verschreibung nicht verordnen, da die Dosis keine Zahl ist.";
	private static final String EMPTY_DRUG = "Konnte die Verschreibung nicht verordnen, da kein Medikament gewählt wurde.";

	List<CreationPrescriptiontileObserver> observer = new ArrayList<>();

	public CreatePrescriptionTile(List<DrugDTO> list) {
		setTitleAndIcon("Neues Medikament verordnen",
				"img/icons/eyedropper_small.png");
		// Combobox mit allen möglichen Medikamenten
		ComboBox selectDrug = new ComboBox("Medikamente");
		for (DrugDTO drug : list) {
			selectDrug.addItem(drug);
		}
		addComponent(selectDrug);
		// Textfeld für die Dosis
		TextField dosis = new TextField("Dosis");
		addComponent(dosis);

		// DatePicker für Gültig von und bis
		DateField validFrom = new DateField("Gültig von");
		validFrom.setWidth(200, Unit.PIXELS);
		validFrom.setDateFormat("dd.MM.yyyy");
		validFrom.setResolution(Resolution.DAY);
		addComponent(validFrom);

		DateField validUntil = new DateField("Gültig bis");
		validUntil.setWidth(200, Unit.PIXELS);
		validUntil.setDateFormat("dd.MM.yyyy");
		validUntil.setResolution(Resolution.DAY);
		addComponent(validUntil);

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

				PrescriptionDTO prescriptionDTO;

				try {
					prescriptionDTO = new PrescriptionDTO(selectedDrug,
							selectedDosis, new DateRange(validFrom.getValue(), validUntil.getValue()));
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

				} catch (IllegalDateRangeException e) {
					Notification.show(ILLEGAL_DATE_RANGE,Type.HUMANIZED_MESSAGE);
				}
			
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
