package ch.bfh.bti7081.s2016.yellow.SwissMD.view.components;

import java.util.Date;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.DiagnosisDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.IllnessDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PatientDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.CouldNotSaveException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.MeetingStateException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.presenter.CreateDiagnosisTilePresenter;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.Tile;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class CreateDiagnosisTile extends Tile {
	private CreateDiagnosisTilePresenter createDiagnosisTilePresenter = new CreateDiagnosisTilePresenter();

	private IllnessDTO illnessDTO;

	private TextArea noteArea = new TextArea("Kommentar");

	public CreateDiagnosisTile(IllnessDTO illnessDTO, Window window) {
		this.illnessDTO = illnessDTO;
		addComponent(new Label(illnessDTO.getCode() + " - "
				+ illnessDTO.getName()));
		addComponent(noteArea);
		Button addDiagnosis = new Button("Erstelle Diagnose");
		addDiagnosis.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				DiagnosisDTO diagnosisDTO = null;
				diagnosisDTO = new DiagnosisDTO(illnessDTO,
						noteArea.getValue(), new Date(), createDiagnosisTilePresenter.loadPatient());
				try {
					createDiagnosisTilePresenter.createDiagnosis(diagnosisDTO);
				} catch (CouldNotSaveException e) {
					// TODO Go to error page
					e.printStackTrace();
				}
				window.close();
			}
		});
		addComponent(addDiagnosis);
		contentLayout.setMargin(true);

	}

}
