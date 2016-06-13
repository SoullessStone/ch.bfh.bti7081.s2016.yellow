package ch.bfh.bti7081.s2016.yellow.SwissMD.view.components;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.PersonDao;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.PersonDaoImpl;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.WebEntityManagerProvider;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PatientDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PersonDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Patient;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.DangerStateException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.MeetingStateException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.util.PatientInSessionManager;
import ch.bfh.bti7081.s2016.yellow.SwissMD.presenter.PersonPresenter;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.Tile;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.navigation.NavigationIndex;

import com.vaadin.server.ExternalResource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

/**
 * Stellt eine Liste von PersonDTOs dar
 * 
 * @author SoullessStone
 *
 */
@SuppressWarnings("serial")
public class MultiplePersonTile extends Tile {
	private static final String USER_PROFILE = "Benutzerprofil";
	private static final String LINK_STYLE = "link";
	private static final String NEW_MEETING_WITH_PATIENT = "Neues Meeting mit diesem Patient";
	private static final String SELECT_PERSON = "Person auswählen";
	private static final String ICONS_SURGEON = "img/icons/surgeon.png";
	private static final String ICONS_BUSINESSMAN = "img/icons/businessman.png";
	private static final String GRID_BORDER_STYLE = "gridBorder";
	private static final String NEW_MEETING_PROPERTY = "Neues Meeting";
	private static final String PROFILE_PROPERTY = "Profil";
	private static final String BIRTHDAY_PROPERTY = "Geburtsdatum";
	private static final String NAME_PROPERTY = "Name";
	private static final String ICON_PROPERTY = "Icon";
	private static final String NO_PERSON_FOUND = "Keine Personen gefunden!";
	
	private List<PersonDTO> persons;
	private PersonPresenter personPresenter = new PersonPresenter();	

	private final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

	public MultiplePersonTile(List<PersonDTO> persons) {
		super();
		this.persons = persons;
	}
	
	@Override
	protected void createDefaultLayout() {
		contentLayout = new VerticalLayout();
		tile.setContent(contentLayout);
		
		contentLayout.setSpacing(true);
		contentLayout.setMargin(true);
		setStdWidth(1);
		
		updateTileValue();

	};

	private void updateTileValue() {
		contentLayout.removeAllComponents();
		if (persons == null || persons.isEmpty()) {
			contentLayout.addComponent(new Label(NO_PERSON_FOUND));
			return;
		}
		persons.sort(new Comparator<PersonDTO>() {

			@Override
			public int compare(PersonDTO o1, PersonDTO o2) {
				return o2.getDtype().compareTo(o1.getDtype());
			}
		});

		Table table = new Table();
		table.setWidth("100%");
		table.addContainerProperty(ICON_PROPERTY, Image.class, null);
		table.addContainerProperty(NAME_PROPERTY, String.class, null);
		table.addContainerProperty(BIRTHDAY_PROPERTY, String.class, null);
		table.addContainerProperty(PROFILE_PROPERTY, Button.class, null);
		table.addContainerProperty(NEW_MEETING_PROPERTY, Button.class, null);

		for (PersonDTO person : persons) {
			Image userPicture = new Image();
			if ("Patient".equals(person.getDtype())) {
				userPicture.setStyleName(GRID_BORDER_STYLE);
				userPicture.setSource(new ThemeResource(
						ICONS_BUSINESSMAN));
			}
			if ("Doctor".equals(person.getDtype())) {
				userPicture.setStyleName(GRID_BORDER_STYLE);
				userPicture
						.setSource(new ThemeResource(ICONS_SURGEON));
			}
			String name = person.getName();
			String birthdate = format.format(person.getBirthdate());

			Button profileButton = new Button(SELECT_PERSON);
			profileButton.setStyleName(LINK_STYLE);

			Button meetingButton = null;

			if (person.getDtype().toLowerCase().equals("patient")) {
				profileButton.addClickListener(new ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						PatientDTO patient = null;
						try {
							patient = (PatientDTO) personPresenter.findPersonById(person.getId());
						} catch (MeetingStateException e) {
							e.printStackTrace();
						} 
						
						PatientInSessionManager
								.setPatientInSession(patient, null,
										getUI().getSession());

						getUI().getNavigator().navigateTo(
								NavigationIndex.PERSONVIEW.getNavigationPath());
					}
				});

				meetingButton = new Button(NEW_MEETING_WITH_PATIENT);
				meetingButton.setStyleName(LINK_STYLE);
				meetingButton.addClickListener(new ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						PatientDTO patient = null;
						try {
							patient = (PatientDTO) personPresenter.findPersonById(person.getId());

							PatientInSessionManager
							.setPatientInSession(patient, null,
									getUI().getSession());

							getUI().getNavigator().navigateTo(
									NavigationIndex.MEETINGVIEW
											.getNavigationPath()
											+ "/new="
											+ patient.getId());
						} catch (MeetingStateException e) {
							e.printStackTrace();
						}
					}
				});
			} else {
				profileButton.addClickListener(new ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						// set patient to null, we now have saved a
						// non-patient-person to the session
						PatientInSessionManager
						.setPatientInSession(null, person,
								getUI().getSession());
						getUI().getNavigator().navigateTo(
								NavigationIndex.PERSONVIEW.getNavigationPath());
					}
				});
			}

			table.addItem(new Object[] { userPicture, name, birthdate,
					profileButton, meetingButton }, persons.indexOf(person) + 1);
		}

		table.setPageLength(table.size());
		contentLayout.addComponent(table);
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
