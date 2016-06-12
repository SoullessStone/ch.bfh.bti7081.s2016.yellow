package ch.bfh.bti7081.s2016.yellow.SwissMD.view.components;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.DiagnosisDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PersonDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.Tile;

import com.vaadin.ui.Label;

@SuppressWarnings("serial")
public class DiagnosisTile extends Tile {
	private DiagnosisDTO diagnosis;

	public DiagnosisTile(DiagnosisDTO diagnosis, String title) {
		this.diagnosis = diagnosis;
		setTitleAndIcon(title, "img/icons/users_small.png");
		addComponent(new Label("Krankheit: " + diagnosis.getIllness().toString()));
		addComponent(new Label("Diagnostiziert am: "
				+ diagnosis.getDate()));
		addComponent(new Label("Notizen: "
				+ diagnosis.getNotes()));
		System.out.println("DiagnosisTile created");
	}
}
