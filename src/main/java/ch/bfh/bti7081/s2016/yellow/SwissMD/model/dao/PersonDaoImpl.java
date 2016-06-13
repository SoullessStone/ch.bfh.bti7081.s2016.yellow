package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao;

import java.util.List;

import javax.persistence.Query;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Diagnosis;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Patient;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Person;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.util.DangerStateType;

/**
 * Implementation of the {@code Person} specific database operations.
 * 
 * @author K.Suter
 * 
 */
public class PersonDaoImpl extends GenericDaoImpl<Person, Long> implements
		PersonDao {

	public PersonDaoImpl(EntityManagerProvider emp) {
		super(emp);
	}
	
	public void updateDangerState(Patient patient) {
		@SuppressWarnings("unchecked")	
		Query updateQuery =  mEntityManager
				.createQuery(
						"UPDATE Person SET dangerState = :dangerState WHERE id = :id");
		updateQuery.setParameter("dangerState", patient.getDangerState());
		updateQuery.setParameter("id", patient.getId());
		updateQuery.executeUpdate();
	}
	
}
