package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao;

import java.util.List;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Patient;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Prescription;

/**
 * Interface of the {@code Prescription} specific database operations.
 * 
 * @author K.Suter
 * 
 */
public interface PrescriptionDao extends GenericDao<Prescription, Long> {

	List<Prescription> readPrescriptionForPatient(Patient patient);

}
