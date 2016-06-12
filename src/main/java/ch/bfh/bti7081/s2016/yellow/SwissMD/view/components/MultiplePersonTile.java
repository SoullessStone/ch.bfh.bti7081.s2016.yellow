package ch.bfh.bti7081.s2016.yellow.SwissMD.view.components;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;

import com.vaadin.server.ExternalResource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.PersonDao;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.PersonDaoImpl;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.WebEntityManagerProvider;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PatientDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PersonDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Patient;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.MeetingStateException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.Tile;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.navigation.NavigationIndex;

@SuppressWarnings("serial")
public class MultiplePersonTile extends Tile {
	private List<PersonDTO> persons;
	private PersonDao personDao;
	private VerticalLayout layout;
	private final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

	public MultiplePersonTile(List<PersonDTO> persons) {
		this.persons = persons;
		this.personDao = new PersonDaoImpl(new WebEntityManagerProvider());
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
		table.addContainerProperty("Profil", Button.class, null);
		table.addContainerProperty("Neues Meeting", Button.class, null);

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
			
			Button profileButton = new Button("Benutzerprofil");
			profileButton.setStyleName("link");
			
			Button meetingButton = null;
			
			if (person.getDtype().toLowerCase().equals("patient")) {
				profileButton.addClickListener(new ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						PatientDTO patient = null;
						try {
							patient = findPersonById(person.getId());
						} catch (MeetingStateException e) {
							e.printStackTrace();
						}
						getUI().getSession().setAttribute("currentPatient", patient);
						getUI().getSession().setAttribute("currentPerson", null);
//						Label sessionPatientLabel = (Label) MenuTile.findComponentById(getUI(), "sessionPatientLabel", patient.getName());
						getUI().getNavigator().navigateTo(
								NavigationIndex.PERSONVIEW
										.getNavigationPath());
					}
				});
				
				meetingButton = new Button("Neues Meeting mit diesem Patient");
				meetingButton.setStyleName("link");
				meetingButton.addClickListener(new ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						PatientDTO patient = null; // didn't work
						System.out.println("MEETINGVIEW!!!");
						try {
							patient = findPersonById(person.getId());
						} catch (MeetingStateException e) {
							e.printStackTrace();
						}
						getUI().getSession().setAttribute("currentPatient", patient);
						getUI().getSession().setAttribute("currentPerson", null);
//						Label sessionPatientLabel = (Label) MenuTile.findComponentById(getUI(), "sessionPatientLabel");
						getUI().getNavigator().navigateTo(
								NavigationIndex.MEETINGVIEW
								.getNavigationPath()
								+ "/new=" + patient.getId()
								);
					}
				});
			}
			else{
				profileButton.addClickListener(new ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						// set patient to null, we now have saved a non-patient-person to the session
						getUI().getSession().setAttribute("currentPatient", null);
						getUI().getSession().setAttribute("currentPerson", person);
						getUI().getNavigator().navigateTo(
								NavigationIndex.PERSONVIEW
										.getNavigationPath());
					}
				});
			}
			

				
			Link profileLink = new Link("Benutzerprofil", new ExternalResource(
					"#!" + NavigationIndex.PERSONVIEW + "/" + person.getId()));
			Link meetingLink = null;
			if (person.getDtype().toLowerCase().equals("patient")) {
				meetingLink = new Link("Neues Meeting mit diesem Patient",
						new ExternalResource("#!" + NavigationIndex.MEETINGVIEW
								+ "/new=" + person.getId()));
			}

			table.addItem(new Object[] { userPicture, name, birthdate, profileButton, meetingButton }, persons.indexOf(person)+1);
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
	public PatientDTO findPersonById(Long id) throws MeetingStateException {
		Patient patient = (Patient) personDao.read(id);
		if (patient != null) {
			return new PatientDTO(patient);
		}
		return null;
	}
}
