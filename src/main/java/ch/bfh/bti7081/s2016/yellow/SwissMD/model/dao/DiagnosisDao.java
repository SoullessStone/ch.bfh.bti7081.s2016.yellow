package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao;

import java.util.List;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Diagnosis;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Drug;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Patient;

/**
 * Interface of the {@code Drug} specific database operations.
 * 
 * @author K.Suter
 * 
 */
public interface DiagnosisDao extends GenericDao<Diagnosis, Long> {
	public List<Diagnosis> findDiagnosisForPerson(Patient patient);
}