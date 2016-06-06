package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao;

import java.util.List;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Illness;

/**
 * Interface of the {@code Illness} specific database operations.
 * 
 * @author K.Suter
 * 
 */
public interface IllnessDao extends GenericDao<Illness, Long> {
	public List<Illness> findByCodeOrName(String searchString);
}