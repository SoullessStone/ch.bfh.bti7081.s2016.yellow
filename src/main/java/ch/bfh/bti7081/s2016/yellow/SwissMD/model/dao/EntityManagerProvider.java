package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao;

import javax.persistence.EntityManager;

/**
 * Interface for providers of an entity manager, which allows to perform the database operations.
 * 
 * @author K.Suter
 * 
 */
public interface EntityManagerProvider {
	public EntityManager createEntityManager();
}
