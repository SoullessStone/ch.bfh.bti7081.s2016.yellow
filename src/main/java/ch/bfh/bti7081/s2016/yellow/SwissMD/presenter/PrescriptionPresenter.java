package ch.bfh.bti7081.s2016.yellow.SwissMD.presenter;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.PrescriptionDaoImpl;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PrescriptionDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Drug;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Prescription;

public class PrescriptionPresenter {
	PrescriptionDaoImpl prescriptionDao;

	public PrescriptionPresenter() {
		this.prescriptionDao = new PrescriptionDaoImpl();
	}
	
	public void savePrescription(PrescriptionDTO prescriptionDTO) {
		Prescription prescription = new Prescription();
		prescription.setDrug(new Drug(prescriptionDTO.getDrug()));
		prescription.setDosisInMilligrams(prescriptionDTO.getDosisInMilligrams());
		prescription.setValidity(prescriptionDTO.getValidity());
		prescriptionDao.create(prescription);
	}
}
