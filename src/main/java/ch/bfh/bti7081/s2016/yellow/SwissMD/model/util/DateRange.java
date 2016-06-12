package ch.bfh.bti7081.s2016.yellow.SwissMD.model.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.IllegalDateRangeException;

/**
 * Definiert einen Zeitbereich anhand von Start- und Enddatum
 * 
 * @author Mutz
 */
public class DateRange {

	private Date from;
	private Date to;

	public DateRange(Date from, Date to) throws IllegalDateRangeException {
		if (!validate(from, to)) {
			throw new IllegalDateRangeException("Enddatum vor Startdatum!");
		}
		this.from = new Date(from.getTime());
		this.to = new Date(to.getTime());

	}

	/**
	 * 
	 * @param from
	 *            Startdatum des Bereichs
	 * @param to
	 *            Enddatum des Bereichs
	 * @return Validiert die Parameter und gibt deren Validität zurück
	 */
	private boolean validate(Date from, Date to) {
		if (from == null || to == null) {
			return false;
		}
		if (to.after(from)) {
			return true;
		}
		return false;
	}

	public Date getFrom() {
		return new Date(from.getTime());
	}

	public Date getTo() {
		return new Date(to.getTime());
	}

	@Override
	public String toString() {
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		return df.format(from) + "-" + df.format(to);
	}
}
