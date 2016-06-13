package ch.bfh.bti7081.s2016.yellow.SwissMD.view.components;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.DiagnosisDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.Tile;

import com.vaadin.ui.Label;

/**
 * Eine Tile, welche eine Diagnose abbildet
 * 
 * @author SoullessStone
 *
 */
@SuppressWarnings("serial")
public class DiagnosisTile extends Tile {
	private static final String NOSHADOW_STYLE = "noshadow";
	private static final String NOTES = "Notizen: ";
	private static final String DIAGNOSIS_DATE = "Diagnostiziert am: ";
	private static final String ILLNESS = "Krankheit: ";
	private static final String ICONS_USERS = "img/icons/users_small.png";

	public DiagnosisTile(DiagnosisDTO diagnosis, String title) {
		setTitleAndIcon(title, ICONS_USERS);
		addComponent(new Label(ILLNESS
				+ diagnosis.getIllness().toString()));
		addComponent(new Label(DIAGNOSIS_DATE + diagnosis.getDiagnosisDateString()));
		addComponent(new Label(NOTES + diagnosis.getNotes()));
	}
	
	public void disableShadow(){
		tile.addStyleName(NOSHADOW_STYLE);
	}
}
