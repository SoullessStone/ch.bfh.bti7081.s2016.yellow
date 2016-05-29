package ch.bfh.bti7081.s2016.yellow.SwissMD.view.components;

import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.PersonDaoImpl;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PersonDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Person;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.Tile;

/**
 * Tile for Patient Overview
 * 
 * @author S. Schaad
 * 
 */

public class GridTile extends Tile {
	private PersonDaoImpl personDao;
	
	public GridTile(PersonDTO personDTO) {
		personDao = new PersonDaoImpl();
		
		setTitle("Medzinische Grunddaten " + personDTO.getName());
		setStdWidth(3);

		GridLayout grid = new GridLayout(2, 4);
		grid.setSizeFull();
		grid.addComponent(new Label("Diagnose: "));
		grid.addComponent(new Label("Angehörige: "));
		
		grid.addComponent(new Label("Beistand: "
				+ (personDTO.getLegalAid() != null ? personDao.read(personDTO.getLegalAid()).getName() : "---")));
		
		grid.addComponent(new Label("Hausarzt: "
				+ (personDTO.getFamilyDoctor() != null ? personDao.read(personDTO.getFamilyDoctor()).getName() : "---")));
		
		addComponent(grid);
		contentLayout.setMargin(true);
	}

}
