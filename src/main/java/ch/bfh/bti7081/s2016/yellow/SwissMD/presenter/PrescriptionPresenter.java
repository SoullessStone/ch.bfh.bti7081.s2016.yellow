package ch.bfh.bti7081.s2016.yellow.SwissMD.presenter;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.DrugDaoImpl;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.PersonDaoImpl;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.PrescriptionDaoImpl;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PrescriptionDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Drug;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Patient;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Prescription;

public class PrescriptionPresenter {
	PrescriptionDaoImpl prescriptionDao;
	DrugDaoImpl drugDao;
	PersonDaoImpl personDao;
	
	public PrescriptionPresenter() {
		this.prescriptionDao = new PrescriptionDaoImpl();
		this.drugDao = new DrugDaoImpl();
		this.personDao = new PersonDaoImpl();
		
	}
	
	public void savePrescription(PrescriptionDTO prescriptionDTO) {
		Prescription prescription = new Prescription();
		prescription.setPatient((Patient) personDao.read(prescriptionDTO.getPatient().getId()));
		prescription.setDrug(drugDao.read(prescriptionDTO.getDrug().getId()));
		prescription.setDosisInMilligrams(prescriptionDTO.getDosisInMilligrams());
		prescription.setValidity(prescriptionDTO.getValidity());
		prescriptionDao.create(prescription);
	}
}
