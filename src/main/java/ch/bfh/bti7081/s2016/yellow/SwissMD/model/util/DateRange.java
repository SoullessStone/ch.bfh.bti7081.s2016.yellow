package ch.bfh.bti7081.s2016.yellow.SwissMD.model.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Definiert einen Zeitbereich anhand von Start- und Enddatum
 */
public class DateRange {
	private Date from;
	private Date to;

	public DateRange(Date from, Date to) {
		if (!validate(from, to)) {
			throw new IllegalArgumentException("Enddatum vor Startdatum!");
		}
		this.from = from;
		this.to = to;

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
		if (to.after(from)) {
			return true;
		}
		return false;
	}

	public Date getFrom() {
		return from;
	}

	public Date getTo() {
		return to;
	}

	@Override
	public String toString() {
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		return df.format(from) + "-" + df.format(to);
	}
}
