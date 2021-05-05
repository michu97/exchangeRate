package api;

import java.time.LocalDate;

public class DateValidator {
	public static LocalDate validate(LocalDate date) {
		return date.isAfter(LocalDate.now()) ? LocalDate.now() : date;
	}
}
