package ch.bfh.bti7081.s2016.yellow.SwissMD.view.components;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.DrugDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.Tile;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * Tile for Drug Search
 * 
 * @author D. Halter
 * 
 */

public class DrugTile extends Tile {
	private DrugDTO drug;

	public DrugTile(DrugDTO drug, String title) {
		this.drug = drug;
		setTitle(title);	
		addComponent(new Label("Präparatname: " + drug.getTradeName()));
		addComponent(new Label("Wirkstoff: " + drug.getSubstance()));
		addComponent(new Label("Wirkstoffmenge: " + drug.getSubstanceQuantitiy()));
		addComponent(new Label("Dosierung: " + drug.getDose()));
		System.out.println("drug tile created");
	}

}
