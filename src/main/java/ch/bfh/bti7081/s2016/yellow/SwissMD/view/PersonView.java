package ch.bfh.bti7081.s2016.yellow.SwissMD.view;

import java.util.List;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PersonDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Person;
import ch.bfh.bti7081.s2016.yellow.SwissMD.presenter.PersonPresenter;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.components.GridTile;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.components.PersonTile;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.BaseLayout;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.LayoutFactory;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.LayoutFactory.LayoutType;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.Tile;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.TileLayoutFactory;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.navigation.NavigationIndex;

@SuppressWarnings("serial")
public class PersonView extends CustomComponent implements View {
	private final String COULD_NOT_READ_PERSON_ID ="Ungültige Id angegeben!";
	private final String ID_NOT_A_NUMBER ="Übergebener Parameter ist keine Zahl";
	private final String ID_NOT_A_PATIENT ="Die gesuchte Person ist kein Patient";
	private final String PERSON_NOT_FOUND ="Die gewünschte Person konnte nicht gefunden werden";

	private PersonPresenter personPresenter = new PersonPresenter(this);
	private PersonDTO personDTO;
	private BaseLayout layout;

	public PersonView() {
		try {
			layout = LayoutFactory.getInstance(LayoutType.TILE_LAYOUT).createLayout(TileLayoutFactory.Arguments.ELEMENTS_PER_ROW.getName()+":3");
		} catch (Exception e1) {
			// TODO Go to error View
			e1.printStackTrace();
		}
		setCompositionRoot(layout);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		String param = event.getParameters();
		
		// Combobox mit allen möglichen Patienten
		Tile selectPatientTile = new Tile("Patient auswählen");
		HorizontalLayout selectPatientsArea = new HorizontalLayout();
		List<Person> list = personPresenter.getPatients();
		ComboBox selectPatientCBox = new ComboBox();
		for (Person patient : list) {
			System.out.println( patient.getId());
			selectPatientCBox.addItem(patient);
		}
		selectPatientsArea.addComponent(selectPatientCBox);
		selectPatientsArea.addComponent(getSelectPatientButton(selectPatientCBox));	
		selectPatientsArea.setSpacing(true);	
		selectPatientTile.addComponent(selectPatientsArea);
		layout.addComponent(selectPatientTile);
		layout.createRowBrake();
		
		if (param != null && !param.isEmpty()) {
			Long personId = null;
			try {
				personId = Long.valueOf(param);
			} catch (Exception e) {
				Notification.show(ID_NOT_A_NUMBER,
						Type.HUMANIZED_MESSAGE);
			}
			
			if (personId != null) {
				personDTO = personPresenter.findPersonById(personId);
				String personType = personDTO.getDtype();
				
				if(personType.equals("Person") || personType.equals("Doctor")){
					// if person is a Person or Doctor
					Notification.show(ID_NOT_A_PATIENT, Type.HUMANIZED_MESSAGE);
					layout.addComponent(new PersonTile(personDTO, personType));
				}else{
					// if person is a Patient 
					
					// 1. tile: base data
					Tile baseDataTile = new Tile("Allgemeine Grunddaten " + personDTO.getName());
					baseDataTile.setStdWidth(3);
					GridLayout grid = new GridLayout(2,4);
					grid.setSizeFull();
					grid.addComponent(new Label("Name: " + personDTO.getName()));
					grid.addComponent(new Label("Adresse: " + personDTO.getAddress()));
					grid.addComponent(new Label("PLZ / Ort: " + personDTO.getZip() + " " + personDTO.getCity()));
					grid.addComponent(new Label("Mobile: " + personDTO.getMobile()));
					grid.addComponent(new Label("Festnetz: " + personDTO.getLandline()));
					
					baseDataTile.addComponent(grid);
					layout.addComponent(baseDataTile);
					
					// 2. tile: medical base data (data protection critical stuff)
					// TODO fill with real data
					GridTile medicalDataTile = new GridTile("Medzinische Grunddaten " + personDTO.getName());
					layout.addComponent(medicalDataTile);
					
					// TODO fill with meeting tiles that belong to patient
				}
			}
		}
		
	}
	
	private Button getSelectPatientButton(ComboBox personDTO) {
		Button b = new Button("Patient auswählen");
		b.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				Person person = (Person) personDTO.getValue();

				try {
					getUI().getNavigator().navigateTo(
							NavigationIndex.PERSONVIEW + "/"
									+ person.getId());	
				} catch (Exception e) {
					Notification.show(PERSON_NOT_FOUND, Type.ERROR_MESSAGE);
				}
			}
		});
		return b;
	}

	private Label headingLabel() {
		return new Label("PersonView");
	}

}
