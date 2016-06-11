package ch.bfh.bti7081.s2016.yellow.SwissMD.presenter;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.DiagnosisDaoImpl;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.IllnessDao;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.IllnessDaoImpl;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.PersonDao;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.PersonDaoImpl;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.WebEntityManagerProvider;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.DiagnosisDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PatientDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Diagnosis;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Illness;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Patient;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.CouldNotSaveException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.MeetingStateException;

public class CreateDiagnosisTilePresenter {
	private DiagnosisDaoImpl diagnosisDao = new DiagnosisDaoImpl(new WebEntityManagerProvider());
	private PersonDao personDao = new PersonDaoImpl(new WebEntityManagerProvider());
	private IllnessDao illnessDao = new IllnessDaoImpl(new WebEntityManagerProvider());
	
	public void createDiagnosis(DiagnosisDTO diagnosisDTO) throws CouldNotSaveException {
		
		Diagnosis diagnosis = new Diagnosis();
		try {
			diagnosis.setPatient((Patient) personDao.read(diagnosisDTO.getPatient().getId()));
		} catch (ClassCastException e) {
			throw new CouldNotSaveException("Person is not a patient.");
		}
		diagnosis.setIllness(illnessDao.read(diagnosisDTO.getIllness().getId()));
		diagnosis.setNotes(diagnosisDTO.getNotes());
		diagnosis.setDate(diagnosisDTO.getDate());
		diagnosisDao.create(diagnosis);
		System.out.println("create: " + diagnosisDTO);
	}
	
	//TODO remove when ready
	public PatientDTO loadPatient() throws MeetingStateException{
		return new PatientDTO((Patient) personDao.read(11L));
	}
}
