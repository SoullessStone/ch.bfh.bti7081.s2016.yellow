package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Drug;

/**
 * Implementation of the {@code Drug} specific database operations.
 * 
 * @author K.Suter
 * 
 */
public class DrugDaoImpl extends GenericDaoImpl<Drug, Long> implements DrugDao {

	public DrugDaoImpl(EntityManagerProvider emp) {
		super(emp);
	}

}
