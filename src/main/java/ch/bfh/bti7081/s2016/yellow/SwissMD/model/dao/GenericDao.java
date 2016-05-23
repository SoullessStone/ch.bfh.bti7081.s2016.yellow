package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * Interface for the standard CRUD database operations.
 * 
 * @author K.Suter
 * 
 */
public interface GenericDao<T, ID extends Serializable> {

	// create a single entity in the database
	T create(T t);

	// update a single entity in the database
	T update(T t);

	// delete a single entity in the database
	void delete(T t);

	// read a single entity from the database
	T read(ID id);

	// find all domain objects for an entity
	List<T> readAll();

	// find multiple entities in the database
	List<T> readList(List<ID> ids);

	// find entities by field value
	T readByField(String fieldName, Object value);

	// safely read entities with typed query
	<T2> T2 getSingleResultSafeTypedQuery(TypedQuery<T2> query);

	// safely read entities with typed query
	Object getSingleResultSafeQuery(Query query);

}