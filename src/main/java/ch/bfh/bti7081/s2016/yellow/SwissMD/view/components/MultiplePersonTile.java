package ch.bfh.bti7081.s2016.yellow.SwissMD.view.components;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PersonDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.Tile;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Link;
import com.vaadin.ui.VerticalLayout;

public class MultiplePersonTile extends Tile {
	private List<PersonDTO> persons;
	private VerticalLayout layout;
	private final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

	public MultiplePersonTile(List<PersonDTO> persons) {
		this.persons = persons;
		layout = new VerticalLayout();
		addComponent(layout);
		updateTileValue();
		System.out.println("MultiplePersonTile created");
	}

	private void updateTileValue() {
		layout.removeAllComponents();
		for (PersonDTO person : persons) {
			HorizontalLayout horizontalLayout = new HorizontalLayout();
			horizontalLayout.addComponent(new Label(person.getName() + " - "
					+ format.format(person.getBirthdate()) + ""));
			horizontalLayout.addComponent(new Link("Benutzerprofil ",
					new ExternalResource("http://www.google.com")));
			horizontalLayout.addComponent(new Link("Neues Meeting mit diesem Patient ",
					new ExternalResource("http://www.google.com")));
			this.layout.addComponent(horizontalLayout);
		}
	}

	public void addPerson(PersonDTO person) {
		this.persons.add(person);
		updateTileValue();
	}

	public void removePerson(PersonDTO person) {
		this.persons.remove(person);
		updateTileValue();
	}

	public void setPersons(List<PersonDTO> persons) {
		this.persons = persons;
		updateTileValue();
	}
}
