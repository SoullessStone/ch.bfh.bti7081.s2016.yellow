package ch.bfh.bti7081.s2016.yellow.SwissMD.view.components;

import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;

import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.Tile;

/**
 * Tile for Patient Overview
 * 
 * @author S. Schaad
 * 
 */

public class GridTile extends Tile {

	public GridTile(String title) {
		setTitle(title);
		setStdWidth(3);
		
		GridLayout grid = new GridLayout(2,4);
		grid.setSizeFull();
		grid.addComponent(new Label("Diagnose: "));
		grid.addComponent(new Label("Angehörige: Bruder Tack"));
		grid.addComponent(new Label("Beistand: Francine Jordi"));
		grid.addComponent(new Label("Hausarzt: Johnny Evans"));
		addComponent(grid);
		contentLayout.setMargin(true);
	}

}
