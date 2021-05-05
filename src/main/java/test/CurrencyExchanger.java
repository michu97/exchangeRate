package test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import api.CurrencyCode;

public class CurrencyExchanger {
	private final Api api;

	public CurrencyExchanger(Api api) {
		this.api = api;
	}
	
	public Optional<BigDecimal> getAmountInPLN(LocalDate date, CurrencyCode code,
			BigDecimal amount) {
		date = validateDate(date);
		for (int i = 0; i <= 10; i++) {
			Optional<BigDecimal> rate = api.getRate(date.minusDays(i), code);
			if (rate.isPresent()) {
				return Optional.of(rate.get().multiply(amount));
			}
		}
		return Optional.empty();
	}

	
	public Optional<BigDecimal> getAmountInPLN(CurrencyCode code,
			BigDecimal amount) {
		return getAmountInPLN(LocalDate.now(), code, amount);
	}
	
	private LocalDate validateDate(LocalDate date) {
		return date.isBefore(LocalDate.now()) ? date : LocalDate.now();
	}
}
