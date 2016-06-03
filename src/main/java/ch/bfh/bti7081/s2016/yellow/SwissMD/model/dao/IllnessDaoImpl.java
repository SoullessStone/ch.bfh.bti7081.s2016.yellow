package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao;

import java.util.List;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Illness;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Meeting;

/**
 * Implementation of the {@code Illness} specific database operations.
 * 
 * @author K.Suter
 * 
 */
public class IllnessDaoImpl extends GenericDaoImpl<Illness, Long> implements IllnessDao {

	@Override
	public List<Illness> findByCodeOrDescription(String searchString) {
		// TODO Finish method and test it with JUnit
		// @SuppressWarnings("unchecked")
		// List<Illness> resultList = (List<Illness>)
		// mEntityManager.createQuery("SELECT m FROM Illness m WHERE m.code LIKE ':code' OR m.description LIKE '%:description%'")
		// .setParameter("code", searchString)
		// .setParameter("description", searchString)
		// .getResultList();
		// return resultList;
		return null;
	}

}
