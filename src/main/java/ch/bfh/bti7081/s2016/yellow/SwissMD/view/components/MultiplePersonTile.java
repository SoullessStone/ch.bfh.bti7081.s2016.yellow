package ch.bfh.bti7081.s2016.yellow.SwissMD.view.components;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PersonDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.Tile;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.navigation.NavigationIndex;

import com.vaadin.data.Item;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
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
		if (persons == null || persons.isEmpty()) {
			layout.addComponent(new Label("Keine Personen gefunden!"));
			return;
		}
		persons.sort(new Comparator<PersonDTO>() {

			@Override
			public int compare(PersonDTO o1, PersonDTO o2) {
				return o2.getDtype().compareTo(o1.getDtype());
			}
		});

		Table table = new Table();		
		table.addContainerProperty("Icon", Image.class, null);
		table.addContainerProperty("Name", String.class, null);
		table.addContainerProperty("Geburtsdatum", String.class, null);
		table.addContainerProperty("Profil", Link.class, null);
		table.addContainerProperty("Neues Meeting", Link.class, null);

		for (PersonDTO person : persons) {
			Image userPicture = new Image();
			if ("Patient".equals(person.getDtype())) {
				userPicture.setStyleName("gridBorder");
				userPicture.setSource(new ThemeResource("img/icons/businessman.png"));
			}
			if ("Doctor".equals(person.getDtype())) {
				userPicture.setStyleName("gridBorder");
				userPicture.setSource(new ThemeResource(
						"img/icons/surgeon.png"));
			}
			String name = person.getName();
			String birthdate = format.format(person.getBirthdate());
			Link profileLink = new Link("Benutzerprofil", new ExternalResource(
					"#!" + NavigationIndex.PERSONVIEW + "/" + person.getId()));
			Link meetingLink = null;
			if (person.getDtype().toLowerCase().equals("patient")) {
				meetingLink = new Link("Neues Meeting mit diesem Patient",
						new ExternalResource("#!" + NavigationIndex.MEETINGVIEW
								+ "/new=" + person.getId()));
			}

			table.addItem(new Object[] { userPicture, name, birthdate, profileLink, meetingLink }, persons.indexOf(person)+1);
		}
		
		table.setPageLength(table.size());
		this.layout.addComponent(table);
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
