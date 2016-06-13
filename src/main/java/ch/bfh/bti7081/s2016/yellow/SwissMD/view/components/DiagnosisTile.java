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
	public DiagnosisTile(DiagnosisDTO diagnosis, String title) {
		setTitleAndIcon(title, "img/icons/users_small.png");
		addComponent(new Label("Krankheit: "
				+ diagnosis.getIllness().toString()));
		addComponent(new Label("Diagnostiziert am: " + diagnosis.getDiagnosisDateString()));
		addComponent(new Label("Notizen: " + diagnosis.getNotes()));
	}
	
	public void disableShadow(){
		tile.addStyleName("noshadow");
	}
}
