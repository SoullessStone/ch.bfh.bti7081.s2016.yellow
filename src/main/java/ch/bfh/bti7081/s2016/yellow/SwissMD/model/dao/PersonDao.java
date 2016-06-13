package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Patient;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Person;

/**
 * Interface of the {@code Person} specific database operations.
 * 
 * @author K.Suter
 * 
 */
public interface PersonDao extends GenericDao<Person, Long> {
	public void updateDangerState(Patient patient); 

}