package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Diagnosis;

/**
 * Implementation of the {@code Diagnosis} specific database operations.
 * 
 * @author K.Suter
 * 
 */
public class DiagnosisDaoImpl extends GenericDaoImpl<Diagnosis, Long> {

	public DiagnosisDaoImpl(EntityManagerProvider emp) {
		super(emp);
	}

}
