package ch.bfh.bti7081.s2016.yellow.SwissMD.view.components;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PersonDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.Tile;

import com.vaadin.ui.Label;

/**
 * Stellt eine Person dar
 * 
 * @author SoullessStone
 *
 */
@SuppressWarnings("serial")
public class PersonTile extends Tile {
	private static final String AGE = "Alter: ";
	private static final String NAME = "Name: ";
	private static final String ICONS_USERS = "img/icons/users_small.png";

	public PersonTile(PersonDTO person, String title) {
		setTitleAndIcon(title, ICONS_USERS);
		addComponent(new Label(NAME + person.getName()));
		addComponent(new Label(AGE
				+ getAge(new Date(), person.getBirthdate())));
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
