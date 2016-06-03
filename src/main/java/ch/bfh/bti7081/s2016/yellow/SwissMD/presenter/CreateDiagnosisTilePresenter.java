package ch.bfh.bti7081.s2016.yellow.SwissMD.presenter;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.DiagnosisDaoImpl;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.DiagnosisDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Diagnosis;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Illness;

public class CreateDiagnosisTilePresenter {
	private DiagnosisDaoImpl diagnosisDao = new DiagnosisDaoImpl();

	public void createDiagnosis(DiagnosisDTO diagnosisDTO) {
		// TODO Michel
		// Diagnosis diagnosis = new Diagnosis(diagnosisDTO);
		// TODO Michel wait for illnessDao
		// diagnosisDao.create(diagnosis);
		System.out.println("create: " + diagnosisDTO);
	}

}
