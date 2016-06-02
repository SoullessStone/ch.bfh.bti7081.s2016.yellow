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
	public T create(T t);

	// update a single entity in the database
	public T update(T t);

	// delete a single entity in the database
	public void delete(T t);

	// read a single entity from the database
	public T read(ID id);

	// find all domain objects for an entity
	public List<T> readAll();

	// find multiple entities in the database
	public List<T> readList(List<ID> ids);

	// find entities by field value
	public T readByField(String fieldName, Object value);

	// safely read entities with typed query
	public <T2> T2 getSingleResultSafeTypedQuery(TypedQuery<T2> query);

	// safely read entities with typed query
	public Object getSingleResultSafeQuery(Query query);

}