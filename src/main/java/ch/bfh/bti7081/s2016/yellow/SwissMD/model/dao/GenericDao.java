package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao;

import java.io.Serializable;

/**
 * Interface for the standard CRUD database operations.
 * 
 * @author K.Suter
 * 
 * */
public interface GenericDao<T, PK extends Serializable> {
	// create a single entity in the database
	T create(T t);
	// read a single entity from the database
    T read(PK id);
    // update a single entity in the database
    T update(T t);
    //delete a single entity in the database
    void delete(T t);
    
}