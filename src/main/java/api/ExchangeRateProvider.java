package api;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

class ExchangeRateProvider {
	private final ExchangeRateRepository repository;

	public ExchangeRateProvider(ExchangeRateRepository repository) {
		this.repository = repository;
	}
	
	public Optional<BigDecimal> getRateByCodeAndDate(CurrencyCode code,
			LocalDate date) {
		date = isDateAfterToday(date) ? LocalDate.now() : date;
		Optional<BigDecimal> rate = repository.getRateByCodeAndDate(code, date);
		if (rate.isPresent()) {
			return rate;
		}
		return lookForExistingRate(code, date, rate);
	}

	private Optional<BigDecimal> lookForExistingRate(CurrencyCode code,
			LocalDate date, Optional<BigDecimal> rate) {
		for (int i = 1; i < 11; i++) {
			rate = repository.getRateByCodeAndDate(code, date.minusDays(i));
			if (rate.isPresent())
				break;
		}
		return rate;
	}

	private boolean isDateAfterToday(LocalDate date) {
		return date.isAfter(LocalDate.now());
	}
}
