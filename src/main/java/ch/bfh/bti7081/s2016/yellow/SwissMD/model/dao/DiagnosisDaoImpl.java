package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao;

import java.util.List;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Diagnosis;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Meeting;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Patient;

/**
 * Implementation of the {@code Diagnosis} specific database operations.
 * 
 * @author K.Suter
 * 
 */
public class DiagnosisDaoImpl extends GenericDaoImpl<Diagnosis, Long> implements DiagnosisDao{

	public DiagnosisDaoImpl(EntityManagerProvider emp) {
		super(emp);
	}

	public List<Diagnosis> findDiagnosisForPerson(Patient patient) {
		@SuppressWarnings("unchecked")
		List<Diagnosis> resultList = (List<Diagnosis>) mEntityManager
				.createQuery(
						"SELECT d FROM Diagnosis d WHERE d.patient = :patient")
				.setParameter("patient", patient).getResultList();
		return resultList;
	}

}
