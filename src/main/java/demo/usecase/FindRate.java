package demo.usecase;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import demo.CurrencyCode;
import demo.repository.ExhangeRateRepository;

public class FindRate {
	private final ExhangeRateRepository repository;

	public FindRate(ExhangeRateRepository repository) {
		this.repository = repository;
	}
	
	public Optional<BigDecimal> getRateByCodeAndDate(CurrencyCode code, LocalDate date) {
		date = isBeforeToday(date) ? date : LocalDate.now();
		Optional<BigDecimal> rate = repository.findRateByCodeAndDate(code, date);
		if (rate.isEmpty()) {
			for (int i = 0; i < 10; i++) {
				rate = repository.findRateByCodeAndDate(code, date.minusDays(i));
				if (rate.isPresent())
					break;
			}
		}
		return rate;
	}
	
	public Optional<BigDecimal> getRateByCode(CurrencyCode code) {
		return getRateByCodeAndDate(code, LocalDate.now());
	}
	
	private boolean isBeforeToday(LocalDate date) {
		return date.isBefore(LocalDate.now());
	}
}
