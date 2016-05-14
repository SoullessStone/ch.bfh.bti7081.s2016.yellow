package ch.bfh.bti7081.s2016.yellow.SwissMD.model;

import java.util.List;

public class Meeting {
	private Patient patient;
	private Doctor doctor;
	// TODO: Gemäss classmodel muss das eine Liste von Sitzungseinträgen werden. Wurde noch weggelassen
	private String notes;
	private List<Prescription> prescriptions;
}
