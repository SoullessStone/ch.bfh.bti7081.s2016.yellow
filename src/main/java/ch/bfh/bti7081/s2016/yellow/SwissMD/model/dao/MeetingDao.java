package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao;

import java.util.List;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Meeting;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Patient;

/**
 * Interface for the {@code Meeting} specific database operations.
 * 
 * @author S.Zumstein
 * 
 */
public interface MeetingDao extends GenericDao<Meeting, Long> {
	public List<Meeting> findMeetingForPerson(Patient patient);
}