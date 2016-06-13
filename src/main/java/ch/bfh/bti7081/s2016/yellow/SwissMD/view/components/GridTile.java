package ch.bfh.bti7081.s2016.yellow.SwissMD.view.components;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.PersonDaoImpl;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.WebEntityManagerProvider;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PatientDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.util.DangerStateType;
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
	private static final String RISK_STYLE = "highrisk";
	private static final String DANGER_STATE = "Gefährdung: ";
	private static final String MAIN_DOCTOR = "Hausarzt: ";
	private static final String ASSISTANCE = "Beistand: ";
	private static final String RELATIVES = "Angehörige: ";
	private static final String MEDICAL_BASE_DATA = "Medzinische Grunddaten ";
	private PersonDaoImpl personDao;

	public GridTile(PatientDTO patientDTO) {
		personDao = new PersonDaoImpl(new WebEntityManagerProvider());

		setTitle(MEDICAL_BASE_DATA + patientDTO.getName());
		setStdWidth(3);

		GridLayout grid = new GridLayout(2, 4);
		grid.setSizeFull();
		grid.addComponent(new Label(RELATIVES));

		grid.addComponent(new Label(ASSISTANCE
				+ (patientDTO.getLegalAid() != null ? personDao.read(
						patientDTO.getLegalAid()).getName() : "---")));

		grid.addComponent(new Label(MAIN_DOCTOR
				+ (patientDTO.getFamilyDoctor() != null ? personDao.read(
						patientDTO.getFamilyDoctor()).getName() : "---")));

		Label escalation = new Label(DANGER_STATE + patientDTO.getDangerState().getDangerStateTitle());
		if (patientDTO.getDangerState() == DangerStateType.DANGER_TO_OTHERS || patientDTO.getDangerState() == DangerStateType.DANGER_TO_HIMSELF){
			escalation.addStyleName(RISK_STYLE);
		}
		grid.addComponent(escalation);

		addComponent(grid);
		contentLayout.setMargin(true);
	}

}
