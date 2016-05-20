package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Interface for the standard CRUD database operations.
 * 
 * @author K.Suter
 * 
 */
public interface GenericDao<T, ID extends Serializable> {
	
	// create a single entity in the database
	T persist(T t);

	// update a single entity in the database
	T update(T t);

	// delete a single entity in the database
	void remove(T t);
	
	// read a single entity from the database
	T findById(ID id);
	
	// find multiple entities in the database
	List<T> findByIds(List<ID> ids);
	
	// find entities by field value
	T findByField(String fieldName, Object value);

}