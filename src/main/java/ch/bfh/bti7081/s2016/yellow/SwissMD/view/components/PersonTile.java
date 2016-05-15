package ch.bfh.bti7081.s2016.yellow.SwissMD.view.components;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PersonDTO;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class PersonTile extends CustomComponent {
	private PersonDTO person;

	public PersonTile(PersonDTO person, String title) {
		this.person = person;

		Panel tile = new Panel(title);

		VerticalLayout contentLayout = new VerticalLayout();
		contentLayout.addComponent(new Label("Name: " + person.getName()));
		contentLayout.addComponent(new Label("Alter: "
				+ getAge(new Date(), person.getBirthdate())));
		contentLayout.setMargin(true);

		tile.setContent(contentLayout);
		tile.setWidth(200, Unit.PIXELS);
		setCompositionRoot(tile);
	}

	/**
	 * Berechnet die Anzahl ganzer Jahre zwischen zwei Daten
	 * 
	 * @param current
	 * @param birthdate
	 * @return
	 */
	public static int getAge(final Date current, final Date birthdate) {
		if (birthdate == null) {
			return 0;
		}
		if (current == null) {
			return getAge(birthdate);
		} else {
			final Calendar calend = new GregorianCalendar();
			calend.set(Calendar.HOUR_OF_DAY, 0);
			calend.set(Calendar.MINUTE, 0);
			calend.set(Calendar.SECOND, 0);
			calend.set(Calendar.MILLISECOND, 0);

			calend.setTimeInMillis(current.getTime() - birthdate.getTime());

			float result = 0;
			result = calend.get(Calendar.YEAR) - 1970;
			result += (float) calend.get(Calendar.MONTH) / (float) 12;
			return (int) result;
		}

	}

	public static int getAge(final Date birthdate) {
		return getAge(Calendar.getInstance().getTime(), birthdate);
	}

}
