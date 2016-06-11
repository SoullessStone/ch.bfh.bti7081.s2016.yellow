package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao;

import java.util.List;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Patient;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Prescription;

/**
 * Implementation of the {@code Prescription} specific database operations.
 * 
 * @author K.Suter
 * 
 */
public class PrescriptionDaoImpl extends GenericDaoImpl<Prescription, Long>
		implements PrescriptionDao {

	public PrescriptionDaoImpl(EntityManagerProvider emp) {
		super(emp);
	}

	@Override
	public List<Prescription> readPrescriptionForPatient(Patient patient) {
		@SuppressWarnings("unchecked")
		List<Prescription> resultList = (List<Prescription>) mEntityManager
				.createQuery(
						"SELECT p FROM Prescription p WHERE p.patient = :patient")
				.setParameter("patient", patient).getResultList();
		return resultList;
	}
}
