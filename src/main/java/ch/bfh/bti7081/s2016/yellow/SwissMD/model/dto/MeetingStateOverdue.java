package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto;

/**
 * 
 * Meeting which is planned but has a date in the past and hasn't been performed
 * yet. Can still get canceled, performed or postponed. 
 * 
 * This state is not persisted but derived from the appointment date of the meeting!
 * 
 * @author K.Suter
 * 
 */
public class MeetingStateOverdue extends MeetingStatePlanned {

}
