package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao;


import javax.persistence.EntityTransaction;
import javax.persistence.Query;

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
	
	public void updateDangerState(Long id, DangerStateType ds) {
		@SuppressWarnings("unchecked")	

		EntityTransaction tx = null;
		try {
		    tx = mEntityManager.getTransaction();
		    tx.begin();
			Query updateQuery =  mEntityManager.createQuery(
					"UPDATE Person p SET p.dangerState = :dangerState WHERE p.id = :id")
			.setParameter("dangerState", ds)
			.setParameter("id", id);
			updateQuery.executeUpdate();
			tx.commit();
		}
		catch (RuntimeException e) {
		    if ( tx != null && tx.isActive() ) tx.rollback();
		    throw e; // or display error message
		}
		finally {
			mEntityManager.close();
		}
	}
	
}
