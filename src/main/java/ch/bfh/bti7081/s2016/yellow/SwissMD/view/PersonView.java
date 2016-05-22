package ch.bfh.bti7081.s2016.yellow.SwissMD.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
// When just clicked: Shows the users information
// When navigated to from PersonSearch: Shows the chosen Person
// Can show Person, Doctor and Patient






import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.DoctorDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.DrugDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.MeetingDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PatientDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PrescriptionDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.util.DateRange;
import ch.bfh.bti7081.s2016.yellow.SwissMD.presenter.PersonPresenter;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.components.MeetingTile;

@SuppressWarnings("serial")
public class PersonView extends VerticalLayout implements View {
	private PersonPresenter personPresenter = new PersonPresenter(this);

	public PersonView() {
		setSizeFull();
		setSpacing(true);
		//addComponent(new NavigationsMenu());
		addComponent(headingLabel());

		List<PrescriptionDTO> medis = new ArrayList();
		medis.add(new PrescriptionDTO(new DrugDTO("Aspirin"), 50,
				new DateRange(new Date(), new Date(new Date().getTime() + 1000000))));
		medis.add(new PrescriptionDTO(new DrugDTO("Alcacyl"), 50,
				new DateRange(new Date(), new Date(new Date().getTime() + 1000000))));
		medis.add(new PrescriptionDTO(new DrugDTO("Gift"), 50, new DateRange(new Date(), new Date(new Date().getTime() + 1000000))));
		addComponent(new MeetingTile(new MeetingDTO(new PatientDTO("Franz Erl", new Date(-620438400000L)),
				new DoctorDTO("Doktor Who", new Date(335145600000L)), new Date(1457802000000L))));
		setSizeFull();
		setSpacing(true);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// Wird jedes Mal aufgerufen, wenn hierhin navigiert wird. Hier könnte
		// man also den Parameter in der URL auslesen
		System.out.println(event.getParameters());
	}

	private Label headingLabel() {
		return new Label("PersonView");
	}

}
