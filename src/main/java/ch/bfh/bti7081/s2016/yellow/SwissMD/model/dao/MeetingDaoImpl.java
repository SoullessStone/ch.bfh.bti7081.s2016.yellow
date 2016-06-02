package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao;

import java.util.List;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Meeting;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Patient;

/**
 * Implementation of the {@code Meeting} specific database operations.
 * 
 * @author S.Zumstein
 * 
 */
public class MeetingDaoImpl extends GenericDaoImpl<Meeting, Long> implements MeetingDao {

	public List<Meeting> findMeetingForPerson(Patient patient) {
		@SuppressWarnings("unchecked")
		List<Meeting> resultList = (List<Meeting>) mEntityManager.createNamedQuery("SELECT m FROM Meeting m WHERE m.patient = :patient")
	    .setParameter("patient", patient)
	    .getResultList();
		return resultList;
	}
	
	
}
