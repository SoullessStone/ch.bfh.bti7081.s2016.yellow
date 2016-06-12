package ch.bfh.bti7081.s2016.yellow.SwissMD.view.components;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.PersonDaoImpl;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.WebEntityManagerProvider;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PatientDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.Tile;

import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;

/**
 * Tile for Patient Overview
 * 
 * @author S. Schaad
 * 
 */

@SuppressWarnings("serial")
public class GridTile extends Tile {
	private PersonDaoImpl personDao;

	public GridTile(PatientDTO patientDTO) {
		personDao = new PersonDaoImpl(new WebEntityManagerProvider());

		setTitle("Medzinische Grunddaten " + patientDTO.getName());
		setStdWidth(3);

		GridLayout grid = new GridLayout(2, 4);
		grid.setSizeFull();
		grid.addComponent(new Label("Angehörige: "));

		grid.addComponent(new Label("Beistand: "
				+ (patientDTO.getLegalAid() != null ? personDao.read(
						patientDTO.getLegalAid()).getName() : "---")));

		grid.addComponent(new Label("Hausarzt: "
				+ (patientDTO.getFamilyDoctor() != null ? personDao.read(
						patientDTO.getFamilyDoctor()).getName() : "---")));

		Label escalation = new Label("Gefährdung: ");
		// Label escalation = new Label("Gefährdung: "
		// +(patientDTO.getDangerState()));
		// if (patientDTO.getDangerState() == DangerStateType.DANGER_TO_OTHERS
		// ||
		// patientDTO.getDangerState() == DangerStateType.DANGER_TO_HIMSELF){
		// escalation.addStyleName("highrisk");
		// }
		// System.out.println("Aktueller Danger State: "+patientDTO.getDangerState());
		grid.addComponent(escalation);

		// if (patientDTO.getDangerState().equals("1")){
		// escalation.setValue("Gefährdung: Krise");
		// }
		// else if (patientDTO.getDangerState().equals("2")){
		// escalation.setValue("Gefährdung: Selbstgefährdung");
		// }
		// else if(patientDTO.getDangerState().equals("3")){
		// escalation.setValue();
		// }

		addComponent(grid);
		contentLayout.setMargin(true);
	}

}
