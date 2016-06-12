package ch.bfh.bti7081.s2016.yellow.SwissMD.presenter;

import java.util.ArrayList;
import java.util.List;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.DrugDao;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.DrugDaoImpl;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.PersonDao;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.PersonDaoImpl;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.PrescriptionDao;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.PrescriptionDaoImpl;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.WebEntityManagerProvider;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.DrugDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PatientDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PrescriptionDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Drug;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Patient;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Prescription;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.MeetingStateException;

/**
 * Presenter für die PrescriptionView
 * 
 * @author Mutz
 *
 */
public class PrescriptionPresenter {
	PrescriptionDao prescriptionDao;
	DrugDao drugDao;
	PersonDao personDao;

	public PrescriptionPresenter() {
		this.prescriptionDao = new PrescriptionDaoImpl(
				new WebEntityManagerProvider());
		this.drugDao = new DrugDaoImpl(new WebEntityManagerProvider());
		this.personDao = new PersonDaoImpl(new WebEntityManagerProvider());

	}

	public void savePrescription(PrescriptionDTO prescriptionDTO) {
		Prescription prescription = new Prescription();
		prescription.setPatient((Patient) personDao.read(prescriptionDTO
				.getPatient().getId()));
		prescription.setDrug(drugDao.read(prescriptionDTO.getDrug().getId()));
		prescription.setDosisInMilligrams(prescriptionDTO
				.getDosisInMilligrams());
		prescription.setValidity(prescriptionDTO.getValidity());
		prescriptionDao.create(prescription);
	}

	public List<PrescriptionDTO> getPrescriptionsForPatient(
			PatientDTO patientDTO) throws MeetingStateException {
		if (patientDTO == null) {
			throw new IllegalArgumentException("Patient was null");
		}
		Patient patient = (Patient) personDao.read(patientDTO.getId());
		List<Prescription> prescriptions = prescriptionDao
				.readPrescriptionForPatient(patient);

		List<PrescriptionDTO> result = new ArrayList<>();
		for (Prescription prescription : prescriptions) {
			result.add(new PrescriptionDTO(prescription));
		}
		return result;
	}

	/**
	 * Returns a List of {@code Drug} or {@code null} if no drug could be found
	 * 
	 */
	public List<DrugDTO> getPossibleDrugs() {
		List<Drug> drugs = drugDao.readAll();
		List<DrugDTO> drugDTOs = new ArrayList<>();
		for (Drug drug : drugs) {
			drugDTOs.add(new DrugDTO(drug));
		}
		return drugDTOs;
	}
}
