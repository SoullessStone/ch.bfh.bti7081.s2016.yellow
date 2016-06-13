package ch.bfh.bti7081.s2016.yellow.SwissMD.view.components;

import java.util.ArrayList;
import java.util.List;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.DrugDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PatientDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PrescriptionDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.IllegalDateRangeException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.util.DateRange;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.Tile;

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
 * Tile for creating a new prescription. Will be shown in the meeting view
 * 
 * @author D. Halter
 * 
 */

@SuppressWarnings("serial")
public class CreatePrescriptionTile extends Tile {
	private static final String PRESCRIBE = "Verordnen";
	private static final String VALID_TO = "Gültig bis";
	private static final String VALID_FROM = "Gültig von";
	private static final String DOSIS = "Dosis (mg)";
	private static final String DRUGS = "Medikamente";
	private static final String ICONS_EYEDROPPER = "img/icons/eyedropper_small.png";
	private static final String PRESSCRIBE_NEW_DRUG = "Neues Medikament verordnen";
	private static final String ILLEGAL_DATE_RANGE = "Gültig von Datum ist nach gültig bis Datum.";
	private static final String EMPTY_DOSIS = "Konnte die Verschreibung nicht verordnen, da die Dosis leer ist.";
	private static final String DOSIS_MUST_BE_INT = "Konnte die Verschreibung nicht verordnen, da die Dosis keine Zahl ist.";
	private static final String EMPTY_DRUG = "Konnte die Verschreibung nicht verordnen, da kein Medikament gewählt wurde.";

	List<CreationPrescriptiontileObserver> observer = new ArrayList<>();

	public CreatePrescriptionTile(List<DrugDTO> list, PatientDTO patient,
			boolean replaceTileAfterCompletion) {
		setTitleAndIcon(PRESSCRIBE_NEW_DRUG,
				ICONS_EYEDROPPER);
		// create a Combobox with all available drugs
		ComboBox selectDrug = new ComboBox(DRUGS);
		for (DrugDTO drug : list) {
			selectDrug.addItem(drug);
		}
		addComponent(selectDrug);
		// text field for the dosage
		TextField dosis = new TextField(DOSIS);
		addComponent(dosis);

		// DatePicker for the valid from - to field
		DateField validFrom = new DateField(VALID_FROM);
		validFrom.setWidth(200, Unit.PIXELS);
		validFrom.setDateFormat("dd.MM.yyyy");
		validFrom.setResolution(Resolution.DAY);
		addComponent(validFrom);

		DateField validUntil = new DateField(VALID_TO);
		validUntil.setWidth(200, Unit.PIXELS);
		validUntil.setDateFormat("dd.MM.yyyy");
		validUntil.setResolution(Resolution.DAY);
		addComponent(validUntil);

		// button to save the new prescription
		Button saveNewDrug = new Button(PRESCRIBE);
		saveNewDrug.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				// validation
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
				// create the new, necessary prescriptionDTO

				PrescriptionDTO prescriptionDTO;

				try {
					prescriptionDTO = new PrescriptionDTO(selectedDrug,
							selectedDosis, new DateRange(validFrom.getValue(),
									validUntil.getValue()), patient);
					// inform all the observers that a new prescription was created
					for (CreationPrescriptiontileObserver observer : observer) {
						observer.perscriptionCreated(prescriptionDTO);
					}
					if (replaceTileAfterCompletion) {
						// replace the CreatePrescriptionTile with a PrescriptionTile
						Layout parent = (Layout) CreatePrescriptionTile.this
								.getParent();
						parent.replaceComponent(CreatePrescriptionTile.this,
								new PrescriptionTile(prescriptionDTO));
					}

				} catch (IllegalDateRangeException e) {
					Notification.show(ILLEGAL_DATE_RANGE,
							Type.HUMANIZED_MESSAGE);
				}

			}
		});
		addComponent(saveNewDrug);
		contentLayout.setMargin(true);
	}

	public void addObserver(CreationPrescriptiontileObserver observer) {
		this.observer.add(observer);
	}

}
