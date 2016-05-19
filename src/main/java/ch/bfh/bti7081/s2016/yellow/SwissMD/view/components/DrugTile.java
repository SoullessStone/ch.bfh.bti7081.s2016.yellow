package ch.bfh.bti7081.s2016.yellow.SwissMD.view.components;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.DrugDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.Tile;

import com.vaadin.ui.ComboBox;
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
	private String currentDrug;

	public DrugTile(DrugDTO drugDTO, String title) {
		this.drug = drugDTO;
		setTitle(title);
		// TO DO: reale Daten (Liste einlesen)
		currentDrug = drug.getTradeName() + " " + drug.getSubstance() + " " + drug.getSubstanceQuantitiy() + " " + drug.getMaxDose() + " Tabletten";
		ComboBox selectDrug = new ComboBox("Medikamente");
		selectDrug.addItem(currentDrug);
		addComponent(selectDrug);
		System.out.println("drug tile created");
	}

}
