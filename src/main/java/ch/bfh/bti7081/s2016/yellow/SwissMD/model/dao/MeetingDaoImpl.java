package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao;

import java.util.List;

import org.hibernate.SQLQuery;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Meeting;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Patient;

public class MeetingDaoImpl extends GenericDaoImpl<Meeting, Long> {

	public List<Meeting> findMeetingForPerson(Patient patient) {
		List<Meeting> resultList = (List<Meeting>) mEntityManager.createNamedQuery("findMeetingsForPatient")
	    .setParameter("patient", patient)
	    .getResultList();
		return resultList;
	}
	
	
}
