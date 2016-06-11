package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Implementation of the {@code EntityManagerProvider}.
 * 
 * Only for use in the unit tests.
 * 
 * @author K.Suter
 * 
 */
public class TestEntityManagerProvider implements EntityManagerProvider {

	public EntityManager createEntityManager() {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("test");
		return emf.createEntityManager();
	}

}
