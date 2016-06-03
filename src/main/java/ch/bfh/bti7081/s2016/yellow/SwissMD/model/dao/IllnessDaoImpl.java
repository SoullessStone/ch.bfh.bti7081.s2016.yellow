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

	public IllnessDaoImpl(EntityManagerProvider emp) {
		super(emp);
	}

	@Override
	public List<Illness> findByCodeOrName(String searchString) {
		 @SuppressWarnings("unchecked")
		 List<Illness> resultList = (List<Illness>)
		 mEntityManager.createQuery("SELECT m FROM Illness m WHERE m.code LIKE :code OR m.name LIKE :name")
		 .setParameter("code", "%" + searchString + "%")
		 .setParameter("name", "%" + searchString + "%")
		 .getResultList();
		 return resultList;
	}

}
