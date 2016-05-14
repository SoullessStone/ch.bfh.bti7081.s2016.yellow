package ch.bfh.bti7081.s2016.yellow.SwissMD.model;

import java.util.Date;

//TODO: Erstellt von Michel (wegen Abhängigkeit von Meeting), muss noch implementiert werden
// TODO: Erweitere PersonTile, wenn nötig
public class Person {
	private String name;
	private Date birthdate;

	public Person(String name, Date birthdate) {
		this.name = name;
		this.birthdate = birthdate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

}
