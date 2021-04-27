package api;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import demo.CurrencyCode;

class ExchangeRateProvider {
	private final ExchangeRateRepository repository;

	public ExchangeRateProvider(ExchangeRateRepository repository) {
		this.repository = repository;
	}
	
	public Optional<BigDecimal> getRateByCodeAndDate(CurrencyCode code,
			LocalDate date) {
		Optional<BigDecimal> rate = repository.getRateByCodeAndDate(code, date);
		if (rate.isPresent()) {
			return rate;
		}
		for (int i = 1; i < 10; i++) {
			rate = repository.getRateByCodeAndDate(code, date.minusDays(i));
			if (rate.isPresent())
				break;
		}
		return rate;
	}
}
