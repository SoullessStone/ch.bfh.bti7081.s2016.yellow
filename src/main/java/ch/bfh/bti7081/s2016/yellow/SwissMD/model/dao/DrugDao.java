package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Drug;

/**
 * Interface of the {@code Drug} specific database operations.
 * 
 * @author K.Suter
 * 
 */
public interface DrugDao extends GenericDao<Drug, Long> {

}