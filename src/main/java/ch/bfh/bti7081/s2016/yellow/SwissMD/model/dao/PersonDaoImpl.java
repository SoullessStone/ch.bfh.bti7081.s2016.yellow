package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Person;

/**
 * Implementation of the {@code Person} specific database operations.
 * 
 * @author K.Suter
 * 
 */
public class PersonDaoImpl extends GenericDaoImpl<Person, Long> implements PersonDao{

	public PersonDaoImpl(EntityManagerProvider emp) {
		super(emp);
	}

}
