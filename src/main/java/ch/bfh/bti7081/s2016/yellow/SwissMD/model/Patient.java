package ch.bfh.bti7081.s2016.yellow.SwissMD.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

// TODO: Erstellt von Michel (wegen Abhängigkeit von Meeting), muss noch implementiert werden
@Entity
@Table
public class Patient extends Person {
	
	@OneToMany(mappedBy = "patient")
	List<Meeting> meetings;

	public Patient(String name, Date birthdate) {
		super(name, birthdate);
	}
}
